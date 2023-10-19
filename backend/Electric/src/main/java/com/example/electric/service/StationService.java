package com.example.electric.service;

import ch.qos.logback.core.model.INamedModel;
import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import com.example.electric.respository.StationRepository;
import com.example.electric.service.inter.StationServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService implements StationServiceInter {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationById(Long id) {
        Optional<Station> optionalStation = stationRepository.findById(id);
        return optionalStation.orElse(null);
    }

 
    public Station getStationByCoordinate(double latitude, double longitude) {
        Optional<Station> optionalStation = stationRepository.findStationByLatitudeAndLongitude(latitude, longitude);
        return optionalStation.orElse(null);
    }

    public Charger getSlowestAndAvailableCharger(Station station) {
        List<Charger> chargerList = station.getChargers();

        Charger result = null;
        double slowestChargingRate = Double.MAX_VALUE;

        for (Charger charger : chargerList) {
            if (charger.getChargingRate() < slowestChargingRate && charger.getAvail()) {
                slowestChargingRate = charger.getChargingRate();
                result = charger;
            }
        }

        return result;
    }

    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    public Station updateStation(Long id, Station updatedStation) {
        Optional<Station> optionalStation = stationRepository.findById(id);
        if (optionalStation.isPresent()) {
            Station station = optionalStation.get();
    
            // Update the station fields only if they are not null or have non-default values
            if (updatedStation.getName() != null) {
                station.setName(updatedStation.getName());
            }
            if (updatedStation.getLatitude() != 0.0) {
                station.setLatitude(updatedStation.getLatitude());
            }
            if (updatedStation.getLongitude() != 0.0) {
                station.setLongitude(updatedStation.getLongitude());
            }
            if (updatedStation.getChargers() != null) {
                station.setChargers(updatedStation.getChargers());
            }
            if (updatedStation.isAvail() != false) {
                station.setAvail(updatedStation.isAvail());
            }
    
            // Save the updated station entity
            return stationRepository.save(station);
        } else {
            return null; // Station not found
        }
    }

    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }
    
    public Station getStationByName(String name) {
        Optional<Station> optionalStation = stationRepository.findStationByName(name);
        return optionalStation.orElse(null);
    }
}