package com.example.electric.controller;

import com.example.electric.model.Appointment;
import com.example.electric.respository.AppointmentRepository;
import com.example.electric.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class AppointmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @AfterEach
    public void tearDown() {
        appointmentRepository.deleteAll();
    }

    @Test
    public void testGetAllAppointments() throws Exception {
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        mockMvc.perform(get("/api/appointment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

        verify(appointmentService, times(1)).getAllAppointments();
        verifyNoMoreInteractions(appointmentService);
    }


//    @Test
//    public void testGetAppointmentById_NotFound() throws Exception {
//        long appointmentId = 1L;
//        when(appointmentService.getAppointmentById(appointmentId)).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/api/appointment/{appointmentId}", appointmentId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//
//        verify(appointmentService, times(1)).getAppointmentById(appointmentId);
//        verifyNoMoreInteractions(appointmentService);
//    }

    @Test
    public void testGetAppointmentById() throws Exception {
        long appointmentId = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId); // Set the ID for the mock appointment
        appointmentRepository.save(appointment);

        when(appointmentService.getAppointmentById(appointmentId)).thenReturn(Optional.of(appointment));

        mockMvc.perform(get("/api/appointment/{appointmentId}", appointmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(appointmentId));

        verify(appointmentService, times(1)).getAppointmentById(appointmentId);
        verifyNoMoreInteractions(appointmentService);
    }



    @Test
    public void testGetAllAppointmentsByUser() throws Exception {
        long userId = 1L;
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentService.getAllAppointmentsByUser(userId)).thenReturn(appointments);

        mockMvc.perform(get("/api/appointment/user/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

        verify(appointmentService, times(1)).getAllAppointmentsByUser(userId);
        verifyNoMoreInteractions(appointmentService);
    }

    @Test
    public void testGetAllAppointmentsAtStation() throws Exception {
        long stationId = 1L;
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentService.getAllAppointmentsAtStation(stationId)).thenReturn(appointments);

        mockMvc.perform(get("/api/appointment/station/{stationId}", stationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

        verify(appointmentService, times(1)).getAllAppointmentsAtStation(stationId);
        verifyNoMoreInteractions(appointmentService);
    }

//    @Test
//    public void testAddAppointment() throws Exception {
//        Appointment appointmentToAdd = new Appointment();
//        appointmentToAdd.setId(1L); // Set necessary fields in the appointment
//
//        when(appointmentService.addAppointment(any(Appointment.class))).thenReturn(appointmentToAdd);
//
//        mockMvc.perform(post("/api/appointment")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(appointmentToAdd)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L));
//
//        verify(appointmentService, times(1)).addAppointment(any(Appointment.class));
//        verifyNoMoreInteractions(appointmentService);
//    }

    @Test
    public void testUpdateAppointment() throws Exception {
        long appointmentId = 1L;
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setId(appointmentId); // Set necessary fields in the updated appointment

        when(appointmentService.getAppointmentById(appointmentId)).thenReturn(Optional.of(new Appointment()));
        when(appointmentService.updateAppointment(any(Appointment.class), eq(appointmentId))).thenReturn(updatedAppointment);

        mockMvc.perform(put("/api/appointment/{id}", appointmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedAppointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(appointmentId));

        verify(appointmentService, times(1)).getAppointmentById(appointmentId);
        verify(appointmentService, times(1)).updateAppointment(any(Appointment.class), eq(appointmentId));
        verifyNoMoreInteractions(appointmentService);
    }


//    @Test
//    public void testDeleteAppointment() throws Exception {
//        long appointmentId = 1L;
//
//        when(appointmentService.getAppointmentById(appointmentId)).thenReturn(Optional.of(new Appointment()));
//
//        mockMvc.perform(delete("/api/appointment/{id}", appointmentId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(appointmentService, times(1)).getAppointmentById(appointmentId);
//        verify(appointmentService, times(1)).deleteAppointment(appointmentId);
//        verifyNoMoreInteractions(appointmentService);
//    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}