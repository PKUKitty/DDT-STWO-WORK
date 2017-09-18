package com.ddt.location;

public enum LocationType {
    NONE("NONE","0"),
    AIRPORT("AIRPORT","P"),
    CITY("CITY","C"),
    PARENT("PARENT","T"),
    STATE("STATE","S"),
    COUNTRY_IATA("COUNTRY_IATA","I"),
    COUNTRY("COUNTRY","N"),
    ZONE("ZONE","E"),
    AGGR_ZONE("AGGR_ZONE","Z"),
    AREA("AREA","A"),
    ALL("ALL","b"),
    ALL_AIRPORT("ALL_AIRPORT","p"),
    ALL_CITY("ALL_CITY","c"),
    ALL_STATE("ALL_STATE","s"),
    ALL_COUNTRY("ALL_COUNTRY","n"),
    ALL_COUNTRY_IATA("ALL_COUNTRY_IATA","i"),
    ALL_AGGR_ZONE("ALL_AGGR_ZONE","z"),
    ALL_AREA("ALL_AREA","a"),
    TYPE_COUNT("TYPE_COUNT","TYPE_COUNT");

    private final String name;
    private final String description;

    LocationType(final String description, final String name) {
        this.name = name;
        this.description = description;
    }

    /**
     *  get location type name
     * @return location type name
     */
    public String getName() {
        return name;
    }

    /**
     * get location type description
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
