package com.flixview.flixview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flixview.flixview.entities.Userflix;

import java.util.Optional;

@Repository
public interface UserflixRepository extends JpaRepository<Userflix, Long> {
    Optional<Userflix> findByEmail(String email);




}