package com.flixview.flixview.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_per;

    @Column(name = "names_per", nullable = false)
    private String names_per;

    @Column(name = "birthDate_per", nullable = true)
    private LocalDate birthDate_per;

    @Column(name = "photo_per", nullable = true)
    private String photo_per;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkIdUse", referencedColumnName = "id_use")
    private Userflix fkIdUse;

    public Person() {

    }

    public Long getId_per() {
        return id_per;
    }

    public void setId_per(Long id_per) {
        this.id_per = id_per;
    }

    public String getNames_per() {
        return names_per;
    }

    public void setNames_per(String names_per) {
        this.names_per = names_per;
    }

    public LocalDate getBirthDate_per() {
        return birthDate_per;
    }

    public void setBirthDate_per(LocalDate birthDate_per) {
        this.birthDate_per = birthDate_per;
    }

    public String getPhoto_per() {
        return photo_per;
    }

    public void setPhoto_per(String photo_per) {
        this.photo_per = photo_per;
    }

    public Userflix getFk_id_use() {
        return fkIdUse;
    }

    public void setFk_id_use(Userflix fkIdUse) {
        this.fkIdUse = fkIdUse;
    }
}
