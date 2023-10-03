package com.flixview.flixview.services;

import com.flixview.flixview.entities.Role;
import com.flixview.flixview.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;


@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<Object> getAllRoles() {
        HashMap<String, Object> response = new HashMap<>();

        response.put("data", roleRepository.findAll());
        response.put("message", "Roles found!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> getRoleByType(String typeRol) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<Role> roleOptional = roleRepository.findByType(typeRol);

        if (roleOptional.isEmpty()) {
            response.put("error", true);
            response.put("message", "This role doesn't exist!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }

        response.put("data", roleOptional);
        response.put("message", "Role found!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> addNewRole(Role newRole) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<Role> roleOptional = roleRepository.findByType(newRole.getType());

        if (roleOptional.isPresent()) {
            response.put("error", true);
            response.put("message", "This role is already registered");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.UNAUTHORIZED
            );
        }

        roleRepository.save(newRole);

        response.put("data", newRole);
        response.put("message", "Role successfully added!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

}
