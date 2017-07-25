package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;

import java.util.function.BiPredicate;

import static org.apache.commons.lang3.text.WordUtils.uncapitalize;


public class BinaryScalarConditionWithFixedOperand implements Condition<Scalar> {
    private final Expression<Scalar> fixedOperand;
    private final BiPredicate<Scalar, Scalar> predicate;
    private final String toStringFormat;


    public BinaryScalarConditionWithFixedOperand(Expression<Scalar> fixedOperand, BiPredicate<Scalar, Scalar> predicate, String toStringFormat) {
        this.fixedOperand = fixedOperand;
        this.predicate = predicate;
        this.toStringFormat = toStringFormat;
    }

    protected boolean applyTo(Scalar operand, Scalar fixedOperand) {
        return predicate.test(operand, fixedOperand);
    }

    @Override
    public boolean isSatisfiedOn(Scalar value, Context context, Direction direction) {
        return applyTo(value, fixedOperand.evaluateIn(context, direction));
    }

    @Override
    public String getDescription(Context context, Direction direction) {
        return String.format(toStringFormat, fixedOperand.getDescription(context, direction));
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", uncapitalize(getClass().getSimpleName()), fixedOperand);
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || !getClass().isAssignableFrom(object.getClass())) {
            return false;
        }
        BinaryScalarConditionWithFixedOperand other = (BinaryScalarConditionWithFixedOperand) object;
        return fixedOperand.equals(other.fixedOperand) && predicate.equals(other.predicate);
    }

    @Override
    public int hashCode() {
        return fixedOperand.hashCode() * 31 ^ predicate.hashCode();
    }
}
