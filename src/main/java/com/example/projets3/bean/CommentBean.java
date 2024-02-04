package com.example.projets3.bean;

import java.util.Date;

public class CommentBean {
    private Integer id;
    private Date created_at;
    private String comment;
    private Integer conseiller_id;
    private Integer user_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getConseiller_id() {
        return conseiller_id;
    }

    public void setConseiller_id(Integer conseiller_id) {
        this.conseiller_id = conseiller_id;

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}