package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

public abstract class ConditionedExpressionDescription<T> {
    protected final Expression<T> toBeConditioned;
    protected final Condition<T> toBeApplied;

    public ConditionedExpressionDescription(Expression<T> toBeConditioned, Condition<T> toBeApplied) {
        this.toBeConditioned = toBeConditioned;
        this.toBeApplied = toBeApplied;
    }

    public abstract <V extends MetricSpace<V>> String describe(Context context, ExtendGiving<V> direction);

}
