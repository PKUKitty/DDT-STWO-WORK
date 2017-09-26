package com.ddt.utils;

public class Time {

    public byte second;

    public byte minute;

    public byte hour;

    public Time() {
        this.second = 0;
        this.minute = 0;
        this.hour = 0;
    }

    public Time(int second, int minute, int hour) {
        this.second = (byte) second;
        this.minute = (byte) minute;
        this.hour = (byte) hour;
    }

    public static Time createTime(int second, int minute, int hour) {
        Time time = new Time();
        time.second = (byte) second;
        time.minute = (byte) minute;
        time.hour = (byte) hour;
        return time;
    }

    public Time addHours(int hours) {
        Time result = this;
        //add hours
        result.hour += hours;
        if (result.hour >= 24) {
            result.hour = (byte) (result.hour % 24);
        }

        return result;
    }

    public Time subtractHours(int hours) {
        Time result = this;
        //subtract hours
        int temp = result.hour - hours;
        if (temp < 0) {
            if (-temp % 24 == 0) {
                result.hour = 0;
            } else {
                result.hour = (byte) (24 - (-temp % 24));
            }
        } else {
            result.hour = (byte) temp;
        }
        return result;
    }

    public Time addMinutes(int minutes) {
        Time result = this;
        //add minutes
        int temp = result.minute + minutes;
        int hours = 0;
        if (temp >= 60) {
            result.minute = (byte) (temp % 60);
            hours = temp / 60;
        } else {
            result.minute = (byte) temp;
        }
        result = result.addHours(hours);
        return result;
    }

    public Time subtractMinutes(int minutes) {
        Time result = this;
        //subtract minutes
        int temp = result.minute - minutes;
        int hours = 0;
        if (temp < 0) {
            if (-temp % 60 == 0) {
                result.minute = 0;
            } else {
                result.minute = (byte) (60 - (-temp % 60));
            }
            hours = (-temp / 60) + 1;
        } else {
            result.minute = (byte) temp;
        }
        result = result.subtractHours(hours);
        return result;
    }

    public Time add(int hours, int minutes, int seconds) {
        Time result = this;
        result = result.addHours(hours);
        result = result.addMinutes(minutes);
        return add(result, seconds);
    }

    public Time subtract(int hours, int minutes, int seconds) {
        Time result = this;
        result = result.subtractHours(hours);
        result = result.subtractMinutes(minutes);
        return minus(result, seconds);
    }

    public int calcSecondsDifference(final Time timeObject) {
        if (this.greater(timeObject)) {
            return calculatorDiffer(this, timeObject);
        } else {
            return calculatorDiffer(timeObject, this);
        }
    }

    public int calculatorDiffer(final Time timeObject1, final Time timeObject2) {
        int seconds = timeObject1.second - timeObject2.second;
        int minutes = timeObject1.minute - timeObject2.minute;
        int hours = timeObject1.hour - timeObject2.hour;
        return seconds + minutes * 60 + hours * 3600;
    }

    public boolean greater(final Time timeObject) {
        if (this.hour < timeObject.hour) {
            return false;
        }

        if (this.hour > timeObject.hour) {
            return true;
        }

        if (this.hour == timeObject.hour) {
            if (this.minute > timeObject.minute) {
                return true;
            }

            if (this.minute < timeObject.minute) {
                return false;
            }

            if (this.minute == timeObject.minute) {
                return this.second > timeObject.second;
            }
        }
        return false;
    }


    public boolean less(final Time timeObject) {
        if (this.hour > timeObject.hour) {
            return false;
        }

        if (this.hour < timeObject.hour) {
            return true;
        }

        if (this.hour == timeObject.hour) {
            if (this.minute < timeObject.minute) {
                return true;
            }

            if (this.minute > timeObject.minute) {
                return false;
            }

            if (this.minute == timeObject.minute) {
                return this.second < timeObject.second;
            }
        }
        return false;
    }

    public boolean equals(final Time timeObject) {
        return this.hour == timeObject.hour && this.minute == timeObject.minute && this.second == timeObject.second;
    }

    public Time add(final Time timeObject, int seconds) {
        if (seconds < 0) {
            return minus(timeObject, (-1 * seconds));
        }

        Time result = timeObject;
        int secondPos = result.second + seconds;
        //judge whether should we calculate the minute
        if (secondPos < 60) {
            result.second = (byte) secondPos;
            return result;
        } else {
            result.second = (byte) (secondPos % 60);
            int minutePos = result.minute + secondPos / 60;
            // judge whether should we calculate the hour
            if (minutePos < 60) {
                result.minute = (byte) minutePos;
                return result;
            } else {
                result.minute = (byte) (minutePos % 60);
                int hourPos = result.hour + minutePos / 60;
                // judge whether the time larger than one day
                if (hourPos < 24) {
                    result.hour = (byte) hourPos;
                    return result;
                } else {
                    result.hour = (byte) (hourPos % 24);
                    return result;
                }
            }
        }
    }

    public Time minus(final Time timeObject, int seconds) {
        if (seconds < 0) {
            return add(timeObject, (-1 * seconds));
        }

        Time result = timeObject;

        int modSeconds = seconds % 86400;
        int secondPos = result.second - modSeconds;
        // judge whether should we calculate the minute
        if (secondPos >= 0) {
            result.second = (byte) secondPos;
            return result;
        } else {
            int minutePos = result.minute;
            int secondContainMin = minutePos * 60 + secondPos;
            // judge whether should we calculate the hour
            if (secondContainMin >= 0) {
                result.minute = (byte) (secondContainMin / 60);
                result.second = (byte) (secondContainMin % 60);
                return result;
            } else {
                int hourPos = result.hour;
                int secondContainHour = secondContainMin + hourPos * 3600;
                if (secondContainHour >= 0) {
                    result.hour = (byte) (secondContainHour / 3600);
                    result.minute = (byte) ((secondContainHour % 3600) / 60);
                    result.second = (byte) (secondContainHour % 60);
                    return result;
                } else {
                    secondContainHour = secondContainHour + 86400;
                    result.hour = (byte) (secondContainHour / 3600);
                    result.minute = (byte) ((secondContainHour % 3600) / 60);
                    result.second = (byte) (secondContainHour % 60);
                    return result;
                    //                int mod = seconds % 86400;
                    //                return (timeObject - mod);

                }
            }
        }
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

}
