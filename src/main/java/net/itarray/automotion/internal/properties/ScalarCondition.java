package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

public interface ScalarCondition {
    boolean isSatisfiedOn(Scalar value);

    String shortName();

    String toStringWithUnits(String units);
}
