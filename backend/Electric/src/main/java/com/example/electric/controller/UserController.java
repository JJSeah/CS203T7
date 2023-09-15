package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Car;
import com.example.electric.model.Card;
import com.example.electric.model.User;
import com.example.electric.model.response.UserCarPaymentResponse;
import com.example.electric.service.CarService;
import com.example.electric.service.CardService;
import com.example.electric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private CardService cardService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserCarPaymentResponse getUserInfo(@PathVariable Long id) {
        User user = userService.getUserById(id);
        List<Car> car = carService.getAllCarsByUser(id);
        Optional<Card> card = cardService.getCardByOwner(id);

        if (user == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }

        UserCarPaymentResponse response = new UserCarPaymentResponse();
        response.setUser(user);
        response.setCar(car);
        response.setCard(card);

        return response;
    }

//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        if (userService.getUserById(id) == null) {
//            throw new ObjectNotFoundException(ErrorCode.E1002);
//        }
//        return userService.getUserById(id);
//    }

//    @PostMapping("/")
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        if (userService.getUserById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        if (userService.getUserById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        userService.deleteUser(id);
    }

}
