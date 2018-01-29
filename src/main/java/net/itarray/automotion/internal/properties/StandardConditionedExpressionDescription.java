package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

public class StandardConditionedExpressionDescription<T> extends ConditionedExpressionDescription<T> {
    public StandardConditionedExpressionDescription(Expression<T> toBeConditioned, Condition<T> toBeApplied) {
        super(toBeConditioned, toBeApplied);
    }

    public <V extends MetricSpace<V>> String describe(Context context, ExtendGiving<V> direction) {
        T t = toBeConditioned.evaluateIn(context, direction);
        return String.format("Expected %s to be %s. Actual %s is: %s",
                toBeConditioned.getDescription(context, direction),
                toBeApplied.getDescription(context, direction),
                toBeConditioned.getRepeatedDescription(context, direction),
                (t instanceof MetricSpace) ? ((MetricSpace) t).toStringWithUnits("px") : t);
    }

}
