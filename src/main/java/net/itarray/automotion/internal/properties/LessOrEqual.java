package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;

public class LessOrEqual implements Condition<Scalar> {
    private final Scalar upperLimit;

    LessOrEqual(Scalar upperLimit) {
        this.upperLimit = upperLimit;
    }

    public boolean isSatisfiedOn(Scalar value) {
        return value.isLessOrEqualTo(upperLimit);
    }

    public String shortName() {
        return "max";
    }

    @Override
    public String toStringWithUnits(String units) {
        return upperLimit.toStringWithUnits(units);
    }

    @Override
    public String toString() {
        return String.format("lessOrEqualTo(%s)", upperLimit);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LessOrEqual)) {
            return false;
        }
        LessOrEqual other = (LessOrEqual) object;
        return upperLimit.equals(other.upperLimit);
    }

    @Override
    public int hashCode() {
        return upperLimit.hashCode();
    }
}
