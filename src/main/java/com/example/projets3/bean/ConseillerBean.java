package com.example.projets3.bean;

public class ConseillerBean {
    private Integer      id;
    private String    nom;
    private String    prenom;
    private String    email;
    private Integer   rating;
    private String password;
    private String imageLink;
    private String description;
    private Integer      hired;
    private Integer      Hourly_Rate;
    private Integer      Postive_Reviews;
    private Integer      Rehired;

    public Integer getRehired() {
        return Rehired;
    }

    public void setRehired(Integer rehired) {
        Rehired = rehired;
    }

    public Integer getPostive_Reviews() {
        return Postive_Reviews;
    }

    public void setPostive_Reviews(Integer postive_Reviews) {
        Postive_Reviews = postive_Reviews;
    }

    public Integer getHourly_Rate() {
        return Hourly_Rate;
    }

    public void setHourly_Rate(Integer hourly_Rate) {
        Hourly_Rate = hourly_Rate;
    }





    public Integer getHired() {
        return hired;
    }

    public void setHired(Integer hired) {
        this.hired = hired;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    private int    age;
    public Integer getRating() {
        return rating;
    }
    public void setRating(int rating) { this.rating = rating; }
    public String getImageLink() {
        return imageLink;
    }

    // Setter pour imageLink
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
