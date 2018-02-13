package net.itarray.automotion.internal.geometry;

import java.util.function.Function;

import static java.lang.String.format;

public class VectorExtendGiving implements ExtendGiving<Vector> {

    private final Direction x;
    private final Direction y;

    public VectorExtendGiving(Direction x, Direction y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String extendName() {
        return "size";
    }

    @Override
    public String beginName() {
        return format("%s %s", x.beginName(), y.beginName());
    }

    @Override
    public String endName() {
        return format("%s %s", x.endName(), y.endName());
    }

    public String beforeName() {
        return format("%s and %s", x.beforeName(), y.beforeName().toLowerCase());
    }
    public String afterName() {
        return format("%s or %s", x.afterName(), y.afterName().toLowerCase());
    }

    @Override
    public Vector begin(Rectangle rectangle) {
        return new Vector(x.begin(rectangle), y.begin(rectangle));
    }

    @Override
    public Vector end(Rectangle rectangle) {
        return new Vector(x.end(rectangle), y.end(rectangle));
    }

    @Override
    public Function<Vector, Vector> transform() {
        return v -> new Vector(x.transform().apply(v), y.transform().apply(v));
    }

    @Override
    public Vector signedDistance(Vector p1, Vector p2) {
        return new Vector(x.signedDistance(p1.getX(), p2.getX()), y.signedDistance(p1.getY(), p2.getY()));
    }
}
