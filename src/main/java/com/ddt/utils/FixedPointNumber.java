package com.ddt.utils;

public class FixedPointNumber {

    private static final int POINT_POSITION = 9;

    private static final long PRECISION = 1000000000;

    private static final long[] POW10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    private long m_value;

    public FixedPointNumber() {
        this.m_value = 0;
    }

    public FixedPointNumber(double value) {
        this.m_value = (long) (value * PRECISION);
    }

    public FixedPointNumber(int value) {
        this.m_value = (long) value * PRECISION;
    }

    public FixedPointNumber(long value, int pointPosition) {
        m_value = (value) * POW10[POINT_POSITION - pointPosition];
    }

    public FixedPointNumber(final String value) {
        createFixedPointNumber(value);
    }

    public FixedPointNumber(final String value, char decimal) {
        new FixedPointNumber(value, decimal, true);
    }

    public FixedPointNumber(final String value, char decimal, boolean positive) {
        m_value = Long.valueOf(value);

        m_value *= POW10[POINT_POSITION - (int) decimal];
        if (!positive) {
            m_value = -m_value;
        }
    }

    @Override
    public String toString() {
        long decimal = Math.abs(this.m_value % PRECISION);

        int decimalCount = 9;
        if (decimal > 0) {
            while (decimal % 10 == 0) {
                decimal /= 10;
                decimalCount--;
            }
        } else {
            decimalCount = 1;
        }
        return m_value / PRECISION + "." + decimal;
    }


    public boolean isZero() {
        return m_value == 0;
    }

    public FixedPointNumber add(final FixedPointNumber other) {
        FixedPointNumber fixedPointNumber = new FixedPointNumber(0);
        fixedPointNumber.m_value = this.m_value + other.m_value;
        return fixedPointNumber;
    }

    public FixedPointNumber subtract(final FixedPointNumber other) {
        FixedPointNumber fixedPointNumber = new FixedPointNumber(0);
        fixedPointNumber.m_value = this.m_value - other.m_value;
        return fixedPointNumber;
    }

    public FixedPointNumber multiply(final FixedPointNumber other) {
        long integer1 = m_value / PRECISION;
        long decimal1 = m_value % PRECISION;
        long integer2 = other.m_value / PRECISION;
        long decimal2 = other.m_value % PRECISION;

        FixedPointNumber fixedPointNumber = new FixedPointNumber(0);
        fixedPointNumber.m_value = integer1 * integer2 * PRECISION +
                integer1 * decimal2 + integer2 * decimal1 +
                decimal1 * decimal2 / PRECISION;

        return fixedPointNumber;
    }

    public FixedPointNumber divide(final FixedPointNumber other) {
        return new FixedPointNumber((double) m_value / other.m_value);
    }

    public FixedPointNumber divide(final int value) {
        return new FixedPointNumber(this.m_value / PRECISION / value);
    }

    public FixedPointNumber remainder(final FixedPointNumber other) {
        return new FixedPointNumber(m_value % other.m_value);
    }

    public boolean less(final FixedPointNumber other) {
        return this.m_value < other.m_value;
    }

    public boolean greater(final FixedPointNumber other) {
        return this.m_value > other.m_value;
    }

    public boolean equals(final FixedPointNumber other) {
        return this.m_value == other.m_value;
    }

    public boolean greaterEqual(final FixedPointNumber other) {
        return this.m_value >= other.m_value;
    }

    public boolean lessEquals(final FixedPointNumber other) {
        return this.m_value <= other.m_value;
    }

    public int getValue(int digitsAfterPoint) {
        if (digitsAfterPoint > POINT_POSITION) {
            digitsAfterPoint = POINT_POSITION;
        }

        return (int) (m_value / POW10[POINT_POSITION - digitsAfterPoint]);
    }

    public FixedPointNumber round() {
        return new FixedPointNumber(0);//TODO
    }

    public int toInt() {
        return (int) (m_value / POW10[POINT_POSITION]);
    }

    public double toDouble() {
        return (double) m_value / POW10[POINT_POSITION];
    }

    private void createFixedPointNumber(final String value) {
        m_value = 0;
        int powValue = POINT_POSITION;
        boolean positive = true;
        boolean isDecimal = false;
        int idx = 0;
        if (value.charAt(0) == '-') {
            positive = false;
            ++idx;
        }

        while (idx < value.length() && value.charAt(idx) != ' ' && powValue > 0) {
            if (value.charAt(idx) == '.') {
                isDecimal = true;
            } else {
                assert (value.charAt(idx) >= '0');
                assert (value.charAt(idx) <= '9');
                m_value = m_value * 10 + (value.charAt(idx) - '0');
                if (isDecimal) {
                    powValue -= 1;
                }
            }
            ++idx;
        }

        m_value *= POW10[powValue];
        if (!positive) {
            m_value = -m_value;
        }
    }
}
