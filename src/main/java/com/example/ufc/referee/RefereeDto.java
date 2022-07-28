package com.example.ufc.referee;

import java.time.LocalDate;

public class RefereeDto {

    private String name;
    private LocalDate dob;
    private Integer age;
    private String imageUrl;

    public RefereeDto() {
    }

    public RefereeDto(String name, LocalDate dob, Integer age, String imageUrl) {
        this.name = name;
        this.dob = dob;
        this.age = age;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
