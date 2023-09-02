package com.example.electric.controller;

import com.example.electric.model.Car;
import com.example.electric.model.User;
import com.example.electric.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable("id") long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Car> getAllCarsByUser(@PathVariable("user")long userId) {
        return carService.getAllCarsByUser(userId);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/add")
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @PutMapping("/update/{id}")
    public Car updateCar(@RequestBody Car updatedCar, @PathVariable("id") long id) {
        return carService.updateCar(updatedCar, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable("id") long id) {
        carService.deleteCar(id);
    }
}
