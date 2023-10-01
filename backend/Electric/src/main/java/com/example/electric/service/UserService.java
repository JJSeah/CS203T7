package com.example.electric.service;

import com.example.electric.model.Role;
import com.example.electric.model.User;
import com.example.electric.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Update the user fields as needed
            if(updatedUser.getFirstName() != null){
                user.setFirstName(updatedUser.getFirstName());
            }
            if(updatedUser.getLastName() != null){
             user.setLastName(updatedUser.getLastName());
            }
            if(updatedUser.getUsername() != null){
            user.setUsernames(updatedUser.getUsername());
            }
            if(updatedUser.getEmail() != null){
                user.setEmail(updatedUser.getEmail());
            }
            if(updatedUser.getPassword() != null){
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            if(updatedUser.getCars() != null){
                user.setCars(updatedUser.getCars());
            }
            if(updatedUser.getCard() != null){
                user.setCard(updatedUser.getCard());
            }
//            if(updatedUser.getAuthorities() != null){
//                user.setAuthorities(updatedUser.getAuthority());
//            }

            return userRepository.save(user);
        } else {
            return null; // User not found
        }
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return;
        }
        userRepository.deleteById(id);
    }

    public User getUserByEmail(String email) {  // check if user exists
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    public boolean isEmailUnique(String email) {
        Optional<User> existinguser = userRepository.findByEmail(email);
        return !existinguser.isPresent();
    }

}

