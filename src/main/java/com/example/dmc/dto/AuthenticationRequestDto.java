package com.example.dmc.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthenticationRequestDto {
    @NotBlank(message = "Username can't be empty.")
    private String username;

    @NotBlank(message = "Password can't be empty.")
    private String password;
}
