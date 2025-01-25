package com.inspire12.practice.api.config.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    T result;

    public static <T> CommonResponse<T> create(T result){
        return new CommonResponse<>(result);
    }
}
