package com.example.electric.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "duration")
    private Time duration;
    @Column(name = "start_time")
    private Time startTime;
    @Column(name = "end_time")
    private Time endTime;
    @Column(name = "date")
    private Date date;
    @Column(name="cost")
    private double cost;
    @Column(name="status")
    private String status;

    // Link to station
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    // Link to user
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Link to charger
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "charger_id")
    private Charger charger;

    public Appointment(long id, Time duration, Time startTime, Time endTime, Date date, User user, double cost) {
        this.id = id;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.user = user;
        this.cost = cost;
    }

    public Appointment(long l, Time time, Time time2, Time time3, Date date2, int i, Object object, Object object2) {
    }





    

}
