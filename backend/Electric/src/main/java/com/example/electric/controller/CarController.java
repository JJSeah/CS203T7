package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Car;
import com.example.electric.model.User;
import com.example.electric.service.CarService;
import com.example.electric.service.UserService;
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
    public Optional<Car> getCarById(@PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return carService.getCarById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Car> getAllCarsByUser(@PathVariable("userId")long userId) {
        return carService.getAllCarsByUser(userId);
    }
    @GetMapping("/user/{userId}/car/{carId}")
    public Car getCarByUser(@PathVariable("userId")long userId, @PathVariable("carId")long carId) {
        return carService.getCarByUser(userId, carId);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/add/{userId}")
    public Car addCar(@PathVariable("userId") long id, @RequestBody Car car) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        car.setOwner(user);
        return carService.addCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@RequestBody Car updatedCar, @PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return carService.updateCar(updatedCar, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") long id) {
        if (!carService.getCarById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        carService.deleteCar(id);
    }
}
