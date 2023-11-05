package com.example.electric.service;

import com.example.electric.model.Role;
import com.example.electric.model.User;
import com.example.electric.respository.UserRepository;
import com.example.electric.service.inter.UserServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    /**
     * User Service Constructor
     *
     * Constructor for the UserService class, responsible for managing user-related operations.
     *
     * @param userRepository The repository for accessing and managing user data.
     * @param passwordEncoder The BCrypt password encoder for secure password storage and validation.
     */
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Get All Users
     *
     * Retrieves a list of all users in the system.
     *
     * @return A list of User objects representing all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /**
     * Get User by ID
     *
     * Retrieves a user by their unique identifier (ID).
     *
     * @param id The unique identifier of the user to retrieve.
     * @return The User object corresponding to the specified ID, or null if not found.
     */

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    /**
     * Create User
     *
     * Creates a new user by saving the provided user object to the database. It also encodes and secures the user's password.
     *
     * @param user The User object to be created.
     * @return The newly created User object with updated information, including its unique identifier.
     */
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    /**
     * Update User
     *
     * Updates an existing user's information based on their unique identifier (ID).
     *
     * @param id The unique identifier of the user to be updated.
     * @param updatedUser The updated User object with new information.
     * @return The updated User object after applying the changes, or null if the user is not found.
     */
    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Update the user fields as needed
            if(updatedUser.getFirstName() != null) user.setFirstName(updatedUser.getFirstName());
            if(updatedUser.getLastName() != null) user.setLastName(updatedUser.getLastName());
            if(updatedUser.getUsernames() != null) user.setUsernames(updatedUser.getUsernames());
            if(updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
            if(updatedUser.getPassword() != null) user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            if(updatedUser.getCars() != null) user.setCars(updatedUser.getCars());
            if(updatedUser.getCard() != null) user.setCard(updatedUser.getCard());
            return userRepository.save(user);
        } else {
            return null; // User not found
        }
    }

    /**
     * Delete User
     *
     * Deletes a user from the database based on their unique identifier (ID).
     *
     * @param id The unique identifier of the user to be deleted.
     */
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return;
        }
        userRepository.deleteById(id);
    }

    /**
     * Get User by Email
     *
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The User object corresponding to the specified email, or null if not found.
     */
    public User getUserByEmail(String email) {  // check if user exists
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    /**
     * Is Email Unique
     *
     * Checks if an email address is unique and not already associated with an existing user.
     *
     * @param email The email address to check for uniqueness.
     * @return true if the email is unique, false if it already exists.
     */

    public boolean isEmailUnique(String email) {
        Optional<User> existinguser = userRepository.findByEmail(email);
        return !existinguser.isPresent();
    }

}

