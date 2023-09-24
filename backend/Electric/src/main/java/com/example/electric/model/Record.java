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

    //link to user 
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    //link to car
    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

    @Column(name="time")
    private Time duration;
    @Column(name="cost")
    private double cost;


    @ManyToOne
    @JoinColumn(name="station")
    private Station station;
    
    @Column(name="start_time")
    private Time startTime;
    @Column(name="end_time")
    private Time endTime;
    @Column(name="date")
    private Date date;

//    link to appointment
    @OneToOne(mappedBy = "record")
    private Appointment appointment;

    public Record(long id) {
        this.id = id;
    }

    public Record(long id, User user) {
        this.id = id;
        this.user = user;
    }

    
}
