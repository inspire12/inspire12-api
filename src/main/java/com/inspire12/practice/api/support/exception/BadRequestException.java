package com.inspire12.practice.api.support.exception;

public class BadRequestException extends ApiException {

    public BadRequestException() {
        super(ResultCode.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(ResultCode.BAD_REQUEST, message);
    }

    public BadRequestException(ResultCode resultCode) {
        super(resultCode);
    }

    public BadRequestException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

}
