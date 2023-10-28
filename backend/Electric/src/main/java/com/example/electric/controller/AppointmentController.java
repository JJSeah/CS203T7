package com.example.electric.controller;

//import com.example.electric.dto.AppointmentDto;
import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ExceedMaxManualApptException;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.*;
import com.example.electric.service.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private VoronoiService voronoiService;

    @Autowired
    private CarService carService;

    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @Autowired
    private StationService stationService;

    @Autowired
    private ChargerService chargerService;

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
    public Appointment getAppointmentById(@PathVariable("appointmentId") @NotNull long appointmentId) {
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
    public List<Appointment> getAllAppointmentsByUser(@PathVariable("userId") @NotNull long userId) {
        return appointmentService.getAllAppointmentsByUser(userId);
    }

    @GetMapping("/manual/user/{userId}")
    @Operation(summary = "Get User's Manual Active Appointments", description = "Get a list of User's Manual Active Appointment from UserID",tags = {"Manual Active Appointment"})
    public List<Appointment> getActiveManualAppointmentByUser(@PathVariable("userId") @NotNull long userId) {
        return appointmentService.getAllActiveManualAppointmentByUser(userId);
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
    public List<Appointment> getAllAppointmentsAtStation(@PathVariable("stationId") @NotNull long stationId) {
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
     * @throws ExceedMaxManualApptException
     */
    @PostMapping
    @Operation(summary = "Add  Appointment", description = "Add  Appointment",tags = {"Appointment"})
    public Appointment addAppointment(@RequestBody Appointment appointment){
        //Find id for station and charger
        long userId = appointment.getUser().getId();
        long stationId = appointment.getStation().getId();
        long chargerId = appointment.getCharger().getId();
        long carId = appointment.getCar().getId();

        //Find user, station, charger
        User user = userService.getUserById(userId);
        Station station = stationService.getStationById(stationId);
        Charger charger = chargerService.getChargerById(chargerId).orElse(null);
        Car car = carService.getCarById(carId).orElse(null);

        if (user == null || station == null || charger == null || car == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        // Check if current Number of manual appointments exceeded allowed manualAppointment
        appointment.setUser(user);
        appointment.setStation(station);
        appointment.setCharger(charger);
        appointment.setCar(car);

        int numOfExistingManualAppt = appointmentService.checkManualAppointment(appointment);
        if(numOfExistingManualAppt >= 0){
            throw new ExceedMaxManualApptException(numOfExistingManualAppt, Appointment.MAX_MANUALAPPT_ALLOWED);
        }
            return appointmentService.addAppointment(appointment);

    }

    /**
     * Add a new appointment to the system.
     *
     * This endpoint allows the addition of a new appointment through auto booking feature of the system. The provided
     * appointment object should contain the necessary details for creating the appointment.
     *
     * @param appointment The appointment object to be added.
     * @param carId The unique identifier of car to be charged.
     * @return The newly created appointment.
     */
    @PostMapping("/auto/{carId}")
    @Operation(summary = "Add Auto Appointment", description = "Add Auto Appointment",tags = {"Appointment"})
    public Appointment addAppointment(@RequestBody Appointment appointment, @PathVariable long carId) {
        double latitude = appointment.getStation().getLatitude();
        double longitude = appointment.getStation().getLongitude();

        String startTime = appointment.getStartTime().toString();
        String endTime = appointment.getEndTime().toString();
        String date = appointment.getDate().toString();

        Car car = carService.getCarById(carId).orElse(null);
        String userEmail = appointment.getUser().getEmail();
        return voronoiService.autobookAppointment(latitude, longitude,startTime, endTime, date,car,userEmail);
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
     * Complete an existing appointment with the provided information.
     *
     * This endpoint allows the update of an existing appointment identified by its unique
     * identifier (ID). The provided CompletedAppointment object should contain the Completed details
     * for the appointment. If an appointment with the specified ID is not found, it will result in
     * an ObjectNotFoundException.
     *
     * @param id The unique identifier of the appointment to update.
     * @return The completed appointment.
     * @throws ObjectNotFoundException If no appointment with the given ID is found.
     */
    @PutMapping("/completed/{id}/{cardId}")
    @Operation(summary = "Complete Appointment", description = "Complete Appointment using ID",tags = {"Appointment"})
    public Appointment completeAppointment(@PathVariable("id") long id, @PathVariable("cardId") long cardId) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        else {
            Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
            if (!appointment.get().getStatus().equals("charging")) {
                throw new ObjectNotFoundException(ErrorCode.E1002);
            }
            //updating end time
            appointment.get().setEndTime(Time.valueOf(LocalTime.now()));

//            Get duration of charging in minutes
            long duration = (appointment.get().getEndTime().getTime() - appointment.get().getStartTime().getTime())/(60 * 1000);

            double cost = DistanceMatrixService.calculateCostOfCharging(duration);
            appointment.get().setCost(cost);
            try {
                String res = cardService.processPayment(appointment.get().getUser().getId(), cost, cardId);
                appointment.get().setTransactionId(res);
            } catch (Exception e) {
                throw new ObjectNotFoundException(ErrorCode.E2002);
            }

            return appointmentService.completedAppointment(appointment.get(), id, appointment.get().getCar().getId());
        }

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
        if (appointmentService.getAppointmentById(id).get().getStatus().equals("Active")) {
            throw new ObjectNotFoundException(ErrorCode.E1001);
        }
        appointmentService.deleteAppointment(id);
    }

    /**
     * Start Appointment to Charging
     *
     * This endpoint allows the initiation of an appointment for charging. It is designed to be used by
     * authorized users to activate an appointment by specifying its unique identifier (ID).
     *
     * @param id The unique identifier of the appointment to start.
     * @return A message indicating the success of the appointment activation.
     * @throws ObjectNotFoundException If no appointment with the given ID is found or if the appointment is not in an 'Active' state.
     */
    @GetMapping("/start/{id}")
    @Operation(summary = "Start Appointment", description = "Start Appointment using ID",tags = {"Appointment"})
    public String startAppointment(@PathVariable("id") long id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        if (!appointmentService.getAppointmentById(id).get().getStatus().equals("Active")) {
            throw new ObjectNotFoundException(ErrorCode.E1001);
        }
        else {
            Appointment appointment = appointmentService.getAppointmentById(id).get();
            return appointmentService.startAppointment(appointment, id, appointment.getCar().getId());
        }
    }

    /**
     * Cancel Appointment
     *
     * This endpoint allows the cancellation of an existing appointment. Authorized users can use this
     * operation by specifying the unique identifier (ID) of the appointment they wish to cancel.
     *
     * @param id The unique identifier of the appointment to cancel.
     * @return A message indicating the success of the appointment cancellation.
     * @throws ObjectNotFoundException If no appointment with the given ID is found or if the appointment is not in an 'Active' state.
     */
    @PutMapping("/cancel/{id}")
    @Operation(summary = "Cancel Appointment", description = "Cancel Appointment using ID",tags = {"Appointment"})
    public String cancelAppointment(@PathVariable("id") long id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        else {
            Appointment appointment = appointmentService.getAppointmentById(id).get();
            if (!appointment.getStatus().equals("Active")) {
                throw new ObjectNotFoundException(ErrorCode.E1001);
            }
            return appointmentService.cancelAppointment(appointment, id);
        }

    }

    /**
     * Get Available Stations
     *
     * This endpoint retrieves a list of available charging stations for a specified appointment based on the
     * provided appointment details, including start time, end time, and date.
     *
     * @param appointment The appointment details, including start time, end time, and date.
     * @return A list of available charging stations.
     */

    @PostMapping ("/available")
    @Operation(summary = "Get Available Stations", description = "Get a list of available stations",tags = {"Appointment"})
    public List<Station> getAvailableStations(@RequestBody Appointment appointment) {
        String startTime = appointment.getStartTime().toString();
        String endTime = appointment.getEndTime().toString();
        String date = appointment.getDate().toString();

        return appointmentService.getAvailableStationsAndChargers(startTime, endTime, date);
    }

    /**
     * QR Code Checker
     *
     * This endpoint checks for the upcoming appointment status for a user at a specified station and charger.
     * If the user has a valid appointment within the next 20 minutes, it returns the appointment details.
     * If the user doesn't have an appointment in the upcoming 20 minutes, they are allowed to create a new appointment.
     * If none of these conditions are met, it returns a "cannotBookAppointment" response.
     *
     * @param userId The unique identifier of the user.
     * @return The upcoming appointment details or a "cannotBookAppointment" response.
     */

    @GetMapping("/checkComingAppt/charger1/{userID}")
    @Operation(summary = "QR code checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"Appointment"})
    public Appointment checkUpcomingAppointment(@PathVariable("userID") long userId){
        long chargerId = 1;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }



    
}
