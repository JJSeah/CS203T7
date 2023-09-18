package com.example.electric.service;

import com.example.electric.model.User;
import com.example.electric.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
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
            user.setUsername(updatedUser.getUsername());
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

            return userRepository.save(user);
        } else {
            return null; // User not found
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public long getUserIDByEmail(String email) {
        return userRepository.getUserIDByEmail(email);
    }

    public User getUserByEmail(String email) {  // check if user exists
        return userRepository.getUserByEmail(email);
    }

    public boolean isEmailUnique(String email) {
        Optional<User> existinguser = userRepository.findByEmail(email);
        return !existinguser.isPresent();
    }
}
