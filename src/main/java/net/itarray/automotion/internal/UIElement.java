package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.Group;
import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.tools.general.SystemHelper;
import net.itarray.automotion.tools.helpers.TextFinder;
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
    private static final String PIXELS = "px";

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

    @Deprecated
    public Rectangle getRectangle() {
        return rectangle;
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

    public Scalar getOffset(Direction direction, UIElement page) {
        return direction.signedDistance(getEnd(direction), page.getEnd(direction));
    }

    public Scalar getTopOffset(UIElement page) {
        return getOffset(UP, page);
    }

    public Scalar getBottomOffset(UIElement page) {
        return getOffset(DOWN, page);
    }

    public Scalar getLeftOffset(UIElement page) {
        return getOffset(LEFT, page);
    }

    public Scalar getRightOffset(UIElement page) {
        return getOffset(RIGHT, page);
    }

    public boolean hasEqualOppositeOffsets(Direction direction, UIElement page) {
        return getOffset(direction, page).equals(getOffset(direction.opposite(), page));
    }

    public boolean hasSuccessor(Direction direction, UIElement possibleSuccessor) {
        return signedDistanceToSuccessor(direction, possibleSuccessor).isGreaterOrEqualTo(0);
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

    public void validateLeftAlignedWith(UIElement element, Errors errors) {
        validateEqualEnd(LEFT, element, errors);
    }

    public void validateRightAlignedWith(UIElement element, Errors errors) {
        validateEqualEnd(RIGHT, element, errors);
    }

    public void validateTopAlignedWith(UIElement element, Errors errors) {
        validateEqualEnd(UP, element, errors);
    }

    public void validateBottomAlignedWith(UIElement element, Errors errors) {
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
            errors.add(
                    String.format("Element %s has not the same %s as element %s. %s of %s is %s. %s of element is %s",
                            getQuotedName(),
                            direction.extendName(),
                            element.getQuotedName(),
                            capitalize(direction.extendName()),
                            getQuotedName(),
                            direction.extend(rectangle).toStringWithUnits(PIXELS),
                            capitalize(direction.extendName()),
                            direction.extend(element.rectangle).toStringWithUnits(PIXELS)),
                    element);
        }
    }

    public <V extends Group<V>> void validateNotSameExtend(ExtendGiving<V> direction, UIElement element, Errors errors) {
        if (hasEqualExtendAs(direction, element)) {
            errors.add(
                    String.format("Element %s has the same %s as element %s. %s of %s is %s. %s of element is %s",
                            getQuotedName(),
                            direction.extendName(),
                            element.getQuotedName(),
                            capitalize(direction.extendName()),
                            getQuotedName(),
                            direction.extend(rectangle).toStringWithUnits(PIXELS),
                            capitalize(direction.extendName()),
                            direction.extend(element.rectangle).toStringWithUnits(PIXELS)),
                    element);
        }
    }

    public void validateNotSameSize(UIElement element, Errors errors) {
        validateNotSameExtend(ORIGIN_CORNER, element, errors);
    }


    public void validateIsRightOf(UIElement leftElement, Errors errors) {
        validateSuccessor(LEFT, leftElement, errors);
    }

    public void validateIsRightOf(UIElement element, Condition<Scalar> condition, Context context, Errors errors) {
        validateSuccessor(LEFT, element, condition, context, errors);
    }

    public void validateIsLeftOf(UIElement rightElement, Errors errors) {
        validateSuccessor(RIGHT, rightElement, errors);
    }

    public void validateIsLeftOf(UIElement element, Condition<Scalar> condition, Context context, Errors errors) {
        validateSuccessor(RIGHT, element, condition, context, errors);
    }

    public void validateIsBelow(UIElement aboveElement, Errors errors) {
        validateSuccessor(UP, aboveElement, errors);
    }

    public void validateIsBelow(UIElement element, Condition<Scalar> condition, Context context, Errors errors) {
        validateSuccessor(UP, element, condition, context, errors);
    }

    public void validateIsAbove(UIElement belowElement, Errors errors) {
        validateSuccessor(DOWN, belowElement, errors);
    }

    public void validateIsAbove(UIElement element, Condition<Scalar> condition, Context context, Errors errors) {
        validateSuccessor(DOWN, element, condition, context, errors);
    }

    public void validateSuccessor(Direction direction, UIElement toBeValidatedSuccessor, Errors errors) {
        if (!hasSuccessor(direction, toBeValidatedSuccessor)) {
            errors.add(
                    String.format("%s element aligned not properly",
                            direction.afterName()),
                    toBeValidatedSuccessor);
        }
    }

    public void validateSuccessor(Direction direction, UIElement toBeValidatedSuccessor, Condition<Scalar> condition, Context context, Errors errors) {
        Scalar signedDistance = signedDistanceToSuccessor(direction, toBeValidatedSuccessor);
        if (!signedDistance.satisfies(condition, context, direction)) {
            errors.add(
                    String.format("%s element aligned not properly. Expected margin should be %s. Actual margin is %s",
                            direction.afterName(),
                            condition.toStringWithUnits(PIXELS),
                            signedDistance.toStringWithUnits(PIXELS)),
                    toBeValidatedSuccessor);
        }
    }

    public void validateOverlappingWithElement(UIElement element, Errors errors) {
        if (!overlaps(element)) {
            errors.add(
                    String.format("Element %s is not overlapped with element %s but should be",
                            getQuotedName(),
                            element.getQuotedName()),
                    element);
        }
    }

    public void validateNotOverlappingWithElement(UIElement element, Errors errors) {
        if (overlaps(element)) {
            errors.add(
                    String.format("Element %s is overlapped with element %s but should not",
                            getQuotedName(),
                            element.getQuotedName()),
                    element);
        }
    }

    public void validateLeftOffset(Condition condition, UIElement page, Context context, Errors errors) {
        validateOffset(LEFT, condition, page, context, errors);
    }

    public void validateRightOffset(Condition condition, UIElement page, Context context, Errors errors) {
        validateOffset(RIGHT, condition, page, context, errors);
    }

    public void validateTopOffset(Condition condition, UIElement page, Context context, Errors errors) {
        validateOffset(UP, condition, page, context, errors);
    }

    public void validateBottomOffset(Condition condition, UIElement page, Context context, Errors errors) {
        validateOffset(DOWN, condition, page, context, errors);
    }

    public void validateOffset(Direction direction, Condition condition, UIElement page, Context context, Errors errors) {
        if (!getOffset(direction, page).satisfies(condition, context, direction)) {
            errors.add(
                    String.format("Expected %s %s offset of element %s is: %s. Actual %s offset is: %s",
                            condition.shortName(),
                            direction.endName(),
                            getQuotedName(),
                            condition.toStringWithUnits(PIXELS),
                            direction.endName(),
                            getOffset(direction, page).toStringWithUnits(PIXELS)));
        }
    }

    public void validateEqualLeftRightOffset(UIElement page, Errors errors) {
        validateEqualOppositeOffsets(RIGHT, page, errors);
    }

    public void validateEqualTopBottomOffset(UIElement page, Errors errors) {
        validateEqualOppositeOffsets(DOWN, page, errors);
    }

    public void validateEqualOppositeOffsets(Direction direction, UIElement page, Errors errors) {
        Direction opposite = direction.opposite();
        if (!hasEqualOppositeOffsets(direction, page)) {
            errors.add(
                    String.format("Element %s has not equal %s and %s offset. %s offset is %s, %s is %s",
                            getQuotedName(),
                            opposite.endName(),
                            direction.endName(),
                            capitalize(opposite.endName()),
                            getOffset(opposite, page).toStringWithUnits(PIXELS),
                            direction.endName(),
                            getOffset(direction, page).toStringWithUnits(PIXELS)),
                    this);
        }
    }

    public void validateHeight(Condition<Scalar> condition, Context context, Errors errors) {
        validateExtend(DOWN, condition, context, errors);
    }

    public void validateWidth(Condition<Scalar> condition, Context context, Errors errors) {
        validateExtend(RIGHT, condition, context, errors);
    }

    public void validateExtend(Direction direction, Condition condition, Context context, Errors errors) {
        if (!getExtend(direction).satisfies(condition, context, direction)) {
            errors.add(
                    String.format("Expected %s %s of element %s is: %s. Actual %s is: %s",
                            condition.shortName(),
                            direction.extendName(),
                            getQuotedName(),
                            condition.toStringWithUnits(PIXELS),
                            direction.extendName(),
                            direction.extend(rectangle).toStringWithUnits(PIXELS)));
        }
    }

    public void validateDoesNotHaveCssValue(String cssProperty, String[] args, Errors errors) {
        String cssValue = getCssValue(cssProperty);

        if (!cssValue.equals("")) {
            for (String val : args) {
                val = !val.startsWith("#") ? val : SystemHelper.hexStringToARGB(val);
                if (TextFinder.textIsFound(val, cssValue)) {
                    errors.add(String.format("CSS property '%s' should not contain value '%s'. Actual value is '%s'", cssProperty, val, cssValue));
                }
            }
        } else {
            errors.add(
                    String.format("Element %s does not have css property '%s'",
                            getQuotedName(),
                            cssProperty));
        }
    }

    public void validateHasCssValue(String cssProperty, String[] args, Errors errors) {
        String cssValue = getCssValue(cssProperty);

        if (!cssValue.equals("")) {
            for (String val : args) {
                val = !val.startsWith("#") ? val : SystemHelper.hexStringToARGB(val);
                if (!TextFinder.textIsFound(val, cssValue)) {
                    errors.add(String.format("Expected value of '%s' is '%s'. Actual value is '%s'", cssProperty, val, cssValue));
                }
            }
        } else {
            errors.add(
                    String.format("Element %s does not have css property '%s'",
                            getQuotedName(),
                            cssProperty));
        }
    }

    public void validateInsideOfContainer(UIElement containerElement, Errors errors) {
        if (!containerElement.contains(this)) {
            errors.add(
                    String.format("Element '%s' is not inside of '%s'",
                            getName(),
                            containerElement.getName()),
                    containerElement);
        }
    }

    public void validateInsideOfContainer(UIElement element, int top, int right, int bottom, int left, Errors errors) {
        Rectangle paddedRoot = new Rectangle(
                getX() - left,
                getY() - top,
                getCornerX() + right,
                getCornerY() + bottom);

        int paddingTop = getY() - element.getY();
        int paddingLeft = getX() - element.getX();
        int paddingBottom = element.getCornerY() - getCornerY();
        int paddingRight = element.getCornerX() - getCornerX();

        if (!element.contains(paddedRoot)) {
            errors.add(
                    String.format("Padding of element %s is incorrect. Expected padding: top[%d], right[%d], bottom[%d], left[%d]. Actual padding: top[%d], right[%d], bottom[%d], left[%d]",
                            getQuotedName(),
                            top,
                            right,
                            bottom,
                            left,
                            paddingTop,
                            paddingRight,
                            paddingBottom,
                            paddingLeft),
                    element);
        }
    }
}
