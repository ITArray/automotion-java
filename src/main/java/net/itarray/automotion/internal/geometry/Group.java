package net.itarray.automotion.internal.geometry;

public interface Group<V> {
    V plus(V addend);
    V minus(V subtrahend);
}
