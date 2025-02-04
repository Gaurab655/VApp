package com.vapp.builder;

import com.vapp.model.ApiResponse;
import org.springframework.http.HttpStatus;

public class ServiceResponseBuilder {
    public static ApiResponse buildFailedBuilder(String message) {
        return ApiResponse
                .builder()
                .message(message)
                .success(false)
                .build();
    }

    public static ApiResponse buildSuccessBuilder(String message) {
        return ApiResponse
                .builder()
                .message(message)
                .success(true)
                .build();
    }

    public static ApiResponse buildSuccessBuilder(String message, Object data) {
        return ApiResponse
                .builder()
                .message(message)
                .data(data)
                .success(true)
                .build();

    }

}
