package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

public class Minimum implements ScalarCondition {
    private final Scalar limit;

    public Minimum(Scalar limit) {
        this.limit = limit;
    }

    public Minimum(int limit) {
        this(new Scalar(limit));
    }

    public boolean isSatisfiedOn(Scalar value) {
        return limit.isLessOrEqualThan(value);
    }

    public String shortName() {
        return "min";
    }

    @Override
    public String toStringWithUnits(String units) {
        return limit.toStringWithUnits(units);
    }
}
