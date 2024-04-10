package com.e2i1.linkeepserver.domain.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetSocialOAuthResDTO {
    private String jwtToken;
    private int user_num;
    private String accessToken;
    private String tokenType;

}
