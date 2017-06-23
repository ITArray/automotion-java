package net.itarray.automotion.validation;

import org.openqa.selenium.WebElement;
import net.itarray.automotion.validation.properties.Padding;

import java.util.List;

public interface Validator {

    Validator drawMap();

    boolean validate();

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.Validator#changeMetricsUnitsTo(Units)}
     */
    @Deprecated
    Validator changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units);

    Validator changeMetricsUnitsTo(Units units);

    Validator withLeftElement(WebElement element);

    Validator withLeftElement(WebElement element, int minMargin, int maxMargin);

    Validator withRightElement(WebElement element);

    Validator withRightElement(WebElement element, int minMargin, int maxMargin);

    Validator withTopElement(WebElement element);

    Validator withTopElement(WebElement element, int minMargin, int maxMargin);

    Validator withBottomElement(WebElement element);

    Validator withBottomElement(WebElement element, int minMargin, int maxMargin);

    Validator notOverlapWith(WebElement element, String readableName);

    Validator overlapWith(WebElement element, String readableName);

    Validator notOverlapWith(List<WebElement> elements);

    Validator sameOffsetLeftAs(WebElement element, String readableName);

    Validator sameOffsetLeftAs(List<WebElement> elements);

    Validator sameOffsetRightAs(WebElement element, String readableName);

    Validator sameOffsetRightAs(List<WebElement> elements);

    Validator sameOffsetTopAs(WebElement element, String readableName);

    Validator sameOffsetTopAs(List<WebElement> elements);

    Validator sameOffsetBottomAs(WebElement element, String readableName);

    Validator sameOffsetBottomAs(List<WebElement> elements);

    Validator sameWidthAs(WebElement element, String readableName);

    Validator sameWidthAs(List<WebElement> elements);

    Validator minWidth(int width);

    Validator maxWidth(int width);

    Validator widthBetween(int min, int max);

    Validator sameHeightAs(WebElement element, String readableName);

    Validator sameHeightAs(List<WebElement> elements);

    Validator minHeight(int height);

    Validator maxHeight(int height);

    Validator sameSizeAs(WebElement element, String readableName);

    Validator sameSizeAs(List<WebElement> elements);

    Validator notSameSizeAs(WebElement element, String readableName);

    Validator notSameSizeAs(List<WebElement> elements);

    Validator heightBetween(int min, int max);

    Validator minOffset(int top, int right, int bottom, int left);

    Validator maxOffset(int top, int right, int bottom, int left);

    Validator withCssValue(String cssProperty, String... args);

    Validator withoutCssValue(String cssProperty, String... args);

    Validator equalLeftRightOffset();

    Validator equalTopBottomOffset();

    Validator insideOf(WebElement containerElement, String readableContainerName);

    Validator insideOf(WebElement containerElement, String readableContainerName, Padding padding);

}
