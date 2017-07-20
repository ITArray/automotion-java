package net.itarray.automotion.validation.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.internal.properties.PagePercentage;
import net.itarray.automotion.internal.properties.PagePercentageOrPixels;

import static net.itarray.automotion.validation.properties.PercentReference.PAGE;

public interface Expression<T> {

    static PagePercentageOrPixels percentOrPixels(Scalar constant) {
        return new PagePercentageOrPixels(constant);
    }

    static PagePercentageOrPixels percentOrPixels(int constant) {
        return percentOrPixels(new Scalar(constant));
    }

    static Expression<Scalar> percent(int percentage, PercentReference reference) {
        return percent(new Scalar(percentage), reference);

    }

    static Expression<Scalar> percent(Scalar percentage, PercentReference reference) {
        if (PAGE.equals(reference)) {
            return new PagePercentage(percentage);
        } else {
            throw new RuntimeException("unsupported percentage reference " + reference);
        }
    }

    T evaluateIn(Context context, Direction direction);

    String toStringWithUnits(String units);
}
