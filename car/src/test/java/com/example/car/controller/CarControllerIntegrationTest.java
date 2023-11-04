package com.example.car.controller;

import com.example.car.models.CarDetails;
import com.example.car.service.CarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarServiceImpl carService;

    @BeforeEach
    public void setUp() {
        // Initialize any required setup for your tests
        // This may include populating the database with test data
    }

    @Test
    public void testGetAllCars() throws Exception {
        mockMvc.perform(get("/car/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Add more specific assertions for the response content as needed
    }

    // Add more test cases for other endpoints in the CarController

    @Test
    public void testGetCarById() throws Exception {
        CarDetails carToAdd = new CarDetails();
        carToAdd.setCarModel("Test Car");
        carToAdd.setBattery(100);
        carToAdd.setCarId(1);
        carService.addCar(carToAdd);
        // Test the /car/{id} endpoint
        mockMvc.perform(get("/car/{id}", 1))  // Replace "1" with an actual car ID
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Add more specific assertions for the response content as needed
    }

    @Test
    public void testUpdateCar() throws Exception {
        // Test the /car/update endpoint
        CarDetails car = new CarDetails(); // Create a CarDetails object for testing

        mockMvc.perform(put("/car/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(car)))
                .andExpect(status().isOk())
                .andDo(print());

        // Add more specific assertions for the response content as needed
    }

    @Test
    public void testAddCar() throws Exception {
        // Test the /car/add endpoint
        CarDetails car = new CarDetails(); // Create a CarDetails object for testing

        mockMvc.perform(post("/car/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(car)))
                .andExpect(status().isOk())
                .andDo(print());

        // Add more specific assertions for the response content as needed
    }

    @Test
    public void testDeleteCar() throws Exception {
        // Test the /car/delete/{id} endpoint
        mockMvc.perform(delete("/car/delete/{id}", 1))  // Replace "1" with an actual car ID
                .andExpect(status().isOk())
                .andDo(print());

        // Add more specific assertions for the response content as needed
    }

    @Test
    public void testStartCar() throws Exception {
        // Test the /car/start/{id} endpoint
        CarDetails carToAdd = new CarDetails();
        carToAdd.setCarModel("Test Car");
        carToAdd.setBattery(100);
        carToAdd.setCarId(1);
        carToAdd.setStatus("OFF");
        carService.addCar(carToAdd);
        mockMvc.perform(get("/car/start/{id}", 1))  // Replace "1" with an actual car ID
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Add more specific assertions for the response content as needed
    }

    @Test
    public void testStopCar() throws Exception {
        // Test the /car/stop/{id} endpoint
        mockMvc.perform(get("/car/stop/{id}", 1))  // Replace "1" with an actual car ID
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Add more specific assertions for the response content as needed
    }

    // Add more test cases for other endpoints in the CarController

    // Utility method to convert an object to a JSON string
    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
