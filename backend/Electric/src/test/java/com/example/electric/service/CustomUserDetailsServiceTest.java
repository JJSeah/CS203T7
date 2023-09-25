package com.example.electric.service;

import com.example.electric.model.User;
import com.example.electric.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void testLoadUserByUsername() {
        // Arrange
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        user.setPassword("password");
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = customUserDetailsService.userDetailsService().loadUserByUsername(email);

        // Assert
        assertEquals(email, userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.userDetailsService().loadUserByUsername(email);
        });
    }
}
