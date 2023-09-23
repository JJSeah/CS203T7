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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void testLoadUserByUsername_UserExists() {
        User user = new User();
        String email = "donta@gmail.com";

        user.setUsername("donta");
        user.setPassword("donta123");
        user.setEmail(email);
        user.setAuthorities("USER");

        when(userRepository.findUserByEmail(email)).thenReturn(user);

        UserDetails result = customUserDetailsService.loadUserByUsername(email);

        assertEquals(user.getEmail(),result.getUsername());
        assertEquals(user.getPassword(),result.getPassword());
        assertTrue(result.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String email = "testing123@gmail.com";

        when(userRepository.findUserByEmail(email)).thenThrow(UsernameNotFoundException.class);

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(email);
        });
    }
}
