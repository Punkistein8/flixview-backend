package com.flixview.flixview.controllers;

import com.flixview.flixview.entities.Person;
import com.flixview.flixview.entities.PersonRequestDTO;
import com.flixview.flixview.entities.Userflix;
import com.flixview.flixview.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getPersonByUserId(@PathVariable Long id) throws Exception {
        return personService.getPersonByUserId(id);
    }

    @PostMapping
    public ResponseEntity<Object> addPerson(@RequestBody PersonRequestDTO personRequestDTO) throws Exception {
        try {
            Person person = personService.addPerson(personRequestDTO);
            return new ResponseEntity<>(
                    person,
                    HttpStatus.OK
            );
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    "Error -> " + ex.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable Long id, @RequestBody PersonRequestDTO personRequestDTO) throws Exception {
        try {
            Person person = personService.updatePersonById(id, personRequestDTO);
            return new ResponseEntity<>(
                    person,
                    HttpStatus.OK
            );
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    "Error -> " + ex.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }


}
