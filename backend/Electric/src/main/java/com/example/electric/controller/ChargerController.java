package com.example.electric.controller;

import java.util.List;
import java.util.Optional;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.service.ChargerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// This annotation tells Spring that this class is a Controller
import com.example.electric.model.Charger;

@RestController
@RequestMapping("/api/charger")
public class ChargerController {
    @Autowired
    public ChargerService chargerService;

    /**
     * Retrieve a list of all chargers.
     *
     * This endpoint retrieves a list of all chargers currently available in the system.
     * If no chargers are available, an empty list will be returned.
     *
     * @return A list of chargers, which may be empty if no chargers are found.
     */
    @GetMapping
    @Operation(summary = "Get All Chargers", description = "Return List of Chargers",tags = {"Charger"})
    public List<Charger> getAllChargers() {
        return chargerService.getAllChargers();
    }

    /**
     * Retrieve a list of all chargers at a specific station.
     *
     * This endpoint retrieves a list of all chargers associated with a particular station
     * identified by its unique identifier (stationId).
     *
     * @param stationId The unique identifier of the station to retrieve chargers for.
     * @return A list of chargers at the specified station, which may be empty if no chargers are found.
     */
    @GetMapping("/station/{stationId}")
    @Operation(summary = "Get All Chargers at a Station", description = "Return List of Chargers at a Station using StationID",tags = {"Charger"})

    public List<Charger> getAllChargersAtStation(@PathVariable("stationId") long stationId ) {
        return chargerService.getChargersByStation(stationId);
    }

    /**
     * Retrieve a charger by its unique identifier.
     *
     * This endpoint retrieves a charger from the system based on its unique identifier (chargerId).
     * If a charger with the specified ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param chargerId The unique identifier of the charger to retrieve.
     * @return An Optional containing the retrieved charger, or an empty Optional if not found.
     * @throws ObjectNotFoundException If no charger with the given ID is found.
     */
    @GetMapping("/{chargerId}")
    @Operation(summary = "Get Charger", description = "Get a Charger using ID",tags = {"Charger"})

    public Optional<Charger> getChargerById(@PathVariable("chargerId") long chargerId) {
        if (!chargerService.getChargerById(chargerId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return chargerService.getChargerById(chargerId);
    }

    /**
     * Add a new charger to the system.
     *
     * This endpoint allows the addition of a new charger to the system. The provided
     * charger object should contain the necessary details for creating the charger.
     *
     * @param charger The charger object to be added.
     * @return The unique identifier (ID) of the newly created charger.
     */
    @PostMapping
    @Operation(summary = "Add a Charger", description = "Add a Charger",tags = {"Charger"})

    public long addCharger(@RequestBody Charger charger) {
        return chargerService.addCharger(charger);
    }

    /**
     * Update an existing charger with the provided information.
     *
     * This endpoint allows the update of an existing charger identified by its unique
     * identifier (chargerId). The provided charger object should contain the updated details
     * for the charger. If a charger with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param chargerId The unique identifier of the charger to update.
     * @param charger The updated charger object containing new information.
     * @return The updated charger.
     * @throws ObjectNotFoundException If no charger with the given ID is found.
     */
    @PutMapping("/{chargerId}")
    @Operation(summary = "Update Chargers", description = "Update Charger by ID",tags = {"Charger"})
    public Charger updateCharger(@RequestBody Charger charger, @PathVariable("chargerId") long chargerId) {
        if (!chargerService.getChargerById(chargerId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return chargerService.updateCharger(charger, chargerId);
    }

    /**
     * Delete a charger by its unique identifier.
     *
     * This endpoint allows the deletion of a charger from the system based on its unique
     * identifier (chargerId). If a charger with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param chargerId The unique identifier of the charger to delete.
     * @throws ObjectNotFoundException If no charger with the given ID is found.
     */
    @DeleteMapping("/{chargerId}")
    @Operation(summary = "Delete Chargers", description = "Delete Chargers by ID",tags = {"Charger"})
    public void deleteCharger(@PathVariable("chargerId") long chargerId) {
        if (!chargerService.getChargerById(chargerId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        chargerService.deleteCharger(chargerId);
    }
}