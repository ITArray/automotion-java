package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

public class SuccessorConditionedExpressionDescription<T> extends ConditionedExpressionDescription<T> {
    private final Direction direction;

    public SuccessorConditionedExpressionDescription(Expression<T> toBeConditioned, Condition<T> toBeApplied, Direction direction) {
        super(toBeConditioned, toBeApplied);
        this.direction = direction;
    }


    public <V extends MetricSpace<V>> String describe(Context context, ExtendGiving<V> extendGiving) {
        T t = toBeConditioned.evaluateIn(context, extendGiving);
        return String.format(direction.afterName() + " element aligned not properly. Expected margin should be %s. Actual margin is %s",
                toBeApplied.getDescription(context, extendGiving),
                (t instanceof MetricSpace) ? ((MetricSpace) t).toStringWithUnits("px") : t);
    }

}
