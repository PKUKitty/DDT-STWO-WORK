package com.ddt.utils;

public class DateTime {
    public Date date;

    public Time time;

    public DateTime() {
//        this.date = new Date();
//        this.time = new Time();
    }

    public DateTime(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public static DateTime createDatetime(Date date, Time time) {
        DateTime dateTime = new DateTime();
        dateTime.date = date;
        dateTime.time = time;
        return dateTime;
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
        time1.hour = 23;
        time1.minute = 59;
        time1.second = 59;
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
        time1.hour = 0;
        time1.minute = 0;
        time1.second = 0;
        result.time = time1.add(0, 0, nseconds);

        return result;
    }

    public DateTime subtract(int hours, int minutes) {
        DateTime result = this;

        int seconds = (hours * 60 + minutes) * 60;

        Time time1 = new Time();
        time1.hour = 0;
        time1.minute = 0;
        time1.second = 0;
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
        time1.hour = 23;
        time1.minute = 59;
        time1.second = 59;
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
        if (big.date.year != small.date.year || big.date.month != small.date.month || big.date.day != small.date.day) {
            dayDiff = big.calcDaysDifference(small);
        }
        int bigSecond = big.time.hour * 60 * 60 + big.time.minute * 60 + big.time.second;
        int smallSecond = small.time.hour * 60 * 60 + small.time.minute * 60 + small.time.second;

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
}
