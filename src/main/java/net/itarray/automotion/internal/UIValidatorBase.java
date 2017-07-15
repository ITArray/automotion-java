package net.itarray.automotion.internal;

import net.itarray.automotion.internal.properties.Maximum;
import net.itarray.automotion.internal.properties.Minimum;
import net.itarray.automotion.validation.UIElementValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.Units;
import net.itarray.automotion.validation.properties.Padding;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.itarray.automotion.internal.UIElement.*;
import static net.itarray.automotion.validation.Constants.*;

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
        rootElement.validateIsRightOf(asElement(element), getConvertedInt(minMargin, true), getConvertedInt(maxMargin, true), errors);
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
        rootElement.validateIsLeftOf(asElement(element), getConvertedInt(minMargin, true), getConvertedInt(maxMargin, true), errors);
        return this;
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
        rootElement.validateIsBelow(asElement(element), getConvertedInt(minMargin, false), getConvertedInt(maxMargin, false), errors);
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
        rootElement.validateIsAbove(asElement(element), getConvertedInt(minMargin, false), getConvertedInt(maxMargin, false), this.errors);
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
    public UIValidatorBase sameOffsetLeftAs(WebElement element, String readableName) {
        rootElement.validateEqualLeft(asElement(element, readableName), errors);
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
    public UIValidatorBase sameOffsetLeftAs(List<WebElement> webElements) {
        for (UIElement element : asElements(webElements)) {
            rootElement.validateEqualLeft(element, errors);
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
    public UIValidatorBase sameOffsetRightAs(WebElement element, String readableName) {
        rootElement.validateEqualRight(asElement(element, readableName), errors);
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
    public UIValidatorBase sameOffsetRightAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateEqualRight(asElement(element), errors);
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
    public UIValidatorBase sameOffsetTopAs(WebElement element, String readableName) {
        rootElement.validateEqualTop(asElement(element, readableName), errors);
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
    public UIValidatorBase sameOffsetTopAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateEqualTop(asElement(element), errors);
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
    public UIValidatorBase sameOffsetBottomAs(WebElement element, String readableName) {
        rootElement.validateEqualBottom(asElement(element, readableName), errors);
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
    public UIValidatorBase sameOffsetBottomAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            rootElement.validateEqualBottom(asElement(element), errors);
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

    /**
     * Verify that width of element is not less than specified
     *
     * @param width
     * @return UIValidator
     */
    @Override
    public UIValidatorBase minWidth(int width) {
        rootElement.validateMinWidth(getConvertedInt(width, true), errors);
        return this;
    }

    /**
     * Verify that width of element is not bigger than specified
     *
     * @param width
     * @return UIValidator
     */
    @Override
    public UIValidatorBase maxWidth(int width) {
        rootElement.validateMaxWidth(getConvertedInt(width, true), errors);
        return this;
    }

    /**
     * Verify that width of element is in range
     *
     * @param min
     * @param max
     * @return UIValidator
     */
    @Override
    public UIValidatorBase widthBetween(int min, int max) {
        rootElement.validateMinWidth(getConvertedInt(min, true), errors);
        rootElement.validateMaxWidth(getConvertedInt(max, true), errors);
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

    /**
     * Verify that height of element is not less than specified
     *
     * @param height
     * @return UIValidator
     */
    @Override
    public UIValidatorBase minHeight(int height) {
        rootElement.validateMinHeight(getConvertedInt(height, false), errors);
        return this;
    }

    /**
     * Verify that height of element is not bigger than specified
     *
     * @param height
     * @return UIValidator
     */
    @Override
    public UIValidatorBase maxHeight(int height) {
        rootElement.validateMaxHeight(getConvertedInt(height, false), errors);
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
     * Verify that height of element is in range
     *
     * @param min
     * @param max
     * @return UIValidator
     */
    @Override
    public UIValidatorBase heightBetween(int min, int max) {
        rootElement.validateMinHeight(getConvertedInt(min, false), errors);
        rootElement.validateMaxHeight(getConvertedInt(max, false), errors);
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
        if (getConvertedInt(top, false) > MIN_OFFSET && getConvertedInt(right, true) > MIN_OFFSET && getConvertedInt(bottom, false) > MIN_OFFSET && getConvertedInt(left, true) > MIN_OFFSET) {
            validateMinOffset(getConvertedInt(top, false), getConvertedInt(right, true), getConvertedInt(bottom, false), getConvertedInt(left, true));
        }
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
        if (getConvertedInt(top, false) > MIN_OFFSET && getConvertedInt(right, true) > MIN_OFFSET && getConvertedInt(bottom, false) > MIN_OFFSET && getConvertedInt(left, true) > MIN_OFFSET) {
            validateMaxOffset(getConvertedInt(top, false), getConvertedInt(right, true), getConvertedInt(bottom, false), getConvertedInt(left, true));
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
    public UIValidatorBase insideOf(WebElement containerElement, String readableContainerName) {
        rootElement.validateInsideOfContainer(asElement(containerElement, readableContainerName), errors);
        return this;
    }

    @Override
    public UIValidatorBase insideOf(WebElement containerElement, String readableContainerName, Padding padding) {
        rootElement.validateInsideOfContainer(asElement(containerElement, readableContainerName), padding, errors, this);
        return this;
    }

    private void validateMaxOffset(int top, int right, int bottom, int left) {
        rootElement.validateLeftOffset(new Maximum(left), page, errors);
        rootElement.validateTopOffset(new Maximum(top), page, errors);
        rootElement.validateRightOffset(new Maximum(right), page, errors);
        rootElement.validateBottomOffset(new Maximum(bottom), page, errors);
    }

    private void validateMinOffset(int top, int right, int bottom, int left) {
        rootElement.validateLeftOffset(new Minimum(left), page, errors);
        rootElement.validateTopOffset(new Minimum(top), page, errors);
        rootElement.validateRightOffset(new Minimum(right), page, errors);
        rootElement.validateBottomOffset(new Minimum(bottom), page, errors);
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