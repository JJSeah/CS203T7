package com.example.electric.service;

import com.example.electric.model.User;
import com.example.electric.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Update the user fields as needed
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setCars(updatedUser.getCars());
            user.setCard(updatedUser.getCard());
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
}
