package com.example.electric.controller;

import com.example.electric.model.Station;
import com.example.electric.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/v1/all")
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/v1/{id}")
    public Station getStationById(@PathVariable Long id) {
        return stationService.getStationById(id);
    }

    @PostMapping("/v1")
    public Station createStation(@RequestBody Station station) {
        return stationService.createStation(station);
    }

    @PutMapping("/v1/{id}")
    public Station updateStation(@PathVariable Long id, @RequestBody Station updatedStation) {
        return stationService.updateStation(id, updatedStation);
    }

    @DeleteMapping("/v1/{id}")
    public void deleteStation(@PathVariable Long id) {
        stationService.deleteStation(id);
    }
}
