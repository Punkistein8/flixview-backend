package com.flixview.flixview.controllers;

import com.flixview.flixview.entities.Userflix;
import com.flixview.flixview.entities.UserflixDTO;
import com.flixview.flixview.services.UserFlixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/flix-users")
public class UserFlixController {

    private final UserFlixService userFlixService;

    @Autowired
    public UserFlixController(UserFlixService userFlixService) {
        this.userFlixService = userFlixService;
    }

    @GetMapping("/by-email")
    public ResponseEntity<Object> getUserflixByMail(@RequestParam String email) {
        return userFlixService.getUserflixByMail(email);
    }

    @PostMapping("/validate-login")
    public ResponseEntity<Object> validateLogin(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String pass_use = loginRequest.get("password");
        return userFlixService.validateLogin(email, pass_use);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerNewUser(@RequestBody Userflix newUserflix) {
        return userFlixService.registerNewUser(newUserflix);
    }

    @PostMapping("/assign-plan")
    public ResponseEntity<Object> updateUserflixPlanById(@RequestBody Map<String, Long> userIdAndPlanId) {
        Long id_use = userIdAndPlanId.get("id_use");
        Long fk_id_pla = userIdAndPlanId.get("fk_id_pla");
        try {
            return userFlixService.updateUserflixPlanById(id_use, fk_id_pla);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return userFlixService.getAllUsers();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> logicDeleteUser(@PathVariable("id") Long id, @RequestBody UserflixDTO userflixDTO) {
        System.out.println(userflixDTO.getState_use());
        return userFlixService.logicDeleteUser(id, userflixDTO);
    }
}