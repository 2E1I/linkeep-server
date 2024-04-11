package com.e2i1.linkeepserver.common.error;

import org.springframework.http.HttpStatus;

public interface ErrorCodeIfs {

    HttpStatus getHttpStatusCode();

    Integer getErrorCode();

    String getDescription();
}
