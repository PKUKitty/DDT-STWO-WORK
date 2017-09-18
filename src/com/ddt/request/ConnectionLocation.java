package com.ddt.request;

import com.ddt.location.Location;

public class ConnectionLocation {
    final Location connectionPoint;
    boolean inclusive;

    ConnectionLocation()
    {
     this.connectionPoint = null;
     this.inclusive = true;
    }
}
