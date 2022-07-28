package com.example.ufc.sharedResources;

public enum Result {

    DECISION("Decision"),
    SPLIT_DECISION("Split Decision"),
    UNANIMOUS_DECISION("Unanimous Decision"),
    KO("Knockout"),
    TKO("Technical Knockout"),
    DRAW("Draw"),
    DOCTOR_STOPPAGE("Doctor Stoppage"),
    TBA("To Be Announced");


    Result(String s) {
    }

    @Override
    public String toString() {
        return "Result{}";
    }
}
