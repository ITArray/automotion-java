package net.itarray.automotion.internal.geometry;

public interface ExtendGiving<V extends MetricSpace<V>> {
    String beginName();
    String endName();
    String extendName();

    V begin(Rectangle rectangle);
    V end(Rectangle rectangle);
    default V extend(Rectangle rectangle) {
        return end(rectangle).minus(begin(rectangle));
    }
}
