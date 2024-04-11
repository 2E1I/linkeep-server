package com.e2i1.linkeepserver.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs{
    OK(HttpStatus.OK, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR, 512, "Null point")

    ;

    private final HttpStatus httpStatusCode;
    private final Integer errorCode;
    private final String description;

}
