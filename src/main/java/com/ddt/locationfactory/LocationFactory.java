package com.ddt.locationfactory;

import com.ddt.location.LocationsMDB;
import com.ddt.utils.MMap;

public class LocationFactory {

    private LocationsMDB locationsMDB = null;

    private boolean isNew = false;

    public LocationFactory() {

    }

    public void buildLocationDB() {

    }


    public boolean isNew() {
        return isNew;
    }

    public LocationsMDB getLocationsMDB() {
        return locationsMDB;
    }

    public void parseCCD(final String fileName) {
        byte[] bytes = MMap.read(fileName);
        if(bytes != null){
            System.out.println(new String(bytes));
        }

    }
}
