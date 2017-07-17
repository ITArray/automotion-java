package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;

public class GreaterOrEqual implements Condition<Scalar> {
    private final Scalar lowerLimit;

    GreaterOrEqual(Scalar lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public boolean isSatisfiedOn(Scalar value) {
        return value.isGreaterOrEqualTo(lowerLimit);
    }

    public String shortName() {
        return "min";
    }

    @Override
    public String toStringWithUnits(String units) {
        return lowerLimit.toStringWithUnits(units);
    }

    @Override
    public String toString() {
        return String.format("greaterOrEqualTo(%s)", lowerLimit);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GreaterOrEqual)) {
            return false;
        }
        GreaterOrEqual other = (GreaterOrEqual) object;
        return lowerLimit.equals(other.lowerLimit);
    }

    @Override
    public int hashCode() {
        return lowerLimit.hashCode();
    }
}
