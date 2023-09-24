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

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
        // Mock data
        List<Car> cars = new ArrayList<>();
        when(carRepository.findAll()).thenReturn(cars);

        // Call the service method
        List<Car> result = carService.getAllCars();

        // Verify method calls and assertions
        verify(carRepository, times(1)).findAll();
        assertSame(cars, result);
    }

//    @Test
//    public void testGetCarById() {
//        // Mock data
//        long carId = 1L;
//        Car car = new Car();
//        when(carRepository.existsById(carId)).thenReturn(true);
//        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
//
//        // Call the service method
//        Optional<Car> result = carService.getCarById(carId);
//
//        // Verify method calls and assertions
//        verify(carRepository, times(1)).existsById(carId);
//        verify(carRepository, times(1)).findById(carId);
//        assertTrue(result.isPresent());
//        assertSame(car, result.get());
//    }
//
//    @Test
//    public void testGetCarByIdNonExistent() {
//        // Mock data
//        long carId = 1L;
//        when(carRepository.existsById(carId)).thenReturn(false);
//
//        // Call the service method
//        Optional<Car> result = carService.getCarById(carId);
//
//        // Verify method calls and assertions
//        verify(carRepository, times(1)).existsById(carId);
//        verify(carService).getCarById(carId);
//        assertFalse(result.isPresent());
//    }

    @Test
    public void testGetAllCarsByUser() {
        // Mock data
        long userId = 1L;
        List<Car> cars = new ArrayList<>();
        when(userRepository.existsById(userId)).thenReturn(true);
        when(carRepository.findCarsByUserId(userId)).thenReturn(cars);

        // Call the service method
        List<Car> result = carService.getAllCarsByUser(userId);

        // Verify method calls and assertions
        verify(userRepository, times(1)).existsById(userId);
        verify(carRepository, times(1)).findCarsByUserId(userId);
        assertSame(cars, result);
    }

    @Test
    public void testGetAllCarsByUserNonExistentUser() {
        // Mock data
        long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);

        // Call the service method
        List<Car> result = carService.getAllCarsByUser(userId);

        // Verify method calls and assertions
        verify(userRepository, times(1)).existsById(userId);
        verify(carRepository, never()).findCarsByUserId(userId);
        assertNull(result);
    }

    @Test
    public void testAddCar() {
        // Mock data
        Car carToAdd = new Car();
        when(carRepository.save(carToAdd)).thenReturn(carToAdd);

        // Call the service method
        Car result = carService.addCar(carToAdd);

        // Verify method calls and assertions
        verify(carRepository, times(1)).save(carToAdd);
        assertSame(carToAdd, result);
    }

    @Test
    public void testUpdateCar() {
        // Mock data
        long carId = 1L;
        Car updatedCar = new Car();
        updatedCar.setId(carId);
        when(carRepository.existsById(carId)).thenReturn(true);
        when(carRepository.save(updatedCar)).thenReturn(updatedCar);

        // Call the service method
        Car result = carService.updateCar(updatedCar, carId);

        // Verify method calls and assertions
        verify(carRepository, times(1)).existsById(carId);
        verify(carRepository, times(1)).save(updatedCar);
        assertSame(updatedCar, result);
    }

    @Test
    public void testUpdateCarNonExistent() {
        // Mock data
        long carId = 1L;
        Car updatedCar = new Car();
        updatedCar.setId(carId);
        when(carRepository.existsById(carId)).thenReturn(false);

        // Call the service method
        Car result = carService.updateCar(updatedCar, carId);

        // Verify method calls and assertions
        verify(carRepository, times(1)).existsById(carId);
        verify(carRepository, never()).save(updatedCar);
        assertNull(result);
    }

    @Test
    public void testDeleteCar() {
        // Mock data
        long carId = 1L;
        when(carRepository.existsById(carId)).thenReturn(true);
        doNothing().when(carRepository).deleteById(carId);

        // Call the service method
        carService.deleteCar(carId);

        // Verify method calls
        verify(carRepository, times(1)).existsById(carId);
        verify(carRepository, times(1)).deleteById(carId);
    }

    @Test
    public void testDeleteCarNonExistent() {
        // Mock data
        long carId = 1L;
        when(carRepository.existsById(carId)).thenReturn(false);

        // Call the service method
        carService.deleteCar(carId);

        // Verify method calls
        verify(carRepository, times(1)).existsById(carId);
        verify(carRepository, never()).deleteById(carId);
    }

    @Test
    public void testGetCarByUserId() {
        // Mock data
        long userId = 1L;
        long carId = 2L;
        Car car = new Car();
        when(userRepository.existsById(userId)).thenReturn(true);
        when(carRepository.findCarByUserIdAndId(userId, carId)).thenReturn(car);

        // Call the service method
        Car result = carService.getCarByUserId(userId, carId);

        // Verify method calls and assertions
        verify(userRepository, times(1)).existsById(userId);
        verify(carRepository, times(1)).findCarByUserIdAndId(userId, carId);
        assertSame(car, result);
    }

    @Test
    public void testGetCarByUserIdNonExistentUser() {
        // Mock data
        long userId = 1L;
        long carId = 2L;
        when(userRepository.existsById(userId)).thenReturn(false);

        // Call the service method
        Car result = carService.getCarByUserId(userId, carId);

        // Verify method calls and assertions
        verify(userRepository, times(1)).existsById(userId);
        verify(carRepository, never()).findCarByUserIdAndId(userId, carId);
        assertNull(result);
    }
}