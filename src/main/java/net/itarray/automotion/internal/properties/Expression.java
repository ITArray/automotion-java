package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;

public interface Expression<T> {

    T evaluateIn(Context context, Direction direction);

    String toStringWithUnits(String units);
}
