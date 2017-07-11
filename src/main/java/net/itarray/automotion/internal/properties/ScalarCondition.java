package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

/**
 * @author Torsten Mumme.
 */
public interface ScalarCondition {
    boolean evaluate(Scalar value);

    String shortName();

    String toStringWithUnits(String units);
}
