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

    public List<Charger> getChargersByStation(long stationId) {
        return chargerRepository.findChargersByStationId(stationId);
    }

    public Optional<Charger> getChargerById(long id) {
        return chargerRepository.findById(id);
    }

    public long addCharger(Charger charger) {
        chargerRepository.save(charger);
        return charger.getId();
    }

    public Charger updateCharger(Charger updatedCharger, long chargerId) {
        if (!chargerRepository.existsById(chargerId)) {
            return null;
        }

        updatedCharger.setId(chargerId);
        return chargerRepository.save(updatedCharger);
    }

    public Optional<Charger> deleteCharger(long chargerId) {
        if (!chargerRepository.existsById(chargerId)) {
            return null;
        }
        Optional<Charger> deleted = chargerRepository.findById(chargerId);
        chargerRepository.deleteById(chargerId);
        return deleted;
    }
}
