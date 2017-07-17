package net.itarray.automotion.internal.geometry;

import net.itarray.automotion.validation.properties.Condition;

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

    public boolean isLessOrEqualTo(Scalar other) {
        return isLessOrEqualTo(other.value);
    }

    public boolean isLessOrEqualTo(int otherValue) {
        return this.value <= otherValue;
    }

    public boolean isGreaterOrEqualTo(Scalar other) {
        return isGreaterOrEqualTo(other.value);
    }

    public boolean isGreaterOrEqualTo(int otherValue) {
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

    public boolean satisfies(Condition condition) {
        return condition.isSatisfiedOn(this);
    }
}
