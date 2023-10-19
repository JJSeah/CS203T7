package com.example.electric.controller;

import com.example.electric.model.Car;
import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import com.example.electric.service.CarService;
import com.example.electric.service.DistanceMatrixService;
import com.example.electric.service.StationService;
import com.google.maps.model.DistanceMatrix;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        double costOfCharging = distanceMatrixService.calculateCostOfCharging(carService.getCarByUserId(userId, carId));
        int estimateTimeOfCharging = distanceMatrixService.calculateEstimateTimeOfCharging(carService.getCarByUserId(userId, carId));

        //Get available charger
        Station obj = stationService.getStationById(station.getId());
        Charger charger = stationService.getSlowestAndAvailableCharger(obj);

        //Get start time
        LocalTime currentTime = LocalTime.now();
        int minute = currentTime.getMinute();
        int roundedMinute = (minute / 5) * 5;

        LocalTime roundedStartTime = currentTime.withMinute(roundedMinute).withSecond(00);
        String startTime = roundedStartTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        //Get end time
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = start.plus(3, ChronoUnit.HOURS);
        String endTime = end.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        //Get date
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        // Create a Map to return the information in JSON format
        Map<String, Object> response = new HashMap<>();
        response.put("stationId", station.getId());
        response.put("chargerId", charger.getId());
        response.put("startTime", startTime);
        response.put("endTime", endTime);
        response.put("date", date);
        response.put("timeToArrive", timeToArrive);
        response.put("distance", distance);
        response.put("costOfCharging", costOfCharging);
        response.put("estimateTimeOfCharging", estimateTimeOfCharging);
        response.put("charger", charger);

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

    /**
     * Calculate and retrieve the duration of charging.
     *
     * This endpoint calculates and returns the duration (time) it takes to travel
     * the time needed to fully charge the car.
     *
     * @param car A car object containing battery capacity and percentage.
     * @return The calculated duration (time) to charge.
     */
    @PostMapping("/charging/time")
    public int getChargingTime(@RequestBody Car car) {
        return distanceMatrixService.calculateEstimateTimeOfCharging(car);
    }

    /**
     * Calculate and retrieve the cost of charging.
     *
     * This endpoint calculates and returns the cost to fully charge the car.
     *
     * @param car A car object containing battery capacity and percentage.
     * @return The calculated cost to charge.
     */
    @PostMapping("/charging/cost")
    public double getChargingCost(@RequestBody Car car) {
        return distanceMatrixService.calculateCostOfCharging(car);
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
