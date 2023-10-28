package com.example.electric.respository;

import com.example.electric.model.Charger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerRepository extends JpaRepository<Charger, Long> {

    /**
     * Retrieve Chargers by Station ID
     *
     * This method retrieves a list of chargers associated with a specific charging station identified by its unique ID.
     *
     * @param stationId The unique identifier of the charging station.
     * @return A list of Charger objects related to the specified charging station.
     */
    List<Charger> findChargersByStationId(long stationId);
}