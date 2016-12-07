package util.validator;

import org.openqa.selenium.WebElement;

import java.util.List;

interface Validator {

    UIValidator changeMetricsUnitsTo(UIValidator.Units units);

    UIValidator withLeftElement(WebElement element);

    UIValidator withLeftElement(WebElement element, int minMargin, int maxMargin);

    UIValidator withRightElement(WebElement element);

    UIValidator withRightElement(WebElement element, int minMargin, int maxMargin);

    UIValidator withTopElement(WebElement element);

    UIValidator withTopElement(WebElement element, int minMargin, int maxMargin);

    UIValidator withBottomElement(WebElement element);

    UIValidator withBottomElement(WebElement element, int minMargin, int maxMargin);

    UIValidator notOverlapWith(WebElement element, String readableName);

    UIValidator overlapWith(WebElement element, String readableName);

    UIValidator notOverlapWith(List<WebElement> elements);

    UIValidator sameOffsetLeftAs(WebElement element, String readableName);

    UIValidator sameOffsetLeftAs(List<WebElement> elements);

    UIValidator sameOffsetRightAs(WebElement element, String readableName);

    UIValidator sameOffsetRightAs(List<WebElement> elements);

    UIValidator sameOffsetTopAs(WebElement element, String readableName);

    UIValidator sameOffsetTopAs(List<WebElement> elements);

    UIValidator sameOffsetBottomAs(WebElement element, String readableName);

    UIValidator sameOffsetBottomAs(List<WebElement> elements);

    UIValidator sameWidthAs(WebElement element, String readableName);

    UIValidator sameWidthAs(List<WebElement> elements);

    UIValidator minWidth(int width);

    UIValidator maxWidth(int width);

    UIValidator widthBetween(int min, int max);

    UIValidator sameHeightAs(WebElement element, String readableName);

    UIValidator sameHeightAs(List<WebElement> elements);

    UIValidator minHeight(int height);

    UIValidator maxHeight(int height);

    UIValidator sameSizeAs(WebElement element, String readableName);

    UIValidator sameSizeAs(List<WebElement> elements);

    UIValidator heightBetween(int min, int max);

    UIValidator minOffset(int top, int right, int bottom, int left);

    UIValidator maxOffset(int top, int right, int bottom, int left);

    UIValidator withCssValue(String cssProperty, String... args);

    UIValidator withoutCssValue(String cssProperty, String... args);

}
