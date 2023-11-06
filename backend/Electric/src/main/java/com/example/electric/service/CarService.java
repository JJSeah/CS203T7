package com.example.electric.service;

import com.example.electric.dto.CarDetails;
import com.example.electric.model.Car;
import com.example.electric.respository.CarRepository;
import com.example.electric.respository.UserRepository;
import com.example.electric.service.inter.CarServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
        String URL = "http://52.65.206.241:9091/car/add";
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
            if (updatedCar.getChargingRate() >= 0) existingCar.setChargingRate(updatedCar.getChargingRate());
            if (updatedCar.getBatteryPercentage() >= 0.0 || updatedCar.getBatteryPercentage() <= 100.0) existingCar.setBatteryPercentage(updatedCar.getBatteryPercentage());
            if (updatedCar.getBatteryCapacity() >= 0) existingCar.setBatteryCapacity(updatedCar.getBatteryCapacity());
            carRepository.save(existingCar);
            return existingCar;
        } else {
            return null;
        }
    }

    /**
     * Update the battery percentage of an existing car.
     *
     * This method updates the battery percentage of an existing car identified by its unique ID.
     * The 'updatedCar' object should contain the new battery percentage value, which should be within
     * the valid range of 0 to 100 (inclusive). If the 'updatedCar' battery percentage is within the
     * valid range, it updates the car's battery percentage and saves it to the database.
     *
     * @param id         The unique identifier of the car to update.
     */
    public void updateCarBattery(long id) {
        Car car = carRepository.findById(id).get();
        String URL = "http://52.65.206.241:9091/car/battery/" + id;
        String obj =  new RestTemplate().getForObject(URL, String.class);
        double batt = Double.parseDouble(obj);
        System.out.println(batt);
        if (batt > 0.0 && batt < 100.0) car.setBatteryPercentage(batt);
        carRepository.save(car);
    }

    /**
     * Remove Car
     *
     * This method allows the deletion of a car based on its unique identifier (ID).
     *
     * @param id The unique identifier of the car to delete.
     */
    public void RemoveCar(long id) {
        if (!carRepository.existsById(id)) {
            return;
        }
        carRepository.deleteById(id);
    }

    /**
            * Delete Car
     *
             * This method allows the deletion of a car based on its ID, user relationship is set to null.
            *
            * @param id The unique identifier of the car to delete.
     */

    public void deleteCar(long id){
        if (!carRepository.existsById(id)) {
            return;
        }
        Car car = carRepository.findById(id).get();
        car.setUser(null);
        carRepository.save(car);
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

    @Scheduled(fixedRate = 10000) // 10000 milliseconds = 10 sec
    public void updateBattery() {
        try {
            List<Car> cars = carRepository.findAll(); // Retrieve all cars or filter as needed
            for (Car car : cars) {
                updateCarBattery(car.getId());
            }
        } catch (Exception e) {
            // Handle exceptions appropriately, e.g., log the error or send notifications.
            e.printStackTrace();
        }
    }


}
