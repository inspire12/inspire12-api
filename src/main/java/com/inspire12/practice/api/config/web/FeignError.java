package com.inspire12.practice.api.config.web;

import com.inspire12.practice.api.support.exception.ApiException;
import com.inspire12.practice.api.support.exception.BadRequestException;
import com.inspire12.practice.api.support.exception.InternalServerErrorException;
import com.inspire12.practice.api.support.exception.ResultCode;
import com.inspire12.practice.api.support.exception.UrlNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400 -> {
                return new BadRequestException(ResultCode.BAD_REQUEST.getMessage());
            }
            case 404 -> {
                return new UrlNotFoundException();
            }
            case 500 -> {
                try {
                    String responseBody = IOUtils.toString(response.body().asInputStream(), String.valueOf(StandardCharsets.UTF_8));
                    log.error(responseBody, StandardCharsets.UTF_8);
                    return new ApiException(ResultCode.UNAUTHORIZED);
                } catch (IOException e) {
                    log.error("500 error ", e);
                }
                return new InternalServerErrorException(ResultCode.INTERNAL_SERVER_ERROR.getMessage());
            }
            default -> {
                return new InternalServerErrorException(ResultCode.INTERNAL_SERVER_ERROR.getMessage());
            }
        }
    }

}
