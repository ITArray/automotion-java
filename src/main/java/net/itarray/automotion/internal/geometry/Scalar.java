package net.itarray.automotion.internal.geometry;

import net.itarray.automotion.internal.properties.ScalarCondition;

public class Scalar implements Group<Scalar>, Comparable<Scalar> {
    private final int value;

    public Scalar(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Scalar)) {
            return false;
        }
        Scalar other = (Scalar) object;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }

    public String toStringWithUnits(String units) {
        return String.format("%d%s", value, units);
    }

    public int getValue() {
        return value;
    }

    public Scalar plus(int addend) {
        return new Scalar(value + addend);
    }

    public Scalar plus(Scalar addend) {
        return plus(addend.getValue());
    }

    public Scalar minus(int subtrahend) {
        return new Scalar(value - subtrahend);
    }

    public Scalar minus(Scalar subtrahend) {
        return minus(subtrahend.getValue());
    }

    public boolean isLessThan(Scalar other) {
        return isLessThan(other.value);
    }

    public boolean isLessThan(int otherValue) {
        return value < otherValue;
    }

    public boolean isLessOrEqualThan(Scalar other) {
        return isLessOrEqualThan(other.value);
    }

    public boolean isLessOrEqualThan(int otherValue) {
        return this.value <= otherValue;
    }

    public boolean isGreaterOrEqualThan(Scalar other) {
        return isGreaterOrEqualThan(other.value);
    }

    public boolean isGreaterOrEqualThan(int otherValue) {
        return value >= otherValue;
    }

    public boolean isGreaterThan(Scalar other) {
        return isGreaterThan(other.value);
    }

    public boolean isGreaterThan(int otherValue) {
        return value > otherValue;
    }

    @Override
    public int compareTo(Scalar other) {
        return minus(other).getValue();
    }

    public Scalar negated() {
        return new Scalar(-value);
    }

    public Scalar abs() {
        return new Scalar(value >= 0 ? value : -value);
    }

    public boolean satisfies(ScalarCondition condition) {
        return condition.isSatisfiedOn(this);
    }
}
