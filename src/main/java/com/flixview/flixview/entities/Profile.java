package com.flixview.flixview.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pro;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "contentType_pro", nullable = false)
    private String contentType_pro;

    @Column(name = "state_pro", nullable = false)
    private String state_pro;

    @ManyToOne
    @JoinColumn(name = "fkiduse", referencedColumnName = "id_use", nullable = false)
    private Userflix fkiduse;

    public Long getId_pro() {
        return id_pro;
    }

    public Profile(String name, String contentType_pro, String state_pro, Userflix fkiduse) {
        this.name = name;
        this.contentType_pro = contentType_pro;
        this.state_pro = state_pro;
        this.fkiduse = fkiduse;
    }

    public Profile() {
    }

    public void setId_pro(Long id_pro) {
        this.id_pro = id_pro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType_pro() {
        return contentType_pro;
    }

    public void setContentType_pro(String contentType_pro) {
        this.contentType_pro = contentType_pro;
    }

    public String getState_pro() {
        return state_pro;
    }

    public void setState_pro(String state_pro) {
        this.state_pro = state_pro;
    }

    public Userflix getFkiduse() {
        return fkiduse;
    }

    public void setFkiduse(Userflix fkiduse) {
        this.fkiduse = fkiduse;
    }
}


