package com.ddt.location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import static com.ddt.location.LocationType.*;

public class Location implements Serializable {

    private static final long serialVersionUID = -2468947980156114917L;

    public static final int TIMEZONE_LENGTH = 2;

    public static final int NAME_LENGTH = 16;

    public static final int DISPLAYNAME_LENGTH = 128;

    public static final int MAX_NUMBER_OF_LOCATIONS = 5000;

    public static final int MAX_NUMBER_OF_ACTIVE_AIRPORTS = 5000;

    public static final int MAX_NUMBER_OF_ACTIVE_COUNTRIES = 300;

    short id = 0; // unique id for the location
    short activeID = 0; // unique id assigned to only those airports and cities that have flights to them
    int activeCountryID = 0; // unique id assigned to only those countries that have flights to them
    int activeIataCountryID = 0; // unique id assigned to only those countries that have flights to them

    LocationType type = LocationType.AIRPORT; // the location type as specified by the LocationType enumeration
    AirportType airportType = AirportType.AIR_PORT; // specifying the type of the airport

    char[] name = new char[NAME_LENGTH]; // the IATA name of the location. [[[[[is length 256 ?]]]]]

    Location city = null; // pointer to the containing city
    Location parent = null; // pointer to the containing parent location
    Location state = null; // pointer to the containing state object.
    Location country = null; // pointer to the containing country object
    Location iataCountry = null; // pointer to the containing country objectLocation* m_zone; // pointer to the containing zone object
    Location zone = null; // pointer to the containing zone object
    Location aggrZone = null; // pointer to the containing aggregate zone object
    Location area = null; // pointer to the containing area object.

    ArrayList<Location> airports = new ArrayList<>(); // for locations that are cities the array contains all airports in that city
    ArrayList<Location> cities = new ArrayList<>(); // for locations that are parent cities the array contains all cities for that parent city

    char[] timeZone = new char[TIMEZONE_LENGTH]; // timezone code

    boolean areaXuRu = false; // specifying if the location is in the XURU custom area;
    boolean areaUsPrVi = false; // specifying if the location is in the US, Puerto Rico, US Virgin Islands custom area;
    boolean areaUsCaPrVi = false; // specifying if the location is in the US, Canada, Puerto Rico, US Virgin Islands custom area;
    boolean areaUsCaPrViScandinavia = false; // specifying if the location is in the US, Canada, Puerto Rico, US Virgin Islands,

    // Scandinavia custom area;
    int latitude = 0;
    int longitude = 0;

    boolean not = false; // specifies that the location defines the negative of the information specified in it.

    char[] displayName = new char[DISPLAYNAME_LENGTH]; // the user friendly name of the location if such is available.

    boolean[] regionLocations = new boolean[RegionLocations.REGION_LOCATION_COUNT.ordinal()];


    public Location() {
    }

