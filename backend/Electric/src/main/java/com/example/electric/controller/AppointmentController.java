package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Appointment;
import com.example.electric.model.Station;
import com.example.electric.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(summary = "Get All Appointment", description = "Get All Appointment",tags = {"Appointment"})
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
    @Operation(summary = "Get Appointment", description = "Get Appointment using ID", tags = {"Appointment"})
    public Appointment getAppointmentById(@PathVariable("appointmentId") long appointmentId) {
        return appointmentService.getAppointmentById(appointmentId)
                .orElseThrow(() -> new ObjectNotFoundException(ErrorCode.E1002));
    }

    /**
     * Retrieve a list of appointments associated with a user.
     *
     * This endpoint allows the retrieval of a list of appointments that are associated with
     * a specific user, identified by their UserID. It provides access to the user's appointments.
     *
     * @param userId The unique identifier (UserID) of the user.
     * @return A list of appointments associated with the specified user.
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get User's Appointments", description = "Get a list of User's Appointment from UserID",tags = {"Appointment"})
    public List<Appointment> getAllAppointmentsByUser(@PathVariable("userId") long userId) {
        return appointmentService.getAllAppointmentsByUser(userId);
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
    @Operation(summary = "Get Stations' Appointment", description = "Get a list of stations' Appointment using StationID",tags = {"Appointment"})
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
    @Operation(summary = "Add Appointment", description = "Add Appointment",tags = {"Appointment"})
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
    @Operation(summary = "Update Appointment", description = "Update Appointment using ID",tags = {"Appointment"})
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
    @Operation(summary = "Delete Appointment", description = "Delete Appointment using ID",tags = {"Appointment"})
    public void deleteAppointment(@PathVariable("id") long id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        appointmentService.deleteAppointment(id);
    }

    @PostMapping ("/available")
    @Operation(summary = "Get Available Stations", description = "Get a list of available stations",tags = {"Appointment"})
    public List<Station> getAvailableStations(@RequestBody String test) {
        String start = "07:00:00";
        String end = "08:00:00";
        String date = "2023-10-10";
        return appointmentService.getAvailableStationsAndChargers(start, end, date);
    }
}
