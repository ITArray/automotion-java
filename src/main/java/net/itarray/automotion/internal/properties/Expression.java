package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;

public interface Expression<T> {

    public static PagePercentageOrPixels percentOrPixels(Scalar constant) {
        return new PagePercentageOrPixels(constant);
    }

    public static PagePercentageOrPixels percentOrPixels(int constant) {
        return percentOrPixels(new Scalar(constant));
    }

    T evaluateIn(Context context, Direction direction);

    String toStringWithUnits(String units);
}
