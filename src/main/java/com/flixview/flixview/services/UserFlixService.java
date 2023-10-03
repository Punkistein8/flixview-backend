package com.flixview.flixview.services;

import com.flixview.flixview.entities.Plan;
import com.flixview.flixview.entities.Role;
import com.flixview.flixview.entities.Userflix;
import com.flixview.flixview.entities.UserflixDTO;
import com.flixview.flixview.repository.PlanRepository;
import com.flixview.flixview.repository.RoleRepository;
import com.flixview.flixview.repository.UserflixRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserFlixService {

    private final UserflixRepository userFlixRepository;
    private final PlanRepository planRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserFlixService(UserflixRepository userFlixRepository, PlanRepository planRepository, RoleRepository roleRepository) {
        this.planRepository = planRepository;
        this.userFlixRepository = userFlixRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<Object> registerNewUser(Userflix newUser) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<Userflix> userflixOptional = userFlixRepository.findByEmail(newUser.getEmail());

        if (userflixOptional.isPresent()) {
            response.put("error", true);
            response.put("message", "This user is already registered");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.UNAUTHORIZED
            );
        }

        Optional<Role> newRole = roleRepository.findByType("Normal");

        if (newRole.isEmpty()) {
            response.put("error", true);
            response.put("message", "No role to assign this user, create a role first");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }

        newUser.setFk_id_rol(newRole.get());

        userFlixRepository.save(newUser);
        response.put("data", newUser);
        response.put("message", "User successfully registered!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> getUserflixByMail(String email) {
        HashMap<String, Object> response = new HashMap<>();

        if (userFlixRepository.findByEmail(email).isEmpty()) {
            response.put("error", true);
            response.put("message", "User not found");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }

        response.put("data", userFlixRepository.findByEmail(email));
        response.put("message", "User found!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> validateLogin(String email, String pass_use) {
        Optional<Userflix> userflixOptional = userFlixRepository.findByEmail(email);

        System.out.println(email);
        System.out.println(pass_use);

        HashMap<String, Object> response = new HashMap<>();

        if (userflixOptional.isPresent()) {
            Userflix user = userflixOptional.get();
            if (user.getPassword().equals(pass_use)) {
                response.put("data", user);
                response.put("message", "Welcome, " + user.getEmail() + "!");
                return new ResponseEntity<>(
                        response,
                        HttpStatus.OK
                );
            }
        }

        response.put("error", true);
        response.put("message", "Bad credentials");
        return new ResponseEntity<>(
                response,
                HttpStatus.UNAUTHORIZED
        );
    }

    public ResponseEntity<Object> getAllUsers() {
        HashMap<String, Object> response = new HashMap<>();

        List<Userflix> userflixList = userFlixRepository.findAll();

        response.put("data", userflixList);
        response.put("message", "# " + userflixList.size() + " Users found!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

//    public Plan getPlanByUserId(Long id_use) {
//
//        Plan plan = userFlixRepository
//    }

    public ResponseEntity<Object> updateUserflixPlanById(Long id_use, Long fk_id_pla) throws Exception {
        HashMap<String, Object> response = new HashMap<>();

        Optional<Userflix> userflixOptional = userFlixRepository.findById(id_use);
        Optional<Plan> planOptional = planRepository.findById(fk_id_pla);

        if (userflixOptional.isEmpty()) {
            response.put("error", true);
            response.put("message", "Can't assign a new plan, user doesn't exist");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (planOptional.isEmpty()) {
            response.put("error", true);
            response.put("message", "Can't assign a new plan, this plan id doesn't exist");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.UNAUTHORIZED
            );
        }

        Userflix userflix = userflixOptional.get();
        Plan plan = planOptional.get();

        //VALIDAR (EN UN FUTURO) SI EL PLAN EXISTE Y ES APLICABLE
        userflix.setFk_id_pla(plan);

        try {
            userFlixRepository.save(userflix);
        } catch (Exception ex) {
            throw new Exception("No se pudo asignar el plan a este usuario -> " + ex.getMessage());
        }
        response.put("data", userflix);
        response.put("message", "Plan successfully assigned!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );


    }

    public ResponseEntity<Object> logicDeleteUser(Long id, UserflixDTO userflixDTO) {
        HashMap<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("error", true);
            response.put("message", "You must supply an id user to delete!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.EXPECTATION_FAILED
            );
        }

        Optional<Userflix> userToBeDeleted = userFlixRepository.findById(id);

        if (userToBeDeleted.isEmpty()) {
            response.put("error", true);
            response.put("message", "There's no user with that id!");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }

        Userflix userDeleted = userToBeDeleted.get();
        userDeleted.setState_use(userflixDTO.getState_use());
        System.out.println("USERDTO ENVIADO" + userflixDTO.getState_use());
        System.out.println("USER FLIX A EDITAR" + userDeleted.getState_use());
        userFlixRepository.save(userDeleted);

        response.put("data", userDeleted);
        response.put("message", "User deleted successfully!");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}
