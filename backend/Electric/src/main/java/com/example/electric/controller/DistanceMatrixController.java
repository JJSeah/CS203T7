package com.example.electric.controller;

import com.example.electric.model.Station;
import com.example.electric.service.DistanceMatrixService;
import com.example.electric.service.StationService;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DistanceMatrixController {

    @Autowired
    private DistanceMatrixService distanceMatrixService;
    @Autowired
    private StationService stationService;

    @GetMapping("/distanceMatrix")
    public DistanceMatrix getDistance (
            @RequestParam String origin,
            @RequestParam String destination) throws Exception {
        return distanceMatrixService.getDistanceMatrix(origin, destination);
    }

    @GetMapping("/distance/{origin}/{destination}")
    public long getDistanceByName(@PathVariable("origin") String station1,
                                  @PathVariable("destination") String station2) throws Exception {
        return distanceMatrixService.getDistanceByName(station1, station2);
    }

    @GetMapping("/time/{origin}/{destination}")
    public long getDurationByName(@PathVariable("origin") String station1,
                                  @PathVariable("destination") String station2) throws Exception {
        return distanceMatrixService.getDurationByName(station1, station2);
    }
}
