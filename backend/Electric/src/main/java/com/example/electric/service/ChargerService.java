package com.example.electric.service;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Charger;
import com.example.electric.respository.ChargerRepository;
import com.example.electric.respository.StationRepository;
import com.example.electric.service.inter.ChargerServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargerService implements ChargerServiceInter {
    @Autowired
    private ChargerRepository chargerRepository;

    @Autowired
    public StationRepository stationRepository;

    public List<Charger> getAllChargers() {
        return chargerRepository.findAll();
    }

    public List<Charger> getChargersByStation(long stationId) {
        if(stationRepository.findById(stationId).isEmpty()){
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
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

    public Optional<Charger> deleteCharger(long chargerId) {
        if (!chargerRepository.existsById(chargerId)) {
            return null;
        }
        Optional<Charger> deleted = chargerRepository.findById(chargerId);
        chargerRepository.deleteById(chargerId);
        return deleted;
    }


}
