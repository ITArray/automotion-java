package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
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
    public <V extends MetricSpace<V>> Boolean evaluateIn(Context context, ExtendGiving<V> direction) {
        return toBeApplied.isSatisfiedOn(toBeConditioned.evaluateIn(context, direction), context, direction);
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        T t = toBeConditioned.evaluateIn(context, direction);
        return String.format("Expected %s to be %s. Actual %s is: %s",
                toBeConditioned.getDescription(context, direction),
                toBeApplied.getDescription(context, direction),
                toBeConditioned.getRepeatedDescription(context, direction),
                (t instanceof MetricSpace) ? ((MetricSpace) t).toStringWithUnits("px") : t);
    }
}
