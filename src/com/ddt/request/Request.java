package com.ddt.request;

import com.ddt.common.CurrencyCode;
import com.ddt.common.UUID;
import com.ddt.utils.Timer;

import java.util.ArrayList;

public class Request {
    public ArrayList<Segment> segments;

    public CurrencyCode displayCurrency;

    public ArrayList<Passenger> passengers;

    public Preferences preferences;

    public JourneyPreferences journeyPreferences;

    public boolean isDebugRequest;

    public Timer executionTimer;
    public String defaultValues;
    public String transactionId;
    public UUID uuid;
    public boolean isCalendar;
}
