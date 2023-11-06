package com.example.electric.controller;

import com.example.electric.model.Appointment;
import com.example.electric.model.request.LoginReq;
import com.example.electric.model.response.LoginRes;
import com.example.electric.respository.AppointmentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;
import java.sql.Time;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private String token;

    @BeforeEach
    public void setUp() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/auth/login");
        LoginReq req = new LoginReq("Admin@gmail.com", "mysecretpassword");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginReq> requestEntity = new HttpEntity<>(req, headers);

        ResponseEntity<LoginRes> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, LoginRes.class);
        LoginRes loginRes = responseEntity.getBody();
        token = loginRes.getToken();
        System.out.println(token);

    }

   @Test
   public void testGetAllAppointments() throws Exception {
       URI uri = new URI("http://localhost:" + port + "/api/appointment");

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

       ResponseEntity<String> response = restTemplate.exchange(uri , HttpMethod.GET, requestEntity, String.class);

       assertEquals(HttpStatus.OK, response.getStatusCode());
       // You can further validate the response content if needed.
   }

    @Test
    public void testGetAppointmentById_Success() {
        // Create a new appointment
        Appointment appointment = new Appointment(1L, new Time(0), new Time(0), new Time(0), new java.sql.Date(0),null,0);

        // Add the appointment to the system
        Appointment addedAppointment = appointmentRepository.save(appointment);

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getAppointmentById endpoint
        ResponseEntity<Appointment> responseEntity = restTemplate.exchange("/api/appointment/" + addedAppointment.getId(), HttpMethod.GET, requestEntity, Appointment.class);

        // Check that the response has a 200 OK status code and contains the appointment
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addedAppointment, responseEntity.getBody());
    }

    @Test
    public void testGetAppointmentById_Failure() {

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getAppointmentById endpoint with an invalid ID
        ResponseEntity<Appointment> responseEntity = restTemplate.exchange("/api/appointment/999", HttpMethod.GET, requestEntity, Appointment.class);

        // Check that the response has a 404 Not Found status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

//    @Test
//    public void testAddAppointment() throws Exception {
//        // Create a new appointment
//        Appointment appointment = new Appointment(1L, new Time(0), new Time(0), new Time(0), new java.sql.Date(0),null,0);
//
//        // Generate a bearer token
//        String token = generateBearerToken(new HashMap<>());
//
//        // Set up the request headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        //token set for authorization
////        headers.setBearerAuth(token);
//
//        // Set up the request entity
//        HttpEntity<Appointment> requestEntity = new HttpEntity<>(appointment, headers);
//
//        // Send the request to the addAppointment endpoint
//        ResponseEntity<Appointment> responseEntity = restTemplate.exchange("/api/appointment", HttpMethod.POST, requestEntity, Appointment.class);
//
//        // Check that the response has a 200 OK status code and contains an appointment ID
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody().getId());
//    }

//    @Test
//    public void testUpdateAppointment_Sucess() {
//        // Create a new appointment
//        Appointment appointment = new Appointment(1L, new Time(0), new Time(0), new Time(0), new java.sql.Date(0),null,0);

//        // Add the appointment to the system
//        Appointment addedAppointment = appointmentRepository.save(appointment);

//        // Update the appointment
//        addedAppointment.setStartTime(new Time(1));
//        addedAppointment.setEndTime(new Time(2));

//        // Set up the request headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

//        // Set up the request entity
//        HttpEntity<Appointment> requestEntity = new HttpEntity<>(addedAppointment, headers);

//        // Send the request to the updateAppointment endpoint
//        ResponseEntity<Appointment> responseEntity = restTemplate.exchange("/api/appointment/" + addedAppointment.getId(), HttpMethod.PUT, requestEntity, Appointment.class);

//        // Check that the response has a 200 OK status code and contains the updated appointment
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(addedAppointment, responseEntity.getBody());
//    }

    @Test
    public void testUpdateAppointment_Failure() {
        // Create a new appointment
        Appointment appointment = new Appointment(1L, new Time(0), new Time(0), new Time(0), new java.sql.Date(0),null,0);

        // Add the appointment to the system
        Appointment addedAppointment = appointmentRepository.save(appointment);

        // Update the appointment
        addedAppointment.setStartTime(new Time(1));
        addedAppointment.setEndTime(new Time(2));

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity with an invalid ID
        HttpEntity<Appointment> requestEntity = new HttpEntity<>(addedAppointment, headers);

        // Send the request to the updateAppointment endpoint with an invalid ID
        ResponseEntity<Appointment> responseEntity = restTemplate.exchange("/api/appointment/999", HttpMethod.PUT, requestEntity, Appointment.class);

        // Check that the response has a 404 Not Found status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

//    @Test
//    public void testDeleteAppointment_Sucess() {
//        // Create a new appointment
//        Appointment appointment = new Appointment(1L, new Time(0), new Time(0), new Time(0), new java.sql.Date(0),null,0);
//
//        // Add the appointment to the system
//        Appointment addedAppointment = appointmentRepository.save(appointment);
//
//        // Generate a bearer token
//        String token = generateBearerToken(new HashMap<>());
//
//        // Set up the request headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
////        headers.setBearerAuth(token);
//
//        // Set up the request entity
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//
//        // Send the request to the deleteAppointment endpoint
//        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/appointment/" + addedAppointment.getId(), HttpMethod.DELETE, requestEntity, Void.class);
//
//        // Check that the response has a 204 No Content status code
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//        // Check that the appointment was deleted from the system
//        assertFalse(appointmentRepository.findById(addedAppointment.getId()).isPresent());
//    }
    @Test
    public void testDeleteAppointment_Failure() {

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity with an invalid ID
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the deleteAppointment endpoint with an invalid ID
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/appointment/999", HttpMethod.DELETE, requestEntity, Void.class);

        // Check that the response has a 404 Not Found status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
