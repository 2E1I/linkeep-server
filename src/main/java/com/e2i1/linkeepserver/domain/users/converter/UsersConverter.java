package com.e2i1.linkeepserver.domain.users.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.users.dto.NicknameResDTO;
import com.e2i1.linkeepserver.domain.users.dto.SignupReqDTO;
import com.e2i1.linkeepserver.domain.users.entity.UserStatus;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class UsersConverter {
    public UsersEntity toEntity(SignupReqDTO signupInfo, String imgUrl) {
        return UsersEntity.builder()
            .email(signupInfo.getEmail())
            .nickname(signupInfo.getNickname())
            .imgUrl(imgUrl)
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
