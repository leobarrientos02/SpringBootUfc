package com.example.ufc.referee;

import java.time.LocalDate;

public class RefereeDto {

    private String name;
    private LocalDate dob;
    private Integer age;

    public RefereeDto() {
    }

    public RefereeDto(String name, LocalDate dob, Integer age) {
        this.name = name;
        this.dob = dob;
        this.age = age;
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
}
