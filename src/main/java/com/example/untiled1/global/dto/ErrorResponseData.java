package com.example.untiled1.global.dto;

import lombok.Data;

@Data
public class ErrorResponseData {
    private String code;
    private String message;
    private String stackTrace;

    public ErrorResponseData(String code, String message) {
        this(code, message, null);
    }

    public ErrorResponseData(String code, String message, String stackTrace) {
        this.code = code;
        this.message = message;
        this.stackTrace = stackTrace;
    }
}
