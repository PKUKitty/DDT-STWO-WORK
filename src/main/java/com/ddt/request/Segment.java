package com.ddt.request;

import com.ddt.location.Location;
import com.ddt.utils.Date;
import com.ddt.utils.Time;

import java.util.ArrayList;

public class Segment {
    public int id;

    public ArrayList<Location> origin;

    public ArrayList<Location> destination;

    public Date departureDate;

    public Time departAfterTime;

    public Time departBeforeTime;

    public FlexInterval flexInterval;

    public Preferences preferences;

    public ArrayList<ConnectionLocation> connectionLocations;

    public final Request request;

    public Segment() {
        this.id = 0;
        this.departAfterTime = new Time(0, 0, 0);
        this.departBeforeTime = new Time(59, 59, 23);
        this.request = null;
    }

    public boolean equals(Segment other) {
        if (this.origin.size() != other.origin.size()) {
            return false;
        } else {
            for (Location loc : this.origin
                    ) {
                if (!other.origin.contains(loc)) {
                    return false;
                }
            }
        }

        //destination
        if (this.destination.size() != other.destination.size()) {
            return false;
        } else {
            for (Location loc : this.destination
                    ) {
                if (!other.destination.contains(loc)) {
                    return false;
                }
            }
        }

        return this.departureDate.equals(other.departureDate);
    }
}
