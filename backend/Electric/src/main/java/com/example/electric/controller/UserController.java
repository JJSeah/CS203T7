package com.example.electric.controller;

import com.example.electric.respository.UserRepository;
import com.example.electric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


}
