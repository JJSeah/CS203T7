package com.example.electric.service;

import com.example.electric.exception.CanCreateBookingException;
import com.example.electric.model.Appointment;
import com.example.electric.model.Station;
import com.example.electric.model.User;
import com.example.electric.respository.AppointmentRepository;
import com.example.electric.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    public void testGetAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), 0, null, null));
        appointments.add(new Appointment(2L, new Time(0), new Time(0), new Time(0), new Date(0), 0, null, null));
        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointments();

        assertEquals(appointments, result);
    }

    @Test
    public void testGetAppointmentById() {
        long id = 1L;
        Appointment appointment = new Appointment(id, new Time(0), new Time(0), new Time(0), new Date(0), 0, null,
                null);
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));

        Optional<Appointment> result = appointmentService.getAppointmentById(id);

        assertEquals(Optional.of(appointment), result);
    }

    @Test
    public void testGetAppointmentByIdNonExistent() {
        long id = 1L;
        when(appointmentRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Appointment> result = appointmentService.getAppointmentById(id);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testGetAllAppointmentsAtStation() {
        long stationId = 1L;
        List<Appointment> appointments = new ArrayList<>();
        appointments
                .add(new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), 0, new Station(), null));
        appointments
                .add(new Appointment(2L, new Time(0), new Time(0), new Time(0), new Date(0), 0, new Station(), null));
        when(appointmentRepository.findAppointmentsByStationId(stationId)).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointmentsAtStation(stationId);

        assertEquals(appointments, result);
    }

    @Test
    public void testAddAppointment() {
        Appointment appointmentToAdd = new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), 0,
                new Station(), null);
        when(appointmentRepository.save(appointmentToAdd)).thenReturn(
                new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), 0, new Station(), null));

        Appointment result = appointmentService.addAppointment(appointmentToAdd);

        verify(appointmentRepository, times(1)).save(appointmentToAdd);
        assertNotNull(result.getId());
    }

    @Test
    public void testUpdateAppointment() {
        // Create a sample appointment for testing
        long appointmentId = 1L;
        Appointment existingAppointment = new Appointment(appointmentId, new Time(0), new Time(0), new Time(0),
                new Date(0), 0, new Station(), null);

        // Modify the appointment as needed
        existingAppointment.setStatus("Completed");

        // Mock the repository's findById method
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(existingAppointment));
        when(appointmentRepository.save(existingAppointment)).thenReturn(existingAppointment);

        // Call the updateAppointment method
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setStatus("Updated Status");

        // Invoke the updateAppointment method
        Appointment result = appointmentService.updateAppointment(updatedAppointment, appointmentId);

        // Verify that the save method of the repository was called once with the
        // updated appointment
        verify(appointmentRepository).save(existingAppointment);

        // Check if the result matches the updated appointment's status
        assertEquals(updatedAppointment.getStatus(), result.getStatus());
    }

    @Test
    public void testUpdateAppointmentNonExistent() {
        // Create a sample appointment for testing
        long nonExistentAppointmentId = 999L;

        // Mock the repository's findById method for a non-existent appointment
        when(appointmentRepository.findById(nonExistentAppointmentId)).thenReturn(Optional.empty());

        // Call the updateAppointment method with an appointment that doesn't exist
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setStatus("Updated Status");

        // Invoke the updateAppointment method
        Appointment result = appointmentService.updateAppointment(updatedAppointment, nonExistentAppointmentId);

        // Verify that the repository's save method is not called
        verify(appointmentRepository, never()).save(updatedAppointment);

        // Check if the result is null as the appointment doesn't exist
        assertNull(result);
    }

    @Test
    public void testDeleteAppointment() {
        long appointmentId = 1L;
        when(appointmentRepository.existsById(appointmentId)).thenReturn(true);
        doNothing().when(appointmentRepository).deleteById(appointmentId);

        appointmentService.deleteAppointment(appointmentId);

        verify(appointmentRepository, times(1)).existsById(appointmentId);
        verify(appointmentRepository, times(1)).deleteById(appointmentId);
    }

    @Test
    public void testDeleteAppointmentNonExistent() {
        long appointmentId = 1L;
        when(appointmentRepository.existsById(appointmentId)).thenReturn(false);

        appointmentService.deleteAppointment(appointmentId);

        verify(appointmentRepository, times(1)).existsById(appointmentId);
        verify(appointmentRepository, never()).deleteById(appointmentId);
    }

    @Test
    public void testGetAllAppointmentsByUser() {
        long userId = 1L;
        User user = new User(userId, "John");
        List<Appointment> appointments = new ArrayList<>();
        appointments
                .add(new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), 0, new Station(), null));
        appointments
                .add(new Appointment(2L, new Time(0), new Time(0), new Time(0), new Date(0), 0, new Station(), null));

        when(userRepository.existsById(userId)).thenReturn(true);
        when(appointmentRepository.findAppointmentsByUserId(userId)).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointmentsByUser(userId);

        assertEquals(appointments, result);
    }

    @Test
    public void testGetAllAppointmentsByUserNonExistent() {
        long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);
        List<Appointment> result = appointmentService.getAllAppointmentsByUser(userId);

        assertNull(result);
        verify(appointmentRepository, never()).findAppointmentsByUserId(userId);
    }

    @Test
    public void testCheckManualAppointment_WithinLimit() {
        // Create a mock for the Appointment
        Appointment appointment = new Appointment(2L);
        appointment.setManualAppointment(true);
        appointment.setUser(new User(1L, "justin"));

        when(appointmentRepository.findActiveManualApptByUserId(1L)).thenReturn(List.of(new Appointment(1L)));

        int result = appointmentService.checkManualAppointment(appointment);

        assertEquals(-1, result);
        verify(appointmentRepository, times(1)).findActiveManualApptByUserId(1l);
    }

    @Test
    public void testCheckManualAppointment_OutOfLimit() {
        // Create a mock for the Appointment
        Appointment appointment = new Appointment(4L);
        appointment.setManualAppointment(true);
        appointment.setUser(new User(1L, "justin"));

        when(appointmentRepository.findActiveManualApptByUserId(1L))
                .thenReturn(List.of(new Appointment(1L), new Appointment(2L), new Appointment(3L)));

        int result = appointmentService.checkManualAppointment(appointment);

        assertEquals(3, result);
        verify(appointmentRepository, times(1)).findActiveManualApptByUserId(1L);
    }

    @Test
    public void testGetAllActiveManualAppointmentByUser_UserNotExists() {
        long nonExistentUserId = 999L;

        when(userRepository.existsById(nonExistentUserId)).thenReturn(false);

        List<Appointment> result = appointmentService.getAllActiveManualAppointmentByUser(nonExistentUserId);

        assertNull(result);
        verify(appointmentRepository, never()).findActiveManualApptByUserId(nonExistentUserId);
    }

    @Test
    public void testGetAllActiveManualAppointmentByUser_UserExists() {
        long existingUserId = 123L;
        List<Appointment> mockAppointments = Arrays.asList(
                new Appointment(/* construct your Appointment object with necessary details */),
                new Appointment(/* construct another Appointment object */)
        // Add more if needed
        );

        when(userRepository.existsById(existingUserId)).thenReturn(true);

        when(appointmentRepository.findActiveManualApptByUserId(existingUserId)).thenReturn(mockAppointments);

        List<Appointment> result = appointmentService.getAllActiveManualAppointmentByUser(existingUserId);

        assertEquals(mockAppointments.size(), result.size());
    }

    @Test
    public void testGetAvailableStationsAndChargers() {
        String startTime = "09:00";
        String endTime = "12:00";
        String dateNow = "2023-11-03";

        List<Station> availStation = List.of(new Station(1L, "station1"), new Station(2L, "station2"));

        when(appointmentRepository.findAvailableStationsAndChargers(
                LocalTime.parse(startTime),
                LocalTime.parse(endTime),
                LocalDate.parse(dateNow))).thenReturn(availStation);

        List<Station> result = appointmentService.getAvailableStationsAndChargers(startTime, endTime, dateNow);

        assertNotNull(result);
        assertEquals(availStation.size(), result.size());
    }

        @Test
    public void testCompletedAppointmentWithNullUpdatedAppointment() {
        long id = 123L;
        long carId = 456L;

        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.completedAppointment(null, id, carId);
        });
    }

        @Test
    public void testCheckUpcomingAppointmentWithNoActiveAppointments() {
        long chargerId = 123L;
        long userId = 456L;

        when(appointmentRepository.findAppointmentsByChargerIdAndStatus(chargerId, "Active", LocalDate.now())).thenReturn(new ArrayList<>());

        assertThrows(CanCreateBookingException.class, () -> {
            appointmentService.checkUpcomingAppointment(chargerId, userId);
        });
    }

}