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

    UIElementValidator withLeftElement(WebElement element);

    UIElementValidator withLeftElement(WebElement element, int minMargin, int maxMargin);

    UIElementValidator withRightElement(WebElement element);

    UIElementValidator withRightElement(WebElement element, int minMargin, int maxMargin);

    UIElementValidator withTopElement(WebElement element);

    UIElementValidator withTopElement(WebElement element, int minMargin, int maxMargin);

    UIElementValidator withBottomElement(WebElement element);

    UIElementValidator withBottomElement(WebElement element, int minMargin, int maxMargin);

    UIElementValidator notOverlapWith(WebElement element, String readableName);

    UIElementValidator overlapWith(WebElement element, String readableName);

    UIElementValidator notOverlapWith(List<WebElement> elements);

    UIElementValidator sameOffsetLeftAs(WebElement element, String readableName);

    UIElementValidator sameOffsetLeftAs(List<WebElement> elements);

    UIElementValidator sameOffsetRightAs(WebElement element, String readableName);

    UIElementValidator sameOffsetRightAs(List<WebElement> elements);

    UIElementValidator sameOffsetTopAs(WebElement element, String readableName);

    UIElementValidator sameOffsetTopAs(List<WebElement> elements);

    UIElementValidator sameOffsetBottomAs(WebElement element, String readableName);

    UIElementValidator sameOffsetBottomAs(List<WebElement> elements);

    UIElementValidator sameWidthAs(WebElement element, String readableName);

    UIElementValidator sameWidthAs(List<WebElement> elements);

    UIElementValidator minWidth(int width);

    UIElementValidator maxWidth(int width);

    UIElementValidator widthBetween(int min, int max);

    UIElementValidator sameHeightAs(WebElement element, String readableName);

    UIElementValidator sameHeightAs(List<WebElement> elements);

    UIElementValidator minHeight(int height);

    UIElementValidator maxHeight(int height);

    UIElementValidator sameSizeAs(WebElement element, String readableName);

    UIElementValidator sameSizeAs(List<WebElement> elements);

    UIElementValidator notSameSizeAs(WebElement element, String readableName);

    UIElementValidator notSameSizeAs(List<WebElement> elements);

    UIElementValidator heightBetween(int min, int max);

    UIElementValidator minOffset(int top, int right, int bottom, int left);

    UIElementValidator maxOffset(int top, int right, int bottom, int left);

    UIElementValidator withCssValue(String cssProperty, String... args);

    UIElementValidator withoutCssValue(String cssProperty, String... args);

    UIElementValidator equalLeftRightOffset();

    UIElementValidator equalTopBottomOffset();

    UIElementValidator insideOf(WebElement containerElement, String readableContainerName);

    UIElementValidator insideOf(WebElement containerElement, String readableContainerName, Padding padding);

}
