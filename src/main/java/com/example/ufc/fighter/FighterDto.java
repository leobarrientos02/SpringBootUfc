package com.example.ufc.fighter;

import java.time.LocalDate;
public class FighterDto {

    private String name;

    private LocalDate dob;

    private Integer age;

    private Double weight;

    private String height;

    private String weightclass;

    public FighterDto() {
    }

    public FighterDto(String name, LocalDate dob, Integer age, Double weight, String height) {
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.weight = weight;
        this.height = height;
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
}
