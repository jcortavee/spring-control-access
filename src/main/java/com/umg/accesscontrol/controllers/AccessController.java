package com.umg.accesscontrol.controllers;

import com.umg.accesscontrol.models.Access;
import com.umg.accesscontrol.models.User;
import com.umg.accesscontrol.repositories.AccessRepository;
import com.umg.accesscontrol.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/accesses", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class AccessController {

    private final AccessRepository accessRepository;
    private final UserRepository userRepository;

    public AccessController(AccessRepository accessRepository, UserRepository userRepository) {
        this.accessRepository = accessRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<Access>> getAccesses() {
        List<Access> accesses = accessRepository.findAll();

        return new ResponseEntity<>(accesses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Access> getAccess(@PathVariable("id") Long id) {
        Optional<Access> access = accessRepository.findById(id);

        if (!access.isPresent()) {
            throw new RuntimeException("Access not found");
        }

        return new ResponseEntity<>(access.get(), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<List<Access>> getAccessesByUser(@PathVariable("id") Long id) {
        User user = userRepository.getById(id);
        System.out.println(user.getRole());
        System.out.println(user.getUsername());
        List<Access> accesses = accessRepository.findAccessByUser(user);

        return new ResponseEntity<>(accesses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Access> saveAccess(@RequestBody Access access) {
        Access saved = accessRepository.save(access);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Access> deleteAccess(@PathVariable("id") Long id) {
        Optional<Access> accessDb = accessRepository.findById(id);

        if (!accessDb.isPresent()) {
            throw new RuntimeException("Access doesn't exist");
        }

        accessRepository.delete(accessDb.get());

        return new ResponseEntity<>(accessDb.get(), HttpStatus.OK);
    }

}
