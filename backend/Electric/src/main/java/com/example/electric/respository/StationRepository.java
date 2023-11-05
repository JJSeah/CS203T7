package com.example.electric.respository;

import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    /**
     * Find Station by Name
     *
     * This method attempts to find a Station entity by its name.
     *
     * @param name The name of the station to find.
     * @return An Optional<Station> object containing the Station entity if found, otherwise it's empty.
     */
    public Optional<Station> findStationByName(String name);
    /**
     * Find Station by Latitude and Longitude
     *
     * This method attempts to find a Station entity by its latitude and longitude coordinates.
     *
     * @param latitude The latitude coordinate of the station to find.
     * @param longitude The longitude coordinate of the station to find.
     * @return An Optional<Station> object containing the Station entity if found, otherwise it's empty.
     */
    public Optional<Station> findStationByLatitudeAndLongitude(double latitude, double longitude);

    /**
     * Find the Slowest Available Charger for a Station
     *
     * This method retrieves the slowest available Charger for a specified station, considering factors like the specified time range and date.
     *
     * @param station The station for which to find the slowest available charger.
     * @param startTime The start time for the appointment.
     * @param endTime The end time for the appointment.
     * @param date The date for the appointment.
     * @return The Charger object representing the slowest available charger for the station or null if not found.
     */
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
