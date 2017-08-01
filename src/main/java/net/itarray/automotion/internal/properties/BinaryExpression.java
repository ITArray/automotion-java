package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Expression;

import java.util.function.BiFunction;

public class BinaryExpression<L, R, T> implements Expression<T> {

    private final Expression<L> left;
    private final Expression<R> right;
    private final BiFunction<L, R, T> operation;
    private String descriptionFormat;

    public BinaryExpression(Expression<L> left, Expression<R> right, BiFunction<L, R, T> operation, String descriptionFormat) {
        this.left = left;
        this.right = right;
        this.operation = operation;
        this.descriptionFormat = descriptionFormat;
    }

    @Override
    public T evaluateIn(Context context, Direction direction) {
        return operation.apply(left.evaluateIn(context, direction), right.evaluateIn(context, direction));
    }

    @Override
    public String getDescription(Context context, Direction direction) {
        return String.format(
                descriptionFormat,
                left.getDescription(context, direction),
                right.getDescription(context, direction));
    }
}
