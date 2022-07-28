package com.example.ufc.sharedResources;

public enum Weightclass {
    Heavyweight("Heavyweight"),
    Light_Heavyweight("Light Heavyweight"),
    Middleweight("Middleweight"),
    Welterweight("Welterweight"),
    Lightweight("Lightweight"),
    Featherweight("Featherweight"),
    Bantamweight("Bantamweight"),
    Flyweight("Flyweight"),
    Unknown("Unknown");

    Weightclass(String s) {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
