package com.example.electric.service;

import com.example.electric.model.*;
import com.example.electric.respository.AppointmentRepository;
import com.example.electric.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.electric.exception.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public Appointment addAppointment(Appointment appointment) throws ExceedMaxManualApptException {
        // Check if current Number of manual appointments exceeeded allowed manualAppointment 
        if(appointment.isManualAppointment()){
            long user_id = appointment.getUser().getId();
            int numOfExistingManualAppt = appointmentRepository.findActiveManualApptByUserId(user_id).size();
            if(numOfExistingManualAppt >= appointment.MAX_MANUALAPPT_ALLOWED){
                throw new ExceedMaxManualApptException(numOfExistingManualAppt, Appointment.MAX_MANUALAPPT_ALLOWED);
            }
        }
        appointment.setStatus("Active");
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
            if (updatedAppointment.getStatus() != null) {
                appointment.setStatus(updatedAppointment.getStatus());
            }
            if(updatedAppointment.isManualAppointment() != appointment.isManualAppointment()){
                appointment.setManualAppointment(updatedAppointment.isManualAppointment());
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

    public List<Appointment> getAllActiveManualAppointmentByUser(long userId) {
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return appointmentRepository.findActiveManualApptByUserId(userId);
        // return appointmentRepository.findByUser_IdAndManualAppointmentAndstatus(userId,true,"Active");
    }


    public List<Station> getAvailableStationsAndChargers(String startTime, String endTime, String dateNow) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        LocalDate date = LocalDate.parse(dateNow);
        return appointmentRepository.findAvailableStationsAndChargers(start, end, date);
    }

    public Appointment completedAppointment(Appointment updatedAppointment, long id){
    // Check for null values
    if (updatedAppointment == null) {
        throw new IllegalArgumentException("Updated appointment cannot be null");
    }

    // Set status to completed
    updatedAppointment.setStatus("completed");

    // Update the appointment
    Appointment completedAppointment = updateAppointment(updatedAppointment, id);

    return completedAppointment;
    }
}
