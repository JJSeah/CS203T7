package com.example.electric.controller;

import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import com.example.electric.model.request.LoginReq;
import com.example.electric.model.response.LoginRes;
import com.example.electric.respository.ChargerRepository;
import com.example.electric.respository.StationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
   public void getAllChargers_sucess() throws Exception {
       URI uri = new URI(baseUrl + port + "/api/charger");

    //    chargerRepository.save(new Charger(1L, "Charger1"));
    //    chargerRepository.save(new Charger(2L, "Charger2"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

       ResponseEntity<Charger[]> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Charger[].class);
       Charger[] resultChargers = result.getBody();

       assertEquals(200, result.getStatusCode().value());
       assertEquals(120, resultChargers.length);
   }

   @Test
   public void testGetAllChargersbyStationId_Success() throws Exception {
       long stationId = 1L;
    //    Station station = new Station(stationId, "Station1");
    //    stationRepository.save(station);
    //    chargerRepository.save(new Charger(1L, "Charger1", station));
    //    chargerRepository.save(new Charger(2L, "Charger2", station));

        URI uri = new URI(baseUrl + port + "/api/charger" + "/station/"+ stationId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Charger[]> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Charger[].class);
        Charger[] resultChargers = result.getBody();

       assertEquals(200, result.getStatusCode().value());
       assertEquals(2, resultChargers.length);
   }

   @Test
   public void testGetAllChargersAtStationId_Found() throws Exception {
       long stationId = 999L;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send a GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/api/charger/station/{stationId}", stationId)
                .header(HttpHeaders.AUTHORIZATION, token) // Alternatively, add the token directly to the request
                .contentType(MediaType.APPLICATION_JSON)
                .content("") // You might need to add content if needed
                .accept(MediaType.APPLICATION_JSON)) // Set the response content type you expect
                .andExpect(MockMvcResultMatchers.status().isNotFound());
               // .andExpect(jsonPath("$.E1002", equalTo("Data not found")));
       }

   @Test
   public void testGetChargerById_sucess() throws Exception {
       long chargerId = 1L;
       URI url = new URI(baseUrl + + port + "/api/charger" + "/" + chargerId);

    //    Charger testCharger = new Charger(chargerId, "Charger1");
    //    chargerRepository.save(testCharger);
    //    chargerRepository.save(new Charger(2L, "Charger2"));

       ResponseEntity<Charger> result = restTemplate.getForEntity(url, Charger.class);
       Charger resultCharger = result.getBody();

       assertEquals(200, result.getStatusCode().value());
       assertEquals(1, resultCharger.getId());
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
        //300L is not existent in database 
       Long id = 121L;
       Charger testCharger = new Charger(id, "charger121");
       
       URI url = new URI(baseUrl + port + "/api/charger");
        ResponseEntity<Long> result = restTemplate.postForEntity(url , testCharger, Long.class);
       long chargerId = result.getBody();

       assertEquals(HttpStatus.CREATED, result.getStatusCode());
       assertEquals("application/json", result.getHeaders().getContentType().toString());
       System.out.println(id + ":" + chargerId);
       assertEquals(id, chargerId);

       chargerRepository.deleteById(id);
   }


}
