package com.example.electric.controller;

import com.example.electric.service.AppoinmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppoinmentController {
    @Autowired
    private AppoinmentService appoinmentService;
}
