package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Appointment;
import com.example.electric.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get All Appointment", description = "Get All Appointment",tags = {"Appointment"})
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{appointmentId}")
    @Operation(summary = "Get Appointment", description = "Get Appointment using ID",tags = {"Appointment"})
    public Optional<Appointment> getAppointmentById(@PathVariable("appointmentId") long appointmentId) {
        if (!appointmentService.getAppointmentById(appointmentId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get User's Appointments", description = "Get a list of User's Appointment from UserID",tags = {"Appointment"})
    public List<Appointment> getAllAppointmentsByUser(@PathVariable("userId") long userId) {
        return appointmentService.getAllAppointmentsByUser(userId);
    }


    @GetMapping("/station/{stationId}")
    @Operation(summary = "Get Stations' Appointment", description = "Get a list of stations' Appointment using StationID",tags = {"Appointment"})
    public List<Appointment> getAllAppointmentsAtStation(@PathVariable("stationId") long stationId) {
        return appointmentService.getAllAppointmentsAtStation(stationId);
    }

    @PostMapping
    @Operation(summary = "Add Appointment", description = "Add Appointment",tags = {"Appointment"})
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Appointment", description = "Update Appointment using ID",tags = {"Appointment"})
    public Appointment updateAppointment(@RequestBody Appointment updatedAppointment, @PathVariable("id") long id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return appointmentService.updateAppointment(updatedAppointment, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Appointment", description = "Delete Appointment using ID",tags = {"Appointment"})
    public void deleteAppointment(@PathVariable("id") long id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        appointmentService.deleteAppointment(id);
    }
}
