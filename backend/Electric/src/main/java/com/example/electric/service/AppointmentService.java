package com.example.electric.service;

import com.example.electric.model.Appointment;
import com.example.electric.model.Record;
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
            appointment.setUser(updatedAppointment.getUser());
            appointment.setDuration(updatedAppointment.getDuration());
            appointment.setStartTime(updatedAppointment.getStartTime());
            appointment.setEndTime(updatedAppointment.getEndTime());
            appointment.setDate(updatedAppointment.getDate());
            appointment.setStation(updatedAppointment.getStation());
            appointment.setUser(updatedAppointment.getUser());
            appointment.setRecord(updatedAppointment.getRecord());
            return appointmentRepository.save(appointment);
        } else {
            return null; // Record not found
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
