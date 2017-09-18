package com.ddt.request;

import com.ddt.location.Location;

import java.util.ArrayList;
import java.util.Date;

public class Segment {
    public int id;

    public ArrayList<Location> origin;

    public ArrayList<Location> destination;

    public Date departureDate;

    public Date departAfterTime;

    public Date departBeforeTime;

    public FlexInterval flexInterval;

//  public Preferences preferences;

    public ArrayList<ConnectionLocation> connectionLocations;

    public final Request request;

    public Segment() {
        this.id = 0;
        this.departAfterTime = new Date(2017, 1, 1, 0, 0, 0);
        this.departBeforeTime = new Date(2017, 1, 1, 23, 59, 59);
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
