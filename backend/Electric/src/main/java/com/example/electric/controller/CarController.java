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

    @GetMapping("/{id}")
    @Operation(summary = "Get Car", description = "Get Car using ID",tags = {"Car"})
    public Optional<Car> getCarById(@PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return carService.getCarById(id);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get User's Car", description = "Get a list of User's Car from UserID",tags = {"Car"})
    public List<Car> getAllCarsByUser(@PathVariable("userId")long userId) {
        return carService.getAllCarsByUser(userId);
    }
    @GetMapping("/user/{userId}/car/{carId}")
    @Operation(summary = "Get Car By User", description = "Get Car using ID and UserID",tags = {"Car"})
    public Car getCarByUser(@PathVariable("userId")long userId, @PathVariable("carId")long carId) {
        return carService.getCarByUser(userId, carId);
    }

    @GetMapping
    @Operation(summary = "Get All Car", description = "Get Car using ID",tags = {"Car"})
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/add/{userId}")
    @Operation(summary = "Add Car", description = "Add Car using UserID",tags = {"Car"})
    public Car addCar(@PathVariable("userId") long id, @RequestBody Car car) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        car.setOwner(user);
        return carService.addCar(car);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Car", description = "Update Car using ID",tags = {"Car"})
    public Car updateCar(@RequestBody Car updatedCar, @PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return carService.updateCar(updatedCar, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Car", description = "Delete Car using ID",tags = {"Car"})
    public void deleteCar(@PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        carService.deleteCar(id);
    }
}
