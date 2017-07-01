package net.itarray.automotion.internal;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Collectors;

import static net.itarray.automotion.internal.Direction.*;

public class UIElement {
    private final WebElement webElement;
    private final String name;
    private final int originX;
    private final int originY;
    private final int cornerX;
    private final int cornerY;

    private UIElement(WebElement webElement, String name) {
        this.webElement = webElement;
        Point location = webElement.getLocation();
        this.originX = location.getX();
        this.originY = location.getY();
        Dimension size = webElement.getSize();
        this.cornerX = originX + size.getWidth();
        this.cornerY = originY + size.getHeight();
        if (name != null) {
            this.name = name;
        } else {
            this.name = String.format("with properties: tag=[%s], id=[%s], class=[%s], text=[%s], coord=[%s,%s], size=[%s,%s]",
                    webElement.getTagName(),
                    webElement.getAttribute("id"),
                    webElement.getAttribute("class"),
                    getShortenedText(webElement.getText()),
                    String.valueOf(location.getX()),
                    String.valueOf(location.getY()),
                    String.valueOf(size.getWidth()),
                    String.valueOf(size.getHeight()));
        }
    }

    public static UIElement asElement(WebElement element) {
        return asElement(element, null);
    }

    public static UIElement asElement(WebElement element, String name) {
        return new UIElement(element, name);
    }

    public static List<UIElement> asElements(List<WebElement> webElements) {
        return webElements.stream().map(UIElement::asElement).collect(Collectors.toList());
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

    public int getX() {
        return originX;
    }

    public int getY() {
        return originY;
    }

    public int getWidth() {
        return getExtend(RIGHT);
    }

    public int getHeight() {
        return getExtend(DOWN);
    }

    public int getCornerX() {
        return cornerX;
    }

    public int getCornerY() {
        return cornerY;
    }

    public boolean hasEqualBegin(Direction direction, UIElement other) {
        return getBegin(direction) == other.getBegin(direction);
    }

    public boolean hasEqualLeftOffsetAs(UIElement other) {
        return hasEqualBegin(RIGHT, other);
    }

    public boolean hasEqualRightOffsetAs(UIElement other) {
        return hasEqualBegin(LEFT, other);
    }

    public boolean hasEqualTopOffsetAs(UIElement other) {
        return hasEqualBegin(DOWN, other);
    }

    public boolean hasEqualBottomOffsetAs(UIElement other) {
        return hasEqualBegin(UP, other);
    }

    public Rectangle2D.Double rectangle() {
        return new Rectangle2D.Double(
                getX(),
                getY(),
                getWidth(),
                getHeight());
    }


    public boolean hasMaxExtend(Direction direction, int extend) {
        return getExtend(direction) <= extend;
    }

    public boolean hasMinExtend(Direction direction, int extend) {
        return extend <= getExtend(direction);
    }

    public boolean hasMaxHeight(int height) {
        return hasMaxExtend(DOWN, height);
    }

    public boolean hasMinHeight(int height) {
        return hasMinExtend(DOWN, height);
    }

    public boolean hasMaxWidth(int width) {
        return hasMaxExtend(RIGHT, width);
    }

    public boolean hasMinWidth(int width) {
        return hasMinExtend(RIGHT, width);
    }

    public boolean hasEqualExtendAs(Direction direction, UIElement other) {
        return getExtend(direction) == other.getExtend(direction);
    }

    public boolean hasSameWidthAs(UIElement other) {
        return hasEqualExtendAs(RIGHT, other);
    }

    public boolean hasSameHeightAs(UIElement other) {
        return hasEqualExtendAs(DOWN, other);
    }

    public boolean hasSameSizeAs(UIElement other) {
        return hasEqualExtendAs(DOWN, other) && hasEqualExtendAs(RIGHT, other);
    }

    public boolean overlaps(UIElement other) {
        return rectangle().intersects(other.rectangle());
    }

    public int getTopOffset() {
        return getBegin(DOWN);
    }

    public int getBottomOffset(Dimension pageSize) {
        return pageSize.getHeight() - getEnd(DOWN);
    }

    public int getLeftOffset() {
        return getBegin(RIGHT);
    }

    public int getRightOffset(Dimension pageSize) {
        return pageSize.getWidth() - getEnd(RIGHT);
    }

    public boolean hasEqualTopBottomOffset(Dimension pageSize) {
        return getTopOffset() == getBottomOffset(pageSize);
    }

    public boolean hasEqualLeftRightOffset(Dimension pageSize) {
        return getLeftOffset() == getRightOffset(pageSize);
    }

    public boolean hasSuccessor(Direction direction, UIElement possibleSuccessor) {
        return getEnd(direction) <= possibleSuccessor.getBegin(direction);
    }

    public  boolean hasRightElement(UIElement rightElement) {
        return hasSuccessor(RIGHT, rightElement);
    }

    public  boolean hasLeftElement(UIElement leftElement) {
        return leftElement.hasSuccessor(RIGHT, this);
    }

    public boolean hasBelowElement(UIElement bottomElement) {
        return hasSuccessor(DOWN, bottomElement);
    }

    public boolean hasAboveElement(UIElement aboveElement) {
        return aboveElement.hasSuccessor(DOWN, this);
    }

    public String getCssValue(String cssProperty) {
        return webElement.getCssValue(cssProperty);
    }

    public boolean hasEqualWebElement(UIElement other) {
        return webElement.equals(other.webElement);
    }

    public String getName() {
        return name;
    }

    private static String getShortenedText(String text) {
        int maxLength = 13;
        if (text.length() <= maxLength) {
            return text;
        }
        String postfix = "...";
        return text.substring(0, maxLength-postfix.length()) + postfix;
    }
}
