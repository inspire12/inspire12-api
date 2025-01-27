package com.inspire12.practice.api.support.exception;

public class InternalServerErrorException extends ApiException {

    public InternalServerErrorException() {
        super(ResultCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String message) {
        super(ResultCode.INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerErrorException(ResultCode resultCode) {
        super(resultCode);
    }

}
