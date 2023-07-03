package com.application.document.controller;

import com.application.document.model.RestResponse;
import com.application.document.model.request.auth.AuthRequest;
import com.application.document.model.request.auth.UserRegisterRequestDTO;
import com.application.document.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1/register")
    public ResponseEntity<RestResponse> registerUser(@RequestBody UserRegisterRequestDTO registerRequest) {

        return ResponseEntity.ok(new RestResponse<>(200, authService.registerUser(registerRequest)));
    }

    @PostMapping(value = "/v1/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        return ResponseEntity.ok(new RestResponse<>(200, authService.loginUser(authRequest)));
    }
}
