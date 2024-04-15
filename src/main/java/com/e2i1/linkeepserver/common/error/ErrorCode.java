package com.e2i1.linkeepserver.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode{
    OK(HttpStatus.OK, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR,  "Null point"),

    // 소셜 로그인 관련 에러 코드
    UNKNOWN_OAUTH_LOGIN(HttpStatus.BAD_REQUEST,  "알 수 없는 소셜 로그인 형식"),

    // 토큰 관련 에러 코드
    INVALID_TOKEN(HttpStatus.BAD_REQUEST,  "유효하지 않은 토큰"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST,  "만료된 토큰"),
    TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "토큰 알 수 없는 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST,  "인증 헤더 토큰 없음"),

    // 유저 관련 에러 코드
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,  "사용자를 찾을 수 없음"),
    ;

    private final HttpStatus httpStatusCode;
    private final String description;

}
