package com.ddt.location;

public enum AirportType {
    AIR_PORT("Airport"),
    OFFLINE_POINT("OfflinePoint"),
    METROPOLITAN_AREA("MetropolitanArea"),
    BUS_STATION("BusStation"),
    RAILWAY_STATION("RailwayStation"),
    HELIPORT("HeliPort"),
    FERRY_PORT("FerryPort"),
    AIRPORT_TYPE_COUNT("airportTypeCount");

    private final String name;

    AirportType(String name) {
        this.name = name;
    }

    /**
     * @return AirportType name;
     */
    public String getName() {
        return name;
    }
}
