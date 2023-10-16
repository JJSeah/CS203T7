package com.example.electric.controller;

import com.example.electric.model.*;
import com.example.electric.model.response.UserCarPaymentResponse;
import com.example.electric.respository.UserRepository;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

//    protected String mapToJson(Object obj) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(obj);
//    }
//
//    protected <T> T mapFromJson(String json, Class<T> clazz)
//            throws JsonParseException, JsonMappingException, IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(json, clazz);
//    }

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

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
    public void testGetAllUsers() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/api/user/all");

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//     @Test
//     public void testGetUserInfo_Success() {
//         User user = new User(1L,"Don","Ta","donta","donta@gmail.com","donta123", Role.ROLE_USER,null,null,null);

//         Card card = new Card(1L,"donta", 12345L, java.sql.Date.valueOf("2023-12-21"),user);
//         List<Card> cardList = List.of(card);
//         user.setCard(cardList);

//         Car car = new Car(1L,"Tesla","S","SG123",10,10,10,user);
//         List<Car> carList = List.of(car);
//         user.setCars(carList);

//         User addedUser = userRepository.save(user);
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);

//         HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

//         ResponseEntity<UserCarPaymentResponse> responseEntity = restTemplate.exchange("/api/user/1", HttpMethod.GET, requestEntity, UserCarPaymentResponse.class);

//         assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
//         assertEquals(addedUser,responseEntity.getBody());
// //        assertEquals(addedUser.getCars(),responseEntity.getBody().getCar());
// //        assertEquals(addedUser.getCard(),responseEntity.getBody().getCard());
//     }

    @Test
    public void testGetUserInfo_Failure() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserCarPaymentResponse> responseEntity = restTemplate.exchange("/api/user/999", HttpMethod.GET, requestEntity, UserCarPaymentResponse.class);

        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    @Test
//    @WithMockUser(roles = "ADMIN")
    public void testUpdateUser_Success() {
        User user = new User(50L,"Don","Ta","donta","donta@gmail.com","donta123", Role.ROLE_USER,null,null,null);

        User addedUser = userRepository.save(user);
        addedUser.setEmail("tadon@gmail.com");
        addedUser.setUsernames("tadon");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestEntity = new HttpEntity<>(addedUser,headers);

        ResponseEntity<User> responseEntity = restTemplate.exchange("/api/user/50", HttpMethod.PUT, requestEntity, User.class);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals("tadon",responseEntity.getBody().getUsernames());
        assertEquals("tadon@gmail.com",responseEntity.getBody().getUsernames());
    }

    @Test
    public void testUpdateUser_Failure() {
        User user = new User(50L,"Don","Ta","donta","donta@gmail.com","donta123", Role.ROLE_USER,null,null,null);

        User addedUser = userRepository.save(user);
        addedUser.setEmail("tadon@gmail.com");
        addedUser.setUsernames("tadon");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestEntity = new HttpEntity<>(addedUser,headers);

        ResponseEntity<User> responseEntity = restTemplate.exchange("/api/user/999", HttpMethod.PUT, requestEntity, User.class);

        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteUser_Success() {
        User user = new User(1L,"Don","Ta","donta","donta@gmail.com","donta123", Role.ROLE_USER,null,null,null);

        User addedUser = userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestEntity = new HttpEntity<>(addedUser,headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/user/1", HttpMethod.DELETE, requestEntity, Void.class);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteUser_Failure() {
        User user = new User(1L,"Don","Ta","donta","donta@gmail.com","donta123", Role.ROLE_USER,null,null,null);

        User addedUser = userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestEntity = new HttpEntity<>(addedUser,headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/user/999", HttpMethod.DELETE, requestEntity, Void.class);

        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }
}
