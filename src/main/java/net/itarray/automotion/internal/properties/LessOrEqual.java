package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;

public class LessOrEqual implements Condition<Scalar> {
    private final Scalar limit;

    LessOrEqual(Scalar limit) {
        this.limit = limit;
    }

    public boolean isSatisfiedOn(Scalar value) {
        return limit.isLessOrEqualTo(value);
    }

    public String shortName() {
        return "min";
    }

    @Override
    public String toStringWithUnits(String units) {
        return limit.toStringWithUnits(units);
    }

    @Override
    public String toString() {
        return String.format("lessOrEqualTo(%s)", limit);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LessOrEqual)) {
            return false;
        }
        LessOrEqual other = (LessOrEqual) object;
        return limit.equals(other.limit);
    }

    @Override
    public int hashCode() {
        return limit.hashCode();
    }
}
