package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.properties.Context;

public class TestContext implements Context {

    private final Rectangle pageRectangle;

    public TestContext() {
        this(new Rectangle(0, 0, 200, 150));
    }

    public TestContext(Rectangle pageRectangle) {
        this.pageRectangle = pageRectangle;
    }

    @Override
    public Rectangle getPageRectangle() {
        return pageRectangle;
    }
}
