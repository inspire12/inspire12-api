package com.inspire12.practice.api.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final Integer code;
    private final String message;
    private final String path;
}
