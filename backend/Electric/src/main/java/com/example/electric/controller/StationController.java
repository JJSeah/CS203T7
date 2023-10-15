package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Appointment;
import com.example.electric.model.Station;
import com.example.electric.service.AppointmentService;
import com.example.electric.service.StationService;
import com.example.electric.service.VoronoiService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {
    @Autowired
    private final StationService stationService;

    @Autowired
    private VoronoiService voronoiService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    /**
     * Retrieve a list of all stations.
     *
     * This endpoint retrieves a list of all stations currently available in the system.
     * If no stations are available, an empty list will be returned.
     *
     * @return A list of stations, which may be empty if no stations are found.
     */
    @GetMapping("/all")
    @Operation(summary = "Get All Stations", description = "Get All Stations",tags = {"Station"})
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    /**
     * Retrieve a station by its unique identifier.
     *
     * This endpoint retrieves a station from the system based on its unique identifier (ID).
     * If a station with the specified ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param id The unique identifier of the station to retrieve.
     * @return The retrieved station.
     * @throws ObjectNotFoundException If no station with the given ID is found.
     */

    @GetMapping("/{id}")
    @Operation(summary = "Get Station", description = "Get Station by ID",tags = {"Station"})
    public Station getStationById(@PathVariable Long id) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return stationService.getStationById(id);
    }

    /**
     * Retrieve the closest station based on latitude and longitude.
     *
     * This endpoint calculates and returns the closest station to a given latitude and longitude.
     * The provided 'station' object should contain the latitude and longitude coordinates.
     *
     * @param station A station object with latitude and longitude coordinates.
     * @return The closest station to the provided coordinates.
     */
    @PostMapping("/closest")
    @Operation(summary = "Get Closest Station", description = "Get Closest Station by long and lat",tags = {"Algorithm"})
    public Station slgetClosestStation(@RequestBody Station station) {
        double latitude = station.getLatitude();
        double longitude = station.getLongitude();
        return voronoiService.findClosestStation(latitude, longitude);
    }
    /**
     * Create a new station in the system.
     *
     * This endpoint allows the creation of a new station in the system. The provided
     * station object should contain the necessary details for creating the station.
     *
     * @param station The station object to be created.
     * @return The newly created station.
     */

    @PostMapping
    @Operation(summary = "Create Station", description = "Create Station",tags = {"Station"})
    public Station createStation(@RequestBody Station station) {
        return stationService.createStation(station);
    }

    /**
     * Update an existing station with the provided information.
     *
     * This endpoint allows the update of an existing station identified by its unique
     * identifier (ID). The provided updatedStation object should contain the updated details
     * for the station. If a station with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the station to update.
     * @param updatedStation The updated station object containing new information.
     * @return The updated station.
     * @throws ObjectNotFoundException If no station with the given ID is found.
     */

    @PutMapping("/{id}")
    @Operation(summary = "Update Station", description = "Update Station",tags = {"Station"})
    public Station updateStation(@PathVariable Long id, @RequestBody Station updatedStation) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return stationService.updateStation(id, updatedStation);
    }

    /**
     * Delete a station by its unique identifier.
     *
     * This endpoint allows the deletion of a station from the system based on its unique
     * identifier (ID). If a station with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the station to delete.
     * @throws ObjectNotFoundException If no station with the given ID is found.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Station", description = "Delete Station",tags = {"Station"})
    public void deleteStation(@PathVariable Long id) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        stationService.deleteStation(id);
    }
}
