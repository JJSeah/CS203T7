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
    
    @Query("SELECT new com.example.electric.model.Station(s.id, c.id, s.name, c.chargingRate, s.latitude, s.longitude, s.address) FROM Station s " +
    "JOIN Charger c ON s.id = c.station.id " +
    "WHERE NOT EXISTS (" +
    "    SELECT 1 FROM Appointment a " +
    "    WHERE a.station.id = s.id AND a.charger.id = c.id AND a.status = 'Active'" +
    "    AND (a.startTime <= :startTime OR a.endTime >= :endTime) AND  a.date >= :date" +
    ")")
List<Station> findAvailableStationsAndChargers(
    @Param("startTime") LocalTime startTime,
    @Param("endTime") LocalTime endTime,
    @Param("date") LocalDate date
);
}
