package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Station;
import com.example.electric.service.StationService;
import com.example.electric.service.VoronoiService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {
    @Autowired
    private final StationService stationService;

    @Autowired
    private VoronoiService voronoiService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get All Stations", description = "Get All Stations",tags = {"Station"})
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Station", description = "Get Station by ID",tags = {"Station"})
    public Station getStationById(@PathVariable Long id) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return stationService.getStationById(id);
    }

//    @GetMapping("/closest/{latitude}/{longitude}")
//    public Station slgetClosestStation(@PathVariable("latitude") double latitude,
//                                     @PathVariable("longitude") double longitude) {
//        return voronoiService.findClosestStation(latitude, longitude);
//    }

    @PostMapping("/closest")
    @Operation(summary = "Get Closest Station", description = "Get Closest Station by long and lat",tags = {"Algorithm"})
    public Station slgetClosestStation(@RequestBody Station station) {
        double latitude = station.getLatitude();
        double longitude = station.getLongitude();
        return voronoiService.findClosestStation(latitude, longitude);
    }
    @PostMapping
    @Operation(summary = "Create Station", description = "Create Station",tags = {"Station"})
    public Station createStation(@RequestBody Station station) {
        return stationService.createStation(station);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Station", description = "Update Station",tags = {"Station"})
    public Station updateStation(@PathVariable Long id, @RequestBody Station updatedStation) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return stationService.updateStation(id, updatedStation);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Station", description = "Delete Station",tags = {"Station"})
    public void deleteStation(@PathVariable Long id) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        stationService.deleteStation(id);
    }
}
