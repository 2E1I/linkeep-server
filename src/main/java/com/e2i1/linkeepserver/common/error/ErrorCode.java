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
    NICKNAME_DUPLICATED(34000,HttpStatus.BAD_REQUEST,  "닉네임이 중복됩니다."),

    // 모음집 관련 에러 코드
    COLLECTION_NOT_FOUND(44040,HttpStatus.NOT_FOUND, "모음집을 찾을 수 없음"),

    // 링크 관련 에러 코드
    LINK_NOT_FOUND(54040,HttpStatus.NOT_FOUND, "링크를 찾을 수 없음"),

    // collaborator 관련 에러 코드
    ACCESS_DENIED(64030,HttpStatus.FORBIDDEN, "해당 유저는 모음집에 접근할 수 없음"),
    COLLABORATOR_NOT_FOUND(64040,HttpStatus.NOT_FOUND,"사용자가 참여한 모음집을 찾을 수 없음"),

    // S3 관련 에러 코드
    EMPTY_FILE_EXCEPTION(74000, HttpStatus.BAD_REQUEST, "빈 파일입니다."),
    NO_FILE_EXTENTION(74001, HttpStatus.BAD_REQUEST, "확장자가 없습니다."),
    INVALID_FILE_EXTENTION(74002, HttpStatus.BAD_REQUEST, "부적절한 이미지 확장자입니다."),
    IO_EXCEPTION_ON_IMAGE_UPLOAD(75000, HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 중 에러가 발생했습니다."),
    PUT_OBJECT_EXCEPTION(75001, HttpStatus.INTERNAL_SERVER_ERROR, "S3에 이미지 업로드 중 에러가 발생했습니다."),
    IO_EXCEPTION_ON_IMAGE_DELETE(75002, HttpStatus.INTERNAL_SERVER_ERROR, "이미지 삭제 중 에러가 발생했습니다."),

    // 랜덤 닉네임 생성 관련 에러 코드
    RETRY_EXCEEDED(85000, HttpStatus.INTERNAL_SERVER_ERROR, "랜덤 닉네임 생성 재시도 횟수를 초과했습니다."),


    //좋아요 관련 에러 코드
    LIKE_NOT_FOUND(94040,HttpStatus.NOT_FOUND,"사용자가 모음집을 좋아요한 기록이 없습니다.")

    ;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
    private final String description;

}
