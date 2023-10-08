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
import org.springframework.web.bind.annotation.PathVariable;

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

    public ResponseEntity<Object> getPersonByUserId(@PathVariable Long id) throws Exception {
        HashMap<String, Object> response = new HashMap<>();

        Userflix userflix = userFlixRepository.findById(id)
                .orElseThrow(() -> new Exception("Userflix with id " + id + " not found"));

        Optional<Person> optionalPerson = personRepository.getPersonByFkIdUse(userflix);

        if (optionalPerson.isEmpty()) {
            response.put("error", true);
            response.put("errorType", "dontHavePersonYet");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );
        }

        response.put("data", optionalPerson);
        response.put("message", "Person found!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public Person addPerson(PersonRequestDTO personRequestDTO) throws Exception {
        Long fk_id_use = personRequestDTO.getFk_id_use();

        Userflix userflix = userFlixRepository.findById(fk_id_use)
                .orElseThrow(() -> new Exception("Userflix with id " + fk_id_use + " not found"));

        Person newPerson = new Person();

        newPerson.setNames_per(personRequestDTO.getNames_per());
        newPerson.setBirthDate_per(personRequestDTO.getBirthDate_per());
        newPerson.setPhoto_per(personRequestDTO.getPhoto_per());
        newPerson.setFk_id_use(userflix);

        return personRepository.save(newPerson);
    }

    public Person updatePersonById(Long id, PersonRequestDTO personRequestDTO) throws Exception {

        Userflix userflix = userFlixRepository.findById(personRequestDTO.getFk_id_use())
                .orElseThrow(() -> new Exception("Userflix with id " + personRequestDTO.getFk_id_use() + " not found"));


        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isEmpty()) {
            throw new Exception("User " + personRequestDTO.getNames_per() + " does not have a person associated yet");
        }

        Person person = optionalPerson.get();

        person.setNames_per(personRequestDTO.getNames_per());
        person.setBirthDate_per(personRequestDTO.getBirthDate_per());
        person.setPhoto_per(personRequestDTO.getPhoto_per());
        person.setFk_id_use(userflix);

        return personRepository.save(person);
    }

}