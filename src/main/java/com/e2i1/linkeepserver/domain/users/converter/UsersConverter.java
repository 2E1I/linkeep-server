package com.e2i1.linkeepserver.domain.users.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.users.dto.LoginReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.NicknameResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UserStatus;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class UsersConverter {
    public UsersEntity toEntity(LoginReqDTO loginReqDTO) {
        return UsersEntity.builder()
                .nickname(loginReqDTO.getNickname())
                .email(loginReqDTO.getEmail())
                .imgUrl(loginReqDTO.getImgUrl())
                .thumbnailUrl(loginReqDTO.getImgUrl())
                .status(UserStatus.REGISTERED)
                .build();

    }

    public NicknameResDTO toNicknameResponse(UsersEntity user) {
        return NicknameResDTO.builder()
            .userId(user.getId())
            .nickname(user.getNickname())
            .build();
    }
}
