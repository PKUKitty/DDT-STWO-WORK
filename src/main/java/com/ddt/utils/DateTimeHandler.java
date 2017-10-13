package com.ddt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.ddt.utils.DateFormat.*;
import static com.ddt.utils.TimeFormat.*;


public class DateTimeHandler {
    public final static short MAX_YEAR = 9999;
    public final static short MIN_YEAR = 1970;

    private final static String[] PRIVATE_MONTHS_NAME = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    public static final List MONTHS_NAME = Collections.unmodifiableList(Arrays.asList(PRIVATE_MONTHS_NAME));

    public static Date parseDate(final String datetimeStr, final DateFormat dateFormat, final int dateType) {

        Date dateObject = new Date();

        if (null == datetimeStr || datetimeStr.isEmpty()) {
            return null;
        }

        if (((YYYYMMDD == dateFormat || MMDDYYYY == dateFormat || DDMMYYYY == dateFormat)
                && (datetimeStr.compareTo("99999999") == 0) || datetimeStr.compareTo("00000000") == 0)
                ||
                ((YYMMDD == dateFormat || MMDDYY == dateFormat || DDMMYY == dateFormat)
                        && (datetimeStr.compareTo("999999") == 0 || datetimeStr.compareTo("000000") == 0))) {
            //date is discontinue date
            if (0 == dateType) {
                dateObject.setYear(DateTimeHandler.MAX_YEAR);
                dateObject.setMonth((byte) 99);
                dateObject.setDay((byte) 99);
                return dateObject;
            }
            //date is effective date
            else {
                dateObject.setYear(DateTimeHandler.MIN_YEAR);
                dateObject.setMonth((byte) 1);
                dateObject.setDay((byte) 1);
                return dateObject;
            }
        }

        //if date string is blank, set date object as max value
        if (((dateFormat == YYMMDD || dateFormat == MMDDYY || dateFormat == DDMMYY)
                && (0 == datetimeStr.compareTo("      ")))
                || ((dateFormat == YYYYMMDD || dateFormat == MMDDYYYY || dateFormat == DDMMYYYY)
                && (0 == datetimeStr.compareTo("        ")))
                ) {
            if (0 == dateType) {
                dateObject.setYear(DateTimeHandler.MAX_YEAR);
                dateObject.setMonth((byte) 99);
                dateObject.setDay((byte) 99);
            } else {
                dateObject.setYear(DateTimeHandler.MIN_YEAR);
                dateObject.setMonth((byte) 1);
                dateObject.setDay((byte) 1);
            }
            return dateObject;
        }

        String yearPtr;
        String monthPtr;
        String dayPtr;
        boolean monthFlag = true;
        if (YYYYMMDD == dateFormat) {
            yearPtr = datetimeStr.substring(0, 4);
            monthPtr = datetimeStr.substring(4, 6);
            dayPtr = datetimeStr.substring(6, 8);
        } else if (YYMMDD == dateFormat) {
            yearPtr = datetimeStr.substring(0, 2);
            monthPtr = datetimeStr.substring(2, 4);
            dayPtr = datetimeStr.substring(4, 6);
        } else if (MMDDYY == dateFormat) {
            monthPtr = datetimeStr.substring(0, 2);
            dayPtr = datetimeStr.substring(2, 4);
            yearPtr = datetimeStr.substring(4, 6);
        } else if (MMDDYYYY == dateFormat) {
            monthPtr = datetimeStr.substring(0, 2);
            dayPtr = datetimeStr.substring(2, 4);
            yearPtr = datetimeStr.substring(4, 8);
        } else if (DDMMYY == dateFormat) {
            dayPtr = datetimeStr.substring(0, 2);
            monthPtr = datetimeStr.substring(2, 4);
            yearPtr = datetimeStr.substring(4, 6);
        } else if (DDMMYYYY == dateFormat) {
            dayPtr = datetimeStr.substring(0, 2);
            monthPtr = datetimeStr.substring(2, 4);
            yearPtr = datetimeStr.substring(4, 8);
        } else if (DDMONYY == dateFormat) {
            monthFlag = false;
            dayPtr = datetimeStr.substring(0, 2);
            monthPtr = datetimeStr.substring(2, 5);

            for (int i = 0; i < 12; i++) {
                if (monthPtr.equals(PRIVATE_MONTHS_NAME[i])) {
                    dateObject.setMonth((byte) (i + 1));
                    monthFlag = true;
                    break;
                }
            }
            if (!monthFlag) {
                return null;
            }
            monthFlag = false;

            yearPtr = datetimeStr.substring(5, 7);
        } else if (DDMONYYYY == dateFormat) {
            monthFlag = false;
            dayPtr = datetimeStr.substring(0, 2);
            monthPtr = datetimeStr.substring(2, 5);

            for (int i = 0; i < 12; i++) {
                if (monthPtr.equals(PRIVATE_MONTHS_NAME[i])) {
                    dateObject.setMonth((byte) (i + 1));
                    monthFlag = true;
                    break;
                }
            }

            if (!monthFlag) {
                return null;
            }
            monthFlag = false;
            yearPtr = datetimeStr.substring(5, 9);
        } else {
            return null;
        }

        if (YYMMDD == dateFormat || MMDDYY == dateFormat || DDMMYY == dateFormat || DDMONYY == dateFormat) {
            yearPtr.concat(yearPtr);

            if (yearPtr.charAt(0) >= '7') {
                String tmp = "19";
                yearPtr = tmp.concat(yearPtr);
            } else {
                String tmp = "20";
                yearPtr = tmp.concat(yearPtr);
            }
        }

        if (StringUtils.isDigit(yearPtr)) {
            dateObject.setYear(Short.valueOf(yearPtr));
        } else {
            return null;
        }
        if (monthFlag) {
            if (StringUtils.isDigit(monthPtr)) {
                dateObject.setMonth(Byte.valueOf(monthPtr));
            } else {
                return null;
            }
        }

        if (StringUtils.isDigit(dayPtr)) {
            dateObject.setDay(Byte.valueOf(dayPtr));
        } else {
            return null;
        }

        if (!Date.checkDate(dateObject)) {
            return null;
        }

        return dateObject;
    }

    public static Date parseDateB(final String dateStr, final DateFormat dateFormat, final int dateType) {
        if (null == dateStr || dateStr.isEmpty()) {
            return null;
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = null;
        switch (dateFormat) {
            case YYYYMMDD:
                simpleDateFormat = new SimpleDateFormat(DateFormat.YYYYMMDD.getName());
                break;
            case YYMMDD:
                simpleDateFormat = new SimpleDateFormat(DateFormat.YYMMDD.getName());
                break;
            case MMDDYY:
                simpleDateFormat = new SimpleDateFormat(DateFormat.MMDDYY.getName());
                break;
            case MMDDYYYY:
                simpleDateFormat = new SimpleDateFormat(DateFormat.MMDDYYYY.getName());
                break;
            case DDMMYY:
                simpleDateFormat = new SimpleDateFormat(DateFormat.DDMMYY.getName());
                break;
            case DDMMYYYY:
                simpleDateFormat = new SimpleDateFormat(DateFormat.DDMMYYYY.getName());
                break;
            case DDMONYY:
                simpleDateFormat = new SimpleDateFormat(DateFormat.DDMONYY.getName());
                break;
            case DDMONYYYY:
                simpleDateFormat = new SimpleDateFormat(DateFormat.DDMONYYYY.getName());
                break;
        }

        try {
            java.util.Date builtInDate = simpleDateFormat.parse(dateStr);
            date.setDate(builtInDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Time parseTime(final String timeStr, final TimeFormat timeFormat) {
        if (timeStr == null || timeStr.isEmpty()) {
            return null;
        }

        String hourPtr;
        String minutePtr;
        String secondPtr;
        if (timeFormat == HHMMSS) {
            hourPtr = timeStr.substring(0, 2);
            minutePtr = timeStr.substring(2, 4);
            secondPtr = timeStr.substring(4, 6);
        } else if (timeFormat == HHMM) {
            hourPtr = timeStr.substring(0, 2);
            minutePtr = timeStr.substring(2, 4);
            secondPtr = "00";
        } else if (timeFormat == HHMMSS_COLON) { //16:37:01
            hourPtr = timeStr.substring(0, 2);
            minutePtr = timeStr.substring(3, 5);
            secondPtr = timeStr.substring(6, 8);
        } else if (timeFormat == HHMM_COLON) { // 16:37
            hourPtr = timeStr.substring(0, 2);
            minutePtr = timeStr.substring(3, 5);
            secondPtr = "00";
        } else {
            return null;
        }

        Time timeObject = new Time();

        if (StringUtils.isDigit(hourPtr)) {
            timeObject.setHour(Byte.valueOf(hourPtr));
        } else {
            return null;
        }

        if (StringUtils.isDigit(minutePtr)) {
            timeObject.setMinute(Byte.valueOf(minutePtr));
        } else {
            return null;
        }

        if (StringUtils.isDigit(secondPtr)) {
            timeObject.setSecond(Byte.valueOf(secondPtr));
        } else {
            return null;
        }

        //converted the hour 24 to 0
        if (timeObject.getHour() == 24) {
            timeObject.setHour((byte) 0);
        }

        if (!Time.checkTime(timeObject)) {
            return null;
        }
        return timeObject;
    }


    public static Time parseTimeB(final String timeStr, final TimeFormat timeFormat) {
        if (null == timeStr || timeStr.isEmpty()) {
            return null;
        }

        Time time = new Time();
        SimpleDateFormat simpleDateFormat = null;
        switch (timeFormat) {
            case HHMMSS:
                simpleDateFormat = new SimpleDateFormat(TimeFormat.HHMMSS.getName());
                break;
            case HHMM:
                simpleDateFormat = new SimpleDateFormat(TimeFormat.HHMM.getName());
                break;
            case HHMMSS_COLON:
                simpleDateFormat = new SimpleDateFormat(TimeFormat.HHMMSS_COLON.getName());
                break;
            case HHMM_COLON:
                simpleDateFormat = new SimpleDateFormat(TimeFormat.HHMM_COLON.getName());
                break;
        }

        try {
            java.util.Date builtInDate = simpleDateFormat.parse(timeStr);
            time.setTime(builtInDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }


    public static DateTime parseDateTime(final String datetimeStr, final DateFormat dateFormat, final int dateType,
                                         final TimeFormat timeFormat) {
        //deal with date
        if (null == datetimeStr || datetimeStr.isEmpty()) {
            return null;
        }
        String dateStr;
        String timeStr;

        if (YYYYMMDD == dateFormat || MMDDYYYY == dateFormat || DDMMYYYY == dateFormat) {
            dateStr = datetimeStr.substring(0, 7);
            timeStr = datetimeStr.substring(8, 13);
        } else {
            dateStr = datetimeStr.substring(0, 5);
            timeStr = datetimeStr.substring(6, 11);
        }

        DateTime dateTime = new DateTime();

        dateTime.setDate(parseDate(dateStr, dateFormat, dateType));
        if (dateTime.getDate() == null) {
            return null;
        }

        if (9999 == dateTime.getDate().getYear()) {
            dateTime.setTime(59, 59, 23);
            return dateTime;
        } else if (1970 == dateTime.getDate().getYear()) {
            dateTime.setTime(0, 0, 0);
            return dateTime;
        }

        dateTime.setTime(parseTime(timeStr, timeFormat));
        if (dateTime.getTime() == null) {
            return null;
        }
        return dateTime;
    }


    public static DateTime parseDateTimeB(final String datetimeStr, final DateFormat dateFormat, final int dateType, final TimeFormat timeFormat) {
        //deal with date
        if (null == datetimeStr || datetimeStr.isEmpty()) {
            return null;
        }
        DateTime dateTime = new DateTime();

        String datetimeFormat = dateFormat.getName() + timeFormat.getName();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datetimeFormat);
        try {
            java.util.Date date = simpleDateFormat.parse(datetimeStr);
            dateTime.setDateTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateTime;
    }


    public static Date getSystemDate() {
        Date date = new Date();
        date.setYear((short) Calendar.getInstance().get(Calendar.YEAR));
        date.setMonth((byte) (Calendar.getInstance().get(Calendar.MONTH)/*start at 0*/ + 1));
        date.setDay((byte) Calendar.getInstance().get(Calendar.DATE));
        return date;
    }

    public static Time getSystemTime() {
        Time time = new Time();
        time.setHour((byte) Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        time.setMinute((byte) (Calendar.getInstance().get(Calendar.MINUTE)));
        time.setSecond((byte) Calendar.getInstance().get(Calendar.SECOND));
        return time;
    }

    public static DateTime getSystemDateTime() {
        DateTime dateTime = new DateTime();
        dateTime.setDate(getSystemDate());
        dateTime.setTime(getSystemTime());
        return dateTime;
    }

    /**
     * note: compare with  System.currentTimeMillis()
     *
     * @return 0-999 ms
     */
    public static int getSystemMilliseconds() {
        return Calendar.getInstance().get(Calendar.MILLISECOND);
    }

    public static int getDayofWeek(final Date dateObject) {
        Date baseDate = new Date();
        baseDate.setYear(DateTimeHandler.MIN_YEAR);
        baseDate.setMonth((byte) 1);
        baseDate.setDay((byte) 1);

        int days = dateObject.calcDaysDifference(baseDate);

        //4: 19700101 is thursday
        int week = (days + 4) % 7;

        if (week != 0) {
            return week;
        }
        return 7;
    }

    public static char getDayofWeekChar(final Date dateObject) {
        int week = getDayofWeek(dateObject);
        char weekday = ' ';
        switch (week) {
            case 1:
                weekday = '1';
                break;
            case 2:
                weekday = '2';
                break;
            case 3:
                weekday = '3';
                break;
            case 4:
                weekday = '4';
                break;
            case 5:
                weekday = '5';
                break;
            case 6:
                weekday = '6';
                break;
            case 7:
                weekday = '7';
                break;
        }
        return weekday;
    }
}
