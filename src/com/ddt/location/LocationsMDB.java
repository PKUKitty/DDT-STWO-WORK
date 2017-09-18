package com.ddt.location;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class LocationsMDB {

    public ConcurrentHashMap<String, Location> mapLocation = null;

    public ConcurrentHashMap<String, Location> mapNotLocation = null;

    public ArrayList<Location> listLocation;

    public ArrayList<Location>[] customRegion;

    public ArrayList<Location>[] typeLocations;

    public LocationsMDB() {
        mapLocation = new ConcurrentHashMap<>();
        mapNotLocation = new ConcurrentHashMap<>();
        listLocation = new ArrayList<>();
        customRegion = new ArrayList[RegionLocations.REGION_LOCATION_COUNT.ordinal()];
        typeLocations = new ArrayList[LocationType.TYPE_COUNT.ordinal()];
    }
}
