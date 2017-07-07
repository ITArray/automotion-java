package net.itarray.automotion.internal;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class Rectangle {
    private final Vector origin;
    private final Vector corner;

    public static Rectangle rectangle(WebElement webElement) {
        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();
        return new Rectangle(location.getX(), location.getY(), location.getX() + size.getWidth(), location.getY() + size.getHeight());
    }

    public Rectangle(int originX, int originY, int cornerX, int cornerY) {
        this.origin = new Vector(originX, originY);
        this.corner = new Vector(cornerX, cornerY);
    }

    public Vector getOrigin() {
        return origin;
    }

    public Vector getCorner() {
        return corner;
    }

    public Vector getExtend() {
        return corner.minus(origin);
    }

    public Scalar getBegin(Direction direction) {
        return direction.begin(this);
    }

    public Scalar getEnd(Direction direction) {
        return direction.end(this);
    }

    public Scalar getExtend(Direction direction) {
        return getEnd(direction).minus(getBegin(direction));
    }

    public boolean intersects(Direction direction, Rectangle other) {
        return getBegin(direction).isLessThan(other.getEnd(direction))
                && other.getBegin(direction).isLessThan(getEnd(direction));
    }

    public boolean intersects(Rectangle other) {
        return intersects(Direction.RIGHT, other) && intersects(Direction.DOWN, other);
    }

    public boolean contains(Direction direction, Rectangle other) {
        return getBegin(direction).isLessOrEqualThan(other.getBegin(direction))
                && other.getEnd(direction).isLessOrEqualThan(getEnd(direction));
    }

    public boolean contains(Rectangle other) {
        return contains(Direction.RIGHT, other) && contains(Direction.DOWN, other);
    }

}
