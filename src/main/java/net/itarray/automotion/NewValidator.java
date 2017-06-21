package net.itarray.automotion;

import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;
import util.validator.properties.Padding;

import java.util.List;

public interface NewValidator {

    NewValidator changeMetricsUnitsTo(ResponsiveUIValidator.Units units);

    NewValidator withLeftElement(WebElement element);

    NewValidator withLeftElement(WebElement element, int minMargin, int maxMargin);

    NewValidator withRightElement(WebElement element);

    NewValidator withRightElement(WebElement element, int minMargin, int maxMargin);

    NewValidator withTopElement(WebElement element);

    NewValidator withTopElement(WebElement element, int minMargin, int maxMargin);

    NewValidator withBottomElement(WebElement element);

    NewValidator withBottomElement(WebElement element, int minMargin, int maxMargin);

    NewValidator notOverlapWith(WebElement element, String readableName);

    NewValidator overlapWith(WebElement element, String readableName);

    NewValidator notOverlapWith(List<WebElement> elements);

    NewValidator sameOffsetLeftAs(WebElement element, String readableName);

    NewValidator sameOffsetLeftAs(List<WebElement> elements);

    NewValidator sameOffsetRightAs(WebElement element, String readableName);

    NewValidator sameOffsetRightAs(List<WebElement> elements);

    NewValidator sameOffsetTopAs(WebElement element, String readableName);

    NewValidator sameOffsetTopAs(List<WebElement> elements);

    NewValidator sameOffsetBottomAs(WebElement element, String readableName);

    NewValidator sameOffsetBottomAs(List<WebElement> elements);

    NewValidator sameWidthAs(WebElement element, String readableName);

    NewValidator sameWidthAs(List<WebElement> elements);

    NewValidator minWidth(int width);

    NewValidator maxWidth(int width);

    NewValidator widthBetween(int min, int max);

    NewValidator sameHeightAs(WebElement element, String readableName);

    NewValidator sameHeightAs(List<WebElement> elements);

    NewValidator minHeight(int height);

    NewValidator maxHeight(int height);

    NewValidator sameSizeAs(WebElement element, String readableName);

    NewValidator sameSizeAs(List<WebElement> elements);

    NewValidator notSameSizeAs(WebElement element, String readableName);

    NewValidator notSameSizeAs(List<WebElement> elements);

    NewValidator heightBetween(int min, int max);

    NewValidator minOffset(int top, int right, int bottom, int left);

    NewValidator maxOffset(int top, int right, int bottom, int left);

    NewValidator withCssValue(String cssProperty, String... args);

    NewValidator withoutCssValue(String cssProperty, String... args);

    NewValidator equalLeftRightOffset();

    NewValidator equalTopBottomOffset();

    NewValidator insideOf(WebElement containerElement, String readableContainerName);

    NewValidator insideOf(WebElement containerElement, String readableContainerName, Padding padding);

}
