package net.itarray.automotion.validation.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Between;
import net.itarray.automotion.internal.properties.BinaryScalarConditionWithFixedOperand;
import net.itarray.automotion.internal.properties.ConditionedExpression;
import net.itarray.automotion.internal.properties.ConstantExpression;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.internal.properties.PixelConstant;

public interface Condition<T> {

    static Condition<Scalar> equalTo(int limit) {
        return equalTo(new Scalar(limit));
    }

    static Condition<Scalar> equalTo(Scalar limit) {
        return equalTo(new PixelConstant(limit));
    }

    static Condition<Scalar> equalTo(Expression<Scalar> lowerLimit) {
        return new BinaryScalarConditionWithFixedOperand(lowerLimit, Scalar::equals, "equal to %s");
    }

    static Condition<Scalar> greaterOrEqualTo(int limit) {
        return greaterOrEqualTo(new Scalar(limit));
    }

    static Condition<Scalar> greaterOrEqualTo(Scalar limit) {
        return greaterOrEqualTo(new PixelConstant(limit));
    }

    static Condition<Scalar> greaterOrEqualTo(Expression<Scalar> lowerLimit) {
        return new BinaryScalarConditionWithFixedOperand(lowerLimit, Scalar::isGreaterOrEqualTo, "greater or equal to %s");
    }

    static Condition<Scalar> greaterThan(int limit) {
        return greaterThan(new Scalar(limit));
    }

    static Condition<Scalar> greaterThan(Scalar limit) {
        return greaterThan(new PixelConstant(limit));
    }

    static Condition<Scalar> greaterThan(Expression<Scalar> lowerLimit) {
        return new BinaryScalarConditionWithFixedOperand(lowerLimit, Scalar::isGreaterThan, "greater than %s");
    }

    static Condition<Scalar> lessOrEqualTo(int limit) {
        return lessOrEqualTo(new Scalar(limit));
    }

    static Condition<Scalar> lessOrEqualTo(Scalar limit) {
        return lessOrEqualTo(new PixelConstant(limit));
    }

    static Condition<Scalar> lessOrEqualTo(Expression<Scalar> upperLimit) {
        return new BinaryScalarConditionWithFixedOperand(upperLimit, Scalar::isLessOrEqualTo, "less or equal to %s");
    }

    static Condition<Scalar> lessThan(int limit) {
        return lessThan(new Scalar(limit));
    }

    static Condition<Scalar> lessThan(Scalar limit) {
        return lessThan(new PixelConstant(limit));
    }

    static Condition<Scalar> lessThan(Expression<Scalar> upperLimit) {
        return new BinaryScalarConditionWithFixedOperand(upperLimit, Scalar::isLessThan, "less than %s");
    }

    static LowerLimit between(int lowerLimit) {
        return new LowerLimit(lowerLimit);
    }

    static LowerLimit between(Scalar lowerLimit) {
        return new LowerLimit(lowerLimit);
    }

    static LowerLimit between(Expression<Scalar> lowerLimit) {
        return new LowerLimit(lowerLimit);
    }

    class LowerLimit {

        private final Expression<Scalar> lowerLimit;

        public LowerLimit(Expression<Scalar> lowerLimit) {
            this.lowerLimit = lowerLimit;
        }

        public LowerLimit(Scalar lowerLimit) {
            this(new PixelConstant(lowerLimit));
        }

        public LowerLimit(int lowerLimit) {
            this(new PixelConstant(new Scalar(lowerLimit)));
        }

        public Condition<Scalar> and(int upperLimit) {
            return and(new Scalar(upperLimit));
        }

        public Condition<Scalar> and(Scalar upperLimit) {
            return and(new PixelConstant(upperLimit));
        }

        public Condition<Scalar> and(Expression<Scalar> upperLimit) {
            return new Between(lowerLimit, upperLimit);
        }
    }

    default boolean isSatisfiedOn(T value, Context context, Direction direction) {
        return applyTo(new ConstantExpression<T>(value)).evaluateIn(context, direction);
    }

    default Expression<Boolean> applyTo(Expression<T> toBeConditioned) {
        return new ConditionedExpression<>(toBeConditioned, this);
    }

    String getDescription(Context context, Direction direction);
}
