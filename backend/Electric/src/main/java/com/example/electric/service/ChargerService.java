package com.example.electric.service;

import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import com.example.electric.model.User;
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
        Optional<Charger> OptionalCharger = chargerRepository.findById(chargerId);

        if (OptionalCharger.isPresent()) {
            Charger exisitinCharger = OptionalCharger.get();
            if(updatedCharger.getCharId() != null){
                exisitinCharger.setCharId(updatedCharger.getCharId());
            }
            if(updatedCharger.getName() != null){
                exisitinCharger.setName(updatedCharger.getName());
            }
            if(updatedCharger.getAvail() != exisitinCharger.getAvail()){
                exisitinCharger.setCharId(updatedCharger.getCharId());
            }
            if(updatedCharger.getType() != null){
                exisitinCharger.setType(updatedCharger.getType());
            }            
            if(updatedCharger.getChargingRate() != 0.0){
                exisitinCharger.setChargingRate(updatedCharger.getChargingRate());
            }
            if(updatedCharger.getStation() != null){
                exisitinCharger.setStation(updatedCharger.getStation());
            }
            return chargerRepository.save(exisitinCharger);
        } else {
            return null; // charger not found
        }
    }

    public void deleteCharger(long chargerId) {
        chargerRepository.deleteById(chargerId);
    }
}
