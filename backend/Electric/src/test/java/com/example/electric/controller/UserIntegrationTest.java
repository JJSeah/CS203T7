package com.example.electric.controller;

import com.example.electric.model.*;
import com.example.electric.model.request.LoginReq;
import com.example.electric.model.response.LoginRes;
import com.example.electric.model.response.UserCarPaymentResponse;
import com.example.electric.respository.CarRepository;
import com.example.electric.respository.CardRepository;
import com.example.electric.respository.UserRepository;
import com.example.electric.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URI;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

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
    public void testGetAllUsers() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/api/user/all");
        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Set up the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//     @Test
//     public void testGetUserInfo_Success() {
//         User user = new User("Don","Ta","donta12345","donta12345@gmail.com","donta123", Role.ROLE_ADMIN,null,null,null);
//
//         User addedUser = userRepository.save(user);
//
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);
//         headers.setBearerAuth(token);
//
//         HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//
//         ResponseEntity<UserCarPaymentResponse> responseEntity = restTemplate.exchange("/api/user/" + addedUser.getId(), HttpMethod.GET, requestEntity, UserCarPaymentResponse.class);
//
//         //Clean up
//         userRepository.deleteById(addedUser.getId());
//
//         assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
// //        assertEquals(addedUser.getCars(),responseEntity.getBody().getCar());
// //        assertEquals(addedUser.getCard(),responseEntity.getBody().getCard());
//     }


//     @Test
//     public void testGetUserInfo_Success() {
//         User user = new User("test","ing","test","testest@gmail.com","testd", Role.ROLE_USER,null,null,null);
//         User addedUser = userRepository.save(user);
//         long userId = addedUser.getId();


//         Card card = new Card("donta", "1234567890123456", java.sql.Date.valueOf("2023-12-21"),addedUser);
//         Card addedCard = cardRepository.save(card);
//         List<Card> cardList = List.of(card);
//         addedUser.setCard(cardList);

//         Car car = new Car("Tesla","S","SG123",10,10,10,addedUser);
//         Car addedCar = carRepository.save(car);
//         List<Car> carList = List.of(car);
//         addedUser.setCars(carList);

//         userService.updateUser(userId, addedUser);

//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);
//         headers.setBearerAuth(token);

//         HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

//         ResponseEntity<UserCarPaymentResponse> responseEntity = restTemplate.exchange("/api/user/1", HttpMethod.GET, requestEntity, UserCarPaymentResponse.class);

//         assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
//         assertEquals(addedUser,responseEntity.getBody());
// //        assertEquals(addedUser.getCars(),responseEntity.getBody().getCar());
// //        assertEquals(addedUser.getCard(),responseEntity.getBody().getCard());

//         carRepository.deleteById(addedCard.getId());
//         cardRepository.deleteById(addedCard.getId());
//         userRepository.deleteById(userId);
//     }

    @Test
    public void testGetUserInfo_Failure() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserCarPaymentResponse> responseEntity = restTemplate.exchange("/api/user/999", HttpMethod.GET, requestEntity, UserCarPaymentResponse.class);

        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

//     @Test
// //    @WithMockUser(roles = "ADMIN")
//     public void testUpdateUser_Success() {
//         User user = new User("Donga","Testing","donta123","donta1@gmail.com","donta123568df", Role.ROLE_ADMIN,null,null,null);

//         User addedUser = userRepository.save(user);
//         Long userId = addedUser.getId();
//         addedUser.setEmail("tadon@gmail.com");
//         addedUser.setUsernames("tadon");

//         // HttpHeaders headers = new HttpHeaders();
//         // headers.setContentType(MediaType.APPLICATION_JSON);

//         // HttpEntity<User> requestEntity = new HttpEntity<>(addedUser,headers);
//         HttpEntity<User> requestEntity = new HttpEntity<>(addedUser);
//         System.out.println("herrrrrrrrrrrrrreee -> "+userId);

//         ResponseEntity<User> responseEntity = restTemplate.exchange("/api/user/" + userId, HttpMethod.PUT, requestEntity, User.class);

//         assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
//         assertEquals("tadon",responseEntity.getBody().getUsernames());
//         assertEquals("tadon@gmail.com",responseEntity.getBody().getUsernames());
//     }

    // @Test
    // public void testUpdateUser_Failure() {
    //     User user = new User("Don","Ta","donta","donta@gmail.com","donta123", Role.ROLE_USER,null,null,null);

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);
    //     headers.setBearerAuth(token);

    //     HttpEntity<User> requestEntity = new HttpEntity<>(user,headers);

    //     ResponseEntity<User> responseEntity = restTemplate.exchange("/api/user/500" , HttpMethod.PUT, requestEntity, User.class);

    //     assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    // }

    @Test
    public void testDeleteUser_Success() {
        User user = new User("test","ing","test12345","testest12345@gmail.com","testd", Role.ROLE_USER,null,null,null);

        User addedUser = userRepository.save(user);
        Long userId = addedUser.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<User> requestEntity = new HttpEntity<>(addedUser,headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/user/"+userId, HttpMethod.DELETE, requestEntity, Void.class);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        
    }

    @Test
    public void testDeleteUser_Failure() {
        User user = new User("test","ing","test12345","testest12345@gmail.com","testd", Role.ROLE_USER,null,null,null);

        User addedUser = userRepository.save(user);
        Long userId = addedUser.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<User> requestEntity = new HttpEntity<>(addedUser,headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/user/999", HttpMethod.DELETE, requestEntity, Void.class);

        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());

        userRepository.deleteById(userId);
    }
}
