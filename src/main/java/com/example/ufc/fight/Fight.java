package com.example.ufc.fight;

import com.example.ufc.fighter.Fighter;
import com.example.ufc.referee.Referee;
import com.example.ufc.sharedResources.FightType;
import com.example.ufc.sharedResources.Result;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
@Entity
@Table
public class Fight {
    @Id
    @SequenceGenerator(
            name = "fight_sequence",
            sequenceName = "fight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fight_sequence"
    )
    private Long id;

    @OneToOne
    private Fighter fighter1;
    @OneToOne
    private Fighter fighter2;
    private FightType fightType;
    @OneToOne
    private Referee referee;
    private LocalDate date;
    private String location;
    private Result result;
    @Column(length=500)
    private String description;

    public Fight() {
    }

    public Fight(Long id, Fighter fighter1, Fighter fighter2, FightType fightType, Referee referee, LocalDate date, String location, Result result, String description) {
        this.id = id;
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.fightType = fightType;
        this.referee = referee;
        this.date = date;
        this.location = location;
        this.result = result;
        this.description = description;
    }

    public Fight(Fighter fighter1, Fighter fighter2, FightType fightType, Referee referee, LocalDate date, String location, Result result, String description) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.fightType = fightType;
        this.referee = referee;
        this.date = date;
        this.location = location;
        this.result = result;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fighter getFighter1() {
        return fighter1;
    }

    public void setFighter1(Fighter fighter1) {
        this.fighter1 = fighter1;
    }

    public Fighter getFighter2() {
        return fighter2;
    }

    public void setFighter2(Fighter fighter2) {
        this.fighter2 = fighter2;
    }

    public FightType getFightType() {
        return fightType;
    }

    public void setFightType(FightType fightType) {
        this.fightType = fightType;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Fight{" +
                "id=" + id +
                ", fighter1=" + fighter1 +
                ", fighter2=" + fighter2 +
                ", fightType=" + fightType +
                ", referee=" + referee +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", result=" + result +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fight)) return false;
        Fight fight = (Fight) o;
        return Objects.equals(getId(), fight.getId()) && Objects.equals(getFighter1(), fight.getFighter1()) && Objects.equals(getFighter2(), fight.getFighter2()) && getFightType() == fight.getFightType() && Objects.equals(getReferee(), fight.getReferee()) && Objects.equals(getDate(), fight.getDate()) && Objects.equals(getLocation(), fight.getLocation()) && getResult() == fight.getResult() && Objects.equals(getDescription(), fight.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFighter1(), getFighter2(), getFightType(), getReferee(), getDate(), getLocation(), getResult(), getDescription());
    }
}
