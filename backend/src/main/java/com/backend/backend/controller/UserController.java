package com.backend.backend.controller;

import com.backend.backend.entity.User;
import com.backend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        System.out.println("Received Request: Register User");

        User savedUser = userService.registerUser(user);

        System.out.println("Registration Successful for Email: " + savedUser.getEmail());
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        System.out.println("Received Request: Login User with Email: " + email);

        boolean isAuthenticated = userService.loginUser(email, password);

        if (isAuthenticated) {
            System.out.println("Login Successful for Email: " + email);
            return ResponseEntity.ok("Login successful!");
        } else {
            System.out.println("Login Failed for Email: " + email);
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
