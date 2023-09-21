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

    /**
     * Retrieve a list of all appointments.
     *
     * This endpoint retrieves a list of all appointments currently stored in the system.
     * If no appointments are available, an empty list will be returned.
     *
     * @return A list of appointments, which may be empty if no appointments are found.
     */
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    /**
     * Retrieve an appointment by its unique identifier.
     *
     * This endpoint retrieves an appointment from the system based on its unique identifier (ID).
     * If an appointment with the specified ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param appointmentId The unique identifier of the appointment to retrieve.
     * @return An Optional containing the retrieved appointment, or an empty Optional if not found.
     * @throws ObjectNotFoundException If no appointment with the given ID is found.
     */
    @GetMapping("/{appointmentId}")
    public Optional<Appointment> getAppointmentById(@PathVariable("appointmentId") long appointmentId) {
        if (!appointmentService.getAppointmentById(appointmentId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return appointmentService.getAppointmentById(appointmentId);
    }

    /**
     * Retrieve a list of all appointments at a specific station.
     *
     * This endpoint retrieves a list of all appointments associated with a particular station
     * identified by its unique identifier (stationId). If no appointments are found for the
     * specified station, an empty list will be returned.
     *
     * @param stationId The unique identifier of the station to retrieve appointments for.
     * @return A list of appointments at the specified station, which may be empty if no appointments are found.
     */
    @GetMapping("/station/{stationId}")
    public List<Appointment> getAllAppointmentsAtStation(@PathVariable("stationId") long stationId) {
        return appointmentService.getAllAppointmentsAtStation(stationId);
    }

    /**
     * Add a new appointment to the system.
     *
     * This endpoint allows the addition of a new appointment to the system. The provided
     * appointment object should contain the necessary details for creating the appointment.
     *
     * @param appointment The appointment object to be added.
     * @return The newly created appointment.
     */
    @PostMapping
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    /**
     * Update an existing appointment with the provided information.
     *
     * This endpoint allows the update of an existing appointment identified by its unique
     * identifier (ID). The provided updatedAppointment object should contain the updated details
     * for the appointment. If an appointment with the specified ID is not found, it will result in
     * an ObjectNotFoundException.
     *
     * @param id The unique identifier of the appointment to update.
     * @param updatedAppointment The updated appointment object containing new information.
     * @return The updated appointment.
     * @throws ObjectNotFoundException If no appointment with the given ID is found.
     */
    @PutMapping("/{id}")
    public Appointment updateAppointment(@RequestBody Appointment updatedAppointment, @PathVariable("id") long id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return appointmentService.updateAppointment(updatedAppointment, id);
    }

    /**
     * Delete an appointment by its unique identifier.
     *
     * This endpoint allows the deletion of an appointment from the system based on its unique
     * identifier (ID). If an appointment with the specified ID is not found, it will result in
     * an ObjectNotFoundException.
     *
     * @param id The unique identifier of the appointment to delete.
     * @throws ObjectNotFoundException If no appointment with the given ID is found.
     */
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable("id") long id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        appointmentService.deleteAppointment(id);
    }
}
