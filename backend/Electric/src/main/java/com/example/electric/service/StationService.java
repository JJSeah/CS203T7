package com.example.electric.service;

import com.example.electric.respository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;
}
