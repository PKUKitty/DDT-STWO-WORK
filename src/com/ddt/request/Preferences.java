package com.ddt.request;

import com.ddt.common.BrandNameCode;
import com.ddt.common.Cabin;
import com.ddt.common.CarrierCode;

import java.util.HashSet;

enum AllowedStops {
    ZERO(0),
    ONE(1),
    TWO(2),
    UNLIMITED(100);

    public final int value;

    AllowedStops(int value) {
        this.value = value;
    }
}

public class Preferences {
    AllowedStops allowedStops;
    boolean changeable;
    boolean refundable;
    boolean upgradable;
    HashSet<Cabin> cabinCodes;
    HashSet<BrandNameCode> brandNames;
    boolean isVirtualCabin;
    Cabin requestSpecifiedCabin;
    boolean downsell;
    boolean upsell;
    HashSet<CarrierCode> flightsAllowedOperatingCarriers;
    HashSet<CarrierCode> flightsRequiredOperatingCarriers;
    HashSet<CarrierCode> flightsAllowedMarketingCarriers;
    HashSet<CarrierCode> flightsRequiredMarketingCarriers;
    HashSet<CarrierCode> faresAllowedCarriers;
    HashSet<CarrierCode> faresForbiddenCarriers;
    boolean noCodeshare;
    boolean noOvernightStay;
}
