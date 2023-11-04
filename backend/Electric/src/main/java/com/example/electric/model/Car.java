package com.example.electric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="nickname")
    private String nickname;
    @Column(name="model")
    private String model;
    @Column(name="plate")
    private String plate;
    @Column(name="charging_rate")
    private int chargingRate;
    @Column(name="battery_percentage")
    private double batteryPercentage;
    @Column(name="battery_capacity")
    private int batteryCapacity;

    //link to owner(user)
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    public Car(long id, String tesla, String modelS) {
        this.id = id;
        this.nickname = tesla;
        this.model = modelS;
    }


    public Car(String nickname, String model, String plate, int chargingRate, double batteryPercentage,
            int batteryCapacity, User user) {
        this.nickname = nickname;
        this.model = model;
        this.plate = plate;
        this.chargingRate = chargingRate;
        this.batteryPercentage = batteryPercentage;
        this.batteryCapacity = batteryCapacity;
        this.user = user;
    }

}
