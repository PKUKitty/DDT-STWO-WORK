package com.ddt.utils;

import java.util.Calendar;

public class DateTime {
    private Date date;

    private Time time;

    public DateTime() {
        this.date = new Date();
        this.time = new Time();
    }

    public DateTime(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public DateTime(int day, int month, int year, int second, int minute, int hour) {
        this.date.setDate(day, month, year);
        this.time.setTime(second, minute, hour);
    }

    public static DateTime createDatetime(Date date, Time time) {
        DateTime dateTime = new DateTime();
        dateTime.date = date;
        dateTime.time = time;
        return dateTime;
    }

    public void setDateTime(java.util.Date javaUtilDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(javaUtilDate);
        this.date.setDate((byte) calendar.get(Calendar.DAY_OF_MONTH), (byte) (calendar.get(Calendar.MONTH) + 1), (short) calendar.get(Calendar.YEAR));
        this.time.setTime((byte) calendar.get(Calendar.HOUR_OF_DAY), (byte) (calendar.get(Calendar.MINUTE)), (byte) calendar.get(Calendar.SECOND));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(int day, int month, int year) {
        this.date.setDate(day, month, year);
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setTime(int second, int minute, int hour) {
        this.time.setTime(second, minute, hour);
    }

    public DateTime add(int years, int months, int days) {
        DateTime result = this;
        result.date = result.date.add(years, months, days);
        return result;
    }

    public DateTime subtract(int years, int months, int days) {
        DateTime result = this;
        result.date = result.date.subtract(years, months, days);
        return result;
    }

    public DateTime add(int hours, int minutes) {
        DateTime result = this;

        int seconds = (hours * 60 + minutes) * 60;

        Time time1 = new Time();
        time1.setTime(59, 59, 23);
        Time time = result.time;
        int diff1 = time1.calcSecondsDifference(time);
        if (seconds <= diff1) {
            result.time = time.add(0, 0, seconds);
            return result;
        }
        int ndays = (seconds - diff1 - 1) / (24 * 60 * 60) + 1;
        int nseconds = (seconds - diff1 - 1) % (24 * 60 * 60);
        Date date = (Date) result.date;
        result.date = date.plusDays(ndays);
        time1.setTime(0, 0, 0);
        result.time = time1.add(0, 0, nseconds);

        return result;
    }

    public DateTime subtract(int hours, int minutes) {
        DateTime result = this;

        int seconds = (hours * 60 + minutes) * 60;

        Time time1 = new Time();
        time1.setTime(0, 0, 0);
        Time time = result.time;
        int diff1 = time1.calcSecondsDifference(time);
        if (seconds <= diff1) {
            result.time = time.subtract(0, 0, seconds);
            return result;
        }
        int ndays = (seconds - diff1 - 1) / (24 * 60 * 60) + 1;
        int nseconds = (seconds - diff1 - 1) % (24 * 60 * 60);
        Date date = result.date;
        result.date = date.minusDays(ndays);
        time1.setTime(59, 59, 23);
        result.time = time1.subtract(0, 0, nseconds);

        return result;
    }

    public int calcSecondsDifference(final DateTime datetimeObject) {
        DateTime big, small;
        if (this.less(datetimeObject)) {
            big = datetimeObject;
            small = this;
        } else {
            big = this;
            small = datetimeObject;
        }
        int dayDiff = 0;
        if (big.date.getYear() != small.date.getYear() || big.date.getMonth() != small.date.getMonth() || big.date.getDay() != small.date.getDay()) {
            dayDiff = big.calcDaysDifference(small);
        }
        int bigSecond = big.time.getHour() * 60 * 60 + big.time.getMinute() * 60 + big.time.getSecond();
        int smallSecond = small.time.getHour() * 60 * 60 + small.time.getMinute() * 60 + small.time.getSecond();

        return ((dayDiff * 60 * 60 * 24) + bigSecond) - smallSecond;
    }

    public boolean less(final DateTime other) {
        return this.date.less(other.date) && this.time.less(other.time);
    }

    public boolean greater(final DateTime other) {
        return this.date.greater(other.date) || this.date.equals(other.date) && this.time.greater(other.time);
    }

    public boolean equals(final DateTime other) {
        return this.date.equals(other.date) && this.time.equals(other.time);
    }

    public int calcDaysDifference(final DateTime datetimeObject) {
        return this.date.calcDaysDifference(datetimeObject.date);
    }

    public DateTime add(final DateTime dateObject, int days) {
        DateTime result = dateObject;
        Date date = result.date;
        date = date.plusDays(days);
        result.date = date;

        return result;
    }

    public DateTime mius(final DateTime dateObject, int days) {
        DateTime result = dateObject;
        Date date = result.date;
        date = date.minusDays(days);
        result.date = date;

        return result;
    }

    @Override
    public String toString() {
        return this.date.toString() + " " + this.time.toString();
    }
}
