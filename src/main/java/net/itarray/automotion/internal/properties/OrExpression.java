package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.validation.properties.Expression;

public class OrExpression implements Expression<Boolean> {
    private final Expression<Boolean> left;
    private final Expression<Boolean> right;

    public OrExpression(Expression<Boolean> left, Expression<Boolean> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public <V extends MetricSpace<V>> Boolean evaluateIn(Context context, ExtendGiving<V> direction) {
        return left.evaluateIn(context, direction) || right.evaluateIn(context, direction);
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        return null;
    }
}
