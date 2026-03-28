package com.api.StudentManagement.controller;

import com.api.StudentManagement.model.User;
import com.api.StudentManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return  ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<User> deleteUser(@PathVariable Long id){
        User deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }
}
