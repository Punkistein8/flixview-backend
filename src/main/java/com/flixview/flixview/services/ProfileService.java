package com.flixview.flixview.services;

import com.flixview.flixview.entities.*;
import com.flixview.flixview.repository.PlanRepository;
import com.flixview.flixview.repository.ProfileRepository;
import com.flixview.flixview.repository.UserflixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserflixRepository userflixRepository;
    private final PlanRepository planRepository;


    @Autowired
    public ProfileService(ProfileRepository profileRepository, UserflixRepository userflixRepository, PlanRepository planRepository) {
        this.profileRepository = profileRepository;
        this.userflixRepository = userflixRepository;
        this.planRepository = planRepository;
    }

    public ResponseEntity<Object> checkIfUserCanAddProfile(ProfileRequestDTO profileRequestDTO) throws Exception {
        HashMap<String, Object> response = new HashMap<>();
        Long fk_id_use = profileRequestDTO.getFk_id_use();

        Userflix userflix = userflixRepository.findById(fk_id_use)
                .orElseThrow(() -> new Exception("User with id " + fk_id_use + " not found"));

        //obteniendo tipo de plan del usuario
        Plan plan = userflix.getFk_id_pla();

        // PARA PLAN NULO
        if (plan == null) {
            response.put("error", true);
            response.put("message", "You can't add a profile before choosing a plan!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );
        }
        response.put("data", true);
        response.put("message", "You can add a profile");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> addProfile(ProfileRequestDTO profileRequestDTO) throws Exception {
        HashMap<String, Object> response = new HashMap<>();
        Long fk_id_use = profileRequestDTO.getFk_id_use();

        Userflix userflix = userflixRepository.findById(fk_id_use)
                .orElseThrow(() -> new Exception("User with id " + fk_id_use + " not found"));

        //obteniendo tipo de plan del usuario
        Plan plan = userflix.getFk_id_pla();

        // obteniendo cantidad de perfiles del usuario
        List<Profile> profileList = getProfilesListByUser(userflix);

        // PARA PLAN NULO
        if (plan == null) {
            response.put("error", true);
            response.put("message", "You can't add a profile before choosing a plan!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );
        }

        Profile newProfile;

        // VALIDAR PERFILES REPETIDOS
        if (profileRepository.findByName(profileRequestDTO.getName_pro()).isPresent()) {
            response.put("error", true);
            response.put("message", "This profile name already exists!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.UNAUTHORIZED
            );
        }

        // PARA PLAN BASICO
        if (plan.getType().equals("Basic") && profileList.size() < 2) {
            newProfile = new Profile(profileRequestDTO.getName_pro(), profileRequestDTO.getContentType_pro(), profileRequestDTO.getState_pro(), userflix);
            profileRepository.save(newProfile);
            response.put("data", newProfile);
            response.put("message", "Profile successfully added!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );
        } else if (plan.getType().equals("Intermediate") && profileList.size() < 3) {
            newProfile = new Profile(profileRequestDTO.getName_pro(), profileRequestDTO.getContentType_pro(), profileRequestDTO.getState_pro(), userflix);
            profileRepository.save(newProfile);
            response.put("data", newProfile);
            response.put("message", "Profile successfully added!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );
        } else if (plan.getType().equals("Advanced") && profileList.size() < 5) {
            newProfile = new Profile(profileRequestDTO.getName_pro(), profileRequestDTO.getContentType_pro(), profileRequestDTO.getState_pro(), userflix);
            profileRepository.save(newProfile);
            response.put("data", newProfile);
            response.put("message", "Profile successfully added!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );
        }
        response.put("error", true);
        response.put("message", "Your current plan doesn't allow you to create more profiles");
        return new ResponseEntity<>(
                response,
                HttpStatus.UNAUTHORIZED
        );

    }

    public List<Profile> getProfilesListByUser(Userflix userflix) {
        List<Profile> profilesList = null;

        profilesList = profileRepository.getProfilesByFkiduse(userflix);

        return profilesList;
    }

    public ResponseEntity<Object> getProfilesListByUserDTO(UserflixDTO userflixDTO) throws Exception {
        List<Profile> profilesList = null;
        HashMap<String, Object> response = new HashMap<>();
        Long fk_id_use = userflixDTO.getId_use();

        System.out.println("EFEKAIDUSER 😋 " + fk_id_use);

        Userflix userflix = userflixRepository.findById(fk_id_use)
                .orElseThrow(() -> new Exception("User with id " + fk_id_use + " not found"));

        profilesList = profileRepository.getProfilesByFkiduse(userflix);

        response.put("data", profilesList);
        response.put("message", "Profiles found");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }


}
