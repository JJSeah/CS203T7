package com.example.electric.service;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.AppointmentNotFoundException;
import com.example.electric.model.Appointment;
import com.example.electric.respository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointmentsAtStation(long stationId) {
        return appointmentRepository.findAppointmentsByStationId(stationId);
    }

    public Appointment addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        return appointment;
    }

    public Appointment updateAppointment(Appointment updatedAppointment, long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            return null;
        }

        updatedAppointment.setId(appointmentId);
        return appointmentRepository.save(updatedAppointment);
    }

    public void deleteAppointment(long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
