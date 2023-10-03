package com.flixview.flixview.repository;

import com.flixview.flixview.entities.Profile;
import com.flixview.flixview.entities.Userflix;
import com.flixview.flixview.entities.UserflixDTO;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> getProfilesByFkiduse(Userflix fkiduse);

    Optional<Profile> findByName(String name);
}
