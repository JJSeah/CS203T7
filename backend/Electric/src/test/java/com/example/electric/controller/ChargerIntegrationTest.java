package com.example.electric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import com.example.electric.respository.ChargerRepository;
import com.example.electric.respository.StationRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ChargerIntegrationTest {
    @LocalServerPort
    private int port;

    private  String baseUrl = "http://localhost:" ;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ChargerRepository chargerRepository;

    @Autowired 
    private StationRepository stationRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        chargerRepository.deleteAll();
    }

    @Test
    public void getAllChargers_sucess() throws Exception {
        URI uri = new URI(baseUrl + port + "/api/charger");

        chargerRepository.save(new Charger(1L, "Charger1"));
        chargerRepository.save(new Charger(2L, "Charger2"));

        ResponseEntity<Charger[]> result = restTemplate.getForEntity(uri, Charger[].class);
        Charger[] resultChargers = result.getBody();

        assertEquals(200, result.getStatusCode().value());
        assertEquals(2, resultChargers.length);
    }

    @Test
    public void testGetAllChargersbyStationId_Success() throws Exception {
        long stationId = 1L; 
        Station station = new Station(stationId, "Station1");
        stationRepository.save(station);
        chargerRepository.save(new Charger(1L, "Charger1", station));
        chargerRepository.save(new Charger(2L, "Charger2", station));
        
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + port + "/api/charger" + "/station/{stationId}", stationId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testGetAllChargersAtStationId_Found() throws Exception {
        long stationId = 99L;  
        
        // Send a GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + port + "/api/charger" + "/station/{stationId}", stationId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
                // .andExpect(jsonPath("$.E1002", equalTo("Data not found")));    
        }

    @Test
    public void testGetChargerById_sucess() throws Exception {
        long chargerId = 1L;
        URI url = new URI(baseUrl + + port + "/api/charger" + "/" + chargerId);

        Charger testCharger = new Charger(chargerId, "Charger1");
        chargerRepository.save(testCharger);
        chargerRepository.save(new Charger(2L, "Charger2"));

        ResponseEntity<Charger> result = restTemplate.getForEntity(url, Charger.class);
        Charger resultCharger = result.getBody();

        assertEquals(200, result.getStatusCode().value());
        assertEquals(testCharger, resultCharger);
    }

    @Test
    public void testGetChargerById_NotFound() throws Exception {
        long chargerId = 999;
        URI url = new URI(baseUrl + port + "/api/charger"+ "/" + chargerId);

        ResponseEntity<Charger> result = restTemplate.getForEntity(url, Charger.class);

        assertEquals(404, result.getStatusCode().value());
    }

    @Test 
    public void  addCharger() throws Exception{
        Long id = 1L;
        Charger testCharger = new Charger(id, "Charger1");
        chargerRepository.save(testCharger);
        URI url = new URI(baseUrl + port + "/api/charger");

        ResponseEntity<Long> result = restTemplate.postForEntity(url , testCharger, Long.class);
        long chargerId = result.getBody();

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("application/json", result.getHeaders().getContentType().toString());
        assertEquals(id, chargerId);
    }


}
