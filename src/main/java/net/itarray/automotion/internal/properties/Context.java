package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Rectangle;

public interface Context {
    Rectangle getPageRectangle();
    default boolean isPixels() { return false; }
}
