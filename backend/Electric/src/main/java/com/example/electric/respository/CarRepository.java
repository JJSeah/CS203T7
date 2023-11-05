package com.example.electric.respository;

import com.example.electric.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     * Retrieve Cars by User ID
     *
     * This method retrieves a list of cars associated with a specific user identified by their unique ID.
     *
     * @param userId The unique identifier of the user.
     * @return A list of Car objects related to the specified user.
     */
    public List<Car> findCarsByUserId(long userId);

    /**
     * Retrieve a Car by User ID and Car ID
     *
     * This method retrieves a specific car associated with a particular user identified by their unique user ID and the car's unique ID.
     *
     * @param userId The unique identifier of the user.
     * @param carId The unique identifier of the car.
     * @return The Car object related to the specified user and car IDs.
     */
    Car findCarByUserIdAndId(long userId, long carId);
}
