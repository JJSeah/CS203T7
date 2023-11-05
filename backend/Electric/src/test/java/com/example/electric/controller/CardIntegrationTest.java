package com.example.electric.controller;

import com.example.electric.model.Car;
import com.example.electric.model.Card;
import com.example.electric.model.Role;
import com.example.electric.model.User;
import com.example.electric.respository.CarRepository;
import com.example.electric.respository.CardRepository;
import com.example.electric.respository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

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

    @Test
    public void testStatusEndpoint() {
        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getCarById endpoint
        ResponseEntity<String> responseEntity = restTemplate.exchange("/api/card/status", HttpMethod.GET, requestEntity, String.class);


        // Ensure that the status code is OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testProcess_Success() {
        long id = 1L;
        double orderValue = 100.0;

        //Card object
        Card card = new Card(1L,"John Doe","4620875910309275",new java.sql.Date(2025,01,01),null);

        //Add card to repository
        Card addedCard = cardRepository.save(card);

        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getCarById endpoint
        ResponseEntity<String> responseEntity = restTemplate.exchange("/api/card/process/1/100/" + addedCard.getId(), HttpMethod.GET, requestEntity, String.class);

        // Ensure that the status code is OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        //Clean up
        cardRepository.delete(addedCard);
    }

    @Test
    public void testProcess_NotFound() {
        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getCarById endpoint
        ResponseEntity<String> responseEntity = restTemplate.exchange("/api/card/process/1/100/999", HttpMethod.GET, requestEntity, String.class);

        // Ensure that the status code is NOT FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllCards() {
        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getAllCardsByUser endpoint
        ResponseEntity<List<Card>> responseEntity = restTemplate.exchange("/api/card", HttpMethod.GET, requestEntity,  new ParameterizedTypeReference<List<Card>>() {});

        // Check that the response has a 200 OK status code and does not contain a car list
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCardById_Success() {

        //Create a new card
        Card card = new Card(1L,"John Doe","4620875910309275",new java.sql.Date(2025,01,01),null);

        //Add card to the system
        Card addedCard = cardRepository.save(card);

        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getCarById endpoint
        ResponseEntity<Card> responseEntity = restTemplate.exchange("/api/card/" + addedCard.getId(), HttpMethod.GET, requestEntity, Card.class);


        // Check that the response has a 200 OK status code and contains the card
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Card responseCard = responseEntity.getBody();
        assertNotNull(responseCard);
        assertEquals(addedCard.getId(), responseCard.getId());
        assertEquals(addedCard.getName(), responseCard.getName());
        assertEquals(addedCard.getNumber(), responseCard.getNumber());
        java.util.Date addedCardDate = new java.util.Date(addedCard.getExpiry().getDate());
        java.util.Date responseCardDate = new java.util.Date(responseCard.getExpiry().getDate());
        assertEquals(addedCardDate, responseCardDate);

        //Clean up
        cardRepository.delete(addedCard);
    }

    @Test
    public void testGetCardById_NotFound() {
        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getCarById endpoint with an invalid ID
        ResponseEntity<Car> responseEntity = restTemplate.exchange("/api/car/999", HttpMethod.GET, requestEntity, Car.class);

        // Check that the response has a 404 Not Found status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCardByUser_Success() {
        //Create a new user
        User user = new User(1L,"dang", "gie", "knearestneighbour123", "knearestneighbour123@gmail.com", "danggie123", Role.ROLE_USER, null, null, null);
        //Create a new card
        Card card = new Card(1L,"John Doe","4620875910309275",new java.sql.Date(2025,01,01),null);

        //Add car to user and user to car
//        car.setUser(user);
        List<Card> cardList = new ArrayList<>();
        cardList.add(card);
        user.setCard(cardList);

        //Add user, car to the system
        User addedUser = userRepository.save(user);
        Card addedCard = cardRepository.save(card);

        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getAllCarsByUser endpoint
        ResponseEntity<List<Card>> responseEntity = restTemplate.exchange("/api/card/user/" + addedUser.getId(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Card>>() {});

        // Check that the response has a 200 OK status code and contains the appointment
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        //Clean up
        userRepository.delete(addedUser);
        cardRepository.delete(addedCard);
    }

//    @Test
//    public void testGetCardByUser_NotFound() {
//        //Make sure user does not exist
//        userRepository.deleteById(999L);
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
//        // Send the request to the getCardByUser endpoint
//        ResponseEntity<List<Card>> responseEntity = restTemplate.exchange("/api/card/user/999", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Card>>() {});
//
//        // Check that the response has a 200 OK status code and does not contain a card list
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    }

//    @Test
//    public void testAddCard_Success() {
//        //Create a new user
//        User user = new User(1L,"dang", "gie", "nickidanggie", "nickiedanggie123@gmail.com", "danggie123", Role.ROLE_USER, null, null, null);
//        //Create a new card
//        Card card = new Card(1L,"John Doe","4620875910309275",new java.sql.Date(2025,01,01),null);
//
//        List<Card> cardList = new ArrayList<>();
//        cardList.add(card);
//        user.setCard(cardList);
//
//        User addedUser = userRepository.save(user);
//
//        // Generate a bearer token
//        String token = generateBearerToken(new HashMap<>());
//
//        // Set up the request headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Create a request entity with the car as the request body
//        HttpEntity<Card> requestEntity = new HttpEntity<>(card, headers);
//
//        // Send a POST request to the /api/car/add/{userId} endpoint, providing the user's ID
//        ResponseEntity<Card> responseEntity = restTemplate.exchange(
//                "/api/card/add/" + addedUser.getId(), HttpMethod.POST, requestEntity, Card.class);
//
//        //Clean up
//        userRepository.delete(addedUser);
//        cardRepository.delete(card);
//
//        // Verify that the response has a 2xx status code (e.g., 201 Created for a successful POST request)
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//
//        //Clean up
//        userRepository.delete(user);
//        cardRepository.delete(card);
//    }

    @Test
    public void testAddCard_NotFound() {
        //Make sure user does not exist
        userRepository.deleteById(999L);

        //New Card
        Card card = new Card(1L,"John Doe","4620875910309275",new java.sql.Date(2025,01,01),null);

        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Card> requestEntity = new HttpEntity<>(card,headers);

        // Send the request to the getCardByUser endpoint
        ResponseEntity<Card> responseEntity = restTemplate.exchange("/api/card/add/999", HttpMethod.POST, requestEntity, Card.class);

        // Check that the response has a 200 OK status code and does not contain a card list
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

//    @Test
//    public void testUpdateCard_Success() {
//        // Create a new card
//        Card card = new Card(1L,"John Doe","4620875910309275",new java.sql.Date(2025,01,01),null);
//
//        // Save the the card to the database
//        Card addedCard = cardRepository.save(card);
//
//        // Generate a bearer token if needed
//        String token = generateBearerToken(new HashMap<>());
//
//        // Set up the request headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
////        // Set the Bearer token if required
////        headers.setBearerAuth(token);
//
//        // Set up the request entity
//        HttpEntity<Card> requestEntity = new HttpEntity<>(card, headers);
//
//        // Send the request to the updateCar endpoint
//        ResponseEntity<Card> responseEntity = restTemplate.exchange(
//                 "/api/card/" + addedCard.getId(),
//                HttpMethod.PUT,
//                requestEntity,
//                Card.class
//        );
//
//        // Check that the response has a 200 OK status code
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//        // Verify that the car has been updated in the database
//        Card updatedCard = cardRepository.findById(addedCard.getId()).orElse(null);
//        assertNotNull(updatedCard);
//
//        // Clean up - Delete the user and the car
//        cardRepository.delete(addedCard);
//    }
//
//    @Test
//    public void testUpdateCard_NotFound() {
//        //Delete card
//        cardRepository.deleteById(1L);
//
//        //Card object
//        Card card = new Card();
//
//        // Generate a bearer token if needed
//        String token = generateBearerToken(new HashMap<>());
//
//        // Set up the request headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
////        // Set the Bearer token if required
////        headers.setBearerAuth(token);
//
//        // Set up the request entity
//        HttpEntity<Card> requestEntity = new HttpEntity<>(card, headers);
//
//        // Send the request to the updateCar endpoint
//        ResponseEntity<Card> responseEntity = restTemplate.exchange("/api/card/999", HttpMethod.PUT, requestEntity, Card.class);
//
//        // Check that the response has a 404 NOT FOUND status code
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    }

    @Test
    public void testDeleteCard_Success() {
        // Create a new card
        Card card = new Card(1L,"John Doe","4620875910309275",new java.sql.Date(2025,01,01),null);

        // Save the the card to the database
        Card addedCard = cardRepository.save(card);

        // Generate a bearer token if needed
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        // Set the Bearer token if required
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Card> requestEntity = new HttpEntity<>(card, headers);

        // Send a DELETE request to update the car battery
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "/api/card/" + addedCard.getId(), HttpMethod.DELETE,
                requestEntity, Void.class);

        // Check the response status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteCard_NotFound() {
        //Delete card
        cardRepository.deleteById(1L);

        // Generate a bearer token if needed
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        // Set the Bearer token if required
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Card> requestEntity = new HttpEntity<>(headers);

        // Send a DELETE request to update the car battery
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "/api/card/1", HttpMethod.DELETE,
                requestEntity, Void.class);

        // Check the response status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
