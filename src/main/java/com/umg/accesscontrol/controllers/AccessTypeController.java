package com.umg.accesscontrol.controllers;

import com.umg.accesscontrol.models.AccessType;
import com.umg.accesscontrol.repositories.AccessTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "access-types", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class AccessTypeController {

    private final AccessTypeRepository accessTypeRepository;

    public AccessTypeController(AccessTypeRepository accessTypeRepository) {
        this.accessTypeRepository = accessTypeRepository;
    }

    @GetMapping
    public ResponseEntity<List<AccessType>> findAll() {
        return new ResponseEntity<>(accessTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AccessType> find(@PathVariable("id") Long id) {
        return new ResponseEntity<>(accessTypeRepository.getById(id), HttpStatus.OK);
    }

}
