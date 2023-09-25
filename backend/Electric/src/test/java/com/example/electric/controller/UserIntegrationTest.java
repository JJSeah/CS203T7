package com.example.electric.controller;

import com.example.electric.respository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URI;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository users;

    private ObjectMapper objectMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;


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
}
