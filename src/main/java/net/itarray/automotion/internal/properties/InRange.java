package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

public class InRange implements ScalarCondition {


    private final ScalarCondition minimum;
    private final ScalarCondition maximum;

    public InRange(Scalar lowerLimit, Scalar upperLimit) {
        this.minimum = new Minimum(lowerLimit);
        this.maximum = new Maximum(upperLimit);
    }

    @Override
    public boolean isSatisfiedOn(Scalar value) {
        return minimum.isSatisfiedOn(value) && maximum.isSatisfiedOn(value);
    }

    public String shortName() {
        return "range";
    }

    @Override
    public String toStringWithUnits(String units) {
        return String.format("[%s, %s]", maximum.toStringWithUnits(units), maximum.toStringWithUnits(units));
    }
}
