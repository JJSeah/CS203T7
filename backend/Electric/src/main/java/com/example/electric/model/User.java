package com.example.electric.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user")
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
    @Column(name="username")
    private String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", flags = Pattern.Flag.UNICODE_CASE)
    @Column(unique = true, name = "Email")
    private String email;

    @Column(name="password")
    private String password;

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
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Appointment> appointment;

    //link to record
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Record> records;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

        @Column(name ="authorities")
    // @NotNull(message = "Authorities should not be null")
    // We define two roles/authorities: ROLE_USER or ROLE_ADMIN (for now)
    private String authorities;

    /* Return a collection of authorities (roles) granted to the user.
    */

    public String getAuthority(){
        return authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities == null){
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return Arrays.asList(new SimpleGrantedAuthority(authorities));
    }

    /*
    The various is___Expired() methods return a boolean to indicate whether
    or not the user’s account is enabled or expired.
    */
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

    public User(long userId, String john) {
        this.id = userId;
        this.firstName = john;
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
}