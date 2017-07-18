package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Scalar;

public class LessOrEqualTo extends BinaryScalarConditionWithFixedOperand {

    LessOrEqualTo(Scalar upperLimit) {
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
