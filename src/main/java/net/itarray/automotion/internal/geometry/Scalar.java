package net.itarray.automotion.internal.geometry;

import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.apache.commons.math3.fraction.Fraction;

public class Scalar implements Group<Scalar>, Comparable<Scalar> {
    private final Fraction fraction;

    public Scalar(int value) {
        fraction = new Fraction(value);
    }

    public Scalar(Fraction fraction) {
        this.fraction = fraction;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Scalar)) {
            return false;
        }
        Scalar other = (Scalar) object;
        return fraction.equals(other.fraction);
    }

    @Override
    public int hashCode() {
        return fraction.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s", fraction);
    }

    public String toStringWithUnits(String units) {
        return String.format("%s%s", fraction, units);
    }

    public int intValue() { // todo: remove usages, this is introspection
        return fraction.intValue();
    }

    public Scalar plus(int addend) {
        return plus(new Scalar(addend));
    }

    public Scalar plus(Scalar addend) {
        return new Scalar(fraction.add(addend.fraction));
    }

    public Scalar minus(int subtrahend) {
        return minus(new Scalar(subtrahend));
    }

    public Scalar minus(Scalar subtrahend) {
        return new Scalar(fraction.subtract(subtrahend.fraction));
    }

    public boolean isLessThan(Scalar other) {
        return compareTo(other) < 0;
    }

    public boolean isLessOrEqualTo(Scalar other) {
        return compareTo(other) <= 0;
    }

    public boolean isGreaterOrEqualTo(Scalar other) {
        return compareTo(other) >= 0;
    }

    public boolean isGreaterThan(Scalar other) {
        return compareTo(other) > 0;
    }

    @Override
    public int compareTo(Scalar other) {
        return fraction.compareTo(other.fraction);
    }

    public Scalar negated() {
        return new Scalar(fraction.negate());
    }

    public Scalar abs() {
        return new Scalar(fraction.abs());
    }

    public boolean satisfies(Condition condition, Context context, Direction direction) {
        return condition.isSatisfiedOn(this, context, direction);
    }

    public Scalar times(Scalar multiplicator) {
        return new Scalar(fraction.multiply(multiplicator.fraction));
    }

    public Scalar by(Scalar divisor) {
        return new Scalar(fraction.divide(divisor.fraction));
    }
}
