package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;

public class Conditions {
    public static Condition<Scalar> greaterOrEqualTo(Scalar limit) {
        return new GreaterOrEqualTo(limit);
    }

    public static Condition<Scalar> lessOrEqualTo(Scalar limit) {
        return new LessOrEqualTo(limit);
    }

    public static Between between(Scalar lowerLimit, Scalar upperLimit) {
        return new Between(lowerLimit, upperLimit);
    }
}
