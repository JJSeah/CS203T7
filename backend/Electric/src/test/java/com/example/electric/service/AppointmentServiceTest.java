package com.example.electric.service;

import com.example.electric.model.Appointment;
import com.example.electric.model.Station;
import com.example.electric.model.User;
import com.example.electric.respository.AppointmentRepository;
import com.example.electric.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        appointments.add(new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), null, null, null));
        appointments.add(new Appointment(2L, new Time(0), new Time(0), new Time(0), new Date(0), null, null, null));
        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointments();

        assertEquals(appointments, result);
    }

    @Test
    public void testGetAppointmentById() {
        long id = 1L;
        Appointment appointment = new Appointment(id, new Time(0), new Time(0), new Time(0), new Date(0), null, null, null);
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
        appointments.add(new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), new Station(), null, null));
        appointments.add(new Appointment(2L, new Time(0), new Time(0), new Time(0), new Date(0), new Station(), null, null));
        when(appointmentRepository.findAppointmentsByStationId(stationId)).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointmentsAtStation(stationId);

        assertEquals(appointments, result);
    }

    @Test
    public void testAddAppointment() {
        Appointment appointmentToAdd = new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), new Station(), new User(), null);
        when(appointmentRepository.save(appointmentToAdd)).thenReturn(new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), new Station(), new User(), null));

        Appointment result = appointmentService.addAppointment(appointmentToAdd);

        verify(appointmentRepository, times(1)).save(appointmentToAdd);
        assertNotNull(result.getId());
    }

    @Test
    public void testUpdateAppointment() {
        long appointmentId = 1L;
        Appointment updatedAppointment = new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), new Station(), new User(), null);
        updatedAppointment.setId(appointmentId);
        when(appointmentRepository.existsById(appointmentId)).thenReturn(true);
        when(appointmentRepository.save(updatedAppointment)).thenReturn(updatedAppointment);

        Appointment result = appointmentService.updateAppointment(updatedAppointment, appointmentId);

        verify(appointmentRepository, times(1)).existsById(appointmentId);
        verify(appointmentRepository, times(1)).save(updatedAppointment);
        assertSame(updatedAppointment, result);
    }

    @Test
    public void testUpdateAppointmentNonExistent() {
        long appointmentId = 1L;
        Appointment updatedAppointment = new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0), new Station(), new User(), null);
        updatedAppointment.setId(appointmentId);
        when(appointmentRepository.existsById(appointmentId)).thenReturn(false);

        Appointment result = appointmentService.updateAppointment(updatedAppointment, appointmentId);

        verify(appointmentRepository, times(1)).existsById(appointmentId);
        verify(appointmentRepository, never()).save(updatedAppointment);
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
        appointments.add(new Appointment(1L, new Time(0), new Time(0), new Time(0), new Date(0),  user ));
        appointments.add(new Appointment(2L, new Time(0), new Time(0), new Time(0), new Date(0),  user));

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
}