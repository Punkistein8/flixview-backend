package com.flixview.flixview.entities;

public class ProfileRequestDTO {

    private Long id_pro;
    private String name_pro;
    private String contentType_pro;
    private String state_pro;
    private Long fk_id_use;

    public Long getId_pro() {
        return id_pro;
    }

    public void setId_pro(Long id_pro) {
        this.id_pro = id_pro;
    }

    public String getName_pro() {
        return name_pro;
    }

    public void setName_pro(String name_pro) {
        this.name_pro = name_pro;
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

    public Long getFk_id_use() {
        return fk_id_use;
    }

    public void setFk_id_use(Long fk_id_use) {
        this.fk_id_use = fk_id_use;
    }
}

