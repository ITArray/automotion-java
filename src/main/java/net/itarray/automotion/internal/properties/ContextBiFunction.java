package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

@FunctionalInterface
public interface ContextBiFunction<L, R, T> {
    T apply(L l, R r, Context context);

    ContextBiFunction<Scalar, Scalar, Boolean> equalToWithTolerance = (scalar, object, context) -> scalar.minus(object).norm().isLessOrEqualTo(context.getTolerance());
    ContextBiFunction<Scalar, Scalar, Boolean> greaterOrEqualTo = (scalar, other, context) -> scalar.isGreaterOrEqualTo(other.minus(context.getTolerance()));
    ContextBiFunction<Scalar, Scalar, Boolean> greaterThan = (scalar, other, context) -> scalar.isGreaterThan(other.minus(context.getTolerance()));
    ContextBiFunction<Scalar, Scalar, Boolean> lessOrEqualTo = (scalar, other, context) -> scalar.isLessOrEqualTo(other.plus(context.getTolerance()));
    ContextBiFunction<Scalar, Scalar, Boolean> lessThan = (scalar, other, context) -> scalar.isLessThan(other.plus(context.getTolerance()));

}
