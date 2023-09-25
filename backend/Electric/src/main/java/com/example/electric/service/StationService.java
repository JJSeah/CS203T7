package com.example.electric.service;

import com.example.electric.model.Station;
import com.example.electric.respository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.List;
import java.util.Optional;

@Service
public class StationService {

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
    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    public Station updateStation(Long id, Station updatedStation) {
        Optional<Station> optionalStation = stationRepository.findById(id);
        if (optionalStation.isPresent()) {
            Station station = optionalStation.get();
            // Update the station fields as needed
            station.setName(updatedStation.getName());
            station.setLatitude(updatedStation.getLatitude());
            station.setLongitude(updatedStation.getLongitude());
            station.setChargers(updatedStation.getChargers());
            station.setAvail(updatedStation.isAvail());
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