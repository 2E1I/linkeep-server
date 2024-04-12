package com.e2i1.linkeepserver.domain.oauth.model;

import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetSocialOAuthResDTO {
    private TokenResDTO jwtToken;
    private int user_num;
    private String accessToken;
    private String tokenType;

}
