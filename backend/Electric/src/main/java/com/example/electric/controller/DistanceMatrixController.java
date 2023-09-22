package com.example.electric.controller;

import com.example.electric.model.Car;
import com.example.electric.model.Station;
import com.example.electric.service.CarService;
import com.example.electric.service.DistanceMatrixService;
import com.example.electric.service.StationService;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DistanceMatrixController {

    @Autowired
    private DistanceMatrixService distanceMatrixService;
    @Autowired
    private StationService stationService;

    @Autowired
    private CarService carService;

    /**
     * Calculate the distance matrix between two locations.
     *
     * This endpoint calculates the distance matrix between two locations (origin
     * and destination).
     * It takes two request parameters, 'origin' and 'destination', representing the
     * locations for which
     * the distance matrix is to be calculated. The result is a DistanceMatrix
     * object containing the distances
     * and durations for different travel modes and routes between the specified
     * locations.
     *
     * @param origin      The starting location for distance calculation.
     * @param destination The destination location for distance calculation.
     * @return A DistanceMatrix object representing distances and durations between
     *         locations.
     * @throws Exception If there is an issue with calculating the distance matrix.
     */
    @GetMapping("/distanceMatrix")
    @Operation(summary = "Get Distance from 2 points", description = "Calculate distance between origin and destination by lat and long.", tags = {
            "Algorithm" })
    public DistanceMatrix getDistance(
            @RequestParam String origin,
            @RequestParam String destination) throws Exception {
        return distanceMatrixService.getDistanceMatrix(origin, destination);
    }

    /**
     * Calculate and retrieve station information.
     *
     * This endpoint calculates various details such as time to arrive, distance,
     * cost, and
     * estimated time of charging between two points based on latitude and longitude
     * coordinates.
     * It provides valuable information for route planning and charging estimations.
     *
     * @param userId  The unique identifier (UserID) of the user.
     * @param carId   The unique identifier (CarID) of the user's car.
     * @param station Station object containing latitude and longitude
     *                coordinates of the use's current location.
     * @return A map containing information including latitude, longitude, time to
     *         arrive, distance, cost of charging, and estimated time of charging.
     * @throws Exception If there is an error during the calculation.
     */
    @PostMapping("/stationCheck/{userId}/{carId}")
    @Operation(summary = "Get Direction Calculation from 2 points", description = "Calculate time,distance between origin and destination by lat and long. As well as cost,time of charging.", tags = {
            "Algorithm" })
    public Map<String, Object> getStationInfo(@PathVariable("userId") long userId, @PathVariable("carId") long carId,
            @RequestBody Station station) throws Exception {
        // Calculate time to arrive, distance, cost, and estimate time of charging
        String timeToArrive = String.valueOf(distanceMatrixService.getDurationByID(station));
        String distance = String.valueOf(distanceMatrixService.getDistanceByID(station));
        String costOfCharging = distanceMatrixService.calculateCostOfCharging(carService.getCarByUser(userId, carId));
        String estimateTimeOfCharging = distanceMatrixService.calculateEstimateTimeOfCharging(carService.getCarByUser(userId, carId));

        // Create a Map to return the information in JSON format
        Map<String, Object> response = new HashMap<>();
        response.put("latitude", station.getLatitude());
        response.put("longitude", station.getLongitude());
        response.put("timeToArrive", timeToArrive);
        response.put("distance", distance);
        response.put("costOfCharging", costOfCharging);
        response.put("estimateTimeOfCharging", estimateTimeOfCharging);

        return response;
    }


    /**
     * Calculate and retrieve the distance between two points.
     *
     * This endpoint calculates and returns the distance between two points based on
     * latitude and longitude coordinates. It is useful for distance-related calculations.
     *
     * @param station A station object containing latitude and longitude
     *                coordinates.
     * @return The calculated distance between the two points.
     * @throws Exception If there is an error during the calculation.
     */
    @PostMapping("/distance")
    @Operation(summary = "Get Distance from 2 points", description = "Calculate distance between origin and destination by lat and long.", tags = {
            "Algorithm" })
    public long getDistanceByID(@RequestBody Station station) throws Exception {
        return distanceMatrixService.getDistanceByID(station);
    }

    /**
     * Calculate and retrieve the duration between two points.
     *
     * This endpoint calculates and returns the duration (time) it takes to travel
     * between two points based on latitude and longitude coordinates. It is useful for
     * time-related calculations.
     *
     * @param station A station object containing latitude and longitude
     *                coordinates.
     * @return The calculated duration (time) between the two points.
     * @throws Exception If there is an error during the calculation.
     */
    @PostMapping("/time")
    @Operation(summary = "Get Time taken to travel between 2 points", description = "Calculate duration between origin and destination by lat and long.", tags = {
            "Algorithm" })
    public long getDurationByID(@RequestBody Station station) throws Exception {
        return distanceMatrixService.getDurationByID(station);
    }

    // @GetMapping("/distance/{latitude}/{longitude}/{destination}")
    // public long getDistanceByName(@PathVariable("latitude") String latitude,
    // @PathVariable("longitude") String longitude,
    // @PathVariable("destination") String station2) throws Exception {
    // return distanceMatrixService.getDistanceByName(latitude,longitude, station2);
    // }

    // @GetMapping("/time/{origin}/{destination}")
    // public long getDurationByName(@PathVariable("origin") String station1,
    // @PathVariable("destination") String station2) throws Exception {
    // return distanceMatrixService.getDurationByName(station1, station2);
    // }

}
