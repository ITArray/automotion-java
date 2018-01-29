package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

public class ConditionedExpression<T> implements Expression<Boolean> {

    private final Expression<T> toBeConditioned;
    private final Condition<T> toBeApplied;
    private final ConditionedExpressionDescription description;

    public ConditionedExpression(Expression<T> toBeConditioned, Condition<T> toBeApplied) {
        this(toBeConditioned, toBeApplied, new StandardConditionedExpressionDescription<>(toBeConditioned, toBeApplied));
    }

    public ConditionedExpression(Expression<T> toBeConditioned, Condition<T> toBeApplied, ConditionedExpressionDescription<T> description) {
        this.toBeConditioned = toBeConditioned;
        this.toBeApplied = toBeApplied;
        this.description = description;
    }

    @Override
    public <V extends MetricSpace<V>> Boolean evaluateIn(Context context, ExtendGiving<V> direction) {
        return toBeApplied.isSatisfiedOn(toBeConditioned, context, direction);
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        return description.describe(context, direction);
    }

}
