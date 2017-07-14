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

    // isRightOf(element)
    UIElementValidator withLeftElement(WebElement element);

    UIElementValidator withLeftElement(WebElement element, int minMargin, int maxMargin);

    // isLeftOf(element)
    UIElementValidator withRightElement(WebElement element);

    UIElementValidator withRightElement(WebElement element, int minMargin, int maxMargin);

    // isBelow(element)
    UIElementValidator withTopElement(WebElement element);

    UIElementValidator withTopElement(WebElement element, int minMargin, int maxMargin);

    // isAbove(element)
    UIElementValidator withBottomElement(WebElement element);

    UIElementValidator withBottomElement(WebElement element, int minMargin, int maxMargin);

    // isNotOverlapping(element)
    UIElementValidator notOverlapWith(WebElement element, String readableName);

    UIElementValidator notOverlapWith(List<WebElement> elements);

    // isOverlapping(element)
    UIElementValidator overlapWith(WebElement element, String readableName);

    // isVerticallyLeftAlignedWith(element)
    UIElementValidator sameOffsetLeftAs(WebElement element, String readableName);

    UIElementValidator sameOffsetLeftAs(List<WebElement> elements);

    // isVerticallyRightAlignedWith(element)
    UIElementValidator sameOffsetRightAs(WebElement element, String readableName);

    UIElementValidator sameOffsetRightAs(List<WebElement> elements);

    // isHorizontallyTopAlignedWith(element)
    UIElementValidator sameOffsetTopAs(WebElement element, String readableName);

    UIElementValidator sameOffsetTopAs(List<WebElement> elements);

    // isHorizontallyBottomAlignedWith(element)
    UIElementValidator sameOffsetBottomAs(WebElement element, String readableName);

    UIElementValidator sameOffsetBottomAs(List<WebElement> elements);

    // sameWidthAs
    UIElementValidator sameWidthAs(WebElement element, String readableName);

    UIElementValidator sameWidthAs(List<WebElement> elements);

    UIElementValidator sameHeightAs(WebElement element, String readableName);

    UIElementValidator sameHeightAs(List<WebElement> elements);

    UIElementValidator sameSizeAs(WebElement element, String readableName);

    UIElementValidator sameSizeAs(List<WebElement> elements);

    UIElementValidator notSameSizeAs(WebElement element, String readableName);

    UIElementValidator notSameSizeAs(List<WebElement> elements);

    // hasWidthGreaterOrEqualTo(width)
    UIElementValidator minWidth(int width);

    // hasWidthLessOrEqualTo(width)
    UIElementValidator maxWidth(int width);

    // hasWidthBetween(min, max)
    UIElementValidator widthBetween(int min, int max);

    // hasHeightGreaterOrEqualTo(width)
    UIElementValidator minHeight(int height);

    // hasHeightLessOrEqualTo(width)
    UIElementValidator maxHeight(int height);

    // hasHeightBetween(min, max)
    UIElementValidator heightBetween(int min, int max);

    // - methods per direction -> ok
    // page(document)/screen ? -> stay with page first
    // hasLeftOffsetToPageGreaterOrEqualTo(value)
    UIElementValidator minOffset(int top, int right, int bottom, int left);

    UIElementValidator maxOffset(int top, int right, int bottom, int left);

    // hasCssValue
    UIElementValidator withCssValue(String cssProperty, String... args);

    // doesNotHaveCssValue (really needed)
    UIElementValidator withoutCssValue(String cssProperty, String... args);

    // isHorizontallyCenteredOnPage -- needs frontend dev feedback
    UIElementValidator equalLeftRightOffset();

    // isVerticallyCenteredOnPage
    UIElementValidator equalTopBottomOffset();

    // isInsideOf
    UIElementValidator insideOf(WebElement containerElement, String readableContainerName);

    UIElementValidator insideOf(WebElement containerElement, String readableContainerName, Padding padding);

}
