package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.internal.properties.PixelConstant;
import net.itarray.automotion.validation.UIElementValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.Units;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Expression;
import net.itarray.automotion.validation.properties.Padding;
import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static net.itarray.automotion.validation.properties.Expression.percentOrPixels;
import static net.itarray.automotion.validation.Constants.*;
import static net.itarray.automotion.internal.UIElement.asElement;
import static net.itarray.automotion.internal.UIElement.asElements;
import static net.itarray.automotion.internal.properties.PercentReference.PAGE;
import static net.itarray.automotion.validation.properties.Condition.greaterOrEqualTo;
import static net.itarray.automotion.validation.properties.Condition.lessOrEqualTo;
import static net.itarray.automotion.validation.properties.Expression.percentOrPixels;

public class UIValidatorBase extends ResponsiveUIValidatorBase implements UIElementValidator {

    private static final int MIN_OFFSET = -10000;

    private final UIElement rootElement;


    public UIValidatorBase(UISnapshot snapshot, WebElement webElement, String readableNameOfElement) {
        super(snapshot);
        if (!getDriver().isAppiumContext()) {
            try {
                //((JavascriptExecutor) getDriver().getDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
                //((JavascriptExecutor) getDriver().getDriver()).executeScript("javascript:window.scrollBy(0,250);");
                ((JavascriptExecutor) getDriver().getDriver()).executeScript("document.documentElement.style.overflow = 'hidden'");
            } catch (Exception e) {}
        }
        this.rootElement = asElement(webElement, readableNameOfElement);
        doSnapshot();
    }

    @Override
    public UIValidatorBase drawMap() {
        super.drawMap();
        return this;
    }

    @Override
    public UIValidatorBase dontDrawMap() {
        super.dontDrawMap();
        return this;
    }

    /**
     * Change units to Pixels or % (Units.PX, Units.PERCENT)
     *
     * @param units
     * @return UIValidator
     */
    public UIValidatorBase changeMetricsUnitsTo(Units units) {
        getReport().changeMetricsUnitsTo(units);
        return this;
    }

    public UIValidatorBase changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units) {
        getReport().changeMetricsUnitsTo(units.asNewUnits());
        return this;
    }

    /**
     * Verify that element which located left to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isRightOf(WebElement element) {
        return isRightOf(element, greaterOrEqualTo(0));
    }

    public UIValidatorBase isRightOf(WebElement element, Condition<Scalar> distanceCondition) {
        rootElement.validateIsRightOf(asElement(element), distanceCondition, getContext());
        return this;
    }

    /**
     * Verify that element which located right to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isLeftOf(WebElement element) {
        return isLeftOf(element, greaterOrEqualTo(0));
    }

    public UIValidatorBase isLeftOf(WebElement element, Condition<Scalar> distanceCondition) {
        rootElement.validateIsLeftOf(asElement(element), distanceCondition, getContext());
        return this;
    }

    private Expression<Scalar> scalarExpression(int width) {
        return isPixels() ? new PixelConstant(scalar(width)) : Expression.percent(scalar(width), PAGE);
    }

    private Condition<Scalar> betweenCondition(int minMargin, int maxMargin) {
        return Condition.between(scalarExpression(minMargin)).and(scalarExpression(maxMargin));
    }

    /**
     * Verify that element which located top to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isBelow(WebElement element) {
        return isBelow(element, greaterOrEqualTo(0));
    }

    public UIValidatorBase isBelow(WebElement element, Condition<Scalar> distanceCondition) {
        rootElement.validateIsBelow(asElement(element), distanceCondition, getContext());
        return this;
    }

    /**
     * Verify that element which located bottom to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isAbove(WebElement element) {
        return isAbove(element, greaterOrEqualTo(0));
    }

    public UIValidatorBase isAbove(WebElement element, Condition<Scalar> distanceCondition) {
        rootElement.validateIsAbove(asElement(element), distanceCondition, getContext());
        return this;
    }

    /**
     * Verify that element is NOT overlapped with specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isNotOverlapping(WebElement element, String readableName) {
        rootElement.validateNotOverlappingWithElement(asElement(element, readableName), getContext());
        return this;
    }

    /**
     * Verify that element is overlapped with specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isOverlapping(WebElement element, String readableName) {
        rootElement.validateOverlappingWithElement(asElement(element, readableName), getContext());
        return this;
    }

    /**
     * Verify that element is NOT overlapped with every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isNotOverlapping(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateNotOverlappingWithElement(asElement(element), getContext());
        }
        return this;
    }

    /**
     * Verify that element has the same left offset as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isLeftAlignedWith(WebElement element, String readableName) {
        Context context = getContext();
        rootElement.validateLeftAlignedWith(asElement(element, readableName), context);
        context.drawVerticalLine(rootElement.getOrigin());
        return this;
    }

    /**
     * Verify that element has the same left offset as every element is the list
     *
     * @param webElements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isLeftAlignedWith(List<WebElement> webElements) {
        Context context = getContext();
        for (UIElement element : asElements(webElements)) {
            rootElement.validateLeftAlignedWith(element, context);
        }
        context.drawVerticalLine(rootElement.getOrigin());
        return this;
    }

    /**
     * Verify that element has the same right offset as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isRightAlignedWith(WebElement element, String readableName) {
        Context context = getContext();
        rootElement.validateRightAlignedWith(asElement(element, readableName), context);
        context.drawVerticalLine(rootElement.getCorner());
        return this;
    }

    /**
     * Verify that element has the same right offset as every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isRightAlignedWith(List<WebElement> elements) {
        Context context = getContext();
        for (WebElement element : elements) {
            rootElement.validateRightAlignedWith(asElement(element), context);
        }
        context.drawVerticalLine(rootElement.getCorner());
        return this;
    }

    /**
     * Verify that element has the same top offset as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isTopAlignedWith(WebElement element, String readableName) {
        Context context = getContext();
        rootElement.validateTopAlignedWith(asElement(element, readableName), context);
        context.drawHorizontalLine(rootElement.getOrigin());
        return this;
    }

    /**
     * Verify that element has the same top offset as every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isTopAlignedWith(List<WebElement> elements) {
        Context context = getContext();
        for (WebElement element : elements) {
            rootElement.validateTopAlignedWith(asElement(element), context);
        }
        context.drawHorizontalLine(rootElement.getOrigin());
        return this;
    }

    /**
     * Verify that element has the same bottom offset as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isBottomAlignedWith(WebElement element, String readableName) {
        Context context = getContext();
        rootElement.validateBottomAlignedWith(asElement(element, readableName), context);
        context.drawHorizontalLine(rootElement.getCorner());
        return this;
    }

    /**
     * Verify that element has the same bottom offset as every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isBottomAlignedWith(List<WebElement> elements) {
        Context context = getContext();
        for (WebElement element : elements) {
            rootElement.validateBottomAlignedWith(asElement(element), context);
        }
        context.drawHorizontalLine(rootElement.getCorner());
        return this;
    }

    /**
     * Verify that element has the same width as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase hasEqualWidthAs(WebElement element, String readableName) {
        rootElement.validateSameWidth(asElement(element, readableName), getContext());
        return this;
    }

    /**
     * Verify that element has the same width as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase hasEqualWidthAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateSameWidth(asElement(element), getContext());
        }
        return this;
    }

    public UIValidatorBase hasWidth(Condition<Scalar> condition) {
        rootElement.validateWidth(condition, getContext());
        return this;
    }

    /**
     * Verify that element has the same height as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase hasEqualHeightAs(WebElement element, String readableName) {
        rootElement.validateSameHeight(asElement(element, readableName), getContext());
        return this;
    }

    /**
     * Verify that element has the same height as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase hasEqualHeightAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateSameHeight(asElement(element), getContext());
        }
        return this;
    }

    public UIValidatorBase hasHeight(Condition<Scalar> condition) {
        rootElement.validateHeight(condition, getContext());
        return this;
    }

    /**
     * Verify that element has the same size as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase hasEqualSizeAs(WebElement element, String readableName) {
        rootElement.validateSameSize(asElement(element, readableName), getContext());
        return this;
    }

    /**
     * Verify that element has the same size as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase hasEqualSizeAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateSameSize(asElement(element), getContext());
        }
        return this;
    }

    /**
     * Verify that element has not the same size as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidatorBase hasDifferentSizeAs(WebElement element, String readableName) {
        validateNotSameSize(asElement(element, readableName));
        return this;
    }

    /**
     * Verify that element has not the same size as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase hasDifferentSizeAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateNotSameSize(asElement(element));
        }
        return this;
    }

    /**
     * Verify that min offset of element is not less than (min value is -10000)
     *
     * @param top
     * @param right
     * @param bottom
     * @param left
     * @return UIValidator
     */
    @Override
    public UIValidatorBase minOffset(int top, int right, int bottom, int left) {
        if (isNotSwitchedOff(top, right, bottom, left)) {
            validateMinOffsetNew(top, right, bottom, left);
        }
        return this;
    }

    public boolean isNotSwitchedOff(int top, int right, int bottom, int left) {
        return isNotSwitchedOff(top, Direction.UP) &&
                isNotSwitchedOff(right, Direction.RIGHT) &&
                isNotSwitchedOff(bottom, Direction.DOWN) &&
                isNotSwitchedOff(left, Direction.LEFT);
    }

    public boolean isNotSwitchedOff(int value, Direction direction) {
        return percentOrPixels(value).evaluateIn(getContext(), direction).isGreaterThan(scalar(MIN_OFFSET));
    }

    public void validateMinOffsetNew(int top, int right, int bottom, int left) {
        rootElement.validateLeftOffset(greaterOrEqualTo(percentOrPixels(left)), page, getContext());
        rootElement.validateTopOffset(greaterOrEqualTo(percentOrPixels(top)), page, getContext());
        rootElement.validateRightOffset(greaterOrEqualTo(percentOrPixels(right)), page, getContext());
        rootElement.validateBottomOffset(greaterOrEqualTo(percentOrPixels(bottom)), page, getContext());
    }

    public UIElementValidator hasLeftOffsetToPage(Condition<Scalar> condition) {
        rootElement.validateLeftOffset(condition, page, getContext());
        return this;
    }

    public UIElementValidator hasRightOffsetToPage(Condition<Scalar> condition) {
        rootElement.validateRightOffset(condition, page, getContext());
        return this;
    }

    public UIElementValidator hasTopOffsetToPage(Condition<Scalar> condition) {
        rootElement.validateTopOffset(condition, page, getContext());
        return this;
    }

    public UIElementValidator hasBottomOffsetToPage(Condition<Scalar> condition) {
        rootElement.validateBottomOffset(condition, page, getContext());
        return this;
    }

    /**
     * Verify that max offset of element is not bigger than (min value is -10000)
     *
     * @param top
     * @param right
     * @param bottom
     * @param left
     * @return UIValidator
     */
    @Override
    public UIValidatorBase maxOffset(int top, int right, int bottom, int left) {
        if (isNotSwitchedOff(top, right, bottom, left)) {
            validateMaxOffsetNew(top, right, bottom, left);
        }
        return this;
    }

    public void validateMaxOffsetNew(int top, int right, int bottom, int left) {
        rootElement.validateLeftOffset(lessOrEqualTo(percentOrPixels(left)), page, getContext());
        rootElement.validateTopOffset(lessOrEqualTo(percentOrPixels(top)), page, getContext());
        rootElement.validateRightOffset(lessOrEqualTo(percentOrPixels(right)), page, getContext());
        rootElement.validateBottomOffset(lessOrEqualTo(percentOrPixels(bottom)), page, getContext());
    }

    /**
     * Verify that element has correct CSS values
     *
     * @param cssProperty
     * @param args
     * @return UIValidator
     */
    @Override
    public UIValidatorBase hasCssValue(String cssProperty, String... args) {
        rootElement.validateHasCssValue(cssProperty, args, getContext());
        return this;
    }

    /**
     * Verify that concrete CSS values are absent for specified element
     *
     * @param cssProperty
     * @param args
     * @return UIValidator
     */
    @Override
    public UIValidatorBase doesNotHaveCssValue(String cssProperty, String... args) {
        rootElement.validateDoesNotHaveCssValue(cssProperty, args, getContext());
        return this;
    }

    /**
     * Verify that element has equal left and right offsets (e.g. Bootstrap container)
     *
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isCenteredOnPageHorizontally() {
        rootElement.validateCenteredOnVertically(page, getContext());
        return this;
    }

    /**
     * Verify that element has equal top and bottom offset (aligned vertically in center)
     *
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isCenteredOnPageVertically() {
        rootElement.validateCenteredOnHorizontally(page, getContext());
        return this;
    }

    /**
     * Verify that element(s) is(are) located inside of specified element
     *
     * @param containerElement
     * @param readableContainerName
     * @return ResponsiveUIValidator
     */
    @Override
    public UIValidatorBase isInsideOf(WebElement containerElement, String readableContainerName) {
        rootElement.validateInsideOfContainer(asElement(containerElement, readableContainerName), getContext());
        return this;
    }

    @Override
    public UIValidatorBase isInsideOf(WebElement containerElement, String readableContainerName, Padding padding) {
        Scalar top = percentOrPixels(padding.getTop()).evaluateIn(getContext(), Direction.UP);
        Scalar left = percentOrPixels(padding.getLeft()).evaluateIn(getContext(), Direction.LEFT);
        Scalar right = percentOrPixels(padding.getRight()).evaluateIn(getContext(), Direction.RIGHT);
        Scalar bottom = percentOrPixels(padding.getBottom()).evaluateIn(getContext(), Direction.DOWN);

        rootElement.validateInsideOfContainer(asElement(containerElement, readableContainerName), getContext(), top, left, right, bottom);
        return this;
    }

    private void validateNotSameSize(UIElement element) {
        rootElement.validateNotSameSize(element, getContext());
    }

    @Override
    protected String getNameOfToBeValidated() {
        return rootElement.getName();
    }

    @Override
    protected void storeRootDetails(JSONObject rootDetails) {
        rootDetails.put(X, rootElement.getX().intValue());
        rootDetails.put(Y, rootElement.getY().intValue());
        rootDetails.put(WIDTH, rootElement.getWidth().intValue());
        rootDetails.put(HEIGHT, rootElement.getHeight().intValue());
    }

    @Override
    protected void drawRootElement() {
        getContext().drawRoot(rootElement);
    }

}