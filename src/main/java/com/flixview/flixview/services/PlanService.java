package com.flixview.flixview.services;

import com.flixview.flixview.entities.Plan;
import com.flixview.flixview.entities.Userflix;
import com.flixview.flixview.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    HashMap<String, Object> response;


    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> getAllPlans() {
        return this.planRepository.findAll();
    }

    public Optional<Plan> getPLanById(Long id) {
        return planRepository.findById(id);
    }

    public ResponseEntity<Object> getPlanByType(String newPlan) {
        response = new HashMap<>();
        if (planRepository.findByType(newPlan).isEmpty()) {
            response.put("error", true);
            response.put("message", "Plan not found");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }

        response.put("data", planRepository.findByType(newPlan));
        response.put("message", "Plan found!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> addPlan(Plan newPlan) {
        response = new HashMap<>();

        if (planRepository.findByType(newPlan.getType()).isPresent()) {
            response.put("error", true);
            response.put("message", "This plan already exists!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.UNAUTHORIZED
            );
        }

        planRepository.save(newPlan);
        response.put("data", newPlan);
        response.put("message", "Plan successfully added!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> deletePlanByType(Long id) {
        response = new HashMap<>();

        if (planRepository.findById(id).isEmpty()) {
            response.put("error", true);
            response.put("message", "Plan not found");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }

        Optional<Plan> planEliminado = planRepository.findById(id);
        response.put("data", planEliminado);

        planRepository.deleteById(id);

        response.put("message", "Plan deleted successfully!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> editPlan(Plan editedPlan) {
        response = new HashMap<>();

        if (editedPlan.getId() == null) {
            response.put("error", true);
            response.put("message", "You must supply an id plan to update!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.EXPECTATION_FAILED
            );
        }

        if (!planRepository.existsById(editedPlan.getId())) {
            response.put("error", true);
            response.put("message", "There's no plan with that id!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }

        planRepository.save(editedPlan);
        response.put("data", editedPlan);
        response.put("message", "Plan edited successfully!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

}
