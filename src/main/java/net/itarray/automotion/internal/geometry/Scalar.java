package net.itarray.automotion.internal.geometry;

public class Scalar {
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
        return value < other.value;
    }

    public boolean isLessOrEqualThan(Scalar other) {
        return value <= other.value;
    }
}
