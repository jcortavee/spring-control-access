package com.umg.accesscontrol.controllers;

import com.umg.accesscontrol.models.Role;
import com.umg.accesscontrol.repositories.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "roles", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        List<Role> roles = roleRepository.findAll();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Role> find(@PathVariable("id") Long id) {
        Optional<Role> role = roleRepository.findById(id);

        if (!role.isPresent()) {
            throw new RuntimeException("The role doesn't exist");
        }

        return new ResponseEntity<>(role.get(), HttpStatus.OK);
    }

}
