package com.example.electric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "charger")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Charger {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="char_id")
    private String charId;
    @Column(name="name")
    private String name;
    @Column(name="avail")
    private boolean avail;
    @Column(name="type")
    private String type;
    @Column(name="charging_rate")
    private double chargingRate;

    //Link to Station
    @ManyToOne
    @JoinColumn(name="station_id")
    private Station station;

    public boolean getAvail(){
        return this.avail;
    }

    public Charger(Long id) {
        this.id = id;
}

    public Charger(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Charger(Long id, String name, Station station) {
        this.id = id;
        this.name = name;
        this.station = station;
    }

}