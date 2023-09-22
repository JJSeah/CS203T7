package com.example.electric.service;

import com.example.electric.model.Appointment;
import com.example.electric.respository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;
    @Test
    public void testGetAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointments();

        verify(appointmentRepository, times(1)).findAll();
        assertSame(appointments, result);
    }

    @Test
    public void testGetAppointmentById() {
        long appointmentId = 1L;
        Optional<Appointment> appointment = Optional.of(new Appointment());
        when(appointmentRepository.findById(appointmentId)).thenReturn(appointment);

        Optional<Appointment> result = appointmentService.getAppointmentById(appointmentId);

        verify(appointmentRepository, times(1)).findById(appointmentId);
        assertSame(appointment, result);
    }

    @Test
    public void testGetAllAppointmentsAtStation() {
        long stationId = 1L;
        List<Appointment> appointments = new ArrayList<>();
        when(appointmentRepository.findAppointmentsByStationId(stationId)).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointmentsAtStation(stationId);

        verify(appointmentRepository, times(1)).findAppointmentsByStationId(stationId);
        assertSame(appointments, result);
    }

    @Test
    public void testAddAppointment() {
        Appointment appointmentToAdd = new Appointment();
        when(appointmentRepository.save(appointmentToAdd)).thenReturn(appointmentToAdd);

        Appointment result = appointmentService.addAppointment(appointmentToAdd);

        verify(appointmentRepository, times(1)).save(appointmentToAdd);
        assertSame(appointmentToAdd, result);
    }

    @Test
    public void testUpdateAppointment() {
        long appointmentId = 1L;
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setId(appointmentId);
        when(appointmentRepository.existsById(appointmentId)).thenReturn(true);
        when(appointmentRepository.save(updatedAppointment)).thenReturn(updatedAppointment);

        Appointment result = appointmentService.updateAppointment(updatedAppointment, appointmentId);

        verify(appointmentRepository, times(1)).existsById(appointmentId);
        verify(appointmentRepository, times(1)).save(updatedAppointment);
        assertSame(updatedAppointment, result);
    }


    @Test
    public void testDeleteAppointment() {
        long appointmentId = 1L;
        doNothing().when(appointmentRepository).deleteById(appointmentId);

        appointmentService.deleteAppointment(appointmentId);

        verify(appointmentRepository, times(1)).deleteById(appointmentId);
    }

    @Test
    public void testGetAllAppointmentsByUser() {
        long userId = 1L;
        List<Appointment> appointments = new ArrayList<>();
        when(appointmentRepository.findAppointmentsByUserId(userId)).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointmentsByUser(userId);

        verify(appointmentRepository, times(1)).findAppointmentsByUserId(userId);
        assertSame(appointments, result);
    }
}