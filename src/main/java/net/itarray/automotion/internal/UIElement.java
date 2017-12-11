package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.geometry.Vector;
import net.itarray.automotion.internal.properties.ConstantExpression;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.tools.general.SystemHelper;
import net.itarray.automotion.tools.helpers.TextFinder;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.internal.properties.ElementPropertyExpression;
import net.itarray.automotion.validation.properties.Expression;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.itarray.automotion.internal.geometry.Direction.*;
import static net.itarray.automotion.internal.geometry.Rectangle.ORIGIN_CORNER;
import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static net.itarray.automotion.validation.properties.Expression.equalTo;
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
            numbered.add(new UIElement(String.format("#%d:%s", i+1, element.rectangle), element.rectangle, element.cssSource, false));
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

    public <V extends MetricSpace<V>> V  getBegin(ExtendGiving<V>  direction) {
        return direction.begin(rectangle);
    }

    public <V extends MetricSpace<V>> V  getEnd(ExtendGiving<V>  direction) {
        return direction.end(rectangle);
    }

    public <V extends MetricSpace<V>> V getExtend(ExtendGiving<V> direction) {
        return direction.extend(rectangle);
    }

    public Scalar getX() {
        return getOrigin().getX();
    }

    public Vector getOrigin() {
        return rectangle.getOrigin();
    }

    public Scalar getY() {
        return getOrigin().getY();
    }

    public Scalar getWidth() {
        return RIGHT.extend(rectangle);
    }

    public Scalar getHeight() {
        return DOWN.extend(rectangle);
    }

    public Vector getSize() {
        return ORIGIN_CORNER.extend(rectangle);
    }

    public Vector getCorner() {
        return rectangle.getCorner();
    }

    private <V extends MetricSpace<V>> boolean hasEqualExtendAs(UIElement other, ExtendGiving<V> direction, Context context) {
        return equalTo(
                extend(direction),
                other.extend(direction)).evaluateIn(context, direction);
    }

    public <V extends MetricSpace<V>> Expression<V> extend(ExtendGiving<V> direction) {
        return ElementPropertyExpression.extend(direction, this);
    }

    public boolean hasSameWidthAs(UIElement other, Context context) {
        return hasEqualExtendAs(other, RIGHT, context);
    }

    public boolean hasSameHeightAs(UIElement other, Context context) {
        return hasEqualExtendAs(other, DOWN, context);
    }

    public boolean hasSameSizeAs(UIElement other, Context context) {
        return hasEqualExtendAs(other, ORIGIN_CORNER, context);
    }

    public Scalar getLeft() {
        return getOrigin().getX();
    }

    public Scalar getRight() {
        return getCorner().getX();
    }

    public Scalar getTop() {
        return getOrigin().getY();
    }

    public Scalar getBottom() {
        return getCorner().getY();
    }

    public boolean overlaps(UIElement other, Context context) {
        return Condition.lessThan(other.getRight()).isSatisfiedOn(getLeft(), context, RIGHT)  &&
                Condition.lessThan(getRight()).isSatisfiedOn(other.getLeft(), context, RIGHT) &&
                Condition.lessThan(other.getBottom()).isSatisfiedOn(getTop(), context, DOWN) &&
                Condition.lessThan(getBottom()).isSatisfiedOn(other.getTop(), context, DOWN);
    }

    public boolean notOverlaps(UIElement other, Context context) {
        return Condition.greaterOrEqualTo(other.getRight()).isSatisfiedOn(getLeft(), context, RIGHT)  ||
                Condition.greaterOrEqualTo(getRight()).isSatisfiedOn(other.getLeft(), context, RIGHT) ||
                Condition.greaterOrEqualTo(other.getBottom()).isSatisfiedOn(getTop(), context, DOWN) ||
                Condition.greaterOrEqualTo(getBottom()).isSatisfiedOn(other.getTop(), context, DOWN);
    }

    private Scalar getOffset(Direction direction, UIElement page) {
        return direction.signedDistance(getEnd(direction), page.getEnd(direction));
    }

    private boolean hasEqualOppositeOffsets(Direction direction, UIElement page, Context context) {
        return equalTo(
                new ConstantExpression<>(getOffset(direction, page)),
                new ConstantExpression<>(getOffset(direction.opposite(), page))).evaluateIn(context, direction);
    }

    private boolean hasSuccessor(Direction direction, UIElement possibleSuccessor) {
        return signedDistanceToSuccessor(direction, possibleSuccessor).isGreaterOrEqualTo(scalar(0));
    }

    private Scalar signedDistanceToSuccessor(Direction direction, UIElement successor) {
        return direction.signedDistance(direction.end(rectangle), direction.begin(successor.rectangle));
    }

    // todo: used only in PageValidator - no tolerance yet
    public  boolean hasRightElement(UIElement rightElement) {
        return hasSuccessor(RIGHT, rightElement);
    }

    // todo: used only in PageValidator - no tolerance yet
    public boolean hasBelowElement(UIElement bottomElement) {
        return hasSuccessor(DOWN, bottomElement);
    }

    private String getCssValue(String cssProperty) {
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

    public boolean contains(UIElement other, Context context) {
        return
                Condition.lessOrEqualTo(other.getLeft()).isSatisfiedOn(getLeft(), context, RIGHT) &&
                Condition.lessOrEqualTo(getRight()).isSatisfiedOn(other.getRight(), context, RIGHT) &&
                Condition.lessOrEqualTo(other.getTop()).isSatisfiedOn(getTop(), context, DOWN) &&
                Condition.lessOrEqualTo(getBottom()).isSatisfiedOn(other.getBottom(), context, DOWN);
    }

    public void validateLeftAlignedWith(UIElement element, Context context) {
        validateEqualEnd(LEFT, element, context);
    }

    public void validateRightAlignedWith(UIElement element, Context context) {
        validateEqualEnd(RIGHT, element, context);
    }

    public void validateTopAlignedWith(UIElement element, Context context) {
        validateEqualEnd(UP, element, context);
    }

    public void validateBottomAlignedWith(UIElement element, Context context) {
        validateEqualEnd(DOWN, element, context);
    }

    private <V extends MetricSpace<V>> void validateEqualEnd(ExtendGiving<V> direction, UIElement element, Context context) {
        boolean valid = equalTo(
                end(direction),
                element.end(direction)
        ).evaluateIn(context, direction);
        if (!valid) {
            context.add(String.format("Element %s has not the same %s offset as element %s",
                                getQuotedName(),
                                direction.endName(),
                                element.getQuotedName()));
            context.draw(element);
        }
    }

    public <V extends MetricSpace<V>> Expression<V> end(ExtendGiving<V> direction) {
        return ElementPropertyExpression.end(direction, this);
    }

    public void validateSameSize(UIElement element, Context context) {
        validateSameExtend(ORIGIN_CORNER, element, context);
    }

    public void validateSameHeight(UIElement element, Context context) {
        validateSameExtend(DOWN, element, context);
    }

    public void validateSameWidth(UIElement element, Context context) {
        validateSameExtend(RIGHT, element, context);
    }

    public <V extends MetricSpace<V>> void validateSameExtend(ExtendGiving<V> direction, UIElement element, Context context) {
        if (!hasEqualExtendAs(element, direction, context)) {
            context.add(String.format("Element %s has not the same %s as element %s. %s of %s is %s. %s of element is %s",
                                getQuotedName(),
                                direction.extendName(),
                                element.getQuotedName(),
                                capitalize(direction.extendName()),
                                getQuotedName(),
                                direction.extend(rectangle).toStringWithUnits(PIXELS),
                                capitalize(direction.extendName()),
                                direction.extend(element.rectangle).toStringWithUnits(PIXELS)));
            context.draw(element);
        }
    }

    public <V extends MetricSpace<V>> void validateNotSameExtend(ExtendGiving<V> direction, UIElement element, Context context) {
        if (hasEqualExtendAs(element, direction, context)) {
            context.add(String.format("Element %s has the same %s as element %s. %s of %s is %s. %s of element is %s",
                                getQuotedName(),
                                direction.extendName(),
                                element.getQuotedName(),
                                capitalize(direction.extendName()),
                                getQuotedName(),
                                direction.extend(rectangle).toStringWithUnits(PIXELS),
                                capitalize(direction.extendName()),
                                direction.extend(element.rectangle).toStringWithUnits(PIXELS)));
            context.draw(element);
        }
    }

    public void validateNotSameSize(UIElement element, Context context) {
        validateNotSameExtend(ORIGIN_CORNER, element, context);
    }


    public void validateIsRightOf(UIElement element, Condition<Scalar> condition, Context context) {
        validateSuccessor(LEFT, element, condition, context);
    }

    public void validateIsLeftOf(UIElement element, Condition<Scalar> condition, Context context) {
        validateSuccessor(RIGHT, element, condition, context);
    }

    public void validateIsBelow(UIElement element, Condition<Scalar> condition, Context context) {
        validateSuccessor(UP, element, condition, context);
    }

    public void validateIsAbove(UIElement element, Condition<Scalar> condition, Context context) {
        validateSuccessor(DOWN, element, condition, context);
    }

    public void validateSuccessor(Direction direction, UIElement toBeValidatedSuccessor, Condition<Scalar> condition, Context context) {
        Scalar signedDistance = signedDistanceToSuccessor(direction, toBeValidatedSuccessor);
        if (!signedDistance.satisfies(condition, context, direction)) {
            context.add(String.format("%s element aligned not properly. Expected margin should be %s. Actual margin is %s",
                                direction.afterName(),
                                condition.getDescription(context, direction),
                                signedDistance.toStringWithUnits(PIXELS)));
            context.draw(toBeValidatedSuccessor);
        }
    }

    public void validateOverlappingWithElement(UIElement element, Context context) {
        if (!overlaps(element, context)) {
            context.add(String.format("Element %s is not overlapped with element %s but should be",
                                getQuotedName(),
                                element.getQuotedName()));
            context.draw(element);
        }
    }

    public void validateNotOverlappingWithElement(UIElement element, Context context) {
        if (!notOverlaps(element, context)) {
            context.add(String.format("Element %s is overlapped with element %s but should not",
                                getQuotedName(),
                                element.getQuotedName()));
            context.draw(element);
        }
    }

    public void validateLeftOffset(Condition condition, UIElement page, Context context) {
        validateOffset(LEFT, condition, page, context);
    }

    public void validateRightOffset(Condition condition, UIElement page, Context context) {
        validateOffset(RIGHT, condition, page, context);
    }

    public void validateTopOffset(Condition condition, UIElement page, Context context) {
        validateOffset(UP, condition, page, context);
    }

    public void validateBottomOffset(Condition condition, UIElement page, Context context) {
        validateOffset(DOWN, condition, page, context);
    }

    public void validateOffset(Direction direction, Condition condition, UIElement page, Context context) {
        if (!getOffset(direction, page).satisfies(condition, context, direction)) {
            context.add(
                    String.format("Expected %s offset of element %s to be %s. Actual %s offset is: %s",
                            direction.endName(),
                            getQuotedName(),
                            condition.getDescription(context, direction),
                            direction.endName(),
                            getOffset(direction, page).toStringWithUnits(PIXELS)));
        }
    }

    public void validateCenteredOnVertically(UIElement page, Context context) {
        validateCentered(RIGHT, page, context);
    }

    public void validateCenteredOnHorizontally(UIElement page, Context context) {
        validateCentered(DOWN, page, context);
    }

    private void validateCentered(Direction direction, UIElement page, Context context) {
        Direction opposite = direction.opposite();
        if (!hasEqualOppositeOffsets(direction, page, context)) {
            context.add(String.format("Element %s has not equal %s and %s offset. %s offset is %s, %s is %s",
                                getQuotedName(),
                                opposite.endName(),
                                direction.endName(),
                                capitalize(opposite.endName()),
                                getOffset(opposite, page).toStringWithUnits(PIXELS),
                                direction.endName(),
                                getOffset(direction, page).toStringWithUnits(PIXELS)));
            context.draw(this);
        }
    }

    public void validateHeight(Condition<Scalar> condition, Context context) {
        validateExtend(DOWN, condition, context);
    }

    public void validateWidth(Condition<Scalar> condition, Context context) {
        validateExtend(RIGHT, condition, context);
    }

    private void validateExtend(Direction direction, Condition<Scalar> condition, Context context) {
        Expression<Boolean> assertion = condition.applyTo(extend(direction));
        if (!assertion.evaluateIn(context, direction)) {
            context.add(
                    assertion.getDescription(context, direction));
        }
    }

    public void validateDoesNotHaveCssValue(String cssProperty, String[] args, Context context) {
        String cssValue = getCssValue(cssProperty);

        if (!cssValue.equals("")) {
            for (String val : args) {
                val = !val.startsWith("#") ? val : SystemHelper.hexStringToARGB(val);
                if (TextFinder.textIsFound(val, cssValue)) {
                    context.add(String.format("CSS property '%s' should not contain value '%s'. Actual value is '%s'", cssProperty, val, cssValue));
                }
            }
        } else {
            context.add(
                    String.format("Element %s does not have css property '%s'",
                            getQuotedName(),
                            cssProperty));
        }
    }

    public void validateHasCssValue(String cssProperty, String[] args, Context context) {
        String cssValue = getCssValue(cssProperty);

        if (!cssValue.equals("")) {
            for (String val : args) {
                val = !val.startsWith("#") ? val : SystemHelper.hexStringToARGB(val);
                if (!TextFinder.textIsFound(val, cssValue)) {
                    context.add(String.format("Expected value of '%s' is '%s'. Actual value is '%s'", cssProperty, val, cssValue));
                }
            }
        } else {
            context.add(
                    String.format("Element %s does not have css property '%s'",
                            getQuotedName(),
                            cssProperty));
        }
    }

    public void validateInsideOfContainer(UIElement containerElement, Context context) {
        if (!containerElement.contains(this, context)) {
            context.add(String.format("Element '%s' is not inside of '%s'",
                                getName(),
                                containerElement.getName()));
            context.draw(containerElement);
        }
    }

    public void validateInsideOfContainer(UIElement element, Context context, Scalar top, Scalar left, Scalar right, Scalar bottom) {
        Vector originPadding = new Vector(left, top);
        Vector cornerPadding = new Vector(right, bottom);

        UIElement paddedRoot = asElement(new Rectangle(
                getOrigin().minus(originPadding),
                getCorner().plus(cornerPadding)),
                "padded root");

        Vector originOffset = getOrigin().minus(element.getOrigin());
        Vector cornerOffset = getCorner().minus(element.getCorner());

        if (!element.contains(paddedRoot, context)) {
            context.add(String.format("Padding of element %s is incorrect. Expected padding: top[%s], right[%s], bottom[%s], left[%s]. Actual padding: top[%s], right[%s], bottom[%s], left[%s]",
                                getQuotedName(),
                                originPadding.getY(),
                                cornerPadding.getX(),
                                cornerPadding.getY(),
                                originPadding.getX(),
                                originOffset.getY(),
                                cornerOffset.getX(),
                                cornerOffset.getY(),
                                originOffset.getX()));
            context.draw(element);
        }
    }

    public Interval getYInterval() {
        return getInterval(Direction.DOWN);
    }

    public Interval getXInterval() {
        return getInterval(Direction.RIGHT);
    }

    private Interval getInterval(Direction direction) {
        return Interval.interval(getBegin(direction), getEnd(direction));
    }
}
