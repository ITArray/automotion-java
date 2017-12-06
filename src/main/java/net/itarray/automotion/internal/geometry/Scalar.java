package net.itarray.automotion.internal.geometry;

import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.apache.commons.math3.fraction.Fraction;

public class Scalar implements MetricSpace<Scalar>, Comparable<Scalar> {
    private final Fraction fraction;

    private Scalar(int value) {
        fraction = new Fraction(value);
    }

    private Scalar(Fraction fraction) {
        this.fraction = fraction;
    }

    public static Scalar scalar(int value) {
        return new Scalar(value);
    }

    public static Scalar scalar(Fraction fraction) {
        return new Scalar(fraction);
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

    public Fraction fractionValue() {
        return fraction;
    }

    public Scalar plus(int addend) {
        return plus(scalar(addend));
    }

    public Scalar plus(Scalar addend) {
        return scalar(fraction.add(addend.fraction));
    }

    public Scalar minus(int subtrahend) {
        return minus(scalar(subtrahend));
    }

    public Scalar minus(Scalar subtrahend) {
        return scalar(fraction.subtract(subtrahend.fraction));
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
        return scalar(fraction.negate());
    }

    public Scalar abs() {
        return scalar(fraction.abs());
    }

    public boolean satisfies(Condition condition, Context context, Direction direction) {
        return condition.isSatisfiedOn(this, context, direction);
    }

    public Scalar times(Scalar multiplicator) {
        return scalar(fraction.multiply(multiplicator.fraction));
    }

    public Scalar by(Scalar divisor) {
        return scalar(fraction.divide(divisor.fraction));
    }

    public Scalar min(Scalar other) {
        return isLessOrEqualTo(other) ? this : other;
    }

    public Scalar max(Scalar other) {
        return isGreaterOrEqualTo(other) ? this : other;
    }

    public Scalar norm() {
        return abs();
    }
}
