package com.example.electric.service;

import com.example.electric.dto.CarDetails;
import com.example.electric.model.Car;
import com.example.electric.respository.CarRepository;
import com.example.electric.respository.UserRepository;
import com.example.electric.service.inter.CarServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CarService implements CarServiceInter {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(long id) {
        return carRepository.findById(id);
    }


    public List<Car> getAllCarsByUser(long userId) {
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return carRepository.findCarsByUserId(userId);

    }

    public Car addCar(Car car) {

        Car newcar = carRepository.save(car);
        String URL = "http://localhost:9091/car/add";
        CarDetails carDetails = new CarDetails(newcar.getId(), newcar.getModel(),newcar.getBatteryPercentage(),"off");
        Map<String, Object> response = new RestTemplate().postForObject(URL, carDetails, Map.class);

        return carRepository.save(car);
    }

    public Car updateCar(Car updatedCar, Long carId) {
        // Step 1: Retrieve the existing car from the database
        Optional<Car> optionalExistingCar = carRepository.findById(carId);

        if (optionalExistingCar.isPresent()) {
            Car existingCar = optionalExistingCar.get();

            // Step 2: Update only the non-null fields of the existing car with the new values
            if (updatedCar.getNickname() != null) {
                existingCar.setNickname(updatedCar.getNickname());
            }
            if (updatedCar.getModel() != null) {
                existingCar.setModel(updatedCar.getModel());
            }
            if (updatedCar.getPlate() != null) {
                existingCar.setPlate(updatedCar.getPlate());
            }
            if (updatedCar.getChargingRate() != 0) {
                existingCar.setChargingRate(updatedCar.getChargingRate());
            }
            if (updatedCar.getBatteryPercentage() != 0.0) {
                existingCar.setBatteryPercentage(updatedCar.getBatteryPercentage());
            }
            if (updatedCar.getBatteryCapacity() != 0) {
                existingCar.setBatteryCapacity(updatedCar.getBatteryCapacity());
            }
            return carRepository.save(existingCar);
        } else {
            return null;
        }
    }

    public void deleteCar(long id) {
        if (!carRepository.existsById(id)) {
            return;
        }
        carRepository.deleteById(id);
    }

    public Car getCarByUserId(long userId, long carId){
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return carRepository.findCarByUserIdAndId(userId, carId);

    }
}
