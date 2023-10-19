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
    public List<Appointment> findAppointmentsByStationId(long stationId);

    List<Appointment> findAppointmentsByUserId(long userId);

    @Query("SELECT a FROM Appointment a " + 
    "WHERE a.user.id = :user_id " + 
    "AND a.manualAppointment = true " + 
    "AND a.status = 'Active'")
List<Appointment> findActiveManualApptByUserId(
 @Param("user_id") long user_id
);
    
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

    @Query("SELECT a FROM Appointment a " + 
    "WHERE a.station.id = :station_id " + 
    "AND a.charger.id = :charger_id " + 
    "AND a.status = :status " +
    "AND a.date = :date ")
List<Appointment> findAppointmentsByStationIdAndChargerIdAndStatus(@Param("station_id")long stationid, @Param("charger_id") long chargerId, @Param("status")String status, @Param("date")LocalDate date);
}
 