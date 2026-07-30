package com.foodnest.foodnest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodnest.foodnest.entity.User;
import com.foodnest.foodnest.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register User
    public User saveUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole().toUpperCase());

        return userRepository.save(user);
    }

    // Login User
    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        return user;
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User By Id
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // Get User By Email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // Update User
    public User updateUser(int id, User user) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null) {
            return null;
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());
        existingUser.setRole(user.getRole().toUpperCase());

        // Update password only if provided
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    // Delete User
    public String deleteUser(int id) {

        if (!userRepository.existsById(id)) {
            return "User Not Found";
        }

        userRepository.deleteById(id);
        return "User Deleted Successfully";
    }
}