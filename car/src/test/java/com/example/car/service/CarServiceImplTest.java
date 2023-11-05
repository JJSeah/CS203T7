package com.example.car.service;

import com.example.car.models.CarDetails;
import com.example.car.repository.CarRepository;
import com.example.car.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCars() {
        List<CarDetails> cars = new ArrayList<>();
        cars.add(new CarDetails()); // Create a CarDetails object and add it to the list

        Mockito.when(carRepository.findAll()).thenReturn(cars);

        List<CarDetails> result = carService.getAllCars();

        assertEquals(cars, result);
        // Assert and verify the result
    }

    @Test
    public void testGetCarByCarId() {
        long carId = 1;
        CarDetails car = new CarDetails(); // Create a CarDetails object with ID 1

        Mockito.when(carRepository.findByCarId(carId)).thenReturn(car);

        CarDetails result = carService.getCarByCarId(carId);

        assertEquals(car, result);
        // Assert and verify the result
    }

    @Test
    public void testAddCar() {
        CarDetails car = new CarDetails(); // Create a CarDetails object for testing

        Mockito.when(carRepository.save(car)).thenReturn(car);

        CarDetails result = carService.addCar(car);

        // Assert and verify the result
        assertEquals(car, result);
    }

    @Test
    public void testUpdateCar() {
        CarDetails car = new CarDetails(); // Create a CarDetails object for testing

        Mockito.when(carRepository.save(car)).thenReturn(car);

        CarDetails result = carService.updateCar(car);

        // Assert and verify the result
        assertEquals(car, result);
    }

    @Test
    public void testDeleteCar() {
        long carId = 1;
        CarDetails carToDelete = new CarDetails(); // Create a CarDetails object for testing

        ResponseEntity<String> response = carService.deleteCar(carId);

        // Assert that the response has a success status (e.g., HttpStatus.OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());
}
}
