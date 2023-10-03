package com.flixview.flixview.entities;

import java.time.LocalDateTime;

public class PaymentRequestDTO {

    private Long id;
    private LocalDateTime paymentdate;
    private float amountpay;
    private Long fkiduse;
    private LocalDateTime created_on;
    private LocalDateTime updated_on;

    public PaymentRequestDTO(Long id, LocalDateTime paymentdate, float amountpay, Long fkiduse, LocalDateTime created_on, LocalDateTime updated_on) {
        this.id = id;
        this.paymentdate = paymentdate;
        this.amountpay = amountpay;
        this.fkiduse = fkiduse;
        this.created_on = created_on;
        this.updated_on = updated_on;
    }

    public PaymentRequestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(LocalDateTime paymentdate) {
        this.paymentdate = paymentdate;
    }

    public float getAmountpay() {
        return amountpay;
    }

    public void setAmountpay(float amountpay) {
        this.amountpay = amountpay;
    }

    public Long getFkiduse() {
        return fkiduse;
    }

    public void setFkiduse(Long fkiduse) {
        this.fkiduse = fkiduse;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime created_on) {
        this.created_on = created_on;
    }

    public LocalDateTime getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(LocalDateTime updated_on) {
        this.updated_on = updated_on;
    }
}
