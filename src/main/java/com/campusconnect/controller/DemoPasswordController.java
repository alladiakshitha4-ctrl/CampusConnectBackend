
package com.campusconnect.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import com.campusconnect.dto.DemoPasswordRequestDTO;
import com.campusconnect.service.AuthService;

@RestController
@RequestMapping("/auth")
public class DemoPasswordController {

    private final AuthService authService;

    public DemoPasswordController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/demo-password")
    public Map<String, String> setPassword(
            @RequestBody DemoPasswordRequestDTO request) {

        if (request == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body is required");
        }

        authService.demoSetPassword(
            request.getEmail(),
            request.getRole(),
            request.getNewPassword()
        );

        return Map.of(
            "message", "Password updated successfully. You can now sign in."
        );
    }
}
