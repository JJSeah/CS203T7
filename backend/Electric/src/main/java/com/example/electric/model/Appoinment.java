package com.example.electric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name="appoinment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appoinment {
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
}
