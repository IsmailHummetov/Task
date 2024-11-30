package com.example.authservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDto {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String password;
}
