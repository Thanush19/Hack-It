package com.backend.backend.service;

import com.backend.backend.entity.User;
import com.backend.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        System.out.println("Received Registration Data: " + user);

        // Save user to DB without encrypting the password
        User savedUser = userRepository.save(user);

        System.out.println("Saved User Data: " + savedUser);
        return savedUser;
    }

    public boolean loginUser(String email, String rawPassword) {
        System.out.println("Login Attempt: Email = " + email + ", Password = " + rawPassword);

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Plain text password comparison
            boolean passwordMatch = rawPassword.equals(user.getPassword());

            System.out.println("Password Match: " + passwordMatch);
            return passwordMatch;
        }

        System.out.println("User Not Found");
        return false;
    }
}
