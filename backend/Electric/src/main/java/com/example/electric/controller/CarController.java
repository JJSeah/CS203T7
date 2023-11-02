package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Car;
import com.example.electric.model.User;
import com.example.electric.service.CarService;
import com.example.electric.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    /**
     * Retrieve a car by its unique identifier.
     *
     * This endpoint retrieves a car from the system based on its unique identifier
     * (ID).
     * If a car with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the car to retrieve.
     * @return An Optional containing the retrieved car, or an empty Optional if not
     *         found.
     * @throws ObjectNotFoundException If no car with the given ID is found.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get Car", description = "Get Car using ID", tags = { "Car" })
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
    @Operation(summary = "Get User's Car", description = "Get a list of User's Car from UserID", tags = { "Car" })
    public List<Car> getAllCarsByUser(@PathVariable("userId") long userId) {
        return carService.getAllCarsByUser(userId);
    }


    /**
     * Retrieve a car by its ID and associated user.
     *
     * This endpoint allows the retrieval of a specific car using its unique CarID
     * and the associated UserID. It ensures that the requested car is owned by the
     * specified user.
     *
     * @param userId The unique identifier (UserID) of the user.
     * @param carId  The unique identifier (CarID) of the car.
     * @return The car associated with the provided CarID and UserID.
     */
    @GetMapping("/user/{userId}/car/{carId}")
    @Operation(summary = "Get Car By User", description = "Get Car using ID and UserID", tags = { "Car" })
    public Car getCarByUser(@PathVariable("userId") long userId, @PathVariable("carId") long carId) {
        return carService.getCarByUserId(userId, carId);
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
    @Operation(summary = "Get All Car", description = "Get Car using ID", tags = { "Car" })
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
    @PostMapping("/add/{userId}")
    @Operation(summary = "Add Car", description = "Add Car using UserID", tags = { "Car" })
    public Car addCar(@PathVariable("userId") long id, @RequestBody Car car) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        car.setUser(user);
        return carService.addCar(car);
    }

    /**
     * Update an existing car with the provided information.
     *
     * This endpoint allows the update of an existing car identified by its unique
     * identifier (ID). The provided updatedCar object should contain the updated
     * details
     * for the car. If a car with the specified ID is not found, it will result in
     * an
     * ObjectNotFoundException.
     *
     * @param id         The unique identifier of the car to update.
     * @param updatedCar The updated car object containing new information.
     * @return The updated car.
     * @throws ObjectNotFoundException If no car with the given ID is found.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update Car", description = "Update Car using ID", tags = { "Car" })
    public Car updateCar(@RequestBody Car updatedCar, @PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return carService.updateCar(updatedCar, id);
    }

    /**
     * Update an existing car's battery percentage with the provided information.
     *
     * This endpoint allows the update of an existing car's battery percentage
     * identified by its unique identifier (ID). The provided 'updatedCar' object
     * should contain the new battery percentage for the car. If a car with the specified
     * ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param id         The unique identifier of the car to update.
     * @param updatedCar The updated car object containing the new battery percentage.
     * @throws ObjectNotFoundException If no car with the given ID is found.
     */
    @PutMapping("/battery/{id}")
    @Operation(summary = "Update Car Battery ", description = "Update Car Battery using ID", tags = { "Car" })
    public void updateCarBattery(@PathVariable("id") long id,@RequestBody Car updatedCar) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        carService.updateCarBattery(id, updatedCar);
    }

    /**
     * Delete a car by its unique identifier.
     *
     * This endpoint allows the deletion of a car from the system based on its
     * unique
     * identifier (ID). If a car with the specified ID is not found, it will result
     * in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the car to delete.
     * @throws ObjectNotFoundException If no car with the given ID is found.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Car", description = "Delete Car using ID", tags = { "Car" })
    public void deleteCar(@PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        carService.deleteCar(id);
    }
}
