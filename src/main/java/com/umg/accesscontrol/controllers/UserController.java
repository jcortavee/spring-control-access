package com.umg.accesscontrol.controllers;

import com.umg.accesscontrol.models.User;
import com.umg.accesscontrol.repositories.UserRepository;
import com.umg.accesscontrol.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userSaved = userService.createUser(user);

        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        Optional<User> userDb = userRepository.findById(id);

        if (!userDb.isPresent()) {
            throw new RuntimeException("User doesn't exist");
        }

        userRepository.delete(userDb.get());

        return new ResponseEntity<>(userDb.get(), HttpStatus.OK);
    }

}
