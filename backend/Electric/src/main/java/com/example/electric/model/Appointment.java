package com.example.electric.model;

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

    // Link to station
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    // Link to user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // link to record
    @OneToOne
    @JoinColumn(name = "record_id", nullable = true)
    private Record record;

    public Appointment(long id, Time duration, Time startTime, Time endTime, Date date, User user) {
        this.id = id;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.user = user;
    }



    

}
