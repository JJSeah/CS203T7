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

    /**
     * Retrieve a list of all stations.
     *
     * This endpoint retrieves a list of all stations currently available in the system.
     * If no stations are available, an empty list will be returned.
     *
     * @return A list of stations, which may be empty if no stations are found.
     */
    @GetMapping("/all")
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
    public Station getStationById(@PathVariable Long id) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return stationService.getStationById(id);
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
    public void deleteStation(@PathVariable Long id) {
        if (stationService.getStationById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        stationService.deleteStation(id);
    }
}
