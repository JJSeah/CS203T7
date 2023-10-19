package com.example.electric.respository;

import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    public Optional<Station> findStationByName(String name);
    public Optional<Station> findStationByLatitudeAndLongitude(double latitude, double longitude);
}
