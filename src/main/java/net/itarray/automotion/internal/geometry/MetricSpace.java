package net.itarray.automotion.internal.geometry;

public interface MetricSpace<V> {
    V plus(V addend);
    V minus(V subtrahend);
    String toStringWithUnits(String units);
    Scalar norm();
}
