package com.example.electric.model;

import jakarta.persistence.*;
import com.example.electric.model.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    //No car object yet
//    private Car[] cars;
    //Card object not added
}