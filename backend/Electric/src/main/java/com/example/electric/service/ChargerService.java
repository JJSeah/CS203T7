package com.example.electric.service;

import com.example.electric.respository.ChargerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargerService {
    @Autowired
    private ChargerRepository chargerRepository;
}
