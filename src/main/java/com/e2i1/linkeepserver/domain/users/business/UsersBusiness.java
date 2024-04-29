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
import com.e2i1.linkeepserver.domain.users.converter.UsersConverter;
import com.e2i1.linkeepserver.domain.users.dto.EditProfileReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.LinkHomeResDTO;
import com.e2i1.linkeepserver.domain.users.dto.LoginReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.NicknameResDTO;
import com.e2i1.linkeepserver.domain.users.dto.ProfileResDTO;
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

    @Transactional
    public TokenResDTO login(LoginReqDTO loginReqDTO) {
        UsersEntity user = usersService.getUser(loginReqDTO.getEmail());

        // 로그인 시, 해당 유저 없으면 자동 회원가입 진행
        if (user == null) {
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

            if (attempts == 10) {
                throw new ApiException(ErrorCode.RETRY_EXCEEDED);
            }

            loginReqDTO.setNickname(nickname);
            UsersEntity newUser = usersConverter.toEntity(loginReqDTO);
            user = usersService.register(newUser);
        }

        // 토큰 발행
        return tokenBusiness.issueToken(user);
    }

    public UserHomeResDTO getUserHome(UsersEntity user) {
        // user의 모든 link를 최신순으로 가져오기
        List<LinkHomeResDTO> linkHomeList = linksBusiness.findByUserId(user.getId());

        return UserHomeResDTO.builder()
            .nickname(user.getNickname())
            .imgUrl(user.getImgUrl())
            .linkList(linkHomeList)
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
    public void editProfile(MultipartFile imgFile, EditProfileReqDTO editProfile, UsersEntity user) {
        // 기본 값으로 기존 유저의 ImgUrl 사용
        String imgUrl = user.getImgUrl();

        // 기존 이미지가 존재하고 이를 삭제하는 경우
        if (Boolean.TRUE.equals(editProfile.getIsDeletedImg()) && imgUrl != null) { // editProfile.getIsDeletedImg가 null인 경우 false로 인식하기 위해 Boolean.TRUE.equals 사용
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
     * nickname이 유니크하면 true
     * 유니크하지 않으면 false
     */
    public Boolean validateDuplicatedNickname(String nickname) {
        Boolean isDuplicated = usersService.isDuplicatedNickname(nickname);
        return !isDuplicated;
    }
}
