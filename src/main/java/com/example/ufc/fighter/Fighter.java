package com.example.ufc.fighter;

import com.example.ufc.fight.Fight;
import com.example.ufc.sharedResources.Weightclass;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Fighter {
    @Id
    @SequenceGenerator(
            name = "fighter_sequence",
            sequenceName = "fighter_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fighter_sequence"
    )
    private Long id;
    private String name;

    private Integer age;
    private LocalDate dob;
    private Double weight;
    private String height;
    private Weightclass weightclass;


    public Fighter() {

    }

    public Fighter(Long id, String name, Integer age, LocalDate dob, Double weight, String height, Weightclass weightclass) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.weightclass = weightclass;
    }

    public Fighter(String name, Integer age, LocalDate dob, Double weight, String height, Weightclass weightclass) {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.weightclass = weightclass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Weightclass getWeightclass() {
        return weightclass;
    }

    public void setWeightclass(Weightclass weightclass) {
        this.weightclass = weightclass;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Fighter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", weight=" + weight +
                ", height='" + height + '\'' +
                ", weightclass=" + weightclass +
                '}';
    }
}
