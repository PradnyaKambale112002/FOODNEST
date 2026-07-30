package com.foodnest.foodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodnest.foodnest.config.JwtUtil;
import com.foodnest.foodnest.dto.ApiResponse;
import com.foodnest.foodnest.dto.LoginRequest;
import com.foodnest.foodnest.dto.LoginResponse;
import com.foodnest.foodnest.dto.UserResponse;
import com.foodnest.foodnest.entity.User;
import com.foodnest.foodnest.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Register
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(
            @Valid @RequestBody User user) {

        User savedUser = userService.saveUser(user);

        if (savedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(
                            false,
                            "Email already exists",
                            null));
        }

        UserResponse response = new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getAddress(),
                savedUser.getRole());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "User Registered Successfully",
                        response));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestBody LoginRequest loginRequest) {

        User user = userService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(
                            false,
                            "Invalid email or password",
                            null));
        }

        String token = jwtUtil.generateToken(user.getEmail());

        LoginResponse response = new LoginResponse(
                token,
                user.getRole());

        return ResponseEntity.ok(response);
    }

    // Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User By Id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // Update User
    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable int id,
            @RequestBody User user) {

        return userService.updateUser(id, user);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    // Current Logged-in User
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    	String email = authentication.getName();

    	User user = userService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(
                            false,
                            "User not found",
                            null));
        }

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getRole());

        return ResponseEntity.ok(response);
    }
}