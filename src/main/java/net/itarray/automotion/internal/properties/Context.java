package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.UIElement;
import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.geometry.Vector;

public interface Context {
    Rectangle getPageRectangle();
    default boolean isPixels() { return false; }
    Scalar getTolerance();
    default void add(String message) {}
    default void draw(UIElement element) {}
    default void drawRoot(UIElement element) { draw(element);}
    default void drawHorizontalLine(Vector onLine) {}
    default void drawVerticalLine(Vector onLine) {}
    int errorCount();
}
