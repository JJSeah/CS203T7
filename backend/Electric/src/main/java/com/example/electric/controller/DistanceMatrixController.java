package com.example.electric.controller;

import com.example.electric.model.Car;
import com.example.electric.model.Station;
import com.example.electric.service.CarService;
import com.example.electric.service.DistanceMatrixService;
import com.example.electric.service.StationService;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
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

    @GetMapping("/distanceMatrix")
    public DistanceMatrix getDistance (
            @RequestParam String origin,
            @RequestParam String destination) throws Exception {
        return distanceMatrixService.getDistanceMatrix(origin, destination);
    }

    @PostMapping("/stationCheck/{userId}/{carId}")
    public Map<String, Object> getStationInfo(@PathVariable("userId")long userId, @PathVariable("carId")long carId,
                                              @RequestBody Station station) throws Exception {
        // Calculate time to arrive, distance, cost, and estimate time of charging
        String timeToArrive = String.valueOf(distanceMatrixService.getDurationByID(station));
        String distance = String.valueOf(distanceMatrixService.getDistanceByID(station));
        String costOfCharging = calculateCostOfCharging(carService.getCarByUserId(userId, carId));
        String estimateTimeOfCharging = calculateEstimateTimeOfCharging(carService.getCarByUserId(userId, carId));

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

    private String calculateEstimateTimeOfCharging(Car car) {
        double time = (car.getBatteryCapacity() * (100 - car.getBatteryPercentage()) )/ (60*60);

        return "" + time;
    }

    private String calculateCostOfCharging(Car car) {
        double cost = (car.getBatteryCapacity() * (100.0 - car.getBatteryPercentage())) / 1000 * 0.12;

        return "" + cost;
    }


    @PostMapping("/distance")
    public long getDistanceByID(@RequestBody Station station) throws Exception {
        return distanceMatrixService.getDistanceByID(station);
    }

    @PostMapping("/time")
    public long getDurationByID(@RequestBody Station station) throws Exception {
        return distanceMatrixService.getDurationByID(station);
    }

//    @GetMapping("/distance/{latitude}/{longitude}/{destination}")
//    public long getDistanceByName(@PathVariable("latitude") String latitude,
//                                  @PathVariable("longitude") String longitude,
//                                  @PathVariable("destination") String station2) throws Exception {
//        return distanceMatrixService.getDistanceByName(latitude,longitude, station2);
//    }

//    @GetMapping("/time/{origin}/{destination}")
//    public long getDurationByName(@PathVariable("origin") String station1,
//                                  @PathVariable("destination") String station2) throws Exception {
//        return distanceMatrixService.getDurationByName(station1, station2);
//    }


}
