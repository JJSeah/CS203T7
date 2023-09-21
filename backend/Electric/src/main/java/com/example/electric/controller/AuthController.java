package com.example.electric.controller;

import com.example.electric.auth.JwtUtil;
import com.example.electric.model.User;
import com.example.electric.model.request.LoginReq;
import com.example.electric.model.response.ErrorRes;
import com.example.electric.model.response.LoginRes;
import com.example.electric.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;


    private JwtUtil jwtUtil;
    private UserService userService;
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

        this.userService = userService;
    }

    /**
     * Log in a user by validating their credentials and providing an authentication token.
     *
     * This endpoint allows a user to log in by providing their email and password. It validates
     * the credentials, generates an authentication token using JWT (JSON Web Token), and returns
     * the token along with user information. If the provided credentials are invalid, it responds
     * with an error message.
     *
     * @param loginReq The login request containing email and password.
     * @return A ResponseEntity with login information or an error response.
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            User user = new User(email,"");
            String token = jwtUtil.createToken(user);
            // Fetch the user's ID from your database or wherever it is stored
            User u = userService.getUserByEmail(email);
            LoginRes loginRes = new LoginRes(u.getId(),email,token);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    /**
     * Create a new user account.
     *
     * This endpoint allows the creation of a new user account by providing user details.
     *
     * @param user The user object containing user information.
     * @return The created user object.
     */
    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}