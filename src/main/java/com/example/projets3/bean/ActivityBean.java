package com.example.projets3.bean;

import java.util.Date;

public class ActivityBean {
    private Integer id;
    private Integer id_user;
    private Integer id_conseiller;
    private String desc_buy;
    private String desc_sell;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_conseiller() {
        return id_conseiller;
    }

    public void setId_conseiller(Integer id_conseiller) {
        this.id_conseiller = id_conseiller;
    }

    public String getDesc_buy() {
        return desc_buy;
    }

    public void setDesc_buy(String desc_buy) {
        this.desc_buy = desc_buy;
    }

    public String getDesc_sell() {
        return desc_sell;
    }

    public void setDesc_sell(String desc_sell) {
        this.desc_sell = desc_sell;
    }
}
