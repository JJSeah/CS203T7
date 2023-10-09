package com.example.electric.service.inter;

import com.example.electric.exception.ExceedMaxManualApptException;
import com.example.electric.model.Appointment;
import com.example.electric.model.Station;

import java.util.List;
import java.util.Optional;

public interface AppointmentServiceInter {
    List<Appointment> getAllAppointments();

    Optional<Appointment> getAppointmentById(long id);

    List<Appointment> getAllAppointmentsAtStation(long stationId);

    Appointment addAppointment(Appointment appointment) throws ExceedMaxManualApptException;

    int checkManualAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment updatedAppointment, long id);

    void deleteAppointment(long appointmentId);

    List<Appointment> getAllAppointmentsByUser(long userId);

    List<Appointment> getAllActiveManualAppointmentByUser(long userId);

    List<Station> getAvailableStationsAndChargers(String startTime, String endTime, String dateNow);

    Appointment completedAppointment(Appointment updatedAppointment, long id);
}
