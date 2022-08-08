package com.example.ufc.referee;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

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
    private String imageUrl;

    public Referee() {
    }

    public Referee(Long id,
                   String name,
                   LocalDate dob,
                   Integer age,
                   String imageUrl) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.imageUrl = imageUrl;
    }

    public Referee(String name, LocalDate dob, Integer age,String imageUrl) {
        this.name = name;
        this.dob = dob;
        this.age = age;
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

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Referee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Referee)) return false;
        Referee referee = (Referee) o;
        return id.equals(referee.id) && name.equals(referee.name) && dob.equals(referee.dob) && age.equals(referee.age) && imageUrl.equals(referee.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dob, age, imageUrl);
    }
}
