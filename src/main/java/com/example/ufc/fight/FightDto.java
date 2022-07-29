package com.example.ufc.fight;

import java.time.LocalDate;

public class FightDto {

    private String fighter1;
    private String fighter2;
    private String fightType;
    private String refereeName;
    private LocalDate date;
    private String location;
    private String result;


    public FightDto() {
    }

    public FightDto(String fighter1, String fighter2, String fightType, String refereeName, LocalDate date, String location, String result) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.fightType = fightType;
        this.refereeName = refereeName;
        this.date = date;
        this.location = location;
        this.result = result;
    }

    public String getFighter1() {
        return fighter1;
    }

    public void setFighter1(String fighter1) {
        this.fighter1 = fighter1;
    }

    public String getFighter2() {
        return fighter2;
    }

    public void setFighter2(String fighter2) {
        this.fighter2 = fighter2;
    }

    public String getFightType() {
        return fightType;
    }

    public void setFightType(String fightType) {
        this.fightType = fightType;
    }

    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
