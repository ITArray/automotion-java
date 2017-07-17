package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;

public class GreaterOrEqual implements Condition<Scalar> {
    private final Scalar limit;

    GreaterOrEqual(Scalar limit) {
        this.limit = limit;
    }

    public boolean isSatisfiedOn(Scalar value) {
        return limit.isGreaterOrEqualTo(value);
    }

    public String shortName() {
        return "max";
    }

    @Override
    public String toStringWithUnits(String units) {
        return limit.toStringWithUnits(units);
    }

    @Override
    public String toString() {
        return String.format("greaterOrEqualTo(%s)", limit);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GreaterOrEqual)) {
            return false;
        }
        GreaterOrEqual other = (GreaterOrEqual) object;
        return limit.equals(other.limit);
    }

    @Override
    public int hashCode() {
        return limit.hashCode();
    }
}
