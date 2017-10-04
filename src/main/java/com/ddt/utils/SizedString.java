package com.ddt.utils;

public class SizedString {

    private int size;

    private char[] value;

    private SizedString(int size) {
        this.size = size;
        this.value = new char[this.size];
    }

    public SizedString(final String value, final int size) {
        this.size = size;
        this.value = new char[this.size];
        copyValue(value, size);
    }

    public SizedString(final String value, final int size, final int length) {
        this.size = size;
        this.value = new char[this.size];
        int i = 0;
        for (; i < size && i < length; i++) {
            this.value[i] = value.charAt(i);
        }

        for (; i < size; i++) {
            this.value[i] = ' ';
        }
    }

    public SizedString(final SizedString other) {
        this.size = other.size;
        this.value = other.value;
    }

    public int getSize() {
        return size;
    }

    public char[] getData() {
        return value;
    }

    public int compare(final String value) {
        for (int i = 0; i < this.size; ++i) {
            if (i == value.length()/*value.charAt(i) == '\0'*/) {
                for (int j = i; j < this.size; ++j) {
                    if (this.value[j] != ' ') {
                        return 1;
                    }
                }
                return 0;
            }

            if (this.value[i] != value.charAt(i)) {
                return this.value[i] - value.charAt(i);
            }
        }
        return 0;
    }

    public boolean equal(final String other) {
        return compare(other) == 0;
    }

    public boolean equal(final SizedString other) {
        return compare(other.string()) == 0;
    }

    public boolean unEqual(final String other) {
        return compare(other) != 0;
    }

    public boolean unEqual(final SizedString other) {
        return compare(other.string()) != 0;
    }

    public boolean greater(final String other) {
        return compare(other) > 0;
    }

    public boolean greater(final SizedString other) {
        return compare(other.string()) > 0;
    }

    public boolean greaterEqual(final String other) {
        return (!less(other));
    }

    public boolean greaterEqual(final SizedString other) {
        return (!less(other.string()));
    }


    public boolean less(final String other) {
        return compare(other) < 0;
    }

    public boolean less(final SizedString other) {
        return compare(other.string()) < 0;
    }


    public boolean lessEqual(final String other) {
        return !greater(other);
    }

    public boolean lessEqual(final SizedString other) {
        return !greater(other.string());
    }

    public void appendEraseBlank(final SizedString other, final int otherBeginIdx) {
        int i = 0;
        for (; i < this.size; ++i) {
            if (this.value[i] == ' ') {
                break;
            }
        }

        int j = otherBeginIdx;

        for (; i < this.size; ++i) {
            if (j >= other.getSize() || other.getData()[j] == ' ') {
                break;
            }

            {
                this.value[i] = other.getData()[j];
            }

            ++j;
        }
    }

    public SizedString append(final SizedString other) {
        SizedString ret = new SizedString(this.size + other.getSize());
        ret.copy(0, this.value, this.size);
        //ret.copy(this.size, other.getData(), this.size);
        ret.copy(this.size, other.getData(), other.getSize());
        return ret;
    }

    public void copy(final int index, final SizedString other) {
        copy(index, other.getData(), other.getSize());
    }

    public void copy(final SizedString other) {
        copy(0, other.getData(), other.getSize());
    }


    public void copy(final SizedString other, final int sourceStart, final int sourceEnd) {
        int copySize = sourceEnd - sourceStart;
        assert (sourceStart < sourceEnd);
        assert (sourceEnd <= other.getSize());

        if (copySize > this.size) {
            copySize = this.size;
        }

        for (int i = 0; i < copySize; ++i) {
            if (other.getData()[sourceStart + i] == ' ') {
                break;
            }

            this.value[i] = other.getData()[sourceStart + i];
        }
    }

    public void copy(int index, final char[] value, int size) {
        int i = 0;
        for (; i + index < this.size && i < size; ++i) {
            this.value[i + index] = value[i];
        }
    }

    public boolean contains(char ch) {
        for (int charIndex = 0; charIndex < this.size; ++charIndex) {
            if (this.value[charIndex] == ch) {
                return true;
            }
        }

        return false;
    }

    public char charAt(int index) {
        return value[index];
    }

    public String string() {
        return new String(value, 0, this.size);
    }

    public String subString(int start, int len) {
        return new String(this.value, 0, this.size).substring(start, len);
    }

    public String trimString() {
        return new String(value, 0, this.size).trim();
    }

    public boolean empty() {
        for (int charIndex = 0; charIndex < this.size; ++charIndex) {
            if (this.value[charIndex] != ' ') {
                return false;
            }
        }
        return true;
    }

    public long toLong() {
        return Long.valueOf(new String(this.value, 0, this.size));
    }

    public boolean isDigit() {
        for (char c :
                this.value) {
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    public void stripZeros() {
        int copyPosition = 0;
        for (int i = 0; i < this.size; ++i) {
            if (this.value[i] != '0') {
                copyPosition = i;
                break;
            }
        }

        int toPosition = 0;
        for (int i = copyPosition; i < this.size; ++i, ++toPosition) {
            this.value[toPosition] = this.value[i];
        }

        for (int i = toPosition; i < this.size; ++i) {
            this.value[toPosition] = ' ';
        }
    }


    private void copyValue(final String value, final int size) {
        int i = 0;
        for (; i < size && i < value.length(); i++) {
            this.value[i] = value.charAt(i);
        }

        for (; i < size; i++) {
            this.value[i] = ' ';
        }
    }
}
