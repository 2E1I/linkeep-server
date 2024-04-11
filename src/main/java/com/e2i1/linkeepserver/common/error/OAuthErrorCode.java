package com.e2i1.linkeepserver.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OAuthErrorCode implements ErrorCodeIfs{

    UNKNOWN_OAUTH_LOGIN(HttpStatus.BAD_REQUEST, 3400, "알 수 없는 소셜 로그인 형식"),

    ;
    private final HttpStatus httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
