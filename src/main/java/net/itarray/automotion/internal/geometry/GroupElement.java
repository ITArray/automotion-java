package net.itarray.automotion.internal.geometry;

public interface GroupElement<V> {
    V plus(V addend);
    V minus(V subtrahend);
    String toStringWithUnits(String units);
}
