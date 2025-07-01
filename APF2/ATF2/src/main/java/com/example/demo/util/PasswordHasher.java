package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String adminPlainTextPassword = "admin123";
        String hashedPasswordForAdmin = encoder.encode(adminPlainTextPassword);

        System.out.println("Contraseña hasheada para ADMIN ('" + adminPlainTextPassword + "'): " + hashedPasswordForAdmin);
    }
}