package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.GroupElement;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

public class ConditionedExpression<T> implements Expression<Boolean> {

    private final Expression<T> toBeConditioned;
    private final Condition<T> toBeApplied;

    public ConditionedExpression(Expression<T> toBeConditioned, Condition<T> toBeApplied) {
        this.toBeConditioned = toBeConditioned;
        this.toBeApplied = toBeApplied;
    }

    @Override
    public Boolean evaluateIn(Context context, Direction direction) {
        return toBeApplied.isSatisfiedOn(toBeConditioned.evaluateIn(context, direction), context, direction);
    }

    @Override
    public String getDescription(Context context, Direction direction) {
        T t = toBeConditioned.evaluateIn(context, direction);
        return String.format("Expected %s to be %s. Actual %s is: %s",
                toBeConditioned.getDescription(context, direction),
                toBeApplied.getDescription(context, direction),
                toBeConditioned.getRepeatedDescription(context, direction),
                (t instanceof GroupElement) ? ((GroupElement) t).toStringWithUnits("px") : t);
    }
}
