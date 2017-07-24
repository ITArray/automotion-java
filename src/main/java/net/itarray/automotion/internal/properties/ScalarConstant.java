package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Expression;

public class ScalarConstant implements Expression<Scalar> {

    private final Scalar value;

    public ScalarConstant(Scalar value) {
        this.value = value;
    }

    @Override
    public Scalar evaluateIn(Context context, Direction direction) {
        return value;
    }

    @Override
    public String toStringWithUnits(String units) {
        return value.toStringWithUnits(units);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ScalarConstant)) {
            return false;
        }
        ScalarConstant other = (ScalarConstant) object;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
