package com.example.ufc.sharedResources;

public enum FightType {
    THREE_ROUNDS("3 Rounds"),
    FIVE_ROUNDS("5 Rounds"),
    CHAMPIONSHIP("Championship"),

    UNKNOWN("Unknown");

    FightType(String s) {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
