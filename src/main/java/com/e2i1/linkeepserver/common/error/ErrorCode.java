package com.e2i1.linkeepserver.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs{

    OK(HttpStatus.OK,  "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST,  "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  "서버 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR, "Null point"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않습니다.")

    ;

    private final HttpStatus httpStatusCode;
    private final String description;

}
