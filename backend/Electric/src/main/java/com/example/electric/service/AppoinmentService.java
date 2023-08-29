package com.example.electric.service;

import com.example.electric.respository.AppoinmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppoinmentService {
    @Autowired
    private AppoinmentRepository appoinmentRepository;
}
