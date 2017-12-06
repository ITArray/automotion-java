package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;

public class TestContext implements Context {

    private final Rectangle pageRectangle;
    private Scalar tolerance;
    private boolean pixels = true;

    public TestContext() {
        this(new Rectangle(0, 0, 200, 150));
    }

    public TestContext(Rectangle pageRectangle) {
        this.pageRectangle = pageRectangle;
        tolerance = scalar(0);
    }

    @Override
    public Rectangle getPageRectangle() {
        return pageRectangle;
    }

    @Override
    public boolean isPixels() {
        return pixels;
    }

    public void setPixels(boolean pixels) {
        this.pixels = pixels;
    }

    @Override
    public Scalar getTolerance() {
        return tolerance;
    }

    public void setTolerance(Scalar tolerance) {
        this.tolerance = tolerance;
    }
}
