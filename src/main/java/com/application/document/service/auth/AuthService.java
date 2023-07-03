package com.application.document.service.auth;

import com.application.document.model.request.auth.AuthRequest;
import com.application.document.model.request.auth.UserRegisterRequestDTO;
import com.application.document.model.response.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 2.07.2023
 */

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RegisterService registerService;

    private final LoginService loginService;
    public Boolean registerUser(UserRegisterRequestDTO registerRequest) {

        return registerService.apply(registerRequest);
    }

    public JwtResponse loginUser(AuthRequest authRequest) {

        return loginService.apply(authRequest);
    }

}
