package com.example.electric.controller;

import com.example.electric.auth.JwtUtil;
import com.example.electric.model.User;
import com.example.electric.model.request.LoginReq;
import com.example.electric.model.response.LoginRes;
import com.example.electric.respository.UserRepository;
import com.example.electric.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;
    private UserService userService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userService = userService;

    }

    /**
     * Log in a user by validating their credentials and providing an authentication
     * token.
     *
     * This endpoint allows a user to log in by providing their email and password.
     * It validates
     * the credentials, generates an authentication token using JWT (JSON Web
     * Token), and returns
     * the token along with user information. If the provided credentials are
     * invalid, it responds
     * with an error message.
     *
     * @param loginReq The login request containing email and password.
     * @return A ResponseEntity with login information or an error response.
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Operation(summary = "Login", description = "Authenticate Users", tags = { "Auth" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Invalid email or password.")})
    public ResponseEntity login(@RequestBody LoginReq loginReq) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
        var user = userRepository.findByEmail(loginReq.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtUtil.generateToken(user);
        LoginRes loginRes = new LoginRes(user.getId(), loginReq.getEmail(), jwt);
        return  ResponseEntity.ok(loginRes);
    }

    /**
     * Register a new user.
     *
     * This endpoint allows the registration of a new user in the system. It
     * verifies hat the provided email is unique before creating the user. Upon successful
     * registration, a response is returned indicating that the user has been
     * created.
     *
     * @param user The user object to be registered.
     * @return A ResponseEntity with a success message upon successful user registration.
     * @throws RuntimeException If the provided email already exists in the system.
     */
    @PostMapping(value = "/signup", name = "Create User")
    @Operation(summary = "Register", description = "Register new Users", tags = { "Auth" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Email already exists.")})
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (!userService.isEmailUnique(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");

    }

}