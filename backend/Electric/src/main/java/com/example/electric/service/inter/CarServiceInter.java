package com.example.electric.service.inter;

import com.example.electric.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarServiceInter {
    List<Car> getAllCars();
    Optional<Car> getCarById(long id);
    List<Car> getAllCarsByUser(long userId);
    Car addCar(Car car);

    Car updateCar(Car updatedCar, Long carId);
    void deleteCar(long id);
    Car getCarByUserId(long userId, long carId);

}
