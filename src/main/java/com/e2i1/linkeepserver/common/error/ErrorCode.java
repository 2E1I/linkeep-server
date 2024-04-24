package com.e2i1.linkeepserver.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode{
    OK(200,HttpStatus.OK, "성공"),
    BAD_REQUEST(400,HttpStatus.BAD_REQUEST, "잘못된 요청"),
    SERVER_ERROR(500,HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"),
    NULL_POINT(500, HttpStatus.INTERNAL_SERVER_ERROR,  "Null point"),
    NOT_FOUND(404,HttpStatus.NOT_FOUND, "존재하지 않습니다."),

    // 소셜 로그인 관련 에러 코드
    UNKNOWN_OAUTH_LOGIN(14000, HttpStatus.BAD_REQUEST,  "알 수 없는 소셜 로그인 형식"),

    // 토큰 관련 에러 코드
    INVALID_TOKEN(24000,HttpStatus.BAD_REQUEST,  "유효하지 않은 토큰"),
    EXPIRED_TOKEN(24001,HttpStatus.BAD_REQUEST,  "만료된 토큰"),
    TOKEN_EXCEPTION(24002,HttpStatus.BAD_REQUEST, "토큰 알 수 없는 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(24003,HttpStatus.BAD_REQUEST,  "인증 헤더 토큰 없음"),

    // 유저 관련 에러 코드
    USER_NOT_FOUND(34040,HttpStatus.NOT_FOUND,  "사용자를 찾을 수 없음"),

    // 모음집 관련 에러 코드
    COLLECTION_NOT_FOUND(44040,HttpStatus.NOT_FOUND, "모음집을 찾을 수 없음"),

    // 링크 관련 에러 코드
    LINK_NOT_FOUND(54040,HttpStatus.NOT_FOUND, "링크를 찾을 수 없음"),

    // collaborator 관련 에러 코드
    ACCESS_DENIED(64030,HttpStatus.FORBIDDEN, "해당 유저는 모음집에 접근할 수 없음"),
    COLLABORATOR_NOT_FOUND(64040,HttpStatus.NOT_FOUND,"사용자가 참여한 모음집을 찾을 수 없음")
    ;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
    private final String description;

}
