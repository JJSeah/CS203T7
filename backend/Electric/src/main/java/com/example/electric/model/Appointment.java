package com.example.electric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    public static final int MAX_MANUALAPPT_ALLOWED = 2;

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
    @Column(name="transaction_id")
    private String transactionId;

    // Link to station
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    // Link to user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Link to charger
    @ManyToOne
    @JoinColumn(name = "charger_id")
    private Charger charger;

    //ManualBooking = true, AutoBooking = False;
    @Column(name="manual_Appointment")
    private boolean manualAppointment;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

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

    public Appointment(int i, Time time, Time time1, Time time2, Date date, Double cost, String active, Station closestStation, User user, Charger charger) {
    }

    public static int getMaxManualapptAllowed() {
        return MAX_MANUALAPPT_ALLOWED;
    }

    public Appointment(long l) {
    }



    





    

}
