package com.example.electric.controller;

import com.example.electric.model.*;
import com.example.electric.model.response.UserCarPaymentResponse;
import com.example.electric.respository.UserRepository;
import com.example.electric.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.With;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.net.URI;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    private String generateBearerToken() {
        // Generate a JWT token with the appropriate claims
        String secret = "mysecretpassword";
        String encodedSecret = new BCryptPasswordEncoder().encode(secret);
        String token = Jwts.builder()
                .setSubject("ex@example.com")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512, encodedSecret)
                .compact();

        // Return the token as a bearer token
        return "Bearer " + token;
    }

    @Test
    public void testGetAllUsers() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/api/user/all");

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    @WithMockUser
//    public void testGetUserInfo_Success() throws Exception{
//        Car car = new Car();
//        List<Car> carList = List.of(car);
//        Card card = new Card();
//        List<Card> cardList = List.of(card);
//        User user = new User(1L,"Don","Ta","donta","donta@gmail.com",null, Role.ROLE_USER,carList,cardList,null,null);
//        User addedUser = userRepository.save(user);
//
////        String token = generateBearerToken();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<UserCarPaymentResponse> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/user" + addedUser.getId(), HttpMethod.GET, requestEntity, UserCarPaymentResponse.class);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(addedUser, responseEntity.getBody());
//    }
}
