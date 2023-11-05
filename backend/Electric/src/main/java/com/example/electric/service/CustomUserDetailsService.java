package com.example.electric.service;


import com.example.electric.respository.UserRepository;
import com.example.electric.service.inter.UserServiceDetailsInter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserServiceDetailsInter {
    private final UserRepository userRepository;

    /**
     * User Details Service
     *
     * This method creates a custom UserDetailsService, which is used to load user details by their username.
     * It attempts to find a user in the system by their email (username) and returns the corresponding UserDetails.
     *
     * @return A UserDetailsService implementation that loads user details by their username (email).
     * @throws UsernameNotFoundException If the user with the specified email (username) is not found.
     */
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                System.out.println("username: " + username);
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
