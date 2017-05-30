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

    public boolean equalLeftOffsetAs(Element elementToCompare) {
        return getX() == elementToCompare.getX();
    }

    public boolean equalRightOffsetAs(Element elementToCompare) {
        return getCornerX() == elementToCompare.getCornerX();
    }

    public boolean equalTopOffsetAs(Element elementToCompare) {
        return getY() == elementToCompare.getY();
    }

    public boolean equalBottomOffsetAs(Element elementToCompare) {
        return getCornerY() == elementToCompare.getCornerY();
    }

    public Rectangle2D.Double rectangle() {
        return new Rectangle2D.Double(
                getX(),
                getY(),
                getWidth(),
                getHeight());
    }


}
