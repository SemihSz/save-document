package com.application.document.model.response;

import lombok.*;

import java.util.List;

/**
 * Created by Semih, 3.07.2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
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
