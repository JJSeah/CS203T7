package com.example.electric.controller;

import com.example.electric.service.DistanceMatrixService;
import com.google.maps.model.DistanceMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DistanceMatrixController {

    @Autowired
    private DistanceMatrixService distanceMatrixService;

    @GetMapping("/distance")
    public DistanceMatrix getDistance (
            @RequestParam String origin,
            @RequestParam String destination) throws Exception {
        return distanceMatrixService.getDistance(origin, destination);
    }
}
