package com.example.electric.respository;

import com.example.electric.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    public List<Car> findCarsByOwnerId(long ownerId);

    Car findCarByOwnerIdAndId(long userId, long carId);

}
