package com.example.electric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

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
    @JoinColumn(name="owner.id")
    private User owner;

    //link to records
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Record> records; 
    

}
