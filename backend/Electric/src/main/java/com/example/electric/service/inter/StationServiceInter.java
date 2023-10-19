package com.example.electric.service.inter;

import com.example.electric.model.Charger;
import com.example.electric.model.Station;

import java.util.List;

public interface StationServiceInter {
    List<Station> getAllStations();
    Station getStationById(Long id);
    Station getStationByCoordinate(double latitude, double longitude);
    Station createStation(Station station);
    Station updateStation(Long id, Station updatedStation);
    void deleteStation(Long id);
    Station getStationByName(String name);
    Charger getSlowestAndAvailableCharger(Station station);
}
