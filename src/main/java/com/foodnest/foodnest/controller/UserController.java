package com.foodnest.foodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.foodnest.foodnest.config.JwtUtil;
import com.foodnest.foodnest.entity.User;
import com.foodnest.foodnest.service.UserService;
import com.foodnest.foodnest.dto.LoginRequest;
import jakarta.validation.Valid;
import com.foodnest.foodnest.dto.ApiResponse;
import com.foodnest.foodnest.dto.UserResponse;
import com.foodnest.foodnest.dto.LoginResponse;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

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
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
    @PostMapping("/register")
    public ApiResponse<UserResponse> registerUser(@Valid @RequestBody User user) {

        User savedUser = userService.saveUser(user);

        if (savedUser != null) {

            UserResponse response = new UserResponse(
                    savedUser.getId(),
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getPhone(),
                    savedUser.getAddress(),
                    savedUser.getRole());

            return new ApiResponse<>(
                    true,
                    "User Registered Successfully",
                    response);
        }

        return new ApiResponse<>(
                false,
                "Email Already Exists",
                null);
    }
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {

        User user = userService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword());

        System.out.println("User = " + user);

        if (user != null) {

            String token = jwtUtil.generateToken(user.getEmail());

            System.out.println("Role = " + user.getRole());

            return new LoginResponse(token, user.getRole());
        }

        System.out.println("Login failed");

        return null;
    }
}