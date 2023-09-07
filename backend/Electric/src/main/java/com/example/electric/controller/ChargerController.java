package com.example.electric.controller;

import java.util.List;
import java.util.Optional;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.service.ChargerService;
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
    public List<Charger> getAllChargers() {
        return chargerService.getAllChargers();
    }

    @GetMapping("/station/{stationId}")
    public List<Charger> getAllChargersAtStation(@PathVariable("stationId") long stationId ) {
        return chargerService.getChargersByStation(stationId);
    }

    @GetMapping("/{chargerId}")
    public Optional<Charger> getChargerById(@PathVariable("chargerId") long chargerId) {
        if (!chargerService.getChargerById(chargerId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return chargerService.getChargerById(chargerId);
    }

    @PostMapping
    public long addCharger(@RequestBody Charger charger) {
        return chargerService.addCharger(charger);
    }

    @PutMapping("/{chargerId}")
    public void updateCharger(@RequestBody Charger charger, @PathVariable("chargerId") long chargerId) {
        if (!chargerService.getChargerById(chargerId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        chargerService.updateCharger(charger, chargerId);
    }

    @DeleteMapping("/{chargerId}")
    public void deleteCharger(@PathVariable("chargerId") long chargerId) {
        if (!chargerService.getChargerById(chargerId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        chargerService.deleteCharger(chargerId);
    }
}