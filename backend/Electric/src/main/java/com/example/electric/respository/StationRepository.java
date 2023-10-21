package com.example.electric.respository;

import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    public Optional<Station> findStationByName(String name);
    public Optional<Station> findStationByLatitudeAndLongitude(double latitude, double longitude);

    @Query("SELECT c FROM Charger c " +
            "WHERE c.station = :station " +  // Add a condition to filter by the specified station
            "AND c.id NOT IN (" +
            "    SELECT a.charger.id FROM Appointment a " +
            "    WHERE a.status = 'Active'" +
            "    AND (a.startTime <= :startTime OR a.endTime >= :endTime) AND  a.date = :date" +
            ")" +
            "ORDER BY c.chargingRate ASC " +
            "LIMIT 1")
    Charger findSlowestAvailableChargerForStation(
            @Param("station") Station station,  // Add a parameter for the station
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("date") LocalDate date
    );
}
