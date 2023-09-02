package com.example.electric.controller;

import com.example.electric.model.Appointment;
import com.example.electric.service.AppointmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{appointmentId}")
    public Optional<Appointment> getAppointmentById(@PathVariable("appointmentId") long appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping("/station/{stationId}")
    public List<Appointment> getAllAppointmentsAtStation(@PathVariable("stationId") long stationId) {
        return appointmentService.getAllAppointmentsAtStation(stationId);
    }

    @PostMapping("/add")
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping("/update/{id}")
    public Appointment updateAppointment(@RequestBody Appointment updatedAppointment, @PathVariable("id") long id) {
        return appointmentService.updateAppointment(updatedAppointment, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppointment(@PathVariable("id") long id) {
        appointmentService.deleteAppointment(id);
    }
}
