package net.itarray.automotion.internal;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Collectors;

public class UIElement {
    private final WebElement webElement;
    private final String name;
    private final int originX;
    private final int originY;
    private final int cornerX;
    private final int cornerY;

    private UIElement(WebElement webElement, String name) {
        this.webElement = webElement;
        this.name = name;
        Point location = webElement.getLocation();
        this.originX = location.getX();
        this.originY = location.getY();
        Dimension size = webElement.getSize();
        this.cornerX = originX + size.getWidth();
        this.cornerY = originY + size.getHeight();
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

    public int getX() {
        return originX;
    }

    public int getY() {
        return originY;
    }

    public int getWidth() {
        return cornerX - originX;
    }

    public int getHeight() {
        return cornerY - originY;
    }

    public int getCornerX() {
        return cornerX;
    }

    public int getCornerY() {
        return cornerY;
    }

    public boolean hasEqualLeftOffsetAs(UIElement elementToCompare) {
        return getX() == elementToCompare.getX();
    }

    public boolean hasEqualRightOffsetAs(UIElement elementToCompare) {
        return getCornerX() == elementToCompare.getCornerX();
    }

    public boolean hasEqualTopOffsetAs(UIElement elementToCompare) {
        return getY() == elementToCompare.getY();
    }

    public boolean hasEqualBottomOffsetAs(UIElement elementToCompare) {
        return getCornerY() == elementToCompare.getCornerY();
    }

    public Rectangle2D.Double rectangle() {
        return new Rectangle2D.Double(
                getX(),
                getY(),
                getWidth(),
                getHeight());
    }

    public boolean hasMaxHeight(int height) {
        return getHeight() <= height;
    }


    public boolean hasMinHeight(int height) {
        return getHeight() >= height;
    }

    public boolean hasMaxWidth(int width) {
        return getWidth() <= width;
    }

    public boolean hasMinWidth(int width) {
        return getWidth() >= width;
    }

    public boolean hasSameWidthAs(UIElement elementToCompare) {
        return getWidth() == elementToCompare.getWidth();
    }

    public boolean hasSameHeightAs(UIElement elementToCompare) {
        return getHeight() == elementToCompare.getHeight();
    }

    public boolean hasSameSizeAs(UIElement elementToCompare) {
        return hasSameHeightAs(elementToCompare) && hasSameWidthAs(elementToCompare);
    }

    public boolean overlaps(UIElement elementToCompare) {
        return rectangle().intersects(elementToCompare.rectangle());
    }

    public int getTopOffset() {
        return getY();
    }

    public int getBottomOffset(Dimension pageSize) {
        return pageSize.getHeight() - getCornerY();
    }

    public int getLeftOffset() {
        return getX();
    }

    public int getRightOffset(Dimension pageSize) {
        return pageSize.getWidth() - getCornerX();
    }

    public boolean hasEqualTopBottomOffset(Dimension pageSize) {
        return getTopOffset() == getBottomOffset(pageSize);
    }

    public boolean hasEqualLeftRightOffset(Dimension pageSize) {
        return getLeftOffset() == getRightOffset(pageSize);
    }

    public  boolean hasRightElement(UIElement rightElement) {
        return getCornerX() <= rightElement.getX();
    }

    public  boolean hasLeftElement(UIElement leftElement) {
        return leftElement.hasRightElement(this);
    }

    public boolean hasBelowElement(UIElement bottomElement) {
        return getCornerY() <= bottomElement.getY();
    }

    public boolean hasAboveElement(UIElement aboveElement) {
        return aboveElement.hasBelowElement(this);
    }

    public String getTagName() {
        return webElement.getTagName();
    }

    public String getAttribute(String id) {
        return webElement.getAttribute(id);
    }

    public String getText() {
        return webElement.getText();
    }

    public String getCssValue(String cssProperty) {
        return webElement.getCssValue(cssProperty);
    }

    public boolean hasEqualWebElement(UIElement other) {
        return webElement.equals(other.webElement);
    }

    private String getFormattedMessage() {
        return String.format("with properties: tag=[%s], id=[%s], class=[%s], text=[%s], coord=[%s,%s], size=[%s,%s]",
                getTagName(),
                getAttribute("id"),
                getAttribute("class"),
                getShortendText(),
                String.valueOf(getX()),
                String.valueOf(getY()),
                String.valueOf(getWidth()),
                String.valueOf(getHeight()));
    }

    private String getShortendText() {
        int maxLength = 13;
        if (getText().length() <= maxLength) {
            return getText();
        }
        String postfix = "...";
        return getText().substring(0, maxLength-postfix.length()) + postfix;
    }

    public String getName() {
        return name != null ? name : getFormattedMessage();
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
}
