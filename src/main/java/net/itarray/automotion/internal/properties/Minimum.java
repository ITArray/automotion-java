package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

public class Minimum implements ScalarCondition {
    private final Scalar limit;

    public Minimum(Scalar limit) {
        this.limit = limit;
    }

    public boolean evaluate(Scalar value) {
        return limit.isLessOrEqualThan(value);
    }
}
