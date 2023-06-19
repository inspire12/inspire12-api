package com.inspire12.practice.api.exception;


public class ApiException extends RuntimeException {

    private final ResultCode resultCode;

    public ApiException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ApiException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    public ApiException(ResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    public ApiException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

}
