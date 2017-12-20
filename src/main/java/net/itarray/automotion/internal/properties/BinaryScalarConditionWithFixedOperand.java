package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

import java.util.function.BiPredicate;

import static java.lang.String.format;
import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.apache.commons.lang3.text.WordUtils.uncapitalize;


public class BinaryScalarConditionWithFixedOperand implements Condition<Scalar> {
    private final Expression<Scalar> fixedOperand;
    private final ContextBiFunction<Scalar, Scalar, Boolean> contextPredicate;
    private final String toStringFormat;

    public BinaryScalarConditionWithFixedOperand(Expression<Scalar> fixedOperand, ContextBiFunction<Scalar, Scalar, Boolean> contextPredicate, String toStringFormat) {
        this.fixedOperand = fixedOperand;
        this.contextPredicate = contextPredicate;
        this.toStringFormat = toStringFormat;
    }

    @Override
    public <V extends MetricSpace<V>> boolean isSatisfiedOn(Expression<Scalar> toBeConditioned, Context context, ExtendGiving<V> direction) {
        return contextPredicate.apply(toBeConditioned.evaluateIn(context, direction), fixedOperand.evaluateIn(context, direction), context);
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        String tolerance = context.getTolerance().equals(scalar(0)) ? "" : format("(~%s)", context.getTolerance());
        return format(toStringFormat, fixedOperand.getDescription(context, direction)) + tolerance;
    }

    @Override
    public String toString() {
        return format("%s(%s)", uncapitalize(getClass().getSimpleName()), fixedOperand);
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || !getClass().isAssignableFrom(object.getClass())) {
            return false;
        }
        BinaryScalarConditionWithFixedOperand other = (BinaryScalarConditionWithFixedOperand) object;
        return fixedOperand.equals(other.fixedOperand) && contextPredicate.equals(other.contextPredicate);
    }

    @Override
    public int hashCode() {
        return fixedOperand.hashCode() * 31 ^ contextPredicate.hashCode();
    }
}
