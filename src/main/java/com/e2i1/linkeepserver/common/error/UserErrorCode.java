package com.e2i1.linkeepserver.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 유저 관련 에러 코드는 1로 시작
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs{

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 1404, "사용자를 찾을 수 없음"),
    ;

    private final HttpStatus httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
