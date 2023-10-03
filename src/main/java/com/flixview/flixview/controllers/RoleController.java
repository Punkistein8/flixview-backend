package com.flixview.flixview.controllers;


import com.flixview.flixview.entities.Role;
import com.flixview.flixview.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/by-type")
    public ResponseEntity<Object> getRoleByType(@RequestParam String type) {
        return roleService.getRoleByType(type);
    }

    @PostMapping
    public ResponseEntity<Object> addRole(@RequestBody Map<String, String> newRole) {
        Role role = new Role();
        role.setType(newRole.get("type"));
        return roleService.addNewRole(role);
    }
}
