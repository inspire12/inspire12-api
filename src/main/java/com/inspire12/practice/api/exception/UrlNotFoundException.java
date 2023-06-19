package com.inspire12.practice.api.exception;

public class UrlNotFoundException extends ApiException {

    public UrlNotFoundException() {
        super(ResultCode.NOT_FOUND);
    }

    public UrlNotFoundException(String message) {
        super(ResultCode.NOT_FOUND, message);
    }

    public UrlNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public UrlNotFoundException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

}
