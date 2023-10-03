package com.flixview.flixview.controllers;

import com.flixview.flixview.entities.Payment;
import com.flixview.flixview.entities.PaymentRequestDTO;
import com.flixview.flixview.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Object> addPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {

        try {

            Payment payment = paymentService.addPayment(paymentRequestDTO);
            return new ResponseEntity<>(
                    payment,
                    HttpStatus.OK
            );
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    "Error -> " + ex.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }

    }

}
