package com.application.document.model.request.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 2.07.2023
 */
@Getter
@Setter
public class UserRegisterRequestDTO {

    private String username;

    private String password;

    private String email;

    private String role;
}
