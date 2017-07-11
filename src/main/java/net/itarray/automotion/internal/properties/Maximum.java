package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

public class Maximum implements ScalarCondition {
    private final Scalar limit;

    public Maximum(Scalar limit) {
        this.limit = limit;
    }

    public boolean evaluate(Scalar value) {
        return limit.isGreaterOrEqualThan(value);
    }
}
