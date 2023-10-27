package com.example.electric.service;

//import com.example.electric.dto.AppointmentDto;

import com.example.electric.exception.CanCreateBookingException;
import com.example.electric.exception.CannotCreateBookingException;
import com.example.electric.exception.ExceedMaxManualApptException;
import com.example.electric.model.Appointment;
import com.example.electric.model.Station;
import com.example.electric.respository.AppointmentRepository;
import com.example.electric.respository.UserRepository;
import com.example.electric.service.inter.AppointmentServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements AppointmentServiceInter {
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

    /**
     * Add New Appointment
     *
     * This endpoint allows the addition of a new appointment to the system. The appointment is provided
     * as an object in the request, and the appointment's status is set to 'Active' upon creation.
     *
     * @param appointment The appointment to be added to the system.
     * @return The newly created appointment with the 'Active' status.
     * @throws ExceedMaxManualApptException If adding a manual appointment exceeds the maximum limit.
     */

    public Appointment addAppointment(Appointment appointment) throws ExceedMaxManualApptException {
        // Set the status of the appointment to 'Active'.
        appointment.setStatus("Active");
        // Save the appointment in the appointment repository.
        appointmentRepository.save(appointment);

        return appointment;
    }

    /**
     * Check Manual Appointment Limit
     *
     * This method checks if a user can create a new manual appointment based on their current number of
     * active manual appointments. If the user has reached or exceeded the allowed limit of manual appointments,
     * it returns the number of existing manual appointments. Otherwise, it returns -1 to indicate that a new
     * manual appointment can be created.
     *
     * @param appointment The appointment for which the manual appointment limit is to be checked.
     * @return The number of existing manual appointments if the limit is reached, or -1 if a new manual appointment is allowed.
     */
    public int checkManualAppointment(Appointment appointment){
        // Check if current Number of manual appointments exceeeded allowed manualAppointment
        if(appointment.isManualAppointment()){
            long user_id = appointment.getUser().getId();

            // Find the number of existing active manual appointments for the user.

            int numOfExistingManualAppt = appointmentRepository.findActiveManualApptByUserId(user_id).size();
            // Check if the number of existing manual appointments exceeds the allowed limit.

            if(numOfExistingManualAppt >= appointment.MAX_MANUALAPPT_ALLOWED){
                return numOfExistingManualAppt;
            }
        }
        // Return -1 to indicate that a new manual appointment can be created.
        return -1;
    }

//    public Appointment autobookAppointment(double latitude, double longitude, String startTime, String endTime, String dateNow, Car car, String userEmail) {
//        //Find station
//        Station closestStation = voronoiService.findClosestStation(latitude,longitude,startTime,endTime,dateNow);
//
//        //Find date and time
//        LocalTime start = LocalTime.parse(startTime);
//        LocalTime end = LocalTime.parse(endTime);
//        Duration duration = Duration.between(start,end);
//        LocalTime timeBetween = LocalTime.MIDNIGHT
//                .plusHours(duration.toHours())
//                .plusMinutes(duration.toMinutesPart())
//                .plusSeconds(duration.toSecondsPart());
//
//        //Find cost
//        Double cost = Double.parseDouble(distanceMatrixService.calculateCostOfCharging(car));
//
//        //Find user
//        User user = userRepository.findUserByEmail(userEmail);
//
//        //Find charger
//        List<Charger> chargers = closestStation.getChargers();
//
//        //Appointment object
//        Appointment appointment = new Appointment(1,
//                Time.valueOf(timeBetween),
//                Time.valueOf(start),
//                Time.valueOf(end),
//                java.sql.Date.valueOf(dateNow),
//                cost,
//                "Active",
//                closestStation,
//                user,
//                chargers.get(0));
//
//        return appointmentRepository.save(appointment);
//    }

    /**
     * Update Appointment
     *
     * This method allows the update of an existing appointment based on the provided updated appointment details.
     * It identifies the appointment by its unique identifier (ID) and updates its fields as needed.
     *
     * @param updatedAppointment The updated appointment details.
     * @param id The unique identifier of the appointment to be updated.
     * @return The updated appointment after the changes are applied, or null if the appointment is not found.
     */

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
    // public Appointment updateAppointment(AppointmentDto updatedAppointment, long id){
    //     Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
    //     if (optionalAppointment.isPresent()) {
    //         Appointment appointment = optionalAppointment.get();
    //         mapper.updateAppointmentFromDto(updatedAppointment, appointment);
    //         return appointmentRepository.save(appointment);

    //     }else{
    //         //appointment not found
    //         return null;
    //     }
    // }

    /**
     * Delete Appointment
     *
     * This method allows the deletion of an appointment based on its unique identifier (ID).
     * If the appointment with the specified ID is found, it is deleted from the system.
     *
     * @param appointmentId The unique identifier of the appointment to delete.
     */
    public void deleteAppointment(long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            return;
        }
        appointmentRepository.deleteById(appointmentId);
    }

    /**
     * Get All Appointments by User
     *
     * This method retrieves a list of all appointments associated with a specific user based on their
     * unique user identifier. If the user with the given ID is not found, it returns null.
     *
     * @param userId The unique identifier of the user.
     * @return A list of appointments for the user or null if the user is not found.
     */
    public List<Appointment> getAllAppointmentsByUser(long userId) {
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return appointmentRepository.findAppointmentsByUserId(userId);
    }

    /**
     * Get All Appointments by User
     *
     * This method retrieves a list of all appointments associated with a specific user based on their
     * unique user identifier. If the user with the given ID is not found, it returns null.
     *
     * @param userId The unique identifier of the user.
     * @return A list of appointments for the user or null if the user is not found.
     */
    public List<Appointment> getAllActiveManualAppointmentByUser(long userId) {
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return appointmentRepository.findActiveManualApptByUserId(userId);
        // return appointmentRepository.findByUser_IdAndManualAppointmentAndstatus(userId,true,"Active");
    }

    /**
     * Get Available Stations and Chargers
     *
     * This method retrieves a list of available charging stations and chargers based on the provided start time,
     * end time, and date. It is used to find available stations for making appointments.
     *
     * @param startTime The start time in HH:MM format.
     * @param endTime The end time in HH:MM format.
     * @param dateNow The date in YYYY-MM-DD format.
     * @return A list of available stations and chargers.
     */
    public List<Station> getAvailableStationsAndChargers(String startTime, String endTime, String dateNow) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        LocalDate date = LocalDate.parse(dateNow);
        return appointmentRepository.findAvailableStationsAndChargers(start, end, date);
    }

    /**
     * Mark Appointment as Completed
     *
     * This method is used to mark an appointment as completed. It updates the appointment's status
     * to 'completed' and triggers an external action to stop a car (provided a car ID). The updated
     * appointment is then returned.
     *
     * @param updatedAppointment The updated appointment details.
     * @param id The unique identifier of the appointment to update.
     * @param carId The unique identifier of the car associated with the appointment.
     * @return The completed appointment with the 'completed' status.
     * @throws IllegalArgumentException If the updated appointment is null.
     */
    public Appointment completedAppointment(Appointment updatedAppointment, long id,long carId){
    // Check for null values
    if (updatedAppointment == null) {
        throw new IllegalArgumentException("Updated appointment cannot be null");
    }

    String URL = "http://13.236.9.86:9091/car/stop/" + carId;
    String obj =  new RestTemplate().getForObject(URL, String.class);

    // Set status to completed
    updatedAppointment.setStatus("completed");
    // Update the appointment
    Appointment completedAppointment = updateAppointment(updatedAppointment, id);

    return completedAppointment;
    }

    /**
     * Cancel Appointment
     *
     * This method is used to cancel an appointment. It updates the appointment's status to 'cancelled'
     * and triggers an external action to cancel a payment (provided a transaction ID). The updated
     * appointment is then returned.
     *
     * @param appointment The appointment to be cancelled.
     * @param id The unique identifier of the appointment to update.
     * @return A message indicating the status of the payment cancellation.
     * @throws IllegalArgumentException If the provided appointment is null.
     */

    public String cancelAppointment(Appointment appointment, long id){
        // Check for null values
        if (appointment == null) {
            throw new IllegalArgumentException("Updated appointment cannot be null");
        }

        appointment.setStatus("cancelled");
        String transactionId = appointment.getTransactionId();

        String URL = "http://3.26.230.241:9090/payment/cancel/" + transactionId;
        String obj =  new RestTemplate().getForObject(URL, String.class);
        updateAppointment(appointment, id);

        return obj;
    }

    /**
     * Start Appointment for Charging
     *
     * This method is used to start an appointment for charging. It updates the appointment's status
     * to 'charging' and triggers an external action to start a car (provided a car ID). The updated
     * appointment is then returned.
     *
     * @param appointment The appointment to be started.
     * @param id The unique identifier of the appointment to update.
     * @param carId The unique identifier of the car associated with the appointment.
     * @return A message indicating the status of the car start operation.
     * @throws IllegalArgumentException If the provided appointment is null.
     */
    public String startAppointment(Appointment appointment, long id, long carId){
        // Check for null values
        if (appointment == null) {
            throw new IllegalArgumentException("Updated appointment cannot be null");
        }

        appointment.setStatus("charging");
        appointment.setStartTime(Time.valueOf(LocalTime.now()));

        String URL = "http://13.236.9.86:9091/car/start/" + carId;
        String obj =  new RestTemplate().getForObject(URL, String.class);
        updateAppointment(appointment, id);

        return obj;
    }

    /**
     * Check Upcoming Appointment Eligibility
     *
     * This method checks the eligibility of a user to create a new booking at a specified station and charger.
     * It considers the existing active appointments, their timings, and the current user's eligibility to create
     * a booking. It returns an appointment if the user is eligible to create a booking, or throws exceptions to
     * indicate eligibility or ineligibility.
     *
     * @param stationId The unique identifier of the charging station.
     * @param chargerId The unique identifier of the charger.
     * @param userId The unique identifier of the user.
     * @return The upcoming appointment if the user is eligible to create a booking.
     * @throws CanCreateBookingException If the user is eligible to create a booking.
     * @throws CannotCreateBookingException If the user is ineligible to create a booking.
     */
    public Appointment checkUpcomingAppointment(long stationId, long chargerId, long userId){
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        //return list of active appointment at charger 
        List<Appointment> upcomingAppointment = appointmentRepository.findAppointmentsByStationIdAndChargerIdAndStatus(stationId, chargerId, "Active", currentDate);
        if(upcomingAppointment.size() == 0){
            throw new CanCreateBookingException();
        }
        upcomingAppointment.sort((appointment1, appointment2) -> appointment1.getStartTime().compareTo(appointment2.getStartTime()));

        Appointment firstAppointment = upcomingAppointment.get(0);
        LocalDate firstApptDate = firstAppointment.getDate().toLocalDate();
        LocalTime firstApptTime = firstAppointment.getStartTime().toLocalTime();

        //Check if User has an appointment in the next 15 minute, if yes return the appointment
        if(firstApptDate.equals(currentDate) && firstAppointment.getUser().getId() == userId){
            return firstAppointment;
        }
        // //check if 
        // else if(firstApptDate == currentDate && firstAppointment.getUser().getId() != user.getId() && currentTime.until(firstApptTime, ChronoUnit.MINUTES) < 15){
        //         throw new CannotCreateBookingException(firstApptTime);
        // }

        //else if next appointment has no booking booking in next 30 mins, return canCreatebooking exception, but need to leave
        //within time to next appointment. 
        else if (firstApptDate.equals(currentDate)&& currentTime.until(firstApptTime, ChronoUnit.MINUTES) >= 20){
            throw new CanCreateBookingException(firstApptTime);
        }
        //else return CanCreateBookingException.
        else {
            throw new CannotCreateBookingException(firstApptTime);
        }
    }

}
