package com.ddt.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Date {

    public static final Date MIN_DATE = createDate((byte) 0, (byte) 0, (short) 0);

    public static final Date MAX_DATE = createDate((byte) 31, (byte) 12, Short.MAX_VALUE);

    public static final int MONTH_SIZE = 12;

    private static final int[] PRIVATE_MONTH_DATE = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static final List MONTH_DATE = Collections.unmodifiableList(Arrays.asList(PRIVATE_MONTH_DATE));

    private byte day;

    private byte month;

    private short year;

    public Date() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public Date(final int day, final int month, final int year) {
        this.day = (byte) day;
        this.month = (byte) month;
        this.year = (short) year;
    }


    public static Date createDate(final int day, final int month, final int year) {
        Date date = new Date();
        date.day = (byte) day;
        date.month = (byte) month;
        date.year = (short) year;
        return date;
    }

    public Date(java.util.Date javaUtilDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(javaUtilDate);
        this.day = (byte) calendar.get(Calendar.DAY_OF_MONTH);
        this.month = (byte) (calendar.get(Calendar.MONTH) + 1);
        this.year = (short) calendar.get(Calendar.YEAR);
    }

    public void setDate(int day, int month, int year) {
        this.day = (byte) day;
        this.month = (byte) month;
        this.year = (short) year;
    }

    public void setDate(java.util.Date javaUtilDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(javaUtilDate);
        this.day = (byte) calendar.get(Calendar.DAY_OF_MONTH);
        this.month = (byte) (calendar.get(Calendar.MONTH) + 1);
        this.year = (short) calendar.get(Calendar.YEAR);
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public static boolean checkDate(Date date) {
        return date != null && checkDate(date.getYear(), date.getMonth(), date.getDay());
    }

    public static boolean checkDate(short year, byte month, byte day) {
        int[] month_date = Arrays.copyOf(PRIVATE_MONTH_DATE, MONTH_SIZE);

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


    public Date plusDays(final int days) {
        int[] month_date = Arrays.copyOf(PRIVATE_MONTH_DATE, MONTH_SIZE);
        if (days < 0) {
            return this.minusDays(-1 * days);
        }
        Date result = this;

        if (0 == result.year % 400 || (0 == result.year % 4 && 0 != result.year % 100)) {
            month_date[1] = 29;
        } else {
            month_date[1] = 28;
        }

        int sum = result.day + days;
        do {
            if (sum > month_date[result.month - 1]) {
                sum -= month_date[result.month - 1];
                ++(result.month); //bigger than the current month
                if (result.month == 13) //over 12 months
                {
                    ++(result.year);
                    if (0 == result.year % 400 || (0 == result.year % 4 && 0 != result.year % 100)) {
                        month_date[1] = 29;
                    } else {
                        month_date[1] = 28;
                    }
                    result.month = 1; //the first month of the next year
                }
            }
        } while (sum > month_date[result.month - 1]); //end contidion
        result.day = (byte) sum;

        return result;
    }

    public Date minusDays(int days) {
        int[] month_date = Arrays.copyOf(PRIVATE_MONTH_DATE, MONTH_SIZE);

        if (days < 0) {
            Date result = this.plusDays(-1 * days);
            return result;
        }

        Date result = this;

        if (0 == result.year % 400 || (0 == result.year % 4 && 0 != result.year % 100)) {
            month_date[1] = 29;
        } else {
            month_date[1] = 28;
        }

        int sum = result.day - days;
        do {
            if (sum < 1) {
                --(result.month); //the prior month
                if (0 == result.month) //the prior year
                {
                    --(result.year);
                    if (0 == result.year % 400 || (0 == result.year % 4 && 0 != result.year % 100)) {
                        month_date[1] = 29;
                    } else {
                        month_date[1] = 28;
                    }
                    result.month = 12;
                }

                sum += month_date[result.month - 1];
            }
        } while (sum < 1); //end contidion
        result.day = (byte) sum;

        return result;
    }

    public Date add(int years, int months, int days) {
        int[] month_date = Arrays.copyOf(PRIVATE_MONTH_DATE, MONTH_SIZE);

        Date result = this;
        //add years
        result.year += years;
        //add months
        result.month += months;
        //result month > 12
        if (result.month > 12) {
            result.year = (short) (result.year + (result.month / 12));
            result.month = (byte) (result.month % 12);
        }

        //correct the last day in a month
        if (result.month != 2) {
            if (result.day > month_date[result.month - 1]) {
                result.day = (byte) month_date[result.month - 1];
            }
        } else {
            if (0 == result.year % 400 || (0 == result.year % 4 && 0 != result.year % 100)) {
                month_date[1] = 29;
            } else {
                month_date[1] = 28;
            }
            if (result.day > month_date[1]) {
                result.day = (byte) month_date[1];
            }
        }
        //add days;
        return result.plusDays(days);
    }

    public Date subtract(int years, int months, int days) {
        int[] month_date = Arrays.copyOf(PRIVATE_MONTH_DATE, MONTH_SIZE);

        Date result = this;
        //add years
        result.year -= years;
        //add months
        int temp = result.month - months;
        //result month < 1
        if (temp < 1) {
            result.year = (short) (result.year - (-temp / 12) - 1);
            result.month = (byte) (12 - (-temp % 12));
        } else {
            result.month = (byte) temp;
        }

        //correct the last day in a month
        if (result.month != 2) {
            if (result.day > month_date[result.month - 1]) {
                result.day = (byte) month_date[result.month - 1];
            }
        } else {
            if (0 == result.year % 400 || (0 == result.year % 4 && 0 != result.year % 100)) {
                month_date[1] = 29;
            } else {
                month_date[1] = 28;
            }
            if (result.day > month_date[1]) {
                result.day = (byte) month_date[1];
            }
        }
        //add days;
        return result.minusDays(days);
    }

    public int calcDaysDifference(final Date dateObject) {
        int[] month_date = Arrays.copyOf(PRIVATE_MONTH_DATE, MONTH_SIZE);

        if (this == dateObject) {
            return 0;
        }

        Date big, small;
        if (this.less(dateObject)) {
            big = dateObject;
            small = this;
        } else {
            big = this;
            small = dateObject;
        }
        if (big.year == small.year && big.month == small.month) {
            return big.day - small.day;
        } else if (big.year == small.year) {
            if (0 == big.year % 400 || (0 == big.year % 4 && 0 != big.year % 100)) {
                month_date[1] = 29;
            } else {
                month_date[1] = 28;
            }
            int sum = 0;
            for (int i = small.month; i < big.month; ++i) {
                sum += month_date[i - 1];
            }

            return sum - small.day + big.day;
        }
        int sum = 0;
        for (int i = small.year; i <= big.year; ++i) {
            int beginMonth, endMonth;
            if (i == small.year) {
                beginMonth = small.month;
                endMonth = 12;
            } else if (i == big.year) {
                beginMonth = 1;
                endMonth = big.month - 1;
            } else {
                beginMonth = 1;
                endMonth = 12;
            }
            if (0 == i % 400 || (0 == i % 4 && 0 != i % 100)) {
                month_date[1] = 29;
            } else {
                month_date[1] = 28;
            }
            for (int j = beginMonth; j <= endMonth; ++j) {
                sum += month_date[j - 1];
            }
        }

        return sum - small.day + big.day;
    }

    public int calcYearsDifference(final Date dateObject) {
        Date big, small;
        if (this.less(dateObject)) {
            big = dateObject;
            small = this;
        } else {
            big = this;
            small = dateObject;
        }

        if (big.month > small.month) {
            return big.year - small.year;
        }
        if (big.month < small.month) {
            return big.year - small.year - 1;
        }

        // The month is the same, test the days.
        if (big.day >= small.day) {
            return big.year - small.year;
        }

        return big.year - small.year - 1;
    }

    public boolean less(final Date dateObject) {
        if (this.year < dateObject.year) {
            return true;
        }

        if (this.year > dateObject.year) {
            return false;
        }

        if (this.year == dateObject.year) {
            if (this.month > dateObject.month) {
                return false;
            }

            if (this.month < dateObject.month) {
                return true;
            }

            if (this.month == dateObject.month) {
                return this.day < dateObject.day;
            }
        }
        return false;
    }

    public boolean greater(final Date dateObject) {
        if (this.year < dateObject.year) {
            return false;
        }

        if (this.year > dateObject.year) {
            return true;
        }

        if (this.year == dateObject.year) {
            if (this.month > dateObject.month) {
                return true;
            }

            if (this.month < dateObject.month) {
                return false;
            }

            if (this.month == dateObject.month) {
                return this.day > dateObject.day;
            }
        }
        return false;
    }

    public boolean equals(final Date dateObject) {
        return this.year == dateObject.year && this.month == dateObject.month && this.day == dateObject.day;
    }

    public String toString() {
        return String.format("%d-%02d-%02d", year, month, day);
    }
}
