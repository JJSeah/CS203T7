package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.User;
import com.example.electric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieve a list of all users.
     *
     * This endpoint retrieves a list of all users currently registered in the system.
     * If no users are registered, an empty list will be returned.
     *
     * @return A list of users, which may be empty if no users are found.
     */
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Retrieve a user by their unique identifier.
     *
     * This endpoint retrieves a user from the system based on their unique identifier (ID).
     * If a user with the specified ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param id The unique identifier of the user to retrieve.
     * @return The retrieved user.
     * @throws ObjectNotFoundException If no user with the given ID is found.
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        if (userService.getUserById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return userService.getUserById(id);
    }

    /**
     * Create a new user in the system.
     *
     * This endpoint allows the creation of a new user in the system. The provided
     * user object should contain the necessary details for creating the user.
     *
     * @param user The user object to be created.
     * @return The newly created user.
     */
    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Update an existing user with the provided information.
     *
     * This endpoint allows the update of an existing user identified by their unique
     * identifier (ID). The provided updatedUser object should contain the updated details
     * for the user. If a user with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the user to update.
     * @param updatedUser The updated user object containing new information.
     * @return The updated user.
     * @throws ObjectNotFoundException If no user with the given ID is found.
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        if (userService.getUserById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return userService.updateUser(id, updatedUser);
    }

    /**
     * Delete a user by their unique identifier.
     *
     * This endpoint allows the deletion of a user from the system based on their unique
     * identifier (ID). If a user with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the user to delete.
     * @throws ObjectNotFoundException If no user with the given ID is found.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        if (userService.getUserById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        userService.deleteUser(id);
    }

}
