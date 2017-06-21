package net.itarray.automotion;

import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;
import util.validator.properties.Padding;

import java.util.List;

public interface ValidatorBase {

    ValidatorBase changeMetricsUnitsTo(ResponsiveUIValidator.Units units);

    ValidatorBase withLeftElement(WebElement element);

    ValidatorBase withLeftElement(WebElement element, int minMargin, int maxMargin);

    ValidatorBase withRightElement(WebElement element);

    ValidatorBase withRightElement(WebElement element, int minMargin, int maxMargin);

    ValidatorBase withTopElement(WebElement element);

    ValidatorBase withTopElement(WebElement element, int minMargin, int maxMargin);

    ValidatorBase withBottomElement(WebElement element);

    ValidatorBase withBottomElement(WebElement element, int minMargin, int maxMargin);

    ValidatorBase notOverlapWith(WebElement element, String readableName);

    ValidatorBase overlapWith(WebElement element, String readableName);

    ValidatorBase notOverlapWith(List<WebElement> elements);

    ValidatorBase sameOffsetLeftAs(WebElement element, String readableName);

    ValidatorBase sameOffsetLeftAs(List<WebElement> elements);

    ValidatorBase sameOffsetRightAs(WebElement element, String readableName);

    ValidatorBase sameOffsetRightAs(List<WebElement> elements);

    ValidatorBase sameOffsetTopAs(WebElement element, String readableName);

    ValidatorBase sameOffsetTopAs(List<WebElement> elements);

    ValidatorBase sameOffsetBottomAs(WebElement element, String readableName);

    ValidatorBase sameOffsetBottomAs(List<WebElement> elements);

    ValidatorBase sameWidthAs(WebElement element, String readableName);

    ValidatorBase sameWidthAs(List<WebElement> elements);

    ValidatorBase minWidth(int width);

    ValidatorBase maxWidth(int width);

    ValidatorBase widthBetween(int min, int max);

    ValidatorBase sameHeightAs(WebElement element, String readableName);

    ValidatorBase sameHeightAs(List<WebElement> elements);

    ValidatorBase minHeight(int height);

    ValidatorBase maxHeight(int height);

    ValidatorBase sameSizeAs(WebElement element, String readableName);

    ValidatorBase sameSizeAs(List<WebElement> elements);

    ValidatorBase notSameSizeAs(WebElement element, String readableName);

    ValidatorBase notSameSizeAs(List<WebElement> elements);

    ValidatorBase heightBetween(int min, int max);

    ValidatorBase minOffset(int top, int right, int bottom, int left);

    ValidatorBase maxOffset(int top, int right, int bottom, int left);

    ValidatorBase withCssValue(String cssProperty, String... args);

    ValidatorBase withoutCssValue(String cssProperty, String... args);

    ValidatorBase equalLeftRightOffset();

    ValidatorBase equalTopBottomOffset();

    ValidatorBase insideOf(WebElement containerElement, String readableContainerName);

    ValidatorBase insideOf(WebElement containerElement, String readableContainerName, Padding padding);

}
