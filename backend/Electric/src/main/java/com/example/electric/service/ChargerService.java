package com.example.electric.service;

import com.example.electric.model.Charger;
import com.example.electric.respository.ChargerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargerService {
    @Autowired
    private ChargerRepository chargerRepository;

    public List<Charger> getAllChargers() {
        return chargerRepository.findAll();
    }

    public List<Charger> getChargersByStation(String stationId) {
        return chargerRepository.findChargersByStationId(stationId);
    }

    public Optional<Charger> getChargerById(long id) {
        return chargerRepository.findById(id);
    }

    public long addCharger(Charger charger) {
        chargerRepository.save(charger);
        return charger.getId();
    }

    public void updateCharger(Charger charger, long chargerId) {

    }

    public void deleteCharger(long chargerId) {
        chargerRepository.deleteById(chargerId);
    }
}
