package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.validation.properties.Expression;

import java.util.function.BiFunction;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;

public class BinaryExpression<L, R, T> implements Expression<T> {

    private final Expression<L> left;
    private final Expression<R> right;
    private ContextBiFunction<L, R, T> contextBiFunction;
    private String descriptionFormat;

    public BinaryExpression(Expression<L> left, Expression<R> right, ContextBiFunction<L, R, T> contextBiFunction, String descriptionFormat) {
        this.left = left;
        this.right = right;
        this.descriptionFormat = descriptionFormat;
        this.contextBiFunction = contextBiFunction;
    }

    public BinaryExpression(Expression<L> left, Expression<R> right, BiFunction<L, R, T> operation, String descriptionFormat) {
        this(left, right, (l, r, context) -> operation.apply(l, r), descriptionFormat);
    }

    @Override
    public <V extends MetricSpace<V>> T evaluateIn(Context context, ExtendGiving<V> direction) {
        return contextBiFunction.apply(left.evaluateIn(context, direction), right.evaluateIn(context, direction), context);
    }

    @Override
    public <V extends MetricSpace<V>>String getDescription(Context context, ExtendGiving<V> direction) {
        String toleranceDescription = context.getTolerance().equals(scalar(0)) ? "" : String.format(" with tolerance %s", context.getTolerance());
        return String.format(
                descriptionFormat,
                left.getDescription(context, direction),
                right.getDescription(context, direction)) + toleranceDescription;
    }
}
