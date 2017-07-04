package net.itarray.automotion.internal;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.itarray.automotion.internal.Direction.*;

public class UIElement {
    private final String name;
    private final boolean quoteName;
    private final Rectangle rectangle;
    private final CSSSource cssSource;

    private UIElement(String name, Rectangle rectangle, CSSSource cssSource, boolean quoteName) {
        this.name = name;
        this.quoteName = quoteName;
        this.rectangle = rectangle;
        this.cssSource = cssSource;
    }

    public static UIElement asElement(WebElement webElement) {
        return new UIElement(defaultName(webElement), Rectangle.rectangle(webElement), new SeleniumCSSSource(webElement), true);
    }

    public static UIElement asElement(WebElement webElement, String name) {
        return new UIElement(name, Rectangle.rectangle(webElement), new SeleniumCSSSource(webElement), true);
    }

    public static UIElement asElement(Rectangle rectangle, String name) {
        return new UIElement(name, rectangle, new NoCSSSource(), true);
    }

    public static List<UIElement> asElements(List<WebElement> webElements) {
        return webElements.stream().map(UIElement::asElement).collect(Collectors.toList());
    }

    public static List<UIElement> asNumberedList(List<UIElement> elements) {
        ArrayList<UIElement> numbered = new ArrayList<>(elements.size());
        for (int i = 0; i < elements.size(); i++) {
            UIElement element = elements.get(i);
            numbered.add(new UIElement(String.format("#%d", i+1), element.rectangle, element.cssSource, false));
        }
        return numbered;
    }

    private static String defaultName(WebElement webElement) {
        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();
        return String.format("with properties: tag=[%s], id=[%s], class=[%s], text=[%s], coord=[%s,%s], size=[%s,%s]",
                webElement.getTagName(),
                webElement.getAttribute("id"),
                webElement.getAttribute("class"),
                getShortenedText(webElement.getText()),
                String.valueOf(location.getX()),
                String.valueOf(location.getY()),
                String.valueOf(size.getWidth()),
                String.valueOf(size.getHeight()));
    }

    public int getBegin(Direction direction) {
        return rectangle.getBegin(direction);
    }

    public int getEnd(Direction direction) {
        return rectangle.getEnd(direction);
    }

    public int getExtend(Direction direction) {
        return rectangle.getExtend(direction);
    }

    public int getX() {
        return rectangle.getOriginX();
    }

    public int getY() {
        return rectangle.getOriginY();
    }

    public int getWidth() {
        return rectangle.getExtend(RIGHT);
    }

    public int getHeight() {
        return rectangle.getExtend(DOWN);
    }

    public int getCornerX() {
        return rectangle.getCornerX();
    }

    public int getCornerY() {
        return rectangle.getCornerY();
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
        return rectangle.intersects(other.rectangle);
    }

    private int getTopOffset(UIElement page) {
        return getEnd(UP) - page.getEnd(UP);
    }

    public int getBottomOffset(UIElement page) {
        return page.getEnd(DOWN) - getEnd(DOWN);
    }

    private int getLeftOffset(UIElement page) {
        return getEnd(LEFT) - page.getEnd(LEFT);
    }

    public int getRightOffset(UIElement page) {
        return page.getEnd(RIGHT) - getEnd(RIGHT);
    }

    public boolean hasEqualTopBottomOffset(UIElement page) {
        return getTopOffset(page) == getBottomOffset(page);
    }

    public boolean hasEqualLeftRightOffset(UIElement page) {
        return getLeftOffset(page) == getRightOffset(page);
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
        return cssSource.getCssValue(cssProperty);
    }

    public String getName() {
        return name;
    }

    public String getQuotedName() {
        return quoteName ? String.format("'%s'", name) : name;
    }

    private static String getShortenedText(String text) {
        int maxLength = 13;
        if (text.length() <= maxLength) {
            return text;
        }
        String postfix = "...";
        return text.substring(0, maxLength-postfix.length()) + postfix;
    }

    public boolean contains(UIElement other) {
        return rectangle.contains(other.rectangle);
    }

    public boolean contains(Rectangle other) {
        return rectangle.contains(other);
    }

    public void validateEqualLeftOffset(UIElement element, Errors errors) {
        validateEqualBeginOffset(RIGHT, element, errors);
    }

    public void validateEqualRightOffset(UIElement element, Errors errors) {
        validateEqualBeginOffset(LEFT, element, errors);
    }

    public void validateEqualTopOffset(UIElement element, Errors errors) {
        validateEqualBeginOffset(DOWN, element, errors);
    }

    public void validateEqualBottomOffset(UIElement element, Errors errors) {
        validateEqualBeginOffset(UP, element, errors);
    }

    public void validateEqualBeginOffset(Direction direction, UIElement element, Errors errors) {
        if (!hasEqualBegin(direction, element)) {
            errors.add(
                    String.format("Element %s has not the same %s offset as element %s",
                            getQuotedName(),
                            direction.beginName(),
                            element.getQuotedName()),
                    element);
        }
    }
}
