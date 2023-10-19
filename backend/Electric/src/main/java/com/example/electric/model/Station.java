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
    @Column(name="address")
    private String address;
    @Column(name="latitude")
    private double latitude;
    @Column(name="longitude")
    private double longitude;

    @Column(name="avail")
    private boolean avail;

    //link to charger
//    @JsonIgnore
    @OneToMany (mappedBy = "station", cascade = CascadeType.ALL)
    private List<Charger> chargers;

    private Long chargerId;
    private double chargingRate;

    public Station(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(long id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Station(Long stationId, Long chargerId, String name, double chargingRate, double latitude, double longitude, String address) {
        this.id = stationId;
        this.chargerId = chargerId;
        this.name = name;
        this.chargingRate = chargingRate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public Station(long l, String string, Object object, double d, double e, boolean b, Object object2) {
    }

    

    
}
