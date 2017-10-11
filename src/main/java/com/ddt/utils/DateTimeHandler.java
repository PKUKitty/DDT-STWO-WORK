package com.ddt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import static com.ddt.utils.DateFormat.*;
import static com.ddt.utils.TimeFormat.*;


public class DateTimeHandler {
    public final static short MAX_YEAR = 9999;
    public final static short MIN_YEAR = 1970;

    public final static String[] MONTHS_NAME = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

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
                dateObject.year = DateTimeHandler.MAX_YEAR;
                dateObject.month = 99;
                dateObject.day = 99;
                return dateObject;
            }
            //date is effective date
            else {
                dateObject.year = DateTimeHandler.MIN_YEAR;
                dateObject.month = 1;
                dateObject.day = 1;
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
                dateObject.year = DateTimeHandler.MAX_YEAR;
                dateObject.month = 99;
                dateObject.day = 99;
            } else {
                dateObject.year = DateTimeHandler.MIN_YEAR;
                dateObject.month = 1;
                dateObject.day = 1;
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
                if (monthPtr.equals(MONTHS_NAME[i])) {
                    dateObject.month = (byte) (i + 1);
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
                if (monthPtr.equals(MONTHS_NAME[i])) {
                    dateObject.month = (byte) (i + 1);
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
            dateObject.year = Short.valueOf(yearPtr);
        } else {
            return null;
        }
        if (monthFlag) {
            if (StringUtils.isDigit(monthPtr)) {
                dateObject.month = Byte.valueOf(monthPtr);
            } else {
                return null;
            }
        }

        if (StringUtils.isDigit(dayPtr)) {
            dateObject.day = Byte.valueOf(dayPtr);
        } else {
            return null;
        }

        if (!checkDate(dateObject.year, dateObject.month, dateObject.day)) {
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
                //TODO
                simpleDateFormat = new SimpleDateFormat(DateFormat.DDMONYY.getName());
                break;
            case DDMONYYYY:
                //TODO
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
            timeObject.hour = Byte.valueOf(hourPtr);
        } else {
            return null;
        }

        if (StringUtils.isDigit(minutePtr)) {
            timeObject.minute = Byte.valueOf(minutePtr);
        } else {
            return null;
        }

        if (StringUtils.isDigit(secondPtr)) {
            timeObject.second = Byte.valueOf(secondPtr);
        } else {
            return null;
        }

        //converted the hour 24 to 0
        if (timeObject.hour == 24) {
            timeObject.hour = 0;
        }

        if (!checkTime(timeObject.hour, timeObject.minute, timeObject.second)) {
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

        dateTime.date = parseDate(dateStr, dateFormat, dateType);
        if (dateTime.date == null) {
            return null;
        }

        if (9999 == dateTime.date.year) {
            dateTime.time.hour = 23;
            dateTime.time.minute = 59;
            dateTime.time.second = 59;
            return dateTime;
        } else if (1970 == dateTime.date.year) {
            dateTime.time.hour = 0;
            dateTime.time.minute = 0;
            dateTime.time.second = 0;
            return dateTime;
        }

        dateTime.time = parseTime(timeStr, timeFormat);
        if (dateTime.time == null) {
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
        date.year = (short) Calendar.getInstance().get(Calendar.YEAR);
        date.month = (byte) (Calendar.getInstance().get(Calendar.MONTH)/*start at 0*/ + 1);
        date.day = (byte) Calendar.getInstance().get(Calendar.DATE);
        return date;
    }

    public static Time getSystemTime() {
        Time time = new Time();
        time.hour = (byte) Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        time.minute = (byte) (Calendar.getInstance().get(Calendar.MINUTE));
        time.second = (byte) Calendar.getInstance().get(Calendar.SECOND);
        return time;
    }

    public static DateTime getSystemDateTime() {
        DateTime dateTime = new DateTime();
        dateTime.date = getSystemDate();
        dateTime.time = getSystemTime();
        return dateTime;
    }

    public static int getSystemMilliseconds() {
        return Calendar.getInstance().get(Calendar.MILLISECOND);
    }

    public static int getDayofWeek(final Date dateObject) {
        Date baseDate = new Date();
        baseDate.year = DateTimeHandler.MIN_YEAR;
        baseDate.month = 1;
        baseDate.day = 1;

        int days = dateObject.calcDaysDifference(baseDate);

        //4: 19700101 is thursday
        int week = (days + 4) % 7;

        if (week != 0) {
            return week;
        }
        return 7;
    }

    public static char getDayOfWeekChar(final Date dateObject) {
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

    private static boolean checkDate(short year, byte month, byte day) {
        int[] month_date = Arrays.copyOf(Date.MONTH_DATE, Date.MONTH_SIZE);

        //check if the month is from 1 to 12
        if (1 > month || 12 < month) {
            return false;
        }
        //check if the day of a month is in its normal days
        if (0 == year % 400 || (0 == year % 4 && 0 != year % 100)) {
            month_date[1] = 29;
        } else {
            month_date[1] = 28;
        }
        return 1 <= day && month_date[month - 1] >= day;
    }

    private static boolean checkTime(byte hour, byte minute, byte second) {
        return hour <= 23 && hour >= 0 && minute <= 59 && minute >= 0 && second <= 59 && second >= 0;
    }
}
