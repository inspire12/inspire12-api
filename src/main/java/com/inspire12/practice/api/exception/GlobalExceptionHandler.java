package com.inspire12.practice.api.exception;

import com.inspire12.practice.api.config.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


//    @ExceptionHandler(InsufficientAuthenticationException.class)
//    protected ResponseEntity<ErrorResponse> handleInsufficientAuthenticationException(final InsufficientAuthenticationException e, final HttpServletRequest request) {
//        ErrorCode errorCode = ErrorCode.FORBIDDEN;
//        log.error("InsufficientAuthenticationException: {}", errorCode.getMessage(), e);
//        return ResponseEntity
//                .status(errorCode.getHttpStatus())
//                .body(new ErrorResponse(
//                        errorCode.getCode(),
//                        e.getMessage(),
//                        request.getRequestURI()));
//    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e, final HttpServletRequest request) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        log.error("Exception: {}", e.getMessage(), e);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(
                        errorCode.getCode(),
                        e.getMessage(),
                        request.getRequestURI()));
    }

//    @ExceptionHandler(AuthenticationException.class)
//    protected ResponseEntity<ErrorResponse> handleAuthException(final AuthenticationException e, final HttpServletRequest request) {
//        ErrorCode errorCode = ErrorCode.FORBIDDEN;
//        log.error("FanPossible Authentication Exception: {}", errorCode.getMessage(), e);
//        return ResponseEntity
//                .status(errorCode.getHttpStatus())
//                .body(new ErrorResponse(
//                        errorCode.getCode(),
//                        e.getMessage(),
//                        request.getRequestURI()));
//    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(final RuntimeException e, final HttpServletRequest request) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        log.error("FanPossible runtime Exception: {}", errorCode.getMessage(), e);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(
                        errorCode.getCode(),
                        e.getMessage(),
                        request.getRequestURI()));
    }
}
