package com.flixview.flixview.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private String type;

    @Column(nullable = false)
    private float price_pla;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice_pla() {
        return price_pla;
    }

    public void setPrice_pla(float price_pla) {
        this.price_pla = price_pla;
    }
}
