package com.example.gateway.controller;

import com.example.gateway.controller.model.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        if (Boolean.TRUE.equals(loginRequest.success())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("role", "superUser");
            log.info("Login successful. Session created with ID: {}", session.getId());
            return ResponseEntity.ok(session.getId());
        } else {
            log.warn("Login failed. Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
}
