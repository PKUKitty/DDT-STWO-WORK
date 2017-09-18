package com.ddt.request;

import com.ddt.location.Location;

import java.util.ArrayList;
import java.util.Date;

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
        long diff = date.getTime() - dateOfBirth.getTime();
        return (int) (diff/(1000 * 60 * 60 * 24 * 365)); //TODO 365
    }

    public Passenger(){
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
