package com.inspire12.practice.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    OK(0, "success", HttpStatus.OK),
    NOT_FOUND(10, "data does not exist", HttpStatus.NOT_FOUND),
    BAD_REQUEST(20, "invalid argument", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_DATA(21, "invalid data format", HttpStatus.INTERNAL_SERVER_ERROR),
    GUEST_FORBIDDEN(30, "guest forbidden", HttpStatus.FORBIDDEN),
    FORBIDDEN(40, "no permission", HttpStatus.FORBIDDEN),
    INTERNAL_SERVER_ERROR(98, "internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    ;
    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;

}