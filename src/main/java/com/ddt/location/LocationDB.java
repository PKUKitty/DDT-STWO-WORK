package com.ddt.location;

import com.ddt.redis.RedisStatusCode;
import com.ddt.utils.Databases;
import com.ddt.utils.DatabasesIndex;
import com.ddt.utils.Serialize;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ddt.location.LocationType.*;

public class LocationDB {
    private LocationsMDB locationsMDB;

    private static LocationDB locationDB;

    private ArrayList<Location>[] customRegion;

    private ArrayList<Location>[] typeLocations;

    public static LocationDB getInstance() {
        if (locationDB == null) {
            synchronized (LocationDB.class) {
                if (locationDB == null) {
                    locationDB = new LocationDB();
                    String path = ""; //TODO
                    locationDB.open(path, false);
                }
            }
        }
        return locationDB;
    }


    public final Location getAirport(final String name) {
        return getLocation(name, AIRPORT, false);
    }

    public final Location getCityOrAirport(final String name) {
        final Location result = getLocation(name, CITY, false);
        if (result == null || result.getActiveId() == 0) {
            return getLocation(name, AIRPORT, false);
        }
        return result;
    }

    public final Location getAllLocation(boolean isNot) {
        return getLocation("   ", ALL, isNot);
    }

    public ArrayList<Location> getLocationList(LocationType type) {
        return typeLocations[type.ordinal()];
    }

    public final Location getNotLocation(final String name, LocationType type) {
        return getLocation(name, type, true);
    }

    /*
     * Get location list with specified region type
     * @param [in] regionType: Type of region
     */
    public ArrayList<Location> getCountryList(RegionLocations regionType) {
        return customRegion[regionType.ordinal()];
    }

    public final ArrayList<Location> getLocationList() {
        return locationsMDB.listLocation;
    }

    private LocationDB() {
        this.locationsMDB = null;
    }

    /**
     * brief
     * Open the locationdb file by fileName.
     * This function map the locationdb file to memory.
     *
     * @param fileName: the specified locationdb file
     *                  noManifest:
     * @return void.
     */
    private void open(final String fileName, boolean noManifest) {

    }

    /**
     * brief
     * This function unmap the locationdb file from memory.
     *
     * @return void.
     */
    private void close() {

    }


    private final Location getLocation(final String name, LocationType type, boolean isNotLocation) {
        if (null == name) {
            return null;
        }

        int size;

        switch (type) {
            case COUNTRY:
            case COUNTRY_IATA:
            case STATE:
                size = 2;
                break;

            case AREA:
                size = 1;
                break;

            default:
                size = 3;
        }

        String keyName = "";
        if (ALL != type && LocationType.ALL_AGGR_ZONE != type && ALL_AIRPORT != type && LocationType.ALL_AREA != type && LocationType.ALL_CITY != type
                && LocationType.ALL_COUNTRY != type && ALL_COUNTRY_IATA != type && LocationType.ALL_STATE != type) {
            keyName = name.substring(0, size - 1);
        }

        String key = "";
        key += type.getName();
        key += keyName;
        if (isNotLocation) {
            if (locationsMDB.mapNotLocation.contains(key)) {
                return locationsMDB.mapNotLocation.get(key);
            }
            return null;
        } else {
            if (locationsMDB.mapLocation.contains(key)) {
                return locationsMDB.mapLocation.get(key);
            }
            return null;
        }
    }

    private Location getLocationByRedis(final String key) {
        Jedis jedis = new Jedis(DatabasesIndex.getInstance().getHPHostname(Databases.LOCATION), DatabasesIndex.getInstance().getHPPort(Databases.LOCATION));
        if (jedis.select(DatabasesIndex.getInstance().getHPIdx(Databases.LOCATION)) != RedisStatusCode.REDIS_OK.getName()) {
            return null;
        }
        List<String> value = jedis.hvals(key);
        if (value == null) {
            return null;
        }

        if (value.size() != 1 /*TODO confirm the hash*/) {
            return null;
        }

        Object object = Serialize.byte2Object(value.get(0).getBytes());
        if (object instanceof Location) {
            return (Location) Serialize.byte2Object(value.get(0).getBytes());
        }
        return null;
    }

}
