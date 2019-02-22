package net.itarray.automotion.internal.geometry;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import static net.itarray.automotion.internal.geometry.Direction.DOWN;
import static net.itarray.automotion.internal.geometry.Direction.RIGHT;

public class Rectangle {
    private final Vector origin;
    private final Vector corner;

    public static final ExtendGiving<Vector> ORIGIN_CORNER = new VectorExtendGiving(RIGHT, DOWN);

    public static Rectangle rectangle(WebElement webElement) {
        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();
        return new Rectangle(location.getX(), location.getY(), location.getX() + size.getWidth(), location.getY() + size.getHeight());
    }

    public Rectangle(int originX, int originY, int cornerX, int cornerY) {
        this(new Vector(originX, originY), new Vector(cornerX, cornerY));
    }

    public Rectangle(Scalar originX, Scalar originY, Scalar cornerX, Scalar cornerY) {
        this(new Vector(originX, originY), new Vector(cornerX, cornerY));
    }

    public Rectangle(Vector origin, Vector corner) {
        this.origin = origin;
        this.corner = corner;
    }

    public Vector getOrigin() {
        return origin;
    }

    public Vector getCorner() {
        return corner;
    }

    public boolean intersects(Direction direction, Rectangle other) {
        return direction.begin(this).isLessThan(direction.end(other))
                && direction.begin(other).isLessThan(direction.end(this));
    }

    public boolean intersects(Rectangle other) {
        return intersects(RIGHT, other) && intersects(DOWN, other);
    }

    public boolean contains(Direction direction, Rectangle other) {
        return direction.begin(this).isLessOrEqualTo(direction.begin(other))
                && direction.end(other).isLessOrEqualTo(direction.end(this));
    }

    public boolean contains(Rectangle other) {
        return contains(RIGHT, other) && contains(DOWN, other);
    }

    @Override
    public String toString() {
        Vector extend = corner.minus(origin);
        return String.format("[(%s,%s) - %sx%s]", origin.getX(), origin.getY(), extend.getX(), extend.getY());
    }

}
