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

    @GetMapping
    @Operation(summary = "Get All Chargers", description = "Return List of Chargers",tags = {"Charger"})
    public List<Charger> getAllChargers() {
        return chargerService.getAllChargers();
    }

    @GetMapping("/station/{stationId}")
    @Operation(summary = "Get All Chargers at a Station", description = "Return List of Chargers at a Station using StationID",tags = {"Charger"})

    public List<Charger> getAllChargersAtStation(@PathVariable("stationId") long stationId ) {
        return chargerService.getChargersByStation(stationId);
    }

    @GetMapping("/{chargerId}")
    @Operation(summary = "Get Charger", description = "Get a Charger using ID",tags = {"Charger"})

    public Optional<Charger> getChargerById(@PathVariable("chargerId") long chargerId) {
        if (!chargerService.getChargerById(chargerId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return chargerService.getChargerById(chargerId);
    }

    @PostMapping
    @Operation(summary = "Add a Charger", description = "Add a Charger",tags = {"Charger"})

    public long addCharger(@RequestBody Charger charger) {
        return chargerService.addCharger(charger);
    }

    @PutMapping("/{chargerId}")
    @Operation(summary = "Update Chargers", description = "Update Charger by ID",tags = {"Charger"})
    public Charger updateCharger(@RequestBody Charger charger, @PathVariable("chargerId") long chargerId) {
        if (!chargerService.getChargerById(chargerId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return chargerService.updateCharger(charger, chargerId);
    }

    @DeleteMapping("/{chargerId}")
    @Operation(summary = "Delete Chargers", description = "Delete Chargers by ID",tags = {"Charger"})
    public void deleteCharger(@PathVariable("chargerId") long chargerId) {
        if (!chargerService.getChargerById(chargerId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        chargerService.deleteCharger(chargerId);
    }
}