package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.internal.properties.Expression;
import net.itarray.automotion.internal.properties.ScalarConstant;
import net.itarray.automotion.validation.UIElementValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.Units;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.validation.properties.Padding;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.itarray.automotion.internal.UIElement.*;
import static net.itarray.automotion.internal.properties.Expression.percentOrPixels;
import static net.itarray.automotion.validation.Constants.*;
import static net.itarray.automotion.validation.properties.Condition.*;
import static net.itarray.automotion.validation.properties.PercentReference.PAGE;

public class UIValidatorBase extends ResponsiveUIValidatorBase implements UIElementValidator {

    private static final int MIN_OFFSET = -10000;

    private final OffsetLineCommands offsetLineCommands = new OffsetLineCommands();
    private final UIElement rootElement;


    public UIValidatorBase(UISnapshot snapshot, WebElement webElement, String readableNameOfElement) {
        super(snapshot);
        this.rootElement = asElement(webElement, readableNameOfElement);
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
        rootElement.validateIsRightOf(asElement(element), errors);
        return this;
    }

    /**
     * Verify that element which located left to is correct with specified margins
     *
     * @param element
     * @param minMargin
     * @param maxMargin
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isRightOf(WebElement element, int minMargin, int maxMargin) {
        rootElement.validateIsRightOf(asElement(element), betweenCondition(minMargin, maxMargin), getContext(), errors);
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
        rootElement.validateIsLeftOf(asElement(element), errors);
        return this;
    }

    /**
     * Verify that element which located right to is correct with specified margins
     *
     * @param element
     * @param minMargin
     * @param maxMargin
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isLeftOf(WebElement element, int minMargin, int maxMargin) {
        rootElement.validateIsLeftOf(asElement(element), betweenCondition(minMargin, maxMargin), getContext(), errors);
        return this;
    }

    private Expression<Scalar> scalarExpression(int width) {
        return isPixels() ? new ScalarConstant(new Scalar(width)) : Condition.percent(new Scalar(width), PAGE);
    }

    private Condition<Scalar> betweenCondition(int minMargin, int maxMargin) {
        return Condition.between(scalarExpression(minMargin)).and(scalarExpression(maxMargin));
    }

    private boolean isPixels() {
        return getUnits().equals(Units.PX);
    }

    private Context getContext() {
        return new Context() {
            @Override
            public Rectangle getPageRectangle() {
                return page.getRectangle();
            }

            @Override
            public boolean isPixels() {
                return UIValidatorBase.this.isPixels();
            }
        };
    }

    /**
     * Verify that element which located top to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isBelow(WebElement element) {
        rootElement.validateIsBelow(asElement(element), errors);
        return this;
    }

    /**
     * Verify that element which located top to is correct with specified margins
     *
     * @param element
     * @param minMargin
     * @param maxMargin
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isBelow(WebElement element, int minMargin, int maxMargin) {
        rootElement.validateIsBelow(asElement(element), betweenCondition(minMargin, maxMargin), getContext(), errors);
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
        rootElement.validateIsAbove(asElement(element), errors);
        return this;
    }

    /**
     * Verify that element which located bottom to is correct with specified margins
     *
     * @param element
     * @param minMargin
     * @param maxMargin
     * @return UIValidator
     */
    @Override
    public UIValidatorBase isAbove(WebElement element, int minMargin, int maxMargin) {
        rootElement.validateIsAbove(asElement(element), betweenCondition(minMargin, maxMargin), getContext(), errors);
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
    public UIValidatorBase notOverlapWith(WebElement element, String readableName) {
        rootElement.validateNotOverlappingWithElement(asElement(element, readableName), errors);
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
    public UIValidatorBase overlapWith(WebElement element, String readableName) {
        rootElement.validateOverlappingWithElement(asElement(element, readableName), errors);
        return this;
    }

    /**
     * Verify that element is NOT overlapped with every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase notOverlapWith(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateNotOverlappingWithElement(asElement(element), errors);
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
        rootElement.validateLeftAlignedWith(asElement(element, readableName), errors);
        drawLeftOffsetLine();
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
        for (UIElement element : asElements(webElements)) {
            rootElement.validateLeftAlignedWith(element, errors);
        }
        drawLeftOffsetLine();
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
        rootElement.validateRightAlignedWith(asElement(element, readableName), errors);
        drawRightOffsetLine();
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
        for (WebElement element : elements) {
            rootElement.validateRightAlignedWith(asElement(element), errors);
        }
        drawRightOffsetLine();
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
        rootElement.validateTopAlignedWith(asElement(element, readableName), errors);
        drawTopOffsetLine();
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
        for (WebElement element : elements) {
            rootElement.validateTopAlignedWith(asElement(element), errors);
        }
        drawTopOffsetLine();
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
        rootElement.validateBottomAlignedWith(asElement(element, readableName), errors);
        drawBottomOffsetLine();
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
        for (WebElement element : elements) {
            rootElement.validateBottomAlignedWith(asElement(element), errors);
        }
        drawBottomOffsetLine();
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
    public UIValidatorBase sameWidthAs(WebElement element, String readableName) {
        rootElement.validateSameWidth(asElement(element, readableName), errors);
        return this;
    }

    /**
     * Verify that element has the same width as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase sameWidthAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateSameWidth(asElement(element), errors);
        }
        return this;
    }

    public UIValidatorBase hasWidth(Condition<Scalar> condition) {
        rootElement.validateWidth(condition, getContext(), errors);
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
    public UIValidatorBase sameHeightAs(WebElement element, String readableName) {
        rootElement.validateSameHeight(asElement(element, readableName), errors);
        return this;
    }

    /**
     * Verify that element has the same height as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase sameHeightAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateSameHeight(asElement(element), errors);
        }
        return this;
    }

    public UIValidatorBase hasHeight(Condition<Scalar> condition) {
        rootElement.validateHeight(condition, getContext(), errors);
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
    public UIValidatorBase sameSizeAs(WebElement element, String readableName) {
        rootElement.validateSameSize(asElement(element, readableName), errors);
        return this;
    }

    /**
     * Verify that element has the same size as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidatorBase sameSizeAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateSameSize(asElement(element), errors);
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
    public UIValidatorBase notSameSizeAs(WebElement element, String readableName) {
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
    public UIValidatorBase notSameSizeAs(List<WebElement> elements) {
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
        if (toPixelsVertically(top) > MIN_OFFSET && toPixelsHorizontally(right) > MIN_OFFSET && toPixelsVertically(bottom) > MIN_OFFSET && toPixelsHorizontally(left) > MIN_OFFSET) {
            validateMinOffset(toPixelsVertically(top), toPixelsHorizontally(right), toPixelsVertically(bottom), toPixelsHorizontally(left));
        }
        return this;
    }

    public UIElementValidator hasLeftOffsetToPage(Condition<Scalar> condition) {
        rootElement.validateLeftOffset(condition, page, getContext(), errors);
        return this;
    }

    public UIElementValidator hasRightOffsetToPage(Condition<Scalar> condition) {
        rootElement.validateRightOffset(condition, page, getContext(), errors);
        return this;
    }

    public UIElementValidator hasTopOffsetToPage(Condition<Scalar> condition) {
        rootElement.validateTopOffset(condition, page, getContext(), errors);
        return this;
    }

    public UIElementValidator hasBottomOffsetToPage(Condition<Scalar> condition) {
        rootElement.validateBottomOffset(condition, page, getContext(), errors);
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
        if (toPixelsVertically(top) > MIN_OFFSET && toPixelsHorizontally(right) > MIN_OFFSET && toPixelsVertically(bottom) > MIN_OFFSET && toPixelsHorizontally(left) > MIN_OFFSET) {
            validateMaxOffset(toPixelsVertically(top), toPixelsHorizontally(right), toPixelsVertically(bottom), toPixelsHorizontally(left));
        }
        return this;
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
        rootElement.validateHasCssValue(cssProperty, args, errors);
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
        rootElement.validateDoesNotHaveCssValue(cssProperty, args, errors);
        return this;
    }

    /**
     * Verify that element has equal left and right offsets (e.g. Bootstrap container)
     *
     * @return UIValidator
     */
    @Override
    public UIValidatorBase equalLeftRightOffset() {
        rootElement.validateEqualLeftRightOffset(page, errors);
        return this;
    }

    /**
     * Verify that element has equal top and bottom offset (aligned vertically in center)
     *
     * @return UIValidator
     */
    @Override
    public UIValidatorBase equalTopBottomOffset() {
        rootElement.validateEqualTopBottomOffset(page, errors);
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
        rootElement.validateInsideOfContainer(asElement(containerElement, readableContainerName), errors);
        return this;
    }

    @Override
    public UIValidatorBase isInsideOf(WebElement containerElement, String readableContainerName, Padding padding) {
        int top = toPixelsVertically(padding.getTop());
        int right = toPixelsHorizontally(padding.getRight());
        int bottom = toPixelsVertically(padding.getBottom());
        int left = toPixelsHorizontally(padding.getLeft());

        rootElement.validateInsideOfContainer(asElement(containerElement, readableContainerName), top, right, bottom, left, errors);
        return this;
    }

    private void validateMaxOffset(int top, int right, int bottom, int left) {
        rootElement.validateLeftOffset(lessOrEqualTo(left), page, getContext(), errors);
        rootElement.validateTopOffset(lessOrEqualTo(top), page, getContext(), errors);
        rootElement.validateRightOffset(lessOrEqualTo(right), page, getContext(), errors);
        rootElement.validateBottomOffset(lessOrEqualTo(bottom), page, getContext(), errors);
    }

    private void validateMinOffset(int top, int right, int bottom, int left) {
        rootElement.validateLeftOffset(greaterOrEqualTo(left), page, getContext(), errors);
        rootElement.validateTopOffset(greaterOrEqualTo(top), page, getContext(), errors);
        rootElement.validateRightOffset(greaterOrEqualTo(right), page, getContext(), errors);
        rootElement.validateBottomOffset(greaterOrEqualTo(bottom), page, getContext(), errors);
    }

    private void validateNotSameSize(UIElement element) {
        rootElement.validateNotSameSize(element, errors);
    }

    @Override
    protected String getNameOfToBeValidated() {
        return rootElement.getName();
    }

    @Override
    protected void storeRootDetails(JSONObject rootDetails) {
        rootDetails.put(X, rootElement.getX());
        rootDetails.put(Y, rootElement.getY());
        rootDetails.put(WIDTH, rootElement.getWidth());
        rootDetails.put(HEIGHT, rootElement.getHeight());
    }

    @Override
    protected void drawRootElement(DrawableScreenshot screenshot) {
        screenshot.drawRootElement(rootElement);
    }

    @Override
    protected void drawOffsets(DrawableScreenshot screenshot) {
        screenshot.drawOffsets(rootElement, offsetLineCommands);
    }


    private void drawLeftOffsetLine() {
        offsetLineCommands.drawLeftOffsetLine();
    }

    private void drawRightOffsetLine() {
        offsetLineCommands.drawRightOffsetLine();
    }

    private void drawTopOffsetLine() {
        offsetLineCommands.drawTopOffsetLine();
    }

    private void drawBottomOffsetLine() {
        offsetLineCommands.drawBottomOffsetLine();
    }


}