package com.example.electric.respository;

import com.example.electric.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electric.model.Charger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerRepository extends JpaRepository<Charger, Long> {
    List<Charger> findChargersByStationId(long stationId);
}