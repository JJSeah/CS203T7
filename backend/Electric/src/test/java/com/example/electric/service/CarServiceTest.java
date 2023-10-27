package com.example.electric.service;

import com.example.electric.model.Car;
import com.example.electric.respository.CarRepository;
import com.example.electric.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CarService carService;

    @Test
    public void testGetAllCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1L, "Tesla", "Model S"));
        cars.add(new Car(2L, "Tesla", "Model X"));
        when(carRepository.findAll()).thenReturn(cars);

        List<Car> result = carService.getAllCars();

        assertEquals(cars, result);
    }

    @Test
    public void testGetCarById() {
        long id = 1L;
        Car car = new Car(id, "Tesla", "Model S");
        when(carRepository.findById(id)).thenReturn(Optional.of(car));

        Optional<Car> result = carService.getCarById(id);

        assertEquals(Optional.of(car), result);
    }

    @Test
    public void testGetCarByIdNonExistent() {
        long id = 1L;
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Car> result = carService.getCarById(id);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testGetAllCarsByUser() {
        long userId = 1L;
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1L, "Tesla", "Model S"));
        cars.add(new Car(2L, "Tesla", "Model X"));
        when(userRepository.existsById(userId)).thenReturn(true);
        when(carRepository.findCarsByUserId(userId)).thenReturn(cars);

        List<Car> result = carService.getAllCarsByUser(userId);
      
        // Verify method calls and assertions
        verify(userRepository, times(1)).existsById(userId);
        verify(carRepository, times(1)).findCarsByUserId(userId);
         assertEquals(cars, result);

    }

    @Test
    public void testGetAllCarsByUserNonExistentUser() {
        long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);

        List<Car> result = carService.getAllCarsByUser(userId);

        verify(userRepository, times(1)).existsById(userId);
        verify(carRepository, never()).findCarsByUserId(userId);
        assertNull(result);
    }

    @Test
    public void testAddCar() {
        long carId = 1L;
        Car carToAdd = new Car(carId, "Tesla", "Model S");
        when(carRepository.save(carToAdd)).thenReturn(new Car(carId, "Tesla", "Model S"));

        Car result = carService.addCar(carToAdd);

        verify(carRepository, times(1)).save(carToAdd);
        assertSame(carToAdd, result);
    }

    @Test
    public void testUpdateCar() {
        long carId = 1L;
        Car newCar = new Car(carId, "Tesla", "Model S");

        when(carRepository.findById(carId)).thenReturn(Optional.of(newCar));

        newCar.setModel("Model X");
        Car result = carService.updateCar(newCar, carId);

        verify(carRepository, times(1)).save(newCar);

        assertEquals(newCar.getModel(), result.getModel());

    }

    @Test
    public void testUpdateCarNonExistent() {
        // Create a sample card for testing
        long nonExistentCarId = 999L;

        // Mock the repository's findById method for a non-existent card
        when(carRepository.findById(nonExistentCarId)).thenReturn(Optional.empty());

        // Call the updateCard method with a card that doesn't exist
        Car updatedCar = new Car();
        updatedCar.setModel("Updated Model");

        // Invoke the updateCard method
        Car result = carService.updateCar(updatedCar, nonExistentCarId);

        // Verify that the repository's save method is not called
        verify(carRepository, never()).save(updatedCar);

        // Check if the result is null as the card doesn't exist
        assertNull(result);
    }

    @Test
    public void testDeleteCar() {
        long carId = 1L;
        when(carRepository.existsById(carId)).thenReturn(true);
        doNothing().when(carRepository).deleteById(carId);

        carService.deleteCar(carId);

        verify(carRepository, times(1)).existsById(carId);
        verify(carRepository, times(1)).deleteById(carId);
    }

    @Test
    public void testDeleteCarNonExistent() {
        long carId = 1L;
        when(carRepository.existsById(carId)).thenReturn(false);

        carService.deleteCar(carId);

        verify(carRepository, times(1)).existsById(carId);
        verify(carRepository, never()).deleteById(carId);
    }

    @Test
    public void testGetCarByUserId() {
        long userId = 1L;
        long carId = 2L;
        Car car = new Car();
        when(userRepository.existsById(userId)).thenReturn(true);
        when(carRepository.findCarByUserIdAndId(userId, carId)).thenReturn(car);

        Car result = carService.getCarByUserId(userId, carId);

        verify(userRepository, times(1)).existsById(userId);
        verify(carRepository, times(1)).findCarByUserIdAndId(userId, carId);
        assertSame(car, result);
    }

    @Test
    public void testGetCarByUserIdNonExistentUser() {
        long userId = 1L;
        long carId = 2L;
        when(userRepository.existsById(userId)).thenReturn(false);

        Car result = carService.getCarByUserId(userId, carId);

        verify(userRepository, times(1)).existsById(userId);
        verify(carRepository, never()).findCarByUserIdAndId(userId, carId);
        assertNull(result);
    }
}