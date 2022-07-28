package com.example.ufc.fighter;

import com.example.ufc.sharedResources.Weightclass;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String reach;
    private Weightclass weightclass;
    private LocalDate debut;
    private String fightingOutOf;
    private String imageUrl;


    public Fighter() {}

    public Fighter(Long id, String name, Integer age, LocalDate dob, Double weight, String height, String reach, Weightclass weightclass, LocalDate debut, String fightingOutOf, String imageUrl) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.reach = reach;
        this.weightclass = weightclass;
        this.debut = debut;
        this.fightingOutOf = fightingOutOf;
        this.imageUrl = imageUrl;
    }

    public Fighter(String name, Integer age, LocalDate dob, Double weight, String height, String reach, Weightclass weightclass, LocalDate debut, String fightingOutOf, String imageUrl) {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.reach = reach;
        this.weightclass = weightclass;
        this.debut = debut;
        this.fightingOutOf = fightingOutOf;
        this.imageUrl = imageUrl;
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

    @Override
    public String toString() {
        return "Fighter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", weight=" + weight +
                ", height='" + height + '\'' +
                ", reach='" + reach + '\'' +
                ", weightclass=" + weightclass +
                ", debut=" + debut +
                ", fightingOutOf='" + fightingOutOf + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
