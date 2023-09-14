package com.example.electric.auth;

import com.example.electric.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = customUserDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;

    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder PasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(PasswordEncoder);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/**").permitAll()
                //authorisation here
                    //AuthController
                    .requestMatchers("/auth/**").permitAll()

                    //AppointmentController
                    .requestMatchers(HttpMethod.GET, "/api/appointment/**", "/api/appointment").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/appointment//station/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/appointment").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/appointment/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/appointment/*").permitAll()

                    //carController
                    .requestMatchers(HttpMethod.GET, "/api/car/**", "/api/car").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/car").permitAll()
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
                    .requestMatchers(HttpMethod.GET, "/api/records/all", "/api/records/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/records").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/records/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/records/*").permitAll()


                    //Station contoller
                    .requestMatchers(HttpMethod.GET, "/api/stations/all", "/api/stations/*").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/stations").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/stations/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/stations/*").permitAll()

                    //User contoller
                    .requestMatchers(HttpMethod.GET, "/api/user/all", "/api/user/*").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/user/").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/user/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/user/*").permitAll()



                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtAuthorizationFilter,UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    // @SuppressWarnings("deprecation")
    // @Bean
    // public NoOpPasswordEncoder passwordEncoder() {
    //     return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    // }

    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


