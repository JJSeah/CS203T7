package com.example.electric.respository;

import com.example.electric.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    public List<Car> findCarsByUserId(long userId);

    Car findCarByUserIdAndId(long userId, long carId);
}
