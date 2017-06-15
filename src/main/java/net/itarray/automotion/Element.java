package net.itarray.automotion;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Collectors;

public class Element {
    private final WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public static Element asElement(WebElement element) {
        return new Element(element);
    }

    public static List<Element> asElements(List<WebElement> webElements) {
        return webElements.stream().map(Element::asElement).collect(Collectors.toList());
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

    public boolean hasSameHeightAs(Element elementToCompare) {
        return getHeight() == elementToCompare.getHeight();
    }

    public boolean hasSameSizeAs(Element elementToCompare) {
        return hasSameHeightAs(elementToCompare) && hasSameWidthAs(elementToCompare);
    }

    public boolean overlaps(Element elementToCompare) {
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

    public  boolean hasRightElement(Element rightElement) {
        return getCornerX() <= rightElement.getX();
    }

    public  boolean hasLeftElement(Element leftElement) {
        return leftElement.hasRightElement(this);
    }

    public boolean hasBelowElement(Element bottomElement) {
        return getCornerY() <= bottomElement.getY();
    }

    public boolean hasAboveElement(Element aboveElement) {
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

    public boolean hasEqualWebElement(Element other) {
        return webElement.equals(other.webElement);
    }

    public String getFormattedMessage() {
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
}
