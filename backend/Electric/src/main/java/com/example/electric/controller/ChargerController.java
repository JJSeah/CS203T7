package com.example.electric.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import com.example.electric.model.Station;
import com.example.electric.service.ChargerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// This annotation tells Spring that this class is a Controller
import com.example.electric.model.Charger;
import com.example.electric.respository.ChargerRepository;

@RestController
@RequestMapping("/charger")
public class ChargerController {
    @Autowired
    public ChargerService chargerService;

    @GetMapping("/station/{stationId}/chargers")
    public List<Charger> getAllChargersAtStation(@PathVariable("stationId") long stationId ) {
        return chargerService.getChargersByStation(stationId);
    }

    @GetMapping("/{chargerId}")
    public Optional<Charger> getChargerById(@PathVariable("chargerId") long chargerId) {
        return chargerService.getChargerById(chargerId);
    }

    @PostMapping("/add")
    public long addCharger(@RequestBody Charger charger, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_CREATED);
        return chargerService.addCharger(charger);
    }

    @PutMapping("/update/{chargerId}")
    public void updateCharger(@RequestBody Charger charger, @PathVariable("chargerId") long chargerId) {
        chargerService.updateCharger(charger, chargerId);
    }

    @DeleteMapping("/delete/{chargerId}")
    public void deleteCharger(@PathVariable("chargerId") long chargerId) {
        chargerService.deleteCharger(chargerId);
    }
}