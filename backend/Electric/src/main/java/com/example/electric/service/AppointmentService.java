package com.example.electric.service;

import com.example.electric.exception.ExceedMaxManualApptException;
import com.example.electric.model.Appointment;
import com.example.electric.model.Station;
import com.example.electric.respository.AppointmentRepository;
import com.example.electric.respository.UserRepository;
import com.example.electric.service.inter.AppointmentServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public Appointment addAppointment(Appointment appointment) throws ExceedMaxManualApptException {

        appointment.setStatus("Active");
        appointmentRepository.save(appointment);

        return appointment;
    }
    public int checkManualAppointment(Appointment appointment){
        // Check if current Number of manual appointments exceeeded allowed manualAppointment
        if(appointment.isManualAppointment()){
            long user_id = appointment.getUser().getId();
            int numOfExistingManualAppt = appointmentRepository.findActiveManualApptByUserId(user_id).size();
            if(numOfExistingManualAppt >= appointment.MAX_MANUALAPPT_ALLOWED){
                return numOfExistingManualAppt;
            }
        }
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
