package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

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
    public boolean isSatisfiedOn(Scalar value, Context context, Direction direction) {
        return lowerLimit.isSatisfiedOn(value, context, direction) && upperLimit.isSatisfiedOn(value, context, direction);
    }

    @Override
    public String toStringWithUnits(String units) {
        return String.format("between %s and %s", lowerLimitExpression.toStringWithUnits(units), upperLimitExpression.toStringWithUnits(units));
    }

    @Override
    public String toString() {
        return String.format("between[%s, %s]", lowerLimit, upperLimit);
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
