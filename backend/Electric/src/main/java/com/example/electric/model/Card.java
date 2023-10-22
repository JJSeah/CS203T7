package com.example.electric.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^[0-9]{16}$", flags = Pattern.Flag.UNICODE_CASE)
    @Column(name="number")
    private String number;

    @Column(name="expiry")
    private Date expiry;

    //link to owner
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Card(long l, String johnDoe, String number, String s)  {
        this.id = l;
        this.name = johnDoe;
        this.number = number;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date date = Date.valueOf(LocalDate.parse(s, formatter));
        this.expiry = date;
    }

    public Card(long l, String string, long m, Date valueOf, User user2) {
    }
}
