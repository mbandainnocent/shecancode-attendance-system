package com.shecancode.attendence.auth.passwordGen;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password1 = "password";
        String password2 = "Admin@1234";

        System.out.println("Hash for 'password': " + encoder.encode(password1));
        System.out.println("Hash for 'Admin@1234': " + encoder.encode(password2));

        // Verify the hashes work
        String hash1 = "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy";
        System.out.println("Verify 'password' against known hash: " + encoder.matches(password1, hash1));
    }
}
