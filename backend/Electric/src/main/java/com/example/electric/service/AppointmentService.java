package com.example.electric.service;

import com.example.electric.model.Appointment;
import com.example.electric.respository.AppointmentRepository;
import com.example.electric.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Appointment updateAppointment(Appointment updatedAppointment, long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            // Update the appointment fields as needed
            if (updatedAppointment.getUser() != null) {
                appointment.setUser(updatedAppointment.getUser());
            }
            if (updatedAppointment.getDuration() != null) {
                appointment.setDuration(updatedAppointment.getDuration());
            }
            if (updatedAppointment.getStartTime() != null) {
                appointment.setStartTime(updatedAppointment.getStartTime());
            }
            if (updatedAppointment.getEndTime() != null) {
                appointment.setEndTime(updatedAppointment.getEndTime());
            }
            if (updatedAppointment.getDate() != null) {
                appointment.setDate(updatedAppointment.getDate());
            }
            if (updatedAppointment.getStation() != null) {
                appointment.setStation(updatedAppointment.getStation());
            }
            if (updatedAppointment.getUser() != null) {
                appointment.setUser(updatedAppointment.getUser());
            }
            if (updatedAppointment.getRecord() != null) {
                appointment.setRecord(updatedAppointment.getRecord());
            }
            return appointmentRepository.save(appointment);
        } else {
            return null; // Appointment not found
        }
    }

    public void deleteAppointment(long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            return;
        }
        appointmentRepository.deleteById(appointmentId);
    }

    public List<Appointment> getAllAppointmentsByUser(long userId) {
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return appointmentRepository.findAppointmentsByUserId(userId);
    }
}
