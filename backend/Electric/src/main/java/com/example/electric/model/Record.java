package com.example.electric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @JoinColumn(name="owner")
    @OneToOne
    private User owner;
    @JoinColumn(name="car")
    @OneToOne
    private Car car;
    @Column(name="time")
    private Time duration;
    @Column(name="cost")
    private double cost;
    @JoinColumn(name="station")
    @OneToOne
    private Station station;
    @Column(name="start_time")
    private Time startTime;
    @Column(name="end_time")
    private Time endTime;
    @Column(name="date")
    private Date date;
}
