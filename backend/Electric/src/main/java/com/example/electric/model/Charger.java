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
    @Column(name="station")
    @OneToOne
    private Station station;
    @Column(name="char_id")
    private String charId;
    @Column(name="name")
    private String name;
    @Column(name="avali")
    private boolean avali;
}