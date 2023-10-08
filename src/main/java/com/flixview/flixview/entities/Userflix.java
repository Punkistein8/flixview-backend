package com.flixview.flixview.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Userflix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_use;

    @Column(unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "state_use", nullable = true)
    private String state_use;

    @Column(name = "age", nullable = false)
    private int age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_pla", referencedColumnName = "id")
    private Plan fk_id_pla;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_rol", referencedColumnName = "id", nullable = true)
    private Role fk_id_rol;

    @Column(name = "created_on")
    private LocalDateTime created_on = LocalDateTime.now();

    @Column(name = "updated_on")
    private LocalDateTime updated_on;
    
    public Userflix() {
    }

    public Long getId_use() {
        return id_use;
    }

    public void setId_use(Long id_use) {
        this.id_use = id_use;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState_use() {
        return state_use;
    }

    public void setState_use(String state_use) {
        this.state_use = state_use;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Plan getFk_id_pla() {
        return fk_id_pla;
    }

    public void setFk_id_pla(Plan fk_id_pla) {
        this.fk_id_pla = fk_id_pla;
    }

    public Role getFk_id_rol() {
        return fk_id_rol;
    }

    public void setFk_id_rol(Role fk_id_rol) {
        this.fk_id_rol = fk_id_rol;
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


