package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.entity.UserModel;
import com.example.bookmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a single user by ID
    public UserModel getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    // Add a new user/borrower
    public UserModel addUser(UserModel user) {
        return userRepository.save(user);
    }

    // Update user details
    public UserModel updateUser(Long userId, UserModel userDetails) {
        UserModel user = getUserById(userId);
        user.setName(userDetails.getName());
        user.setAddress(userDetails.getAddress());
        user.setContactNumber(userDetails.getContactNumber());
        return userRepository.save(user);
    }

    // Delete a user
    public void deleteUser(Long userId) {
        UserModel user = getUserById(userId);
        userRepository.delete(user);
    }
}
