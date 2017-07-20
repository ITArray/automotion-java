package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Expression;

public class LessOrEqualTo extends BinaryScalarConditionWithFixedOperand {

    public LessOrEqualTo(Expression<Scalar> upperLimit) {
        super(upperLimit);
    }

    @Override
    protected boolean applyTo(Scalar operand, Scalar fixedOperand) {
        return operand.isLessOrEqualTo(fixedOperand);
    }

    public String shortName() {
        return "max";
    }
}
