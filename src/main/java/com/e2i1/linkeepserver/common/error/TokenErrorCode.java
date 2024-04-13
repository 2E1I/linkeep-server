package com.e2i1.linkeepserver.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 토큰 에러인 경우 에러코드 2000번대 사용
 */
@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{

    INVALID_TOKEN(HttpStatus.BAD_REQUEST,  "유효하지 않은 토큰"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST,  "만료된 토큰"),
    TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST,  "토큰 알 수 없는 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST,  "인증 헤더 토큰 없음"),
    ;

    private final HttpStatus httpStatusCode;
    private final String description;
}
