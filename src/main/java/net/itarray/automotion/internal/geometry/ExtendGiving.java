package net.itarray.automotion.internal.geometry;

import java.util.function.Function;

public interface ExtendGiving<V extends MetricSpace<V>> {
    String beginName();
    String endName();
    String extendName();

    V begin(Rectangle rectangle);
    V end(Rectangle rectangle);
    default V extend(Rectangle rectangle) {
        return transform().apply(rectangle.getCorner().minus(rectangle.getOrigin()));
    }
    default V signedDistance(V p1, V p2) { return p2.minus(p1); }

    Function<Vector, V> transform();
}
