package com.example.electric.auth;

import com.example.electric.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final UserServiceImpl userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                // .requestMatchers("/**").permitAll()
                //authorisation here
                    //AuthController
                    .requestMatchers("/auth/**").permitAll()

                    //AppointmentController
                    .requestMatchers(HttpMethod.GET, "/api/appointment/**", "/api/appointment").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/appointment/station/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/appointment").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/appointment/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/appointment/*").permitAll()

                    //carController
                    .requestMatchers(HttpMethod.GET, "/api/car/**", "/api/car").permitAll()
                    // .requestMatchers(HttpMethod.POST,"/api/car").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/car").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/car/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/car/*").permitAll()


                    //cardController
                    .requestMatchers(HttpMethod.GET, "/api/card/**", "/api/card").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/card").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/card/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/card/*").permitAll()

                    //chargerController
                    .requestMatchers(HttpMethod.GET, "/api/charger/**", "/api/charger").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/charger").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/charger/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/charger/*").permitAll()

                    //DistanceController
                    .requestMatchers(HttpMethod.GET, "/api/distance").permitAll()

                    //RecordController
                    .requestMatchers(HttpMethod.GET, "/api/records/all", "/api/records/**").hasRole("USER")
                    .requestMatchers(HttpMethod.POST,"/api/records").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/records/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/records/*").permitAll()


                    //Station contoller
                    // .requestMatchers(HttpMethod.GET, "/api/stations/all", "/api/stations/*").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/stations/all", "/api/stations/*").authenticated()
                    // .requestMatchers(HttpMethod.POST,"/api/stations", "/api/stations/*").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/stations", "/api/stations/*").authenticated()
                    // .requestMatchers(HttpMethod.POST,"/api/stationCheck/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/stationCheck/**").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/stations/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/stations/*").permitAll()

                    //User contoller
                    .requestMatchers(HttpMethod.GET, "/api/user/all", "/api/user/*").permitAll()
                    // .requestMatchers(HttpMethod.GET, "/api/user/all", "/api/user/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/api/user/").permitAll()
//                    .requestMatchers(HttpMethod.PUT, "/api/user/*").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/user/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/user/*").permitAll()



                .anyRequest().authenticated()
                .and().sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}


