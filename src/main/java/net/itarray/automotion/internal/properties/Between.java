package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

import static java.lang.String.format;
import static net.itarray.automotion.internal.geometry.Scalar.scalar;

public class Between implements Condition<Scalar> {

    private final Condition<Scalar> lowerLimit;
    private final Condition<Scalar> upperLimit;
    private final Expression<Scalar> lowerLimitExpression;
    private final Expression<Scalar> upperLimitExpression;

    public Between(Expression<Scalar> lowerLimit, Expression<Scalar> upperLimit) {
        lowerLimitExpression = lowerLimit;
        this.lowerLimit = Condition.greaterOrEqualTo(lowerLimitExpression);
        upperLimitExpression = upperLimit;
        this.upperLimit = Condition.lessOrEqualTo(upperLimitExpression);
    }

    @Override
    public <V extends MetricSpace<V>> boolean isSatisfiedOn(Expression<Scalar> toBeConditioned, Context context, ExtendGiving<V> direction) {
        return lowerLimit.isSatisfiedOn(toBeConditioned, context, direction) && upperLimit.isSatisfiedOn(toBeConditioned, context, direction);
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        return format(
                "between %s and %s%s",
                lowerLimitExpression.getDescription(context, direction),
                upperLimitExpression.getDescription(context, direction),
                context.getTolerance().equals(scalar(0)) ? "" : format("(~%s)", context.getTolerance()));
    }

    @Override
    public String toString() {
        return format("between[%s, %s]", lowerLimit, upperLimit);
    }


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Between)) {
            return false;
        }
        Between other = (Between) object;
        return lowerLimit.equals(other.lowerLimit) && upperLimit.equals(other.upperLimit);
    }

    @Override
    public int hashCode() {
        return lowerLimit.hashCode() * 31 ^ upperLimit.hashCode();
    }
}
