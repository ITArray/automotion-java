package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

public class GreaterOrEqualTo extends BinaryScalarConditionWithFixedOperand {

    GreaterOrEqualTo(Expression<Scalar> lowerLimit) {
        super(lowerLimit);
    }

    @Override
    protected boolean applyTo(Scalar operand, Scalar fixedOperand) {
        return operand.isGreaterOrEqualTo(fixedOperand);
    }
    public String shortName() {
        return "min";
    }

}
