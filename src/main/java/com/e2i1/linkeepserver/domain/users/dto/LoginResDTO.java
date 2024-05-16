package com.e2i1.linkeepserver.domain.users.dto;

import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResDTO {
    private TokenResDTO tokenDTO;

    private String randomNickname;

    private Boolean existedUser;

}
