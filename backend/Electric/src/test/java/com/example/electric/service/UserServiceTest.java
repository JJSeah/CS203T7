package com.example.electric.service;

import com.example.electric.model.Car;
import com.example.electric.model.User;
import com.example.electric.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L,"John"));
        users.add(new User(2L,"Don"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users, result);
    }

    @Test
    public void testGetUserById() {
        long id = 1L;
        User user = new User(id,"John");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = Optional.ofNullable(userService.getUserById(id));

        assertEquals(Optional.of(user), result);
    }

    @Test
    public void testGetUserByIdNonExistent() {
        long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Optional<User> result = Optional.ofNullable(userService.getUserById(id));

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsernames("testuser");
        user.setPassword("testpassword");

        // Mock the behavior of the passwordEncoder
        when(passwordEncoder.encode("testpassword")).thenReturn("encodedPassword");

        // Mock the behavior of the userRepository
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the createUser method
        User savedUser = userService.createUser(user);

        // Assertions
        verify(passwordEncoder).encode("testpassword"); // Verify that password encoding was called
        verify(userRepository).save(user); // Verify that userRepository.save was called
        assertEquals("encodedPassword", savedUser.getPassword()); // Ensure the password is encoded in the returned user
    }

    @Test
    public void testUpdateUser() {
        long userId = 1L;
        User updatedUser = new User();
        updatedUser.setId(userId);
        User existingUser = new User();
        existingUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when((userRepository.save(updatedUser))).thenReturn(updatedUser);

        User result = userService.updateUser(userId, updatedUser);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository,times(1)).save(updatedUser);

        assertSame(result, updatedUser);
    }

    @Test
    public void testUpdateUserNonExistent() {
        long userId = 1L;
        User updatedUser = new User();
        updatedUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User result = userService.updateUser(userId, updatedUser);

        verify(userRepository,times(1)).findById(userId);
        verify(userRepository,never()).save(updatedUser);
        assertNull(result);
    }

    @Test
    public void testDeleteUser() {
        long userId = 1;
        when(userRepository.existsById(userId)).thenReturn(true);
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUserNonExistent() {
        long carId = 1L;
        when(userRepository.existsById(carId)).thenReturn(false);

        userService.deleteUser(carId);

        verify(userRepository, times(1)).existsById(carId);
        verify(userRepository, never()).deleteById(carId);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "donta@gmail.com";
        User user = new User(1L,"Don","dontatadon",email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = Optional.ofNullable(userService.getUserByEmail(email));

        assertEquals(Optional.of(user), result);
    }

    @Test
    public void testGetUserByEmailNonExistent() {
        String email = "donta@gmail.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<User> result = Optional.ofNullable(userService.getUserByEmail(email));

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testIsEmailUnique_EmailIsUnique() {
        String email = "donta@gmail.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        boolean result = userService.isEmailUnique(email);

        assertTrue(result);
    }

    @Test
    public void testIsEmailUnique_EmailIsNotUnique() {
        String email = "donta@gmail.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        boolean result = userService.isEmailUnique(email);

        assertFalse(result);
    }
}
