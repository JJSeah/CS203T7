package com.example.electric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="number")
    private long number;
    @Column(name="expiry")
    private Date expiry;

    //link to owner
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Card(long l, String johnDoe, String number, String s)  {
        this.id = l;
        this.name = johnDoe;
        this.number = Long.parseLong(number);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date date = Date.valueOf(LocalDate.parse(s, formatter));
        this.expiry = date;

    }
}
