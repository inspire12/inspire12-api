package com.inspire12.practice.api.exception;

public enum ResultCode {

    SUCCESS("성공"),

    BAD_REQUEST("요청에 오류가 있습니다."),
    UNAUTHORIZED("인증이 필요한 요청입니다."),
    FORBIDDEN("허용되지 않은 접근입니다."),
    NOT_FOUND("대상이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),

    TYPE_NOT_FOUND("TYPE 필드가 존재하지 않습니다."),
    URL_NOT_FOUND("URL 필드가 존재하지 않습니다."),

    SUBSCRIBE_REQUEST_ERROR("소켓 구독 요청 중 장애가 발생했습니다."),
    SUBSCRIBE_SESSION_ERROR("소켓 구독 요청 중 세션을 찾을 수 없습니다."),
    SUBSCRIBE_MESSAGE_ERROR("소켓 구독 받는 중 장애가 발생했습니다."),
    SUBSCRIBE_MESSAGE_CLOSE_ERROR("소켓 끊는 과정에서 발생했습니다.");

    private final String message;

    ResultCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
