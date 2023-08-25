package com.example.electric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chargers")
public class Charger {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="char_id")
    private String charId;
    @Column(name="name")
    private String name;
    @Column(name="avali")
    private boolean avali;

    public Charger() {
    }

    public Charger(String charId, String name, boolean avali) {
        this.charId = charId;
        this.name = name;
        this.avali = avali;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharId() {
        return charId;
    }

    public void setCharId(String charId) {
        this.charId = charId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvali() {
        return avali;
    }

    public void setAvali(boolean avali) {
        this.avali = avali;
    }
}