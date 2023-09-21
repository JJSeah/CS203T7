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

    /**
     * Calculate the distance matrix between two locations.
     *
     * This endpoint calculates the distance matrix between two locations (origin and destination).
     * It takes two request parameters, 'origin' and 'destination', representing the locations for which
     * the distance matrix is to be calculated. The result is a DistanceMatrix object containing the distances
     * and durations for different travel modes and routes between the specified locations.
     *
     * @param origin The starting location for distance calculation.
     * @param destination The destination location for distance calculation.
     * @return A DistanceMatrix object representing distances and durations between locations.
     * @throws Exception If there is an issue with calculating the distance matrix.
     */
    @GetMapping("/distance")
    public DistanceMatrix getDistance (
            @RequestParam String origin,
            @RequestParam String destination) throws Exception {
        return distanceMatrixService.getDistance(origin, destination);
    }
}
