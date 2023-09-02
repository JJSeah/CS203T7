package com.example.electric.service;

import com.example.electric.model.Car;
import com.example.electric.respository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(long id) {
        return carRepository.findById(id);
    }

    public List<Car> getAllCarsByUser(long ownerId) {
        return carRepository.findCarsByOwnerId(ownerId);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Car updatedCar, long id) {
        if (!carRepository.existsById(id)) {
            return null;
        }

        updatedCar.setId(id);
        return carRepository.save(updatedCar);
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }
}
