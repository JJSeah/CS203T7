package com.example.electric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="latitude")
    private double latitude;
    @Column(name="longitude")
    private double longitude;
    @Column(name="chargers")
    @OneToMany
    private List<Charger> chargers;
    @Column(name="avail")
    private boolean avail;
}
