package com.api.StudentManagement.controller;

import com.api.StudentManagement.dto.AuthRequest;
import com.api.StudentManagement.dto.AuthResponse;
import com.api.StudentManagement.model.User;
import com.api.StudentManagement.security.JWTUtil;
import com.api.StudentManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthConroller {
    @Autowired
    UserService userService;

    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        User user = userService.findbyEmail(request.getEmail());

        if(user == null ||
                !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        AuthResponse response = new AuthResponse(
                token,
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
        return ResponseEntity.ok(response);
    }
}
