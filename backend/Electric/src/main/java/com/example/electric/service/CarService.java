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

    /**
     * Get All Cars
     *
     * This method retrieves a list of all cars stored in the system.
     *
     * @return A list of all cars in the system.
     */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /**
     * Get Car by ID
     *
     * This method retrieves a car by its unique identifier (ID).
     *
     * @param id The unique identifier of the car to retrieve.
     * @return An optional containing the car, or empty if the car is not found.
     */
    public Optional<Car> getCarById(long id) {
        return carRepository.findById(id);
    }


    /**
     * Get All Cars by User ID
     *
     * This method retrieves a list of cars associated with a specific user based on their unique user identifier.
     * If the user with the given ID is not found, it returns null.
     *
     * @param userId The unique identifier of the user.
     * @return A list of cars associated with the user or null if the user is not found.
     */
    public List<Car> getAllCarsByUser(long userId) {
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return carRepository.findCarsByUserId(userId);

    }

    /**
     * Add Car
     *
     * This method allows the addition of a new car to the system. It also triggers an external action to add
     * the car using a REST API. The new car is then returned.
     *
     * @param car The car to be added to the system.
     * @return The newly added car.
     */
    public Car addCar(Car car) {

        Car newcar = carRepository.save(car);
        String URL = "http://13.236.9.86:9091/car/add";
        CarDetails carDetails = new CarDetails(newcar.getId(), newcar.getModel(),newcar.getBatteryPercentage(),"off");
        Map<String, Object> response = new RestTemplate().postForObject(URL, carDetails, Map.class);

        return car;
    }

    /**
     * Update Car
     *
     * This method allows the update of an existing car based on the provided updated car details.
     * It identifies the car by its unique identifier (ID) and updates its fields as needed.
     *
     * @param updatedCar The updated car details.
     * @param carId The unique identifier of the car to update.
     * @return The updated car after the changes are applied, or null if the car is not found.
     */
    public Car updateCar(Car updatedCar, Long carId) {
        // Step 1: Retrieve the existing car from the database
        Optional<Car> optionalExistingCar = carRepository.findById(carId);

        if (optionalExistingCar.isPresent()) {
            Car existingCar = optionalExistingCar.get();

            // Step 2: Update only the non-null fields of the existing car with the new values
            if (updatedCar.getNickname() != null) existingCar.setNickname(updatedCar.getNickname());
            if (updatedCar.getModel() != null) existingCar.setModel(updatedCar.getModel());
            if (updatedCar.getPlate() != null) existingCar.setPlate(updatedCar.getPlate());
            if (updatedCar.getChargingRate() != 0) existingCar.setChargingRate(updatedCar.getChargingRate());
            if (updatedCar.getBatteryPercentage() != 0.0) existingCar.setBatteryPercentage(updatedCar.getBatteryPercentage());
            if (updatedCar.getBatteryCapacity() != 0) existingCar.setBatteryCapacity(updatedCar.getBatteryCapacity());
            carRepository.save(existingCar);
            return existingCar;
        } else {
            return null;
        }
    }

    /**
     * Delete Car
     *
     * This method allows the deletion of a car based on its unique identifier (ID).
     *
     * @param id The unique identifier of the car to delete.
     */
    public void deleteCar(long id) {
        if (!carRepository.existsById(id)) {
            return;
        }
        carRepository.deleteById(id);
    }

    /**
     * Get Car by User ID and Car ID
     *
     * This method retrieves a car associated with a specific user based on their unique user identifier and the car's unique identifier.
     * If the user with the given ID is not found, it returns null.
     *
     * @param userId The unique identifier of the user.
     * @param carId The unique identifier of the car.
     * @return The car associated with the user and car ID, or null if the user is not found.
     */
    public Car getCarByUserId(long userId, long carId){
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return carRepository.findCarByUserIdAndId(userId, carId);

    }
}
