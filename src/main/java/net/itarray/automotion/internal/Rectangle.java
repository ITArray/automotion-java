package net.itarray.automotion.internal;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class Rectangle {
    private final int originX;
    private final int originY;
    private final int cornerX;
    private final int cornerY;

    public static Rectangle rectangle(WebElement webElement) {
        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();
        return new Rectangle(location.getX(), location.getY(), location.getX() + size.getWidth(), location.getY() + size.getHeight());
    }

    public Rectangle(int originX, int originY, int cornerX, int cornerY) {
        this.originX = originX;
        this.originY = originY;
        this.cornerX = cornerX;
        this.cornerY = cornerY;
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public int getCornerX() {
        return cornerX;
    }

    public int getCornerY() {
        return cornerY;
    }

    public int getBegin(Direction direction) {
        return direction.begin(this);
    }

    public int getEnd(Direction direction) {
        return direction.oposite().begin(this);
    }

    public int getExtend(Direction direction) {
        return getEnd(direction) - getBegin(direction);
    }

    public boolean intersects(Direction direction, Rectangle other) {
        return getBegin(direction) < other.getEnd(direction) && other.getBegin(direction) < getEnd(direction);
    }

    public boolean intersects(Rectangle other) {
        return intersects(Direction.RIGHT, other) && intersects(Direction.DOWN, other);
    }

    public boolean contains(Direction direction, Rectangle other) {
        return getBegin(direction) <= other.getBegin(direction) && other.getEnd(direction) <= getEnd(direction);
    }

    public boolean contains(Rectangle other) {
        return contains(Direction.RIGHT, other) && contains(Direction.DOWN, other);
    }

}
