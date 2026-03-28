package com.api.StudentManagement.service;

import com.api.StudentManagement.exception.ResourceNotFoundException;
import com.api.StudentManagement.model.User;
import com.api.StudentManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    public User updateUser(User userToUpdate, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        if(userToUpdate.getName() != null){
            user.setName(userToUpdate.getName());
        }

        return userRepository.save(user);
    }

    public User deleteUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userRepository.deleteById(userId);
        return user;
    }

    public User findbyEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
