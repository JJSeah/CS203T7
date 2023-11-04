package com.example.electric.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user")
//@Table(name="user_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(unique = true, name="usernames")
    private String usernames;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", flags = Pattern.Flag.UNICODE_CASE)
    @Column(unique = true, name = "Email")
    private String email;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    //link to cars
    @Column(name="cars")
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars;

    //link to Credit card
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Card> card;

    //link to appointment
    @JsonIgnore
    @Column(name="appointment")
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Appointment> appointment;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        // email in our case
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }


        public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(long userId, String firstName) {
        this.id = userId;
        this.firstName = firstName;
    }

    public User(long userId, String firstName, String password) {
        this.id = userId;
        this.firstName = firstName;
        this.password = password;
    }

    public User(long userId, String firstName, String password, String email) {
        this.id = userId;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
    }


    public User(long l, String string, String string2, String string3, String string4, String string5, Role roleUser,
            Object object, Object object2, Object object3) {
    }

    public User(String firstName, String lastName, String usernames,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", flags = Flag.UNICODE_CASE) String email,
            String password, Role role, List<Car> cars, List<Card> card, List<Appointment> appointment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.usernames = usernames;
        this.email = email;
        this.password = password;
        this.role = role;
        this.cars = cars;
        this.card = card;
        this.appointment = appointment;
    }


}