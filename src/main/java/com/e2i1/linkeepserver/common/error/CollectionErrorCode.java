package com.e2i1.linkeepserver.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 유저 관련 에러 코드는 1로 시작
 */
@Getter
@AllArgsConstructor
public enum CollectionErrorCode implements ErrorCodeIfs{

    COLLECTION_NOT_FOUND(HttpStatus.NOT_FOUND, "모음집을 찾을 수 없음"),
    ;

    private final HttpStatus httpStatusCode;
    private final String description;
}
