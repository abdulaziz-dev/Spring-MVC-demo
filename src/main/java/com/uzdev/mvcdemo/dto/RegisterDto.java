package com.uzdev.mvcdemo.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private Long id;
    private String username;
    private String password;
    private String email;
}
