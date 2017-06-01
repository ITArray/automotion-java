package net.itarry.automotion;

import org.openqa.selenium.WebElement;

import java.awt.geom.Rectangle2D;

public class Element {
    private final WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public static Element asElement(WebElement element) {
        return new Element(element);
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public int getX() {
        return webElement.getLocation().getX();
    }

    public int getY() {
        return webElement.getLocation().getY();
    }

    public int getWidth() {
        return webElement.getSize().getWidth();
    }

    public int getHeight() {
        return webElement.getSize().getHeight();
    }

    public int getCornerX() {
        return getX() + getWidth();
    }

    public int getCornerY() {
        return getY() + getHeight();
    }

    public boolean hasEqualLeftOffsetAs(Element elementToCompare) {
        return getX() == elementToCompare.getX();
    }

    public boolean hasEqualRightOffsetAs(Element elementToCompare) {
        return getCornerX() == elementToCompare.getCornerX();
    }

    public boolean hasEqualTopOffsetAs(Element elementToCompare) {
        return getY() == elementToCompare.getY();
    }

    public boolean hasEqualBottomOffsetAs(Element elementToCompare) {
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

    public boolean hasSameWidthAs(Element elementToCompare) {
        return getWidth() == elementToCompare.getWidth();
    }

    public boolean hasSameHeightAs(Element element) {
        return getHeight() == element.getHeight();
    }

    public boolean hasSameSizeAs(Element element) {
        return hasSameHeightAs(element) && hasSameWidthAs(element);
    }
}
