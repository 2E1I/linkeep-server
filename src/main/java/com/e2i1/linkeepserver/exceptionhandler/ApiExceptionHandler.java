package com.e2i1.linkeepserver.exceptionhandler;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.error.ErrorResponse;
import com.e2i1.linkeepserver.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        ErrorCode errorCode = ex.getErrorCode();
        String errorDescription = ex.getErrorDescription();
        if (errorDescription == null) {
            errorDescription = errorCode.getDescription();
        }
        // 3. errorResponse 만들기(에러 메시지 내용과 함께)
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getErrorCode(), errorDescription);

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(errorResponse);
    }

    /**
     * DTO 등에서 validation 실패 시, 해당 예외 처리하는 핸들러
     * NotNull, NotBlank 등의 애노테이션 검증 실패 시 해당 예외 처리해줌
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException ex) {
        log.error("Method argument not valid exception", ex);

        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Validation error";

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .errorCode(40000)
                                .errorMessage(errorMessage)
                                .build()
                );
    }

    // 예상치 못한 예외에 대응하기 위한 Exception handler
    // TODO : 최종 어플리케이션 배포 시, ex.getMessage() 대신 "SERVER ERROR"로 수정
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity
                .status(ErrorCode.SERVER_ERROR.getHttpStatusCode())
                .body(new ErrorResponse(500, ex.getMessage()));
    }
}