    public static boolean isDomestic(final ArrayList<Location> locations) {
        if (locations.isEmpty()) {
            return false;
        }

        Iterator iterator = locations.iterator();
        Location first = null;
        if (iterator.hasNext()) {
            first = (Location) iterator.next();
        }

        while (iterator.hasNext()) {
            Location next = (Location) iterator.next();
            Location country1 = first.type == COUNTRY ? first : first.country;
            Location country2 = next.type == COUNTRY ? next : next.country;
            if (country1 != country2) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDomesticIata(final ArrayList<Location> locations) {
        if (locations.isEmpty()) {
            return false;
        }

        Iterator iterator = locations.iterator();
        Location first = null;
        if (iterator.hasNext()) {
            first = (Location) iterator.next();
        }

        while (iterator.hasNext()) {
            Location next = (Location) iterator.next();
            Location country1 = first.type == COUNTRY_IATA ? first : first.iataCountry;
            Location country2 = next.type == COUNTRY_IATA ? next : next.iataCountry;
            if (country1 != country2) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDomesticXuRu(final ArrayList<Location> locations) {
        return isDomestic(locations) || isInSameArea(locations, loc -> loc.areaXuRu);
    }

    public static boolean isDomesticXuRuIata(final ArrayList<Location> locations) {
        return isDomesticIata(locations) || isInSameArea(locations, loc -> loc.areaXuRu);
    }

    public static boolean isUsPrVi(final ArrayList<Location> locations) {
        return isInSameArea(locations, loc -> loc.areaUsPrVi);
    }

    public static boolean isUsCaPrVi(final ArrayList<Location> locations) {
        return isInSameArea(locations, loc -> loc.areaUsCaPrVi);
    }

    public static boolean isDomesticUsCaPrViScandinavia(final ArrayList<Location> locations) {
        return isDomestic(locations) || isInSameArea(locations, loc -> loc.areaUsCaPrViScandinavia);
    }

    public static boolean isSubset(final Location location1, final Location location2) {
        //both positive
        if (location1.isNot() == location2.isNot()) {
            return isSubsetPosPos(location1, location2, false);
        }
        //one positive , one negative
        else {
            //different type
            if (location1.getType() != location2.getType()) {
            /*
             * inclusion relationship --> false
             *                   else --> true
             */
                return !isSubsetPosPos(location1, location2, false);
            }
            //same type
            else {
            /*
             * exact same location --> false
             *                else --> true
             */
                return !(Arrays.equals(location1.name, location2.name));
            }
        }
    }

    public static boolean isAllInRegion(final ArrayList<Location> locations, RegionLocations regionType) {
        for (Location location : locations) {
            if (!location.isCustomRegion(regionType)) {
                return false;
            }
        }
        return true;
    }

    public boolean isCustomRegion(RegionLocations regionType) {
        return regionLocations[regionType.ordinal()];
    }

    public boolean contains(final Location other) {
        //both positive location
        if (!this.isNot() && !other.isNot()) {
        /*
         * this contains other --> true
         *               else  --> false
         */

            if (this.getType() == other.getType()) {
                return this == other;
            }

            if (this.getType() == COUNTRY_IATA && other.getType() != ZONE) {
                return this == other.getIataCountry();
            }

            if (this.getType() == COUNTRY && other.getType() != ZONE) {
                return this == other.getCountry();
            }

            if (this.getType() == CITY) {
                return this == other.getCity();
            }

            if (this.getType() == AREA) {
                return this == other.getArea();
            }

            if (this.getType() == STATE) {
                return this == other.getState();
            }


            return isSubsetPosPos(this, other, true);
        }
        //both negative location
        else if (this.isNot() && other.isNot()) {
        /*
         * other contains this --> true
         *                else --> false
         */
            return isSubsetPosPos(other, this, true);
        }
        //one negative, one positive
        else {
            //different type
            if (this.getType() != other.getType()) {
            /*
             * inclusion relationship --> false
             *
             */
                if (isSubsetPosPos(this, other, false)) {
                    return false;
                }
            /*
             * no relationship:
             * 1.     if this is a NOT location --> true
             * 2.                         else  --> false
             */
                else if (this.isNot()) {
                    return true;
                } else {
                    return false;
                }
            }
            //same type
            else {
                //same name --> false
                if (Arrays.equals(this.name, other.name)) {
                    return false;
                }
            /*
             * not same name :
             * 1. this is a NOT location . true
             * 2.                  else  . false
             */
                else if (this.isNot()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public boolean isDomesticXuRuIata(final Location location) {
        return this.getIataCountry() != null && location.getIataCountry() != null && (this.getIataCountry() == location.getIataCountry() || this.isAreaXuRu() && location.isAreaXuRu());

    }

    public boolean isDomesticXuRu(final Location location) {
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(this);
        locations.add(location);
        return isDomesticXuRu(locations);
    }

    public boolean isDomesticIata(final Location location) {
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(this);
        locations.add(location);
        return isDomesticIata(locations);
    }

    public short getId() {
        return id;
    }

    public short getActiveId() {
        return activeID;
    }

    public int getActiveIataCountryId() {
        return activeIataCountryID;
    }

    public LocationType getType() {
        return type;
    }

    public AirportType getAirportType() {
        return airportType;
    }

    public final char[] getName() {
        return name;
    }

    public final Location getCity() {
        if (type == CITY) {
            return this;
        }
        return city;
    }

    public final Location getParent() {
        return parent;
    }

    public final Location getState() {
        return state;
    }

    public final Location getCountry() {
        if (type == COUNTRY) {
            return this;
        }
        return country;
    }

    public final Location getIataCountry() {
        if (type == COUNTRY_IATA) {
            return this;
        }
        return iataCountry;
    }

    public final Location getZone() {
        return zone;
    }

    public final Location getAggrZone() {
        return aggrZone;
    }

    public final Location getArea() {
        return area;
    }

    ArrayList<Location> getAirports() {
        assert (getType() == CITY || getType() == LocationType.PARENT || (getType() == LocationType.ALL && not));
        return airports;
    }

    ArrayList<Location> getCities() {
        return cities;
    }

    final char[] getTimeZone() {
        return timeZone;
    }

    boolean isAreaXuRu() {
        return areaXuRu;
    }

    public boolean isAreaUsPrVi() {
        return areaUsPrVi;
    }

    public boolean isAreaUsCaPrVi()

    {
        return areaUsCaPrVi;
    }

    public boolean isAreaUsCaPrViScandinavia()

    {
        return areaUsCaPrViScandinavia;
    }

    public boolean isAreaScandinavian()

    {
        return isCustomRegion(RegionLocations.SCANDINAVIA);
    }

    public boolean isAreaEurope() {
        return isCustomRegion(RegionLocations.EUROPE);
    }

    public boolean isNot() {
        return not;
    }

    final char[] getDisplayName() {
        return displayName;
    }

    public final boolean[] getRegionLocations() {
        return regionLocations;
    }

    public int getTimeZoneLength() {
        return TIMEZONE_LENGTH;
    }

    public int getNameLength() {
        return NAME_LENGTH;
    }

    public int getDisplayNameLength() {
        return DISPLAYNAME_LENGTH;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude()

    {
        return longitude;
    }

    public boolean isNameEqualTo(final String name)

    {
        return name.equals(String.valueOf(getName()));
    }

    final String getTimeZoneCountryCode() {
        String timezoneCountryCode = String.valueOf(iataCountry.getName());

        // For the iataCountry of GG/IM/JE, they only have time zone data with ATPCO country name(GB).
        if (Objects.equals(timezoneCountryCode, "GG") || Objects.equals(timezoneCountryCode, "IM") || Objects.equals(timezoneCountryCode, "JE")) {
            timezoneCountryCode = "GB";
        } else if (Objects.equals(timezoneCountryCode, "XU")) {
            timezoneCountryCode = "RU";
        }

        return timezoneCountryCode;
    }

    public boolean isAll() {
        return type == LocationType.ALL && not == false;
    }

    public boolean isAllSpecific() {
        return type == LocationType.ALL_AGGR_ZONE || type == LocationType.ALL_AIRPORT || type == LocationType.ALL_AREA || type == LocationType.ALL_CITY ||
                type == LocationType.ALL_COUNTRY || type == LocationType.ALL_COUNTRY_IATA || type == LocationType.ALL_STATE;
    }

    private interface SameAreaFunc {
        boolean sameArea(Location loc);
    }

    private static boolean isInSameArea(final ArrayList<Location> locations, SameAreaFunc func) {

        if (locations.isEmpty() || locations.size() == 1) {
            return false;
        }

        for (Location first : locations) {
            boolean isTrue = func.sameArea(first);
            if (!isTrue) {
                return false;
            }
        }
        return true;
    }

    private static boolean isInAreaXuRu(final Location location) {
        return location.areaXuRu;
    }

    private static boolean isInAreaUsPrVi(final Location location) {
        return location.areaUsPrVi;
    }

    private static boolean isInAreaUsCaPrVi(final Location location) {
        return location.areaUsCaPrVi;
    }

    private static boolean isInAreaUsCaPrViScandinavia(final Location location) {
        return location.areaUsCaPrViScandinavia;
    }

    private static boolean isSubsetPosPos(final Location location1, final Location location2, boolean needOrder) {
        if (null == location1 || null == location2) {
            return false;
        }

        //for us & zone exception : US contains Zone(1,4,8)
        if ((location1.getType() == COUNTRY || location1.getType() == COUNTRY_IATA) &&
                location2.getType() == ZONE && location1.name.equals("US")) {
            return location1.isUsContainZone(location2);
        } else if ((location2.getType() == COUNTRY || location1.getType() == COUNTRY_IATA) &&
                location1.getType() == ZONE && location2.name.equals("US")) {
            if (needOrder) {
                return false;
            } else {
                return location2.isUsContainZone(location1);
            }
        }

        //location1 is bigger than location2
        if (location1.type.ordinal() > location2.type.ordinal()) {
        /*
         * location1 contains location2 --> true
         *                        else  --> false
         */
            return location1.containsPosPos(location2);
        }
        //location2 is bigger than location1
        else if (location1.type.ordinal() < location2.type.ordinal()) {
            //if need order --> false
            if (needOrder) {
                return false;
            } else {
                return location2.containsPosPos(location1);
            }
        }
        /*
        * same type:
         * 1.   same name --> true
         * 2.        else --> false
        */
        else {
            return Arrays.equals(location1.name, location2.name);
        }
    }

    private boolean containsPosPos(final Location other) {
        if (null == other) {
            return false;
        }
        //same location  . true
        if (other == this) {
            return true;
        }
        //same type
        else if (type == other.type) {
        /*
         * same name  . true
         *      else  . false
         */
            if (Arrays.equals(this.name, other.name))
                return true;
            else
                return false;
        } else if (type.ordinal() < other.type.ordinal()) {
            return false;
        }

        boolean isContain = false;
        switch (this.type) {
            case ALL:
                return true;
            case CITY:
                isContain = sameLocationPosPos(other.city);
                break;
            case PARENT:
                isContain = sameLocationPosPos(other.parent);
                break;
            case STATE:
                isContain = sameLocationPosPos(other.state);
                break;
            case COUNTRY:
                isContain = sameLocationPosPos(other.country);
                break;
            case COUNTRY_IATA:
                isContain = sameLocationPosPos(other.iataCountry);
                break;
            case ZONE:
                isContain = sameLocationPosPos(other.zone);
                break;
            case AGGR_ZONE:
                isContain = sameLocationPosPos(other.aggrZone);
                break;
            case AREA:
                isContain = sameLocationPosPos(other.area);
                break;

            // Should NOT be used for comparison. Should only be used for constructing a valid location based on the type.
            case ALL_AIRPORT:
            case ALL_CITY:
            case ALL_STATE:
            case ALL_COUNTRY:
            case ALL_COUNTRY_IATA:
            case ALL_AGGR_ZONE:
            case ALL_AREA:
                return false;

            default:
                return false;
        }

        return isContain;
    }

    private boolean isUsContainZone(final Location zoneLocation) {
        return String.valueOf(zoneLocation.name).equals("001") || String.valueOf(zoneLocation.name).equals("004")
                || String.valueOf(zoneLocation.name).equals("008");
    }

    private boolean sameLocationPosPos(final Location other) {
        return other != null && type == other.type && Arrays.equals(name, other.name);
    }
}
