package com.flixview.flixview.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paymentdate", nullable = true)
    private LocalDateTime paymentdate;

    @Column(name = "amountpay", nullable = false)
    private float amountpay;

    @ManyToOne
    @JoinColumn(name = "fkiduse", referencedColumnName = "id_use", nullable = false)
    private Userflix fkiduse;

    @Column(name = "created_on")
    private LocalDateTime created_on;

    @Column(name = "updated_on")
    private LocalDateTime updated_on;

    public Payment() {
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

    public Userflix getFkiduse() {
        return fkiduse;
    }

    public void setFkiduse(Userflix fkiduse) {
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
