//package com.example.electric.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.MediaType;
//import org.springframework.http.HttpHeaders;
//
//import com.example.electric.model.Charger;
//import com.example.electric.model.Station;
//import com.example.electric.respository.ChargerRepository;
//import com.example.electric.respository.StationRepository;
//
//
//import java.net.URI;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfiguration
//public class StationIntegrationTest {
//    @LocalServerPort
//    private int port;
//
//    private String baseUrl = "http://localhost:";
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private ChargerRepository chargerRepository;
//
//    @Autowired
//    private StationRepository stationRepository;
//
//    private HttpHeaders headers;
//
//    @AfterEach
//    void tearDown() {
//        chargerRepository.deleteAll();
//        stationRepository.deleteAll();
//    }
//
//    @Test
//    public void testCreateStation() throws URISyntaxException {
//        Station newStation = new Station();
//        newStation.setName("New Station");
//        newStation.setLatitude(123.456);
//        newStation.setLongitude(789.123);
//
//        URI url = new URI(baseUrl + port + "/api/stations");
//
//        ResponseEntity<Station> response = restTemplate.getForEntity(url, Station.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        Station createdStation = response.getBody();
//        assertEquals(newStation, createdStation);
//
//    }
//
//    @Test
//    public void testDeleteStation_sucess() {
//        Station newStation = new Station(1L,"Station1");
//        stationRepository.save(newStation);
//
//        Long stationIdToDelete = 1L;
//        String url = "http://localhost:" + port + "/api/stations/{ID}" ;
//
//        ResponseEntity<Void> response = restTemplate.exchange(
//                url,
//                HttpMethod.DELETE,
//                null,
//                Void.class,
//                stationIdToDelete);
//
//        // Check if the response status code is 204 (No Content) for a successful delete
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//   @Test
//    public void testDeleteStation_IdNotFound() {
//        Long stationIdToDelete = 1L;
//        String url = "http://localhost:" + port + "/api/stations/{ID}" ;
//
//        ResponseEntity<Void> response = restTemplate.exchange(
//                url,
//                HttpMethod.DELETE,
//                null,
//                Void.class,
//                stationIdToDelete);
//
//        // Check if the response status code is 204 (No Content) for a successful delete
//
//        assertEquals(404, response.getStatusCode().value());
//    }
//
//    @Test
//    public void testGetAllStations() throws URISyntaxException {
//        URI url = new URI(baseUrl + port + "/api/stations/all");
//        stationRepository.save(new Station(1L, "station1"));
//        stationRepository.save(new Station(2L, "station2"));
//
//        ResponseEntity<List> response = restTemplate.getForEntity(
//                url,
//                List.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        List<Station> stations = response.getBody();
//        assertNotNull(stations);
//        // Add further assertions based on your expected data
//    }
//
//
//    @Test
//    public void testGetStationById_Found() {
//        Long stationIdToRetrieve = 1L;
//        Station newStation = new Station(stationIdToRetrieve,"Station1");
//        stationRepository.save(newStation);
//        String url = "http://localhost:" + port + "/api/stations/" + stationIdToRetrieve;
//
//        ResponseEntity<Station> response = restTemplate.getForEntity(url, Station.class);
//
//        // Check if the response status code is 200 (OK) for a successful retrieval
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        // Check if the retrieved station is not null
//        Station retrievedStation = response.getBody();
//        assertNotNull(retrievedStation);
//        // Add further assertions based on your expected data
//    }
//
//    @Test
//    public void testGetStationById_NotFound() {
//        Long nonExistentStationId = 999L;
//
//        String url = "http://localhost:" + port + "/api/stations/" + nonExistentStationId;
//
//        ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);
//
//        // Check if the response status code is 404 (Not Found) for a non-existent ID
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//
//    @Test
//    public void testUpdateStation() {
//        // Assuming you have a station with ID 1 in your database
//        Long stationIdToUpdate = 1L;
//        Station newStation = new Station(stationIdToUpdate,"Station1");
//        stationRepository.save(newStation);
//        String url = baseUrl + port + "/api/stations/" + stationIdToUpdate;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        // Create an updated station with new data
//        Station updatedStation = new Station();
//        updatedStation.setName("Updated Station Name");
//        updatedStation.setLatitude(123.456);
//        updatedStation.setLongitude(789.123);
//
//        HttpEntity<Station> request = new HttpEntity<>(updatedStation, headers);
//
//        ResponseEntity<Station> response = restTemplate.exchange(
//                url,
//                HttpMethod.PUT,
//                request,
//                Station.class);
//
//        // Check if the response status code is 200 (OK) for a successful update
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        // Check if the updated station data matches the response
//        Station updatedStationResponse = response.getBody();
//        assertEquals(updatedStation.getName(), updatedStationResponse.getName());
//        assertEquals(updatedStation.getLatitude(), updatedStationResponse.getLatitude());
//        assertEquals(updatedStation.getLongitude(), updatedStationResponse.getLongitude());
//        // Add further assertions based on your expected data
//    }
//
//
//    @Test
//    public void testUpdateStation_NotFound() {
//        Long nonExistentStationId = 999L; // Use an ID that doesn't exist
//        String url = "http://localhost:" + port + "/api/stations/" + nonExistentStationId;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Create an updated station with new data
//        Station updatedStation = new Station();
//        updatedStation.setName("Updated Station Name");
//        updatedStation.setLatitude(123.456);
//        updatedStation.setLongitude(789.123);
//
//        HttpEntity<Station> request = new HttpEntity<>(updatedStation, headers);
//
//        ResponseEntity<Void> response = restTemplate.exchange(
//                url,
//                HttpMethod.PUT,
//                request,
//                Void.class);
//
//                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//
//    @Test
//    //StationDatabase not populated yet. Hence not working
//    public void testGetClosestStation() {
//        // Create a Station object with latitude and longitude
//        Station requestStation = new Station();
//        requestStation.setLatitude(1.296803);
//        requestStation.setLongitude(103.852239);
//
//        String url = "http://localhost:" + port + "/api/stations/closest";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Station> request = new HttpEntity<>(requestStation, headers);
//
//        ResponseEntity<Station> response = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                request,
//                Station.class);
//
//        // Check if the response status code is 200 (OK) for a successful request
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        // Check if the response contains a valid Station object
//        Station closestStation = response.getBody();
//        assertNotNull(closestStation);
//        // Add further assertions based on your expected data
//    }
//}
