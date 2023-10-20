package com.example.car.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CarDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="car_id")
    private long carId;

    @Column(name="car_model")
    private String carModel;

    @Column(name="battery")
    private double battery;

    //status can be driving,charging,off
    @Column(name="status")
    private String status;


}
