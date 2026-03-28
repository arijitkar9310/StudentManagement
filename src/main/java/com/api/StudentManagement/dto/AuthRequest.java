package com.api.StudentManagement.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
