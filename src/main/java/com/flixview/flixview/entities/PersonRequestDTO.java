package com.flixview.flixview.entities;

import java.time.LocalDate;

public class PersonRequestDTO {

    private String names_per;

    private LocalDate birthDate_per;

    private String photo_per;

    private Long fk_id_use;

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

    public Long getFk_id_use() {
        return fk_id_use;
    }

    public void setFk_id_use(Long fk_id_use) {
        this.fk_id_use = fk_id_use;
    }
}

