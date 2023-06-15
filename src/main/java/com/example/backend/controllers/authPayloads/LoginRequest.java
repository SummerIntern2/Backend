package com.example.backend.controllers.authPayloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Specifies the structure for a request to {@code login} method.
 */
public class LoginRequest {

    @NotBlank(message = "Email is required.")
    @Email
    private String email;

    @NotBlank(message = "Password is required.")
    private String plaintextPassword;

    public LoginRequest(String email, String plaintextPassword) {
        this.email = email;
        this.plaintextPassword = plaintextPassword;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.plaintextPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.plaintextPassword = password;
    }
}
