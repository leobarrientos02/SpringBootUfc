package com.example.ufc.fighter;

import java.time.LocalDate;
public class FighterDto {

    private String name;

    private LocalDate dob;

    private Integer age;

    private Double weight;

    private String height;

    private String reach;

    private String weightclass;

    private LocalDate debut;

    private String fightingOutOf;

    private String imageUrl;

    public FighterDto() {
    }

    public FighterDto(String name,
                      LocalDate dob,
                      Integer age,
                      Double weight,
                      String height,
                      String reach,
                      String fightingOutOf,
                      LocalDate debut,
                      String imageUrl) {
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.reach = reach;
        this.fightingOutOf = fightingOutOf;
        this.debut = debut;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeightclass() {
        return weightclass;
    }

    public void setWeightclass(String weightclass) {
        this.weightclass = weightclass;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public String getFightingOutOf() {
        return fightingOutOf;
    }

    public void setFightingOutOf(String fightingOutOf) {
        this.fightingOutOf = fightingOutOf;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
