package com.ddt.request;

import com.ddt.common.CarrierCode;
import com.ddt.common.ChannelIdCode;

import java.util.ArrayList;
import java.util.HashSet;

public class JourneyPreferences {
    public CarrierCode platingCarrier;
    public int maxResults;
    public boolean splitTicket;
    //ArrayList<FareTypes> fareTypes;
    public boolean noInterline;
    public boolean sameCarrierFare;
    public boolean noEOE;
    public boolean detailedPricing;
    //AvailabilityLevel availability;
    HashSet<String> allowedPollAvlCarriers;
    public boolean includeBaggage;
    public boolean includeAvailability;
    public boolean includeCommission;
    public boolean includeSurcharge;
    public boolean includePenalties;
    public boolean returnResultsInBatches;
    public boolean multiCabin;
    public boolean allowMultiCabinCombo;
    public boolean includeDebugOutput;
    public boolean includeIataTax;
    public boolean includeYqyr;
    public int calendarMinStay;
    public int calendarMaxStay;
    public int minConxTimeOverride;
    public int maxConxTimeOverride;
    HashSet<CarrierCode> agencyAllowedCarriers;
    //SpeedPreferences speedPreferences;
    public boolean corporateFaresOnly;
    ChannelIdCode channelId;
    //MultiCabinComboMode multiCabinComboMode;
    public boolean filterDuplicateCodeshares;
    public boolean applySameCarrierGroup;
}
