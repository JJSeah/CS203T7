package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
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

    @GetMapping("/all")
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Long id) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return stationService.getStationById(id);
    }

    @PostMapping
    public Station createStation(@RequestBody Station station) {
        return stationService.createStation(station);
    }

    @PutMapping("/{id}")
    public Station updateStation(@PathVariable Long id, @RequestBody Station updatedStation) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return stationService.updateStation(id, updatedStation);
    }

    @DeleteMapping("/{id}")
    public void deleteStation(@PathVariable Long id) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        stationService.deleteStation(id);
    }
}
