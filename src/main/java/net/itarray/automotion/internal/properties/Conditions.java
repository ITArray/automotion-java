package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;

public class Conditions {
    public static Condition<Scalar> greaterOrEqualTo(Scalar limit) {
        return greaterOrEqualTo(new ScalarConstant(limit));
    }

    public static Condition<Scalar> greaterOrEqualTo(Expression<Scalar> lowerLimit) {
        return new GreaterOrEqualTo(lowerLimit);
    }

    public static Condition<Scalar> lessOrEqualTo(Scalar limit) {
        return lessOrEqualTo(new ScalarConstant(limit));
    }

    public static Condition<Scalar> lessOrEqualTo(Expression<Scalar> upperLimit) {
        return new LessOrEqualTo(upperLimit);
    }

    public static Between between(Scalar lowerLimit, Scalar upperLimit) {
        return between(new ScalarConstant(lowerLimit), new ScalarConstant(upperLimit));
    }

    public static Between between(ScalarConstant lowerLimit, ScalarConstant upperLimit) {
        return new Between(lowerLimit, upperLimit);
    }
}
