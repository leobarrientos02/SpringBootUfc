package com.example.ufc.referee;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Referee {
    @Id
    @SequenceGenerator(
            name = "referee_sequence",
            sequenceName = "referee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "referee_sequence"
    )
    private Long id;
    private String name;
    private LocalDate dob;

    private Integer age;

    public Referee() {
    }

    public Referee(Long id, String name, LocalDate dob, Integer age) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
    }

    public Referee(String name, LocalDate dob, Integer age) {
        this.name = name;
        this.dob = dob;
        this.age = age;
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

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Referee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
