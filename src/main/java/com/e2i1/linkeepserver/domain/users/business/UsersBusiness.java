package com.e2i1.linkeepserver.domain.users.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.links.business.LinksBusiness;
import com.e2i1.linkeepserver.domain.token.business.TokenBusiness;
import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import com.e2i1.linkeepserver.domain.users.converter.UsersConverter;
import com.e2i1.linkeepserver.domain.users.dto.LinkHomeResDTO;
import com.e2i1.linkeepserver.domain.users.dto.LoginReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.NicknameResDTO;
import com.e2i1.linkeepserver.domain.users.dto.ProfileDTO;
import com.e2i1.linkeepserver.domain.users.dto.UserHomeResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Business
@RequiredArgsConstructor
public class UsersBusiness {
    private final UsersService usersService;
    private final UsersConverter usersConverter;

    private final TokenBusiness tokenBusiness;
    private final LinksBusiness linksBusiness;

    @Transactional
    public TokenResDTO login(LoginReqDTO loginReqDTO) {
        UsersEntity user = usersService.getUser(loginReqDTO.getEmail());

        // 로그인 시, 해당 유저 없으면 자동 회원가입 진행
        // TODO : 유저 닉네임 랜덤 설정
        if (user == null) {
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

    public ProfileDTO getProfile(UsersEntity user) {
        return ProfileDTO.builder()
            .nickname(user.getNickname())
            .description(user.getDescription())
            .imgUrl(user.getImgUrl())
            .build();
    }

    public void editProfile(ProfileDTO profile, UsersEntity user) {
        usersService.editProfile(profile, user);
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
