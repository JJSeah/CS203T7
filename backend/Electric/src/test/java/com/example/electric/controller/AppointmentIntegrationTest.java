package com.example.electric.controller;

import com.example.electric.model.Appointment;
import com.example.electric.respository.AppointmentRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private AppointmentRepository appointmentRepository;


    @Value("${token.signing.key}")
    private String jwtSigningKey;


    private String generateBearerToken(Map<String, Object> extraClaims) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        String token = Jwts.builder().setClaims(extraClaims).setSubject("ex@example.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256).compact();
        return "Bearer " + token;
    }

//    @Test
//    public void testGetAllAppointments() throws Exception {
//        URI uri = new URI("http://localhost:" + port + "/api/appointment");
//
//        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        // You can further validate the response content if needed.
//    }

    @Test
    public void testGetAppointmentById_Success() {
        // Create a new appointment
        Appointment appointment = new Appointment(1L, new Time(0), new Time(0), new Time(0), new java.sql.Date(0),null,0);

        // Add the appointment to the system
        Appointment addedAppointment = appointmentRepository.save(appointment);

        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

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
        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

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
//
//        // Add the appointment to the system
//        Appointment addedAppointment = appointmentRepository.save(appointment);
//
//        // Update the appointment
//        addedAppointment.setStartTime(new Time(1));
//        addedAppointment.setEndTime(new Time(2));
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
//        HttpEntity<Appointment> requestEntity = new HttpEntity<>(addedAppointment, headers);
//
//        // Send the request to the updateAppointment endpoint
//        ResponseEntity<Appointment> responseEntity = restTemplate.exchange("/api/appointment/" + addedAppointment.getId(), HttpMethod.PUT, requestEntity, Appointment.class);
//
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

        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//    headers.setBearerAuth(token);

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
        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//    headers.setBearerAuth(token);

        // Set up the request entity with an invalid ID
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the deleteAppointment endpoint with an invalid ID
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/appointment/999", HttpMethod.DELETE, requestEntity, Void.class);

        // Check that the response has a 404 Not Found status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
