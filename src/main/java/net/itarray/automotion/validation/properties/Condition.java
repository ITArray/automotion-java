package net.itarray.automotion.validation.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Between;
import net.itarray.automotion.internal.properties.Expression;
import net.itarray.automotion.internal.properties.GreaterOrEqualTo;
import net.itarray.automotion.internal.properties.LessOrEqualTo;
import net.itarray.automotion.internal.properties.ScalarConstant;

public interface Condition<T> {

    static Condition<Scalar> greaterOrEqualTo(int limit) {
        return greaterOrEqualTo(new Scalar(limit));
    }

    static Condition<Scalar> greaterOrEqualTo(Scalar limit) {
        return greaterOrEqualTo(new ScalarConstant(limit));
    }

    static Condition<Scalar> greaterOrEqualTo(Expression<Scalar> lowerLimit) {
        return new GreaterOrEqualTo(lowerLimit);
    }

    static Condition<Scalar> lessOrEqualTo(int limit) {
        return lessOrEqualTo(new Scalar(limit));
    }

    static Condition<Scalar> lessOrEqualTo(Scalar limit) {
        return lessOrEqualTo(new ScalarConstant(limit));
    }

    static Condition<Scalar> lessOrEqualTo(Expression<Scalar> upperLimit) {
        return new LessOrEqualTo(upperLimit);
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
            this(new ScalarConstant(lowerLimit));
        }

        public LowerLimit(int lowerLimit) {
            this(new ScalarConstant(new Scalar(lowerLimit)));
        }

        public Condition<Scalar> and(int upperLimit) {
            return and(new Scalar(upperLimit));
        }

        public Condition<Scalar> and(Scalar upperLimit) {
            return and(new ScalarConstant(upperLimit));
        }

        public Condition<Scalar> and(Expression<Scalar> upperLimit) {
            return new Between(lowerLimit, upperLimit);
        }
    }

    boolean isSatisfiedOn(T value);

    String shortName();

    String toStringWithUnits(String units);
}
