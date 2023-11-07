package com.example.electric.auth;

import com.example.electric.service.inter.UserServiceDetailsInter;
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

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final UserServiceDetailsInter userService;

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
                // authorisation here
                // AuthController
                .requestMatchers(antMatcher("/auth/**")).permitAll()
                // .requestMatchers(antMatcher("/api/**")).permitAll()

                // //AppointmentController
                .requestMatchers(HttpMethod.GET, "/api/appointment/**", "/api/appointment", "/api/appointment/*").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/appointment/station/*").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/appointment").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/appointment/auto/*").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/appointment/available").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/appointment/*").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/appointment/completed/*").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/appointment/cancel/*").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/appointment/*").authenticated()
                //
                //carController
                .requestMatchers(HttpMethod.GET, "/api/car/**", "/api/car", "/api/car/*" ).authenticated()
                .requestMatchers(HttpMethod.POST,"/api/car/add/*").authenticated()
                .requestMatchers(HttpMethod.POST,"/api/car").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/car/*").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/car/*").authenticated()
                //
                //
                // cardController
                .requestMatchers(HttpMethod.GET, "/api/card/**", "/api/card/*").authenticated()
                .requestMatchers(HttpMethod.POST,"/api/card").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/card/*").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/card/*").authenticated()
                //
                //chargerController
                .requestMatchers(HttpMethod.GET, "/api/charger/**","/api/charger", "/api/charger/*").authenticated()
                .requestMatchers(HttpMethod.POST,"/api/charger").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/charger/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/charger/*").hasRole("ADMIN")
                //
                //DistanceController
                .requestMatchers(HttpMethod.GET, "/api/distance").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/charging/time").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/charging/cost").authenticated()

                //
                //
                //Station contoller
                .requestMatchers(HttpMethod.GET, "/api/stations/all",
                        "/api/stations/*").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/stations/all",
                        "/api/stations/*").authenticated()
                .requestMatchers(HttpMethod.POST,"/api/stations",
                        "/api/stations/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/stations",
                        "/api/stations/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/stationCheck/**").authenticated()
                .requestMatchers(HttpMethod.POST,"/api/stations/closest").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/stations/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/stations/*").hasRole("ADMIN")

                //User contoller
                .requestMatchers(HttpMethod.GET, "/api/user/*").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/user/all").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/user/").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/user/*").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/user/*").authenticated()

                .anyRequest().authenticated()
                .and().sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS));
        return http.build();
    }


}


