package com.example.user_sign_up.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
