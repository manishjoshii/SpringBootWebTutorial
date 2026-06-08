package com.manishjoshi.SpringBootWebTutorial.advises;

import lombok.Data;

import java.time.Instant;

@Data
public class ApiResponse<T> {
    private T data;
    private ApiError error;
    private final Instant timeStamp;

    public ApiResponse() {
        this.timeStamp = Instant.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}
