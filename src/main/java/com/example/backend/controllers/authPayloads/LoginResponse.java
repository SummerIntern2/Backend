package com.example.backend.controllers.authPayloads;

import java.util.UUID;

public class LoginResponse {

    private String email;
    private UUID id;
    private String jwtToken;

    public LoginResponse(String email, UUID id, String jwtToken) {
        this.email = email;
        this.id = id;
        this.jwtToken = jwtToken;
    }

    public String getEmail() {
        return this.email;
    }

    public UUID getId() {
        return this.id;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
