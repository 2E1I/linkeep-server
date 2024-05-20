package com.e2i1.linkeepserver.domain.users.business;

import static com.e2i1.linkeepserver.common.constant.NicknameConst.ADJECTIVES;
import static com.e2i1.linkeepserver.common.constant.NicknameConst.NOUNS;
import static com.e2i1.linkeepserver.common.constant.NicknameConst.RETRY_NUM;
import static com.e2i1.linkeepserver.common.constant.NicknameConst.WORD_NUM;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.image.service.S3ImageService;
import com.e2i1.linkeepserver.domain.links.business.LinksBusiness;
import com.e2i1.linkeepserver.domain.token.business.TokenBusiness;
import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import com.e2i1.linkeepserver.domain.token.entity.BlackList;
import com.e2i1.linkeepserver.domain.token.service.TokenService;
import com.e2i1.linkeepserver.domain.users.converter.UsersConverter;
import com.e2i1.linkeepserver.domain.users.dto.EditProfileReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.LinkHomeResDTO;
import com.e2i1.linkeepserver.domain.users.dto.LoginReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.LoginResDTO;
import com.e2i1.linkeepserver.domain.users.dto.NicknameResDTO;
import com.e2i1.linkeepserver.domain.users.dto.ProfileResDTO;
import com.e2i1.linkeepserver.domain.users.dto.SignupReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.UserHomeResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Business
@RequiredArgsConstructor
public class UsersBusiness {

    private final UsersService usersService;
    private final UsersConverter usersConverter;

    private final TokenBusiness tokenBusiness;
    private final LinksBusiness linksBusiness;

    private final S3ImageService s3ImageService;
    private final TokenService tokenService;

    @Transactional
    public LoginResDTO login(LoginReqDTO loginReqDTO) {
        UsersEntity user = usersService.getUser(loginReqDTO.getEmail());

        // 로그인 시, 해당 유저 없으면 랜덤 닉네임만 만들어서 반환
        if (user == null) {
            String nickname = createRandomNickname();

            // 신규 유저면 randomNickname 만들어서 반환하기
            return LoginResDTO.builder()
                .existedUser(false)
                .randomNickname(nickname)
                .build();
        }

        // 기존 유저면 token 발행해서 반환하기
        return LoginResDTO.builder()
            .tokenDTO(tokenBusiness.issueToken(user))
            .existedUser(true).build();
    }


    /**
     * 신규 회원 가입 로직
     */
    @Transactional
    public TokenResDTO signup(SignupReqDTO signupInfo, MultipartFile img) {
        // 닉네임 unique한지 검증
        if (!validateDuplicatedNickname(signupInfo.getNickname())) {
            throw new ApiException(ErrorCode.NICKNAME_DUPLICATED);
        }

        // 이미지 존재하면 S3에 저장하고 URL 반환받기
        String imgUrl = null;
        if (img != null && !img.isEmpty()) {
            imgUrl = s3ImageService.upload(img);
        }

        // signupInfo(nickname, email 들어있음)와 이미지URL로 UsersEntity 만들고 저장하기
        UsersEntity newUser = usersConverter.toEntity(signupInfo, imgUrl);
        newUser = usersService.register(newUser);

        // 저장된 newUser로 token 생성하기
        return tokenBusiness.issueToken(newUser);
    }

    public UserHomeResDTO getUserHome(Long lastId, Integer size, UsersEntity user) {
        if (lastId == null) {
            lastId = Long.MAX_VALUE; // lastId가 null인 경우 가능한 가장 큰 ID부터 시작
        }
        // lastId부터 size만큼 링크 가져오기
        List<LinkHomeResDTO> linkHomeList = linksBusiness.findByUserId(user.getId(), lastId, size);

        boolean hasNext = linkHomeList.size() > size;
        if (hasNext) linkHomeList = linkHomeList.subList(0, size);

        return UserHomeResDTO.builder()
            .nickname(user.getNickname())
            .imgUrl(user.getImgUrl())
            .linkList(linkHomeList)
            .hasNext(hasNext)
            .build();
    }

    public List<NicknameResDTO> searchNicknames(String search) {
        List<UsersEntity> nicknameList = usersService.searchNicknames(search);

        return nicknameList.stream()
            .map(usersConverter::toNicknameResponse)
            .collect(Collectors.toList());
    }

    public ProfileResDTO getProfile(UsersEntity user) {
        return ProfileResDTO.builder()
            .nickname(user.getNickname())
            .description(user.getDescription())
            .imgUrl(user.getImgUrl())
            .build();
    }

    @Transactional
    public void editProfile(MultipartFile imgFile, EditProfileReqDTO editProfile,
        UsersEntity user) {
        // 기본 값으로 기존 유저의 ImgUrl 사용
        String imgUrl = user.getImgUrl();

        // 기존 이미지가 존재하고 이를 삭제하는 경우
        if (Boolean.TRUE.equals(editProfile.getIsDeletedImg()) && imgUrl
            != null) { // editProfile.getIsDeletedImg가 null인 경우 false로 인식하기 위해 Boolean.TRUE.equals 사용
            s3ImageService.deleteImageFromS3(user.getImgUrl());
            imgUrl = null;
        }

        // 새로운 이미지 존재하는 경우 ->  S3에 이미지 업로드 후 URL 받아오기
        if (imgFile != null && !imgFile.isEmpty()) {
            imgUrl = s3ImageService.upload(imgFile);
        }

        // imgUrl은 변경사항 없으면 기존 imgUrl 그대로, 삭제한 경우 null, 새로운 이미지 업로드한 경우 새 이미지 URL이 된다
        usersService.editProfile(imgUrl, editProfile, user);

    }

    /**
     * 로그아웃 로직
     * 현재 유저에게 발급한 token을 blacklist에 저장해서 다시 사용하지 못하도록
     */
    public void logout(String token, UsersEntity user) {
        Long userID = tokenBusiness.validationAccessToken(token);
        if (!user.getId().equals(userID)) {
            throw new ApiException(ErrorCode.ACCESS_DENIED, "로그인한 유저의 ID와 token의 유저 ID가 다릅니다.");
        }

        // 해당 token을 blackList에 저장
        tokenService.saveBlackList(BlackList.builder().invalidToken(token).build());
    }


    /**
     * nickname이 유니크하면 true 유니크하지 않으면 false
     */
    public Boolean validateDuplicatedNickname(String nickname) {
        Boolean isDuplicated = usersService.isDuplicatedNickname(nickname);
        return !isDuplicated;
    }

    /**
     * 랜덤 닉네임 만드는 로직
     */
    private String createRandomNickname() {
        String nickname = null;
        int attempts = 0;

        // RETRY_NUM 만큼 닉네임 생성 시도
        while (attempts < RETRY_NUM) {
            Random random = new Random();
            int randomNum1 = random.nextInt(WORD_NUM);
            int randomNum2 = random.nextInt(WORD_NUM);
            int randomNum3 = random.nextInt(WORD_NUM) + 1;

            // 랜덤 닉네임 만들기
            nickname = ADJECTIVES.get(randomNum1) + " " + NOUNS.get(randomNum2) + randomNum3;

            // 닉네임 존재하는지 확인
            Boolean isDuplicated = usersService.isDuplicatedNickname(nickname);

            // 존재하지 않으면 해당 닉네임으로 저장
            if (!isDuplicated) {
                break;
            }

            // 해당 닉네임 존재 시, 다시 시도
            attempts++;
        }
        // 10번 재시도를 넘었다면 에러 발생
        if (attempts == 10) {
            throw new ApiException(ErrorCode.RETRY_EXCEEDED);
        }

        return nickname;
    }


}
