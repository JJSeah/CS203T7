package com.example.electric.respository;

import com.example.electric.model.Appointment;
import com.example.electric.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * Retrieve Appointments by Station ID
     *
     * This method retrieves a list of appointments associated with a specific charging station
     * identified by its unique ID.
     *
     * @param stationId The unique identifier of the charging station.
     * @return A list of Appointment objects related to the specified station.
     */
    public List<Appointment> findAppointmentsByStationId(long stationId);


    /**
     * Retrieve Appointments by User ID
     *
     * This method retrieves a list of appointments associated with a specific user identified by their
     * unique ID.
     *
     * @param userId The unique identifier of the user.
     * @return A list of Appointment objects related to the specified user.
     */
    List<Appointment> findAppointmentsByUserId(long userId);


    /**
     * Retrieve Active Manual Appointments by User ID
     *
     * This method retrieves a list of active manual appointments associated with a specific user.
     * Manual appointments are appointments created by the user. It filters appointments by user ID and
     * status.
     *
     * @param user_id The unique identifier of the user.
     * @return A list of active manual appointments for the specified user.
     */
    @Query("SELECT a FROM Appointment a " + 
    "WHERE a.user.id = :user_id " + 
    "AND a.manualAppointment = true " + 
    "AND a.status = 'Active'")
    List<Appointment> findActiveManualApptByUserId(
     @Param("user_id") long user_id
    );

    /**
     * Retrieve Available Charging Stations and Chargers
     *
     * This method retrieves a list of charging stations and their associated chargers that are available
     * for booking within a specified time window. It excludes stations and chargers that have active
     * appointments during the given time frame.
     *
     * @param startTime The start time of the booking window.
     * @param endTime The end time of the booking window.
     * @param date The date for which availability is checked.
     * @return A list of Station objects with available chargers.
     */
    @Query("SELECT new com.example.electric.model.Station(s.id, c.id, s.name, c.chargingRate, s.latitude, s.longitude, s.address) FROM Station s " +
    "JOIN Charger c ON s.id = c.station.id " +
    "WHERE c.id NOT IN (" +
    "    SELECT a.charger.id FROM Appointment a " +
    "    WHERE a.status = 'Active'" +
    "    AND (a.startTime <= :startTime OR a.endTime >= :endTime) AND  a.date = :date" +
    ")")
    List<Station> findAvailableStationsAndChargers(
        @Param("startTime") LocalTime startTime,
        @Param("endTime") LocalTime endTime,
        @Param("date") LocalDate date
    );

    /**
     * Retrieve Appointments by Station ID, Charger ID, and Status
     *
     * This method retrieves a list of appointments associated with a specific charging station, charger,
     * and status on a given date.
     *
     * @param station_id The unique identifier of the charging station.
     * @param charger_id The unique identifier of the charger.
     * @param status The status of the appointment (e.g., "Active").
     * @param date The date for which appointments are retrieved.
     * @return A list of Appointment objects based on the specified station, charger, status, and date.
     */
    @Query("SELECT a FROM Appointment a " +
    "WHERE a.station.id = :station_id " +
    "AND a.charger.id = :charger_id " +
    "AND a.status = :status " +
    "AND a.date = :date ")
    List<Appointment> findAppointmentsByStationIdAndChargerIdAndStatus(@Param("station_id")long stationid, @Param("charger_id") long chargerId, @Param("status")String status, @Param("date")LocalDate date);
    }
 