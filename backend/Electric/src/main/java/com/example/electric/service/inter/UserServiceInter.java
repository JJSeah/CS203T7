package com.example.electric.service.inter;

import com.example.electric.model.User;

import java.util.List;

public interface UserServiceInter {

    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
    User getUserByEmail(String email);
    boolean isEmailUnique(String email);
}
