package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;

import static org.apache.commons.lang3.text.WordUtils.uncapitalize;


public abstract class BinaryScalarConditionWithFixedOperand implements Condition<Scalar> {
    private final Expression<Scalar> fixedOperand;

    protected BinaryScalarConditionWithFixedOperand(Expression<Scalar> fixedOperand) {
        this.fixedOperand = fixedOperand;
    }

    protected boolean applyTo(Scalar operand, Scalar fixedOperand) {
        return false;
    }

    @Override
    public boolean isSatisfiedOn(Scalar value, Context context, Direction direction) {
        return applyTo(value, fixedOperand.evaluateIn(context, direction));
    }

    @Override
    public String toStringWithUnits(String units) {
        return fixedOperand.toStringWithUnits(units);
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
        return fixedOperand.equals(other.fixedOperand);
    }

    @Override
    public int hashCode() {
        return fixedOperand.hashCode();
    }
}
