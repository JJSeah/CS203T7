package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Appointment;
import com.example.electric.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{appointmentId}")
    public Optional<Appointment> getAppointmentById(@PathVariable("appointmentId") long appointmentId) {
        if (!appointmentService.getAppointmentById(appointmentId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping("/user/{userId}")
    public List<Appointment> getAllAppointmentsByUser(@PathVariable("userId") long userId) {
        return appointmentService.getAllAppointmentsByUser(userId);
    }


    @GetMapping("/station/{stationId}")
    public List<Appointment> getAllAppointmentsAtStation(@PathVariable("stationId") long stationId) {
        return appointmentService.getAllAppointmentsAtStation(stationId);
    }

    @PostMapping
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@RequestBody Appointment updatedAppointment, @PathVariable("id") long id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return appointmentService.updateAppointment(updatedAppointment, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable("id") long id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        appointmentService.deleteAppointment(id);
    }
}
