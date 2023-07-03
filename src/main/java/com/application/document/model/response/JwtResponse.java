package com.application.document.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;

    private String type = "Bearer";

    private Long id;

    private String username;

    private String email;

    private List<String> roles;

    public JwtResponse(String jwt, Long id, String username, String email, List<String> roles) {
        this.token = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
