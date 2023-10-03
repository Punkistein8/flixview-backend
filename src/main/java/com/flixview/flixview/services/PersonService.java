package com.flixview.flixview.services;

import com.flixview.flixview.entities.Person;
import com.flixview.flixview.entities.PersonRequestDTO;
import com.flixview.flixview.entities.Userflix;
import com.flixview.flixview.repository.PersonRepository;
import com.flixview.flixview.repository.UserflixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final UserflixRepository userFlixRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, UserflixRepository userFlixRepository) {
        this.personRepository = personRepository;
        this.userFlixRepository = userFlixRepository;
    }

    public Person updatePerson(PersonRequestDTO personRequestDTO) throws Exception {
        Long fk_id_use = personRequestDTO.getFk_id_use();

        Userflix userflix = userFlixRepository.findById(fk_id_use)
                .orElseThrow(() -> new Exception("Userflix with id " + fk_id_use + " not found"));

        Person newPerson = new Person();

        newPerson.setNames_per(personRequestDTO.getNames_per());
        newPerson.setFk_id_use(userflix);

        return personRepository.save(newPerson);
    }

}