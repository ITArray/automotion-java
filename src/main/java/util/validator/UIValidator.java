package util.validator;

import net.itarray.automotion.internal.AbstractValidator;
import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.UIValidatorBase;
import net.itarray.automotion.validation.Scene;
import org.openqa.selenium.WebElement;
import net.itarray.automotion.validation.properties.Padding;

import java.util.List;

/**
 */
@Deprecated
public class UIValidator extends AbstractValidator implements Validator {

    public UIValidator(Scene scene, DriverFacade driver, WebElement webElement, String readableNameOfElement) {
        super(driver, new UIValidatorBase(scene, webElement, readableNameOfElement));
    }

    protected UIValidatorBase getBase() {
        return (UIValidatorBase) super.getBase();
    }

    @Override
    public UIValidator drawMap() {
        super.drawMap();
        return this;
    }

    /**
     * Change units to Pixels or % (Units.PX, Units.PERCENT)
     *
     * @param units
     * @return UIValidator
     */
    @Override
    public UIValidator changeMetricsUnitsTo(Units units) {
        getBase().changeMetricsUnitsTo(units);
        return this;
    }

    @Override
    public UIValidator changeMetricsUnitsTo(net.itarray.automotion.validation.Units units) {
        getBase().changeMetricsUnitsTo(units);
        return this;
    }

    /**
     * Verify that element which located left to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidator withLeftElement(WebElement element) {
        getBase().withLeftElement(element);
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
    public UIValidator withLeftElement(WebElement element, int minMargin, int maxMargin) {
        getBase().withLeftElement(element, minMargin, maxMargin);
        return this;
    }

    /**
     * Verify that element which located right to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidator withRightElement(WebElement element) {
        getBase().withRightElement(element);
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
    public UIValidator withRightElement(WebElement element, int minMargin, int maxMargin) {
        getBase().withRightElement(element, minMargin, maxMargin);
        return this;
    }

    /**
     * Verify that element which located top to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidator withTopElement(WebElement element) {
        getBase().withTopElement(element);
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
    public UIValidator withTopElement(WebElement element, int minMargin, int maxMargin) {
        getBase().withTopElement(element, minMargin, maxMargin);
        return this;
    }

    /**
     * Verify that element which located bottom to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidator withBottomElement(WebElement element) {
        getBase().withBottomElement(element);
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
    public UIValidator withBottomElement(WebElement element, int minMargin, int maxMargin) {
        getBase().withBottomElement(element, minMargin, maxMargin);
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
    public UIValidator notOverlapWith(WebElement element, String readableName) {
        getBase().notOverlapWith(element, readableName);
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
    public UIValidator overlapWith(WebElement element, String readableName) {
        getBase().overlapWith(element, readableName);
        return this;
    }

    /**
     * Verify that element is NOT overlapped with every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator notOverlapWith(List<WebElement> elements) {
        getBase().notOverlapWith(elements);
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
    public UIValidator sameOffsetLeftAs(WebElement element, String readableName) {
        getBase().sameOffsetLeftAs(element, readableName);
        return this;
    }

    /**
     * Verify that element has the same left offset as every element is the list
     *
     * @param webElements
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetLeftAs(List<WebElement> webElements) {
        getBase().sameOffsetLeftAs(webElements);
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
    public UIValidator sameOffsetRightAs(WebElement element, String readableName) {
        getBase().sameOffsetRightAs(element, readableName);
        return this;
    }

    /**
     * Verify that element has the same right offset as every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetRightAs(List<WebElement> elements) {
        getBase().sameOffsetRightAs(elements);
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
    public UIValidator sameOffsetTopAs(WebElement element, String readableName) {
        getBase().sameOffsetTopAs(element, readableName);
        return this;
    }

    /**
     * Verify that element has the same top offset as every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetTopAs(List<WebElement> elements) {
        getBase().sameOffsetTopAs(elements);
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
    public UIValidator sameOffsetBottomAs(WebElement element, String readableName) {
        getBase().sameOffsetBottomAs(element, readableName);
        return this;
    }

    /**
     * Verify that element has the same bottom offset as every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetBottomAs(List<WebElement> elements) {
        getBase().sameOffsetBottomAs(elements);
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
    public UIValidator sameWidthAs(WebElement element, String readableName) {
        getBase().sameWidthAs(element, readableName);
        return this;
    }

    /**
     * Verify that element has the same width as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameWidthAs(List<WebElement> elements) {
        getBase().sameWidthAs(elements);
        return this;
    }

    /**
     * Verify that width of element is not less than specified
     *
     * @param width
     * @return UIValidator
     */
    @Override
    public UIValidator minWidth(int width) {
        getBase().minWidth(width);
        return this;
    }

    /**
     * Verify that width of element is not bigger than specified
     *
     * @param width
     * @return UIValidator
     */
    @Override
    public UIValidator maxWidth(int width) {
        getBase().maxWidth(width);
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
    public UIValidator widthBetween(int min, int max) {
        getBase().widthBetween(min, max);
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
    public UIValidator sameHeightAs(WebElement element, String readableName) {
        getBase().sameHeightAs(element, readableName);
        return this;
    }

    /**
     * Verify that element has the same height as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameHeightAs(List<WebElement> elements) {
        getBase().sameHeightAs(elements);
        return this;
    }

    /**
     * Verify that height of element is not less than specified
     *
     * @param height
     * @return UIValidator
     */
    @Override
    public UIValidator minHeight(int height) {
        getBase().minHeight(height);
        return this;
    }

    /**
     * Verify that height of element is not bigger than specified
     *
     * @param height
     * @return UIValidator
     */
    @Override
    public UIValidator maxHeight(int height) {
        getBase().maxHeight(height);
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
    public UIValidator sameSizeAs(WebElement element, String readableName) {
        getBase().sameSizeAs(element, readableName);
        return this;
    }

    /**
     * Verify that element has the same size as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameSizeAs(List<WebElement> elements) {
        getBase().sameSizeAs(elements);
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
    public UIValidator notSameSizeAs(WebElement element, String readableName) {
        getBase().notSameSizeAs(element, readableName);
        return this;
    }

    /**
     * Verify that element has not the same size as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator notSameSizeAs(List<WebElement> elements) {
        getBase().notSameSizeAs(elements);
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
    public UIValidator heightBetween(int min, int max) {
        getBase().heightBetween(min, max);
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
    public UIValidator minOffset(int top, int right, int bottom, int left) {
        getBase().minOffset(top, right, bottom, left);
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
    public UIValidator maxOffset(int top, int right, int bottom, int left) {
        getBase().maxOffset(top, right, bottom, left);
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
    public UIValidator withCssValue(String cssProperty, String... args) {
        getBase().withCssValue(cssProperty, args);
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
    public UIValidator withoutCssValue(String cssProperty, String... args) {
        getBase().withoutCssValue(cssProperty, args);
        return this;
    }

    /**
     * Verify that element has equal left and right offsets (e.g. Bootstrap container)
     *
     * @return UIValidator
     */
    @Override
    public UIValidator equalLeftRightOffset() {
        getBase().equalLeftRightOffset();
        return this;
    }

    /**
     * Verify that element has equal top and bottom offset (aligned vertically in center)
     *
     * @return UIValidator
     */
    @Override
    public UIValidator equalTopBottomOffset() {
        getBase().equalTopBottomOffset();
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
    public UIValidator insideOf(WebElement containerElement, String readableContainerName) {
        getBase().insideOf(containerElement, readableContainerName);
        return this;
    }

    @Override
    public UIValidator insideOf(WebElement containerElement, String readableContainerName, Padding padding) {
        getBase().insideOf(containerElement, readableContainerName, padding);
        return this;
    }
}