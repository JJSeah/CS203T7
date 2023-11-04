package com.example.electric.controller;

import com.example.electric.model.Appointment;
import com.example.electric.model.Car;
import com.example.electric.model.Role;
import com.example.electric.model.User;
import com.example.electric.respository.CarRepository;
import com.example.electric.respository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.sql.Time;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

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
    public void testGetCarById_Success() {
        //Create a new car
        Car car = new Car(1L, "Tesla", "Model S", "ABC123", 240, 80.0, 75, null);

        //Add car to the system
        Car addedCar = carRepository.save(car);

        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getCarById endpoint
        ResponseEntity<Car> responseEntity = restTemplate.exchange("/api/car/" + addedCar.getId(), HttpMethod.GET, requestEntity, Car.class);

        // Check that the response has a 200 OK status code and contains the appointment
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addedCar, responseEntity.getBody());
    }

    @Test
    public void testGetCarById_Failure() {
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
    public void testGetAllCarsByUser_Success() {
        //Create a new user
        User user = new User(1L,"dang", "gie", "agile123", "agile123@gmail.com", "danggie123", Role.ROLE_USER, null, null, null);
        //Create a new car
        Car car = new Car(1L, "Tesla", "Model S", "ABC123", 240, 80.0, 75, null);

        //Add car to user and user to car
//        car.setUser(user);
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        user.setCars(carList);

        //Add user, car to the system
        User addedUser = userRepository.save(user);
        Car addedCar = carRepository.save(car);

        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getAllCarsByUser endpoint
        ResponseEntity<List<Car>> responseEntity = restTemplate.exchange("/api/car/user/" + addedUser.getId(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Car>>() {});

        // Check that the response has a 200 OK status code and contains the appointment
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(carList, responseEntity.getBody());

        //Clean up
        userRepository.delete(addedUser);
        carRepository.delete(addedCar);
    }

    @Test
    public void testGetAllCarsByUser_Failure() {
        //Make sure user does not exist
        userRepository.deleteById(999L);
        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getAllCarsByUser endpoint
        ResponseEntity<List<Car>> responseEntity = restTemplate.exchange("/api/car/user/999", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Car>>() {});

        // Check that the response has a 200 OK status code and does not contain a car list
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testGetCarByUser_Success() {
        //Create a new user
        User user = new User(1L,"dang", "gie", "giedang", "giedang123@gmail.com", "danggie123", Role.ROLE_USER, null, null, null);
        //Create a new car
        Car car = new Car(1L, "Tesla", "Model S", "ABC123", 240, 80.0, 75, null);

        //Add car to user and user to car
//        car.setUser(user);
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        user.setCars(carList);

        //Add user, car to the system
        User addedUser = userRepository.save(user);
        Car addedCar = carRepository.save(car);

        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getAllCarsByUser endpoint
        ResponseEntity<Car> responseEntity = restTemplate.exchange("/api/car/user/" + addedUser.getId() + "/car/" + addedCar.getId(), HttpMethod.GET, requestEntity, Car.class);

        // Check that the response has a 200 OK status code and contains the appointment
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(addedCar, responseEntity.getBody());

        //Clean up
        userRepository.delete(addedUser);
        carRepository.delete(addedCar);
    }

    @Test
    public void testGetCarByUser_Failure() {
        //Make sure user does not exist
        userRepository.deleteById(999L);
        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getAllCarsByUser endpoint
        ResponseEntity<Car> responseEntity = restTemplate.exchange("/api/car/user/999/car/999", HttpMethod.GET, requestEntity, Car.class);

        // Check that the response has a 200 OK status code and does not contain a car list
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testGetAllCars_Success() {
        // Generate a bearer token
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send the request to the getAllCarsByUser endpoint
        ResponseEntity<List<Car>> responseEntity = restTemplate.exchange("/api/car", HttpMethod.GET, requestEntity,  new ParameterizedTypeReference<List<Car>>() {});

        // Check that the response has a 200 OK status code and does not contain a car list
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

//    @Test
//    public void testAddCar_Success() {
//        //Create a new user
//        User user = new User(1L,"dang", "gie", "nickidanggie", "nickiedanggie123@gmail.com", "danggie123", Role.ROLE_USER, null, null, null);
//        //Create a new car
//        Car car = new Car(1L, "Tesla", "Model S", "ABC123", 240, 80.0, 75, null);
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
//        HttpEntity<Car> requestEntity = new HttpEntity<>(car, headers);
//
//        // Send a POST request to the /api/car/add/{userId} endpoint, providing the user's ID
//        ResponseEntity<Car> responseEntity = restTemplate.postForEntity(
//                "/api/car/add/" + addedUser.getId(), requestEntity, Car.class);
//
////        ResponseEntity<Car> responseEntity = restTemplate.postForEntity(
////                "/api/car/add/" + addedUser.getId(), requestEntity, Car.class);
//
//        //Clean up
//        userRepository.delete(addedUser);
//        carRepository.delete(car);
//
//        // Verify that the response has a 2xx status code (e.g., 201 Created for a successful POST request)
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//
//        //Clean up
//        userRepository.delete(user);
//        carRepository.delete(car);
//    }

    @Test
    public void testUpdateCar_Success() {
        // Create a new car
        Car car = new Car(1L, "Tesla", "Model S", "ABC123", 240, 80.0, 75, null);

        // Save the the car to the database
        Car addedCar = carRepository.save(car);

        // Generate a bearer token if needed
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        // Set the Bearer token if required
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Car> requestEntity = new HttpEntity<>(car, headers);

        // Send the request to the updateCar endpoint
        ResponseEntity<Car> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/car/" + addedCar.getId(),
                HttpMethod.PUT,
                requestEntity,
                Car.class
        );

        // Check that the response has a 200 OK status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the car has been updated in the database
        Car updatedCar = carRepository.findById(addedCar.getId()).orElse(null);
        assertNotNull(updatedCar);

        // Clean up - Delete the user and the car
        carRepository.delete(addedCar);
    }

    @Test
    public void testUpdateCar_NotFound() {

        //Delete car
        carRepository.deleteById(1L);

        //Car object
        Car car = new Car();

        // Generate a bearer token if needed
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        // Set the Bearer token if required
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Car> requestEntity = new HttpEntity<>(car, headers);

        // Send the request to the updateCar endpoint
        ResponseEntity<Car> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/car/1",
                HttpMethod.PUT,
                requestEntity,
                Car.class
        );

        // Check that the response has a 200 OK status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateCarBattery_Success() {
        // Create a new car
        Car car = new Car(1L, "Tesla", "Model S", "ABC123", 240, 80.0, 75, null);

        // Save the the car to the database
        Car addedCar = carRepository.save(car);

        // Generate a bearer token if needed
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        // Set the Bearer token if required
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Car> requestEntity = new HttpEntity<>(car, headers);

        // Send the request to the updateCar endpoint
        // Send a PUT request to update the car battery
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "/api/car/battery/" + addedCar.getId(), HttpMethod.PUT,
               requestEntity, Void.class);

        // Check that the response has a 200 OK status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the car has been updated in the database
        Car updatedCar = carRepository.findById(addedCar.getId()).orElse(null);
        assertNotNull(updatedCar);

        // Clean up - Delete the user and the car
        carRepository.delete(addedCar);
    }

    @Test
    public void testUpdateCarBattery_NotFound() {
        //Delete car
        carRepository.deleteById(1L);

        //Car object
        Car car = new Car();

        // Generate a bearer token if needed
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        // Set the Bearer token if required
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Car> requestEntity = new HttpEntity<>(car, headers);

        // Send the request to the updateCar endpoint
        ResponseEntity<Car> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/car/1",
                HttpMethod.PUT,
                requestEntity,
                Car.class
        );

        // Check that the response has a 404 NOT FOUND status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteCar_Success() {
        // Create a new car
        Car car = new Car(1L, "Tesla", "Model S", "ABC123", 240, 80.0, 75, null);

        // Save the the car to the database
        Car addedCar = carRepository.save(car);

        // Generate a bearer token if needed
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        // Set the Bearer token if required
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send a DELETE request to update the car battery
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "/api/car/" + addedCar.getId(), HttpMethod.DELETE,
                requestEntity, Void.class);

        // Check the response status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteCar_NotFound() {
        //Delete car
        carRepository.deleteById(1L);

        // Generate a bearer token if needed
        String token = generateBearerToken(new HashMap<>());

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        // Set the Bearer token if required
//        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Send a DELETE request to update the car battery
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "/api/car/1", HttpMethod.DELETE,
                requestEntity, Void.class);

        // Check that the response has a 404 NOT FOUND status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
