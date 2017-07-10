package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.Group;
import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.itarray.automotion.internal.geometry.Direction.*;
import static net.itarray.automotion.internal.geometry.Rectangle.ORIGIN_CORNER;
import static org.apache.commons.lang3.text.WordUtils.capitalize;

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

    public Scalar getBegin(Direction direction) {
        return direction.begin(rectangle);
    }

    public Scalar getEnd(Direction direction) {
        return direction.end(rectangle);
    }

    public Scalar getExtend(Direction direction) {
        return direction.extend(rectangle);
    }

    public int getX() {
        return rectangle.getOrigin().getX().getValue();
    }

    public int getY() {
        return rectangle.getOrigin().getY().getValue();
    }

    public int getWidth() {
        return RIGHT.extend(rectangle).getValue();
    }

    public int getHeight() {
        return DOWN.extend(rectangle).getValue();
    }

    public int getCornerX() {
        return rectangle.getCorner().getX().getValue();
    }

    public int getCornerY() {
        return rectangle.getCorner().getY().getValue();
    }

    public boolean hasEqualBegin(Direction direction, UIElement other) {
        return getBegin(direction).equals(other.getBegin(direction));
    }

    public boolean hasEqualEnd(Direction direction, UIElement other) {
        return getEnd(direction).equals(other.getEnd(direction));
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
        return getExtend(direction).isLessOrEqualThan(extend);
    }

    public boolean hasMinExtend(Direction direction, int extend) {
        return new Scalar(extend).isLessOrEqualThan(getExtend(direction));
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

    public <V extends Group<V>> boolean hasEqualExtendAs(ExtendGiving<V> direction, UIElement other) {
        return direction.extend(rectangle).equals(direction.extend(other.rectangle));
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
        return getEnd(UP).minus(page.getEnd(UP)).getValue();
    }

    public int getBottomOffset(UIElement page) {
        return page.getEnd(DOWN).minus(getEnd(DOWN)).getValue();
    }

    private int getLeftOffset(UIElement page) {
        return getEnd(LEFT).minus(page.getEnd(LEFT)).getValue();
    }

    public int getRightOffset(UIElement page) {
        return page.getEnd(RIGHT).minus(getEnd(RIGHT)).getValue();
    }

    public boolean hasEqualTopBottomOffset(UIElement page) {
        return getTopOffset(page) == getBottomOffset(page);
    }

    public boolean hasEqualLeftRightOffset(UIElement page) {
        return getLeftOffset(page) == getRightOffset(page);
    }

    public boolean hasSuccessor(Direction direction, UIElement possibleSuccessor) {
        return signedDistanceToSuccessor(direction, possibleSuccessor).isGreaterOrEqualThan(0);
    }

    public Scalar signedDistanceToSuccessor(Direction direction, UIElement successor) {
        return direction.signedDistance(direction.end(rectangle), direction.begin(successor.rectangle));
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

    public void validateEqualLeft(UIElement element, Errors errors) {
        validateEqualEnd(LEFT, element, errors);
    }

    public void validateEqualRight(UIElement element, Errors errors) {
        validateEqualEnd(RIGHT, element, errors);
    }

    public void validateEqualTop(UIElement element, Errors errors) {
        validateEqualEnd(UP, element, errors);
    }

    public void validateEqualBottom(UIElement element, Errors errors) {
        validateEqualEnd(DOWN, element, errors);
    }

    public void validateEqualEnd(Direction direction, UIElement element, Errors errors) {
        if (!hasEqualEnd(direction, element)) {
            errors.add(
                    String.format("Element %s has not the same %s offset as element %s",
                            getQuotedName(),
                            direction.endName(),
                            element.getQuotedName()),
                    element);
        }
    }

    public void validateLeftElement(UIElement leftElement, Errors errors) {
        validateSuccessor(LEFT, leftElement, errors);
    }

    public void validateRightElement(UIElement rightElement, Errors errors) {
        validateSuccessor(RIGHT, rightElement, errors);
    }

    public void validateAboveElement(UIElement aboveElement, Errors errors) {
        validateSuccessor(UP, aboveElement, errors);
    }

    public void validateBelowElement(UIElement belowElement, Errors errors) {
        validateSuccessor(DOWN, belowElement, errors);
    }

    public void validateSuccessor(Direction direction, UIElement toBeValidatedSuccessor, Errors errors) {
        if (!hasSuccessor(direction, toBeValidatedSuccessor)) {
            errors.add(
                    String.format("%s element aligned not properly",
                            direction.afterName()),
                    toBeValidatedSuccessor);
        }
    }

    public void validateSameSize(UIElement element, Errors errors) {
        validateSameExtend(ORIGIN_CORNER, element, errors);
    }

    public void validateSameHeight(UIElement element, Errors errors) {
        validateSameExtend(DOWN, element, errors);
    }

    public void validateSameWidth(UIElement element, Errors errors) {
        validateSameExtend(RIGHT, element, errors);
    }

    public <V extends Group<V>> void validateSameExtend(ExtendGiving<V> direction, UIElement element, Errors errors) {
        if (!hasEqualExtendAs(direction, element)) {
            String units = "px";
            errors.add(
                    String.format("Element %s has not the same %s as element %s. %s of %s is %s. %s of element is %s",
                            getQuotedName(),
                            direction.extendName(),
                            element.getQuotedName(),
                            capitalize(direction.extendName()),
                            getQuotedName(),
                            direction.extend(rectangle).toStringWithUnits(units),
                            capitalize(direction.extendName()),
                            direction.extend(element.rectangle).toStringWithUnits(units)),
                    element);
        }
    }

    public <V extends Group<V>> void validateNotSameExtend(ExtendGiving<V> direction, UIElement element, Errors errors) {
        if (hasEqualExtendAs(direction, element)) {
            String units = "px";
            errors.add(
                    String.format("Element %s has the same %s as element %s. %s of %s is %s. %s of element is %s",
                            getQuotedName(),
                            direction.extendName(),
                            element.getQuotedName(),
                            capitalize(direction.extendName()),
                            getQuotedName(),
                            direction.extend(rectangle).toStringWithUnits(units),
                            capitalize(direction.extendName()),
                            direction.extend(element.rectangle).toStringWithUnits(units)),
                    element);
        }
    }

    public void validateNotSameSize(UIElement element, Errors errors) {
        validateNotSameExtend(ORIGIN_CORNER, element, errors);
    }

    public void validateBelowElement(UIElement element, int minMargin, int maxMargin, Errors errors) {
        validateSuccessor(DOWN, element, minMargin, maxMargin, errors);
    }

    public void validateAboveElement(UIElement element, int minMargin, int maxMargin, Errors errors) {
        validateSuccessor(UP, element, minMargin, maxMargin, errors);
    }

    public void validateRightElement(UIElement element, int minMargin, int maxMargin, Errors errors) {
        validateSuccessor(RIGHT, element, minMargin, maxMargin, errors);
    }

    public void validateLeftElement(UIElement element, int minMargin, int maxMargin, Errors errors) {
        validateSuccessor(LEFT, element, minMargin, maxMargin, errors);
    }

    public void validateSuccessor(Direction direction, UIElement toBeValidatedSuccessor, int minMargin, int maxMargin, Errors errors) {
        Scalar signedDistance = signedDistanceToSuccessor(direction, toBeValidatedSuccessor);
        if (signedDistance.isLessThan(minMargin) || signedDistance.isGreaterThan(maxMargin)) {
            errors.add(
                    String.format("%s element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %s",
                            direction.afterName(),
                            minMargin,
                            maxMargin,
                            signedDistance.toStringWithUnits("px")),
                    toBeValidatedSuccessor);
        }
    }
}
