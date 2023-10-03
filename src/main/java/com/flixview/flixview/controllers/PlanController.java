package com.flixview.flixview.controllers;

import com.flixview.flixview.entities.Plan;
import com.flixview.flixview.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/plans")
public class PlanController {

    private final PlanService planService;

    // Dependency Injection
    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public List<Plan> getPlans() {
        return planService.getAllPlans();
    }

    @GetMapping(path = "/by-type")
    public ResponseEntity<Object> getPlanByType(@RequestParam String type) {
        return planService.getPlanByType(type);
    }

    @GetMapping(path = "{id}")
    public Optional<Plan> getPlanById(@PathVariable("id") Long id) {
        return planService.getPLanById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addPlan(@RequestBody Plan newPlan) {
        return planService.addPlan(newPlan);
    }


    @DeleteMapping(path = "/by-type/{id}")
    public ResponseEntity<Object> deletePlanByType(@PathVariable("id") Long id) {
        return planService.deletePlanByType(id);
    }

    @PutMapping
    public ResponseEntity<Object> editPlan(@RequestBody Plan editedPlan) {
        return planService.editPlan(editedPlan);
    }


}
