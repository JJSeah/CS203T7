package com.example.electric.controller;

import com.example.electric.model.Charger;
import com.example.electric.model.request.LoginReq;
import com.example.electric.model.response.LoginRes;
import com.example.electric.respository.ChargerRepository;
import com.example.electric.respository.StationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChargerIntegrationTest {
   @LocalServerPort
   private int port;

   private  String baseUrl = "http://localhost:" ;

   @Autowired
   private TestRestTemplate restTemplate;

   @Autowired
   private ChargerRepository chargerRepository;

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
   public void testGetAllChargers_Success() throws Exception {

       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setBearerAuth(token);
       HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

       URI url = new URI(baseUrl + port + "/api/charger");

       ResponseEntity<List<Charger>> response = restTemplate.exchange(
               url,
               HttpMethod.GET,
               requestEntity,
               new ParameterizedTypeReference<List<Charger>>() {}
       );

       List<Charger> resultChargers = response.getBody();

       assertEquals(HttpStatus.OK, response.getStatusCode());
   }

   @Test
   public void testGetAllChargersByStation_Success() throws Exception {
       long stationId = 1L;

        URI url = new URI(baseUrl + port + "/api/charger" + "/station/"+ stationId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

       ResponseEntity<List<Charger>> response = restTemplate.exchange(
               url,
               HttpMethod.GET,
               requestEntity,
               new ParameterizedTypeReference<List<Charger>>() {}
       );

        List<Charger> resultChargers = response.getBody();

       assertEquals(HttpStatus.OK, response.getStatusCode());
       assertEquals(2, resultChargers.size());
   }

   @Test
   public void testGetChargerById_Success() throws Exception {
       long chargerId = 1L;
       URI url = new URI(baseUrl + + port + "/api/charger" + "/" + chargerId);

       // Set up the request headers
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setBearerAuth(token);

       // Set up the request entity
       HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

       ResponseEntity<Charger> result = restTemplate.exchange(
               url,
               HttpMethod.GET,
               requestEntity,
               Charger.class
       );

       Charger resultCharger = result.getBody();

       assertEquals(200, result.getStatusCode().value());
       assertEquals(1, resultCharger.getId());
   }

   @Test
   public void testGetChargerById_NotFound() throws Exception {
       long chargerId = 999;

       // Set up the request headers
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setBearerAuth(token);

       // Set up the request entity
       HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

       URI url = new URI(baseUrl + port + "/api/charger"+ "/" + chargerId);

       ResponseEntity<Charger> result = restTemplate.exchange(
               url,
               HttpMethod.GET,
               requestEntity,
               Charger.class
       );
       assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
   }

   @Test
   public void  testAddCharger_Success() throws Exception{
        //300L is not existent in database 
       Long id = 121L;
       Charger testCharger = new Charger(id, "charger121");

       // Set up the request headers
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setBearerAuth(token);

       // Set up the request entity
       HttpEntity<Charger> requestEntity = new HttpEntity<>(testCharger, headers);
       
       URI url = new URI(baseUrl + port + "/api/charger");
       ResponseEntity<Long> result = restTemplate.exchange(
               url,
               HttpMethod.POST,
               requestEntity,
               Long.class
       );

       long chargerId = result.getBody();

       assertEquals(HttpStatus.CREATED, result.getStatusCode());
       assertEquals("application/json", result.getHeaders().getContentType().toString());
       assertEquals(id, chargerId);

       chargerRepository.deleteById(id);
   }

    @Test
    public void  UpdateCharger_Success() throws Exception{
        //300L is not existent in database
        Long id = 121L;
        Charger charger = new Charger(id, "charger121");
        Charger addedCharger = chargerRepository.save(charger);

        Charger updatedCharger = new Charger(id, "charger121_UPDATED");

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Charger> requestEntity = new HttpEntity<>(updatedCharger, headers);

        URI url = new URI(baseUrl + port + "/api/charger/" + addedCharger.getId());
        ResponseEntity<Charger> result = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Charger.class
        );

        Charger responseCharger = result.getBody();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("charger121_UPDATED", responseCharger.getName());

        chargerRepository.deleteById(addedCharger.getId());
    }

    @Test
    public void testUpdateCharger_NotFound() throws Exception {
        //Create charger
        Charger updatedCharger = new Charger();

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Charger> requestEntity = new HttpEntity<>(updatedCharger, headers);

        URI url = new URI(baseUrl + port + "/api/charger/999");
        ResponseEntity<Charger> result = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Charger.class
        );

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testDeleteCharger_Success() throws Exception {
        Long id = 121L;
        Charger charger = new Charger(id, "charger121");
        Charger addedCharger = chargerRepository.save(charger);

        URI url = new URI(baseUrl + + port + "/api/charger/" + addedCharger.getId());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> result = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());

        chargerRepository.deleteById(addedCharger.getId());
    }

    @Test
    public void testDeleteCharger_NotFound() throws Exception {
        URI url = new URI(baseUrl + + port + "/api/charger/999");

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> result = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
