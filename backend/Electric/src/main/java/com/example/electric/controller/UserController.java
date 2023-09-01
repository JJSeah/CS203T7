package com.example.electric.controller;

import com.example.electric.model.User;
import com.example.electric.respository.UserRepository;
import com.example.electric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/v1/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("api/v1/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("api/v1/")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("api/v1/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("api/v1/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
