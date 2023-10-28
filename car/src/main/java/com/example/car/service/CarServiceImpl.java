package com.example.car.service;

import com.example.car.models.CarDetails;
import com.example.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    public List<CarDetails> getAllCars() {
        return carRepository.findAll();
    }

    public CarDetails getCarByCarId(long id) {
        return carRepository.findByCarId(id);
    }

    public CarDetails addCar(CarDetails car) {
        return carRepository.save(car);
    }

    public CarDetails updateCar(CarDetails car) {
        return carRepository.save(car);
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }

    //    update battery as time passes
    @Scheduled(fixedRate = 300000) // 300000 milliseconds = 5 minutes
    public void updateBattery() {
        try {
            List<CarDetails> cars = carRepository.findAll(); // Retrieve all cars or filter as needed
            for (CarDetails car : cars) {
                updateBatteryForCar(car);
            }
        } catch (Exception e) {
            // Handle exceptions appropriately, e.g., log the error or send notifications.
            e.printStackTrace();
        }
    }

    private void updateBatteryForCar(CarDetails car) {
        if (car.getBattery() == 100.0 || car.getBattery() == 0.0) {
            return;
        }
        else if (car.getStatus().equals("charging")) {
            car.setBattery(car.getBattery() + 4.0);
        } else if (car.getStatus().equals("driving")) {
            car.setBattery(car.getBattery() - 2.0);
        } else {
            car.setBattery(car.getBattery() - 0.1);
        }
        carRepository.save(car);
        // Optionally, log the update for monitoring and debugging
        System.out.println("Updated battery for car: " + car.getId() + ", New Battery Level: " + car.getBattery());
    }

    //update car status
    public void updateCarStatus(CarDetails car, String status) {
        car.setStatus(status);
        carRepository.save(car);
    }


    public CarDetails start(long id) {
        CarDetails car = carRepository.findByCarId(id);
        updateCarStatus(car, "charging");
        return carRepository.save(car);
    }

    public CarDetails stop(long id) {
        CarDetails car = carRepository.findByCarId(id);
        updateCarStatus(car, "driving");
        return carRepository.save(car);
    }
}
