package com.example.electric.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="station_id")
    private Station station;

    public Charger(long l, String chargerA, boolean b, String typeA, double v, Station stationA) {
        this.id = l;
        this.charId = chargerA;
        this.avail = b;
        this.type = typeA;
        this.chargingRate = v;
        this.station = stationA;
    }

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