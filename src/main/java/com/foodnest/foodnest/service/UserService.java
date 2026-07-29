package com.foodnest.foodnest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodnest.foodnest.entity.User;
import com.foodnest.foodnest.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User By Id
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // Update User
    public User updateUser(int id, User user) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhone(user.getPhone());
            existingUser.setAddress(user.getAddress());
            existingUser.setRole(user.getRole());

            return userRepository.save(existingUser);
        }

        return null;
    }

    // Delete User
    public String deleteUser(int id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User Deleted Successfully";
        }

        return "User Not Found";
    }
    public User loginUser(String email, String password) {

        System.out.println("Email received: " + email);

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            System.out.println("User not found in database");
            return null;
        }

        System.out.println("DB Email: " + user.getEmail());
        System.out.println("DB Role: " + user.getRole());
        System.out.println("Password Match: " +
                passwordEncoder.matches(password, user.getPassword()));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }
    public User saveUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}