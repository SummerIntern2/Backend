package com.example.backend.services;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encryption {

    public static Encryption instance;

    private Encryption() {
    }

    public static Encryption getInst() {
        return instance;
    }


    // --------------------------- Password Encryption ---------------------------
    // Encryption Scheme: Bcrypt

    /**
     * Encrypts the password (given by user) for storage in database.
     */
    public static String encryptPassword(String plaintextPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(plaintextPassword);
    }

    /**
     * Tests if hash(plaintextPassword) == hashedPassword.
     * @param plaintextPassword provided by user.
     * @param hashedPassword stored in database.
     */
    public static boolean verifyPassword(String plaintextPassword, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(plaintextPassword, hashedPassword);
    }
}
