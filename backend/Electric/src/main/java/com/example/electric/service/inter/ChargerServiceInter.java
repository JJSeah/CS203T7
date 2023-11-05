package com.example.electric.service.inter;

import com.example.electric.model.Charger;

import java.util.List;
import java.util.Optional;

public interface ChargerServiceInter {
    List<Charger> getAllChargers();

    List<Charger> getChargersByStation(long stationId);

    Optional<Charger> getChargerById(long id);

    long addCharger(Charger charger);
    Charger updateCharger(Charger updatedCharger, long chargerId);

    Optional<Charger> deleteCharger(long chargerId);


}
