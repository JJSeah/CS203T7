package com.example.car.controller;

import com.example.car.models.CarDetails;
import com.example.car.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/status")
    public String status() {
        return "OK it is working";
    }

    @GetMapping("/battery/{id}")
    public double updateBattery(@PathVariable("id") long id) {
          return carService.getCarStatus(id);
    }

    @GetMapping("/all")
    public List<CarDetails> all() {
        return carService.getAllCars();
    }

    @GetMapping("{id}")
    public CarDetails getCarById(@PathVariable("id") long id) {
        return carService.getCarByCarId(id);
    }

    @PutMapping("/update")
    public CarDetails updateCar(@RequestBody CarDetails car) {
        System.out.println(car);
        return carService.updateCar(car);
    }

    @PostMapping("/add")
    public CarDetails addCar(@RequestBody CarDetails car) {
        System.out.println(car);
        return carService.addCar(car);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable("id") long id) {
        carService.deleteCar(id);
    }
    @GetMapping("/start/{id}")
    public CarDetails start(@PathVariable("id") long id) {
        return carService.start(id);
    }

    @GetMapping("/stop/{id}")
    public CarDetails stop(@PathVariable("id") long id) {
        return carService.stop(id);
    }


}
