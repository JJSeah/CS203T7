package com.example.electric.respository;

import com.example.electric.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public List<Appointment> findAppointmentsByStationId(long stationId);

    List<Appointment> findAppointmentsByUserId(long userId);
}
