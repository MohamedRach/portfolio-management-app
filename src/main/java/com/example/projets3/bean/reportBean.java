package com.example.projets3.bean;



public class reportBean {

    private int id;
    private String name;
    private String first_period_start;
    private String first_period_end;
    private String second_period_start;
    private String second_period_end;
    private int portolio_id;
    private int consultant_id;

    public int getPortolio_id() {
        return portolio_id;
    }

    public void setPortolio_id(int portolio_id) {
        this.portolio_id = portolio_id;
    }

    public int getConsultant_id() {
        return consultant_id;
    }

    public void setConsultant_id(int consultant_id) {
        this.consultant_id = consultant_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_period_start() {
        return first_period_start;
    }

    public void setFirst_period_start(String first_period_start) {
        this.first_period_start = first_period_start;
    }

    public String getFirst_period_end() {
        return first_period_end;
    }

    public void setFirst_period_end(String first_period_end) {
        this.first_period_end = first_period_end;
    }

    public String getSecond_period_start() {
        return second_period_start;
    }

    public void setSecond_period_start(String second_period_start) {
        this.second_period_start = second_period_start;
    }

    public String getSecond_period_end() {
        return second_period_end;
    }

    public void setSecond_period_end(String second_period_end) {
        this.second_period_end = second_period_end;
    }

    public String getThird_period_start() {
        return third_period_start;
    }

    public void setThird_period_start(String third_period_start) {
        this.third_period_start = third_period_start;
    }

    public String getThird_period_end() {
        return third_period_end;
    }

    public void setThird_period_end(String third_period_end) {
        this.third_period_end = third_period_end;
    }

    private String third_period_start;
    private String third_period_end;


}
