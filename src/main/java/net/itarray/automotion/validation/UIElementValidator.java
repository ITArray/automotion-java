package net.itarray.automotion.validation;

import org.openqa.selenium.WebElement;
import net.itarray.automotion.validation.properties.Padding;

import java.util.List;

public interface UIElementValidator {

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.ResponsiveUIValidator#drawMap()}
     */
    @Deprecated
    UIElementValidator drawMap();

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.ResponsiveUIValidator#dontDrawMap()}
     */
    @Deprecated
    UIElementValidator dontDrawMap();

    boolean validate();

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#changeMetricsUnitsTo(Units)}
     */
    @Deprecated
    UIElementValidator changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units);

    UIElementValidator changeMetricsUnitsTo(Units units);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isRightOf(org.openqa.selenium.WebElement)}
     */
    @Deprecated
    default UIElementValidator withLeftElement(WebElement element) { return isRightOf(element); }
    UIElementValidator isRightOf(WebElement element);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isRightOf(org.openqa.selenium.WebElement, int, int)}
     */
    @Deprecated
    default UIElementValidator withLeftElement(WebElement element, int minMargin, int maxMargin) {return isRightOf(element, minMargin, maxMargin); }
    UIElementValidator isRightOf(WebElement element, int minMargin, int maxMargin);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isLeftOf(org.openqa.selenium.WebElement)}
     */
    @Deprecated
    default UIElementValidator withRightElement(WebElement element) { return isLeftOf(element); }
    UIElementValidator isLeftOf(WebElement element);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isRightOf(org.openqa.selenium.WebElement, int, int)}
     */
    @Deprecated
    default UIElementValidator withRightElement(WebElement element, int minMargin, int maxMargin) { return isLeftOf(element, minMargin, maxMargin); }
    UIElementValidator isLeftOf(WebElement element, int minMargin, int maxMargin);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isBelow(org.openqa.selenium.WebElement)}
     */
    @Deprecated
    default UIElementValidator withTopElement(WebElement element) { return isBelow(element); }
    UIElementValidator isBelow(WebElement element);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isBelow(org.openqa.selenium.WebElement, int, int)}
     */
    @Deprecated
    default UIElementValidator withTopElement(WebElement element, int minMargin, int maxMargin) { return isBelow(element, minMargin, maxMargin); }
    UIElementValidator isBelow(WebElement element, int minMargin, int maxMargin);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isAbove(org.openqa.selenium.WebElement)}
     */
    @Deprecated
    default UIElementValidator withBottomElement(WebElement element) { return isAbove(element); }
    UIElementValidator isAbove(WebElement element);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isAbove(org.openqa.selenium.WebElement, int, int)}
     */
    @Deprecated
    default UIElementValidator withBottomElement(WebElement element, int minMargin, int maxMargin) { return isAbove(element, minMargin, maxMargin); }
    UIElementValidator isAbove(WebElement element, int minMargin, int maxMargin);

    // isNotOverlapping(element)
    UIElementValidator notOverlapWith(WebElement element, String readableName);

    UIElementValidator notOverlapWith(List<WebElement> elements);

    // isOverlapping(element)
    UIElementValidator overlapWith(WebElement element, String readableName);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isLeftAlignedWith(org.openqa.selenium.WebElement, String)}
     */
    @Deprecated
    default UIElementValidator sameOffsetLeftAs(WebElement element, String readableName) { return isLeftAlignedWith(element, readableName); }
    UIElementValidator isLeftAlignedWith(WebElement element, String readableName);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isLeftAlignedWith(java.util.List)}
     */
    @Deprecated
    default UIElementValidator sameOffsetLeftAs(List<WebElement> elements) { return isLeftAlignedWith(elements); }
    UIElementValidator isLeftAlignedWith(List<WebElement> elements);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isRightAlignedWith(org.openqa.selenium.WebElement, String)}
     */
    @Deprecated
    default UIElementValidator sameOffsetRightAs(WebElement element, String readableName) { return isRightAlignedWith(element, readableName); }
    UIElementValidator isRightAlignedWith(WebElement element, String readableName);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isRightAlignedWith(java.util.List)}
     */
    @Deprecated
    default UIElementValidator sameOffsetRightAs(List<WebElement> elements) { return isRightAlignedWith(elements); }
    UIElementValidator isRightAlignedWith(List<WebElement> elements);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isTopAlignedWith(org.openqa.selenium.WebElement, String)}
     */
    @Deprecated
    default UIElementValidator sameOffsetTopAs(WebElement element, String readableName) { return isTopAlignedWith(element, readableName); }
    UIElementValidator isTopAlignedWith(WebElement element, String readableName);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isTopAlignedWith(java.util.List)}
     */
    @Deprecated
    default UIElementValidator sameOffsetTopAs(List<WebElement> elements) { return isTopAlignedWith(elements); }
    UIElementValidator isTopAlignedWith(List<WebElement> elements);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isBottomAlignedWith(org.openqa.selenium.WebElement, String)}
     */
    @Deprecated
    default UIElementValidator sameOffsetBottomAs(WebElement element, String readableName) { return isBottomAlignedWith(element, readableName); }
    UIElementValidator isBottomAlignedWith(WebElement element, String readableName);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isBottomAlignedWith(java.util.List)}
     */
    @Deprecated
    default UIElementValidator sameOffsetBottomAs(List<WebElement> elements) { return isBottomAlignedWith(elements); }
    UIElementValidator isBottomAlignedWith(List<WebElement> elements);

    UIElementValidator sameWidthAs(WebElement element, String readableName);

    UIElementValidator sameWidthAs(List<WebElement> elements);

    UIElementValidator sameHeightAs(WebElement element, String readableName);

    UIElementValidator sameHeightAs(List<WebElement> elements);

    UIElementValidator sameSizeAs(WebElement element, String readableName);

    UIElementValidator sameSizeAs(List<WebElement> elements);

    UIElementValidator notSameSizeAs(WebElement element, String readableName);

    UIElementValidator notSameSizeAs(List<WebElement> elements);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#hasWidthGreaterOrEqualTo(int)}
     */
    @Deprecated
    default UIElementValidator minWidth(int width) { return hasWidthGreaterOrEqualTo(width); }
    UIElementValidator hasWidthGreaterOrEqualTo(int width);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#hasWidthLessOrEqualTo(int)}
     */
    @Deprecated
    default UIElementValidator maxWidth(int width) { return hasWidthLessOrEqualTo(width); }
    UIElementValidator hasWidthLessOrEqualTo(int width);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#hasWidthBetween(int, int)}
     */
    @Deprecated
    default UIElementValidator widthBetween(int min, int max) { return hasWidthBetween(min, max); }
    UIElementValidator hasWidthBetween(int min, int max);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#hasHeightGreaterOrEqualTo(int)}
     */
    @Deprecated
    default UIElementValidator minHeight(int height) { return hasHeightGreaterOrEqualTo(height); }
    UIElementValidator hasHeightGreaterOrEqualTo(int height);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#hasHeightLessOrEqualTo(int)}
     */
    @Deprecated
    default UIElementValidator maxHeight(int height) { return hasHeightLessOrEqualTo(height); }
    UIElementValidator hasHeightLessOrEqualTo(int height);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#hasHeightBetween(int, int)}
     */
    @Deprecated
    default UIElementValidator heightBetween(int min, int max) { return hasHeightBetween(min, max); }
    UIElementValidator hasHeightBetween(int min, int max);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#hasLeftOffsetToPageGreaterOrEqualTo(int)}
     *                                            {@link UIElementValidator#hasRightOffsetToPageGreaterOrEqualTo(int)}
     *                                            {@link UIElementValidator#hasTopOffsetToPageGreaterOrEqualTo(int)}
     *                                            {@link UIElementValidator#hasBottomOffsetToPageGreaterOrEqualTo(int)}
     */
    @Deprecated
    UIElementValidator minOffset(int top, int right, int bottom, int left);
    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#hasLeftOffsetToPageLessOrEqualTo(int)}
     *                                            {@link UIElementValidator#hasRightOffsetToPageLessOrEqualTo(int)}
     *                                            {@link UIElementValidator#hasTopOffsetToPageLessOrEqualTo(int)}
     *                                            {@link UIElementValidator#hasBottomOffsetToPageLessOrEqualTo(int)}
     */
    @Deprecated
    UIElementValidator maxOffset(int top, int right, int bottom, int left);

    UIElementValidator hasLeftOffsetToPageGreaterOrEqualTo(int value);
    UIElementValidator hasLeftOffsetToPageLessOrEqualTo(int value);
    UIElementValidator hasRightOffsetToPageGreaterOrEqualTo(int value);
    UIElementValidator hasRightOffsetToPageLessOrEqualTo(int value);
    UIElementValidator hasTopOffsetToPageGreaterOrEqualTo(int value);
    UIElementValidator hasTopOffsetToPageLessOrEqualTo(int value);
    UIElementValidator hasBottomOffsetToPageGreaterOrEqualTo(int value);
    UIElementValidator hasBottomOffsetToPageLessOrEqualTo(int value);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#hasCssValue(String, String...)}
     */
    @Deprecated
    default UIElementValidator withCssValue(String cssProperty, String... args) { return hasCssValue(cssProperty, args); }
    UIElementValidator hasCssValue(String cssProperty, String... args);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#doesNotHaveCssValue(String, String...)}
     */
    @Deprecated
    default UIElementValidator withoutCssValue(String cssProperty, String... args) { return doesNotHaveCssValue(cssProperty, args); }
    UIElementValidator doesNotHaveCssValue(String cssProperty, String... args);

    // isHorizontallyCenteredOnPage -- needs frontend dev feedback
    // isCenteredOnPageHorizontally ?
    UIElementValidator equalLeftRightOffset();

    // isVerticallyCenteredOnPage
    UIElementValidator equalTopBottomOffset();

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isInsideOf(org.openqa.selenium.WebElement, String)}
     */
    @Deprecated
    default UIElementValidator insideOf(WebElement containerElement, String readableContainerName) { return isInsideOf(containerElement, readableContainerName); }
    UIElementValidator isInsideOf(WebElement containerElement, String readableContainerName);

    /**
     * @deprecated As of release 2.0, replaced by {@link UIElementValidator#isInsideOf(org.openqa.selenium.WebElement, String, net.itarray.automotion.validation.properties.Padding)}
     */
    default UIElementValidator insideOf(WebElement containerElement, String readableContainerName, Padding padding) { return isInsideOf(containerElement, readableContainerName, padding); }
    UIElementValidator isInsideOf(WebElement containerElement, String readableContainerName, Padding padding);

}
