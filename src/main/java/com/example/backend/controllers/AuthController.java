package com.example.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.controllers.authPayloads.LoginRequest;
import com.example.backend.controllers.authPayloads.LoginResponse;
import com.example.backend.entities.User;
import com.example.backend.exceptions.ErrorResponse;
import com.example.backend.exceptions.UserDoesNotExistException;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.Encryption;


/**
 * Controller for handling client-specific requests.
 */
@RestController
public class AuthController {

    @Autowired
    UserRepository userRepo;

    Encryption encryptionService = Encryption.getInst();

    
    // TO REMOVE
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepo.findAll();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TO REMOVE
    @GetMapping("/countUsers")
    public ResponseEntity<Long> countUsers() {
        try {
            long users = userRepo.count();

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Long>(-1L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/login",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        try {

            String email = req.getEmail();
            String password = req.getPassword();

            Optional<User> registeredUser = userRepo.findByEmail(email);

            boolean isPasswordMatch = Encryption.verifyPassword(password,
                registeredUser
            .orElseThrow(() -> new UserDoesNotExistException("User with email does not exist."))
            .getPassword());

            if (!isPasswordMatch) {
                throw new UserDoesNotExistException("No such user registered in system. Please sign up.");
            }

            String jwtToken = "jwttoken";
            // generate session token

            return new ResponseEntity<LoginResponse>(
                    new LoginResponse(email, registeredUser.get().getId(), jwtToken), 
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<LoginResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // C
    @PostMapping(path = "/signUp", 
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<User> signUp(@RequestBody User user) {
        try {
            User generatedUser = userRepo.save(
                    new User(user.getEmail(), Encryption.encryptPassword(user.getPassword()), user.getFirstName(), user.getLastName()));

            return new ResponseEntity<>(generatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ExceptionHandler(value = UserDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleUserDoesNotException(UserDoesNotExistException e) {
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }
    
}
