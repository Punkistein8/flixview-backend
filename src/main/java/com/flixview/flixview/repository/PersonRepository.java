package com.flixview.flixview.repository;

import com.flixview.flixview.entities.Person;
import com.flixview.flixview.entities.Userflix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> getPersonByFkIdUse(Userflix userflix);

}