package com.e2i1.linkeepserver.exceptionhandler;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.error.ErrorCodeIfs;
import com.e2i1.linkeepserver.common.error.ErrorResponse;
import com.e2i1.linkeepserver.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE) // 우선순위 제일 높게 설정
public class ApiExceptionHandler {

    // ApiException 클래스로 발생하는 모든 예외는 이 handleApiException 메서드가 처리
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        // 1. 로그찍고
        log.error("", ex);

        // 2. 발생한 예외에서 errorCode 가져와서
        ErrorCodeIfs errorCode = ex.getErrorCodeIfs();
        // 3. errorResponse 만들기(에러 메시지 내용과 함께)
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getDescription());

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(errorResponse);
    }

    // 예상치 못한 예외에 대응하기 위한 Exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity
                .status(ErrorCode.SERVER_ERROR.getHttpStatusCode())
                .body(new ErrorResponse("system error"));
    }
}
