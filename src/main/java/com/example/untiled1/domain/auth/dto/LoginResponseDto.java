package com.example.untiled1.domain.auth.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponseDto implements Serializable {
    private String token;
    private Long exp;
    private String errorCode;
    private String message;
}
