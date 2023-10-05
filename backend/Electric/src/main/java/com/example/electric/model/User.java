package com.example.electric.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    public static final int MAX_MANUALAPPT_ALLOWED = 2;

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

    //To Keep track of ManualAppointment made 
    // @Column(name="currentNumOfManualAppt")
    // private int currentNumOfManualAppt = 0;
    //link to Current Manualappointment && keep track of manualAppointments 
    // @JsonIgnore
    @Column(name="manualAppointments")
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Appointment> manualAppointments;


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

    public static int getMaxManualapptAllowed() {
        return MAX_MANUALAPPT_ALLOWED;
    }
}