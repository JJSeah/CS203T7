package com.example.electric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="duration")
    private Time duration;
    @Column(name="start_time")
    private Time startTime;
    @Column(name="end_time")
    private Time endTime;
    @Column(name="date")
    private Date date;
    @JoinColumn(name="station")
    @OneToOne
    private Station station;
}
