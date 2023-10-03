package com.flixview.flixview.controllers;

import com.flixview.flixview.entities.Plan;
import com.flixview.flixview.entities.ProfileRequestDTO;
import com.flixview.flixview.entities.UserflixDTO;
import com.flixview.flixview.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequestMapping(path = "api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping(path = "/get-by-id")
    public ResponseEntity<Object> getProfilesListByUser(@RequestBody UserflixDTO userflixDTO) throws Exception {
        return profileService.getProfilesListByUserDTO(userflixDTO);
    }

    @PostMapping
    public ResponseEntity<Object> addProfile(@RequestBody ProfileRequestDTO profileRequestDTO) throws Exception {
        return profileService.addProfile(profileRequestDTO);
    }

    @PostMapping(path = "/check-if-can-add")
    public ResponseEntity<Object> checkIfUserCanAddProfile(@RequestBody ProfileRequestDTO profileRequestDTO) throws Exception {
        return profileService.checkIfUserCanAddProfile(profileRequestDTO);
    }

}
