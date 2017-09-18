package com.ddt.utils;

public enum Databases {
    ACTIVE_PORT("ACTIVE_PORT"),

    AGENCY("AGENCY"),

    LOCATION("LOCATION"),

    FLIGHT_LINE("FLIGHT_LINE");

    private final String name;

    Databases(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
