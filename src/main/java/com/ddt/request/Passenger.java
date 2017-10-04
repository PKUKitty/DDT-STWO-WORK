package com.ddt.request;

import com.ddt.location.Location;
import com.ddt.utils.Date;

import java.util.ArrayList;

public class Passenger {

    public int id;
    ArrayList<String> ptc;
    Date dateOfBirth;
    boolean isDateOfBirthSpecified;

    boolean useDefaultLocation;
    final Location nationality;
    final Location residency;
    final Location employee;
    final Location shipRegistry;
    boolean idProvided;
    boolean isVirtual;

    public final int getAgeOn(final Date date) {
        return date.calcYearsDifference(dateOfBirth);
    }

    public Passenger() {
        this.id = 1;
        this.isDateOfBirthSpecified = false;
        this.useDefaultLocation = true;
        this.nationality = null;
        this.residency = null;
        this.employee = null;
        this.shipRegistry = null;
        this.idProvided = false;
        this.isVirtual = false;
    }
}
