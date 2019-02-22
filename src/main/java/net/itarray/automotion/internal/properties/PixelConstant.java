package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Expression;

public class PixelConstant implements Expression<Scalar> {

    private final Scalar value;

    public PixelConstant(Scalar value) {
        this.value = value;
    }

    @Override
    public <V extends MetricSpace<V>> Scalar evaluateIn(Context context, ExtendGiving<V> direction) {
        return value;
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        return value.toString() + "px";
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PixelConstant)) {
            return false;
        }
        PixelConstant other = (PixelConstant) object;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
