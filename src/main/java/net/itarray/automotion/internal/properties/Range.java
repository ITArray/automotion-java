package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

public class Range implements ScalarCondition {


    private final ScalarCondition minimum;
    private final ScalarCondition maximum;

    public Range(Scalar lowerLimit, Scalar upperLimit) {
        this.minimum = new Minimum(lowerLimit);
        this.maximum = new Maximum(upperLimit);
    }

    @Override
    public boolean evaluate(Scalar value) {
        return minimum.evaluate(value) && maximum.evaluate(value);
    }

    public String shortName() {
        return "range";
    }

    @Override
    public String toStringWithUnits(String units) {
        return String.format("[%s, %s]", maximum.toStringWithUnits(units), maximum.toStringWithUnits(units));
    }
}
