package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Car;
import com.example.electric.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * Retrieve a car by its unique identifier.
     *
     * This endpoint retrieves a car from the system based on its unique identifier (ID).
     * If a car with the specified ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param id The unique identifier of the car to retrieve.
     * @return An Optional containing the retrieved car, or an empty Optional if not found.
     * @throws ObjectNotFoundException If no car with the given ID is found.
     */
    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return carService.getCarById(id);
    }

    /**
     * Retrieve a list of all cars owned by a specific user.
     *
     * This endpoint retrieves a list of all cars associated with a particular user
     * identified by their unique identifier (userId).
     *
     * @param userId The unique identifier of the user to retrieve cars for.
     * @return A list of cars owned by the specified user.
     */
    @GetMapping("/user/{userId}")
    public List<Car> getAllCarsByUser(@PathVariable("userId")long userId) {
        return carService.getAllCarsByUser(userId);
    }

    /**
     * Retrieve a list of all cars.
     *
     * This endpoint retrieves a list of all cars currently stored in the system.
     * If no cars are available, an empty list will be returned.
     *
     * @return A list of cars, which may be empty if no cars are found.
     */
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    /**
     * Add a new car to the system.
     *
     * This endpoint allows the addition of a new car to the system. The provided
     * car object should contain the necessary details for creating the car.
     *
     * @param car The car object to be added.
     * @return The newly created car.
     */
    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    /**
     * Update an existing car with the provided information.
     *
     * This endpoint allows the update of an existing car identified by its unique
     * identifier (ID). The provided updatedCar object should contain the updated details
     * for the car. If a car with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the car to update.
     * @param updatedCar The updated car object containing new information.
     * @return The updated car.
     * @throws ObjectNotFoundException If no car with the given ID is found.
     */
    @PutMapping("/{id}")
    public Car updateCar(@RequestBody Car updatedCar, @PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return carService.updateCar(updatedCar, id);
    }

    /**
     * Delete a car by its unique identifier.
     *
     * This endpoint allows the deletion of a car from the system based on its unique
     * identifier (ID). If a car with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the car to delete.
     * @throws ObjectNotFoundException If no car with the given ID is found.
     */
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        carService.deleteCar(id);
    }
}
