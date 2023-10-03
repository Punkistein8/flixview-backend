package com.flixview.flixview.services;

import com.flixview.flixview.entities.Payment;
import com.flixview.flixview.entities.PaymentRequestDTO;
import com.flixview.flixview.entities.Userflix;
import com.flixview.flixview.repository.PaymentRepository;
import com.flixview.flixview.repository.UserflixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserflixRepository userFlixRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserflixRepository userFlixRepository) {
        this.paymentRepository = paymentRepository;
        this.userFlixRepository = userFlixRepository;
    }

    public Payment addPayment(PaymentRequestDTO paymentRequestDTO) throws Exception {
        Long fk_id_use = paymentRequestDTO.getFkiduse();

        Userflix userflix = userFlixRepository.findById(fk_id_use)
                .orElseThrow(() -> new Exception("Userflix with id " + fk_id_use + " not found"));

        Payment payment = new Payment();

        payment.setAmountpay(paymentRequestDTO.getAmountpay());
        payment.setFkiduse(userflix);
        payment.setPaymentdate(paymentRequestDTO.getPaymentdate());
        payment.setCreated_on(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

}
