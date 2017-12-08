package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

import java.util.function.BiPredicate;

import static org.apache.commons.lang3.text.WordUtils.uncapitalize;


public class BinaryScalarConditionWithFixedOperand implements Condition<Scalar> {
    private final Expression<Scalar> fixedOperand;
    private final ContextBiFunction<Scalar, Scalar, Boolean> contextPredicate;
    private final String toStringFormat;


    public BinaryScalarConditionWithFixedOperand(Expression<Scalar> fixedOperand, BiPredicate<Scalar, Scalar> predicate, String toStringFormat) {
        this(fixedOperand, (left, right, context) -> predicate.test(left, right), toStringFormat);
    }

    public BinaryScalarConditionWithFixedOperand(Expression<Scalar> fixedOperand, ContextBiFunction<Scalar, Scalar, Boolean> contextPredicate, String toStringFormat) {
        this.fixedOperand = fixedOperand;
        this.contextPredicate = contextPredicate;
        this.toStringFormat = toStringFormat;
    }

    @Override
    public <V extends MetricSpace<V>> boolean isSatisfiedOn(Scalar value, Context context, ExtendGiving<V> direction) {
        return contextPredicate.apply(value, fixedOperand.evaluateIn(context, direction), context);
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        return String.format(toStringFormat, fixedOperand.getDescription(context, direction));
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", uncapitalize(getClass().getSimpleName()), fixedOperand); // todo: tolerance
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
