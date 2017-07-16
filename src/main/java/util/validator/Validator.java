package util.validator;

import net.itarray.automotion.validation.UIElementValidator;
import org.openqa.selenium.WebElement;
import net.itarray.automotion.validation.properties.Padding;

import java.util.List;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.UIElementValidator}
 */
@Deprecated
public interface Validator extends UIElementValidator {

    UIValidator changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units);

    UIValidator changeMetricsUnitsTo(net.itarray.automotion.validation.Units units);

    UIValidator isRightOf(WebElement element);

    UIValidator isRightOf(WebElement element, int minMargin, int maxMargin);

    UIValidator isLeftOf(WebElement element);

    UIValidator isLeftOf(WebElement element, int minMargin, int maxMargin);

    UIValidator isBelow(WebElement element);

    UIValidator isBelow(WebElement element, int minMargin, int maxMargin);

    UIValidator isAbove(WebElement element);

    UIValidator isAbove(WebElement element, int minMargin, int maxMargin);

    UIValidator notOverlapWith(WebElement element, String readableName);

    UIValidator overlapWith(WebElement element, String readableName);

    UIValidator notOverlapWith(List<WebElement> elements);

    UIValidator isVerticallyLeftAlignedWith(WebElement element, String readableName);

    UIValidator isVerticallyLeftAlignedWith(List<WebElement> elements);

    UIValidator isVerticallyRightAlignedWith(WebElement element, String readableName);

    UIValidator isVerticallyRightAlignedWith(List<WebElement> elements);

    UIValidator isHorizontallyTopAlignedWith(WebElement element, String readableName);

    UIValidator isHorizontallyTopAlignedWith(List<WebElement> elements);

    UIValidator isHorizontallyBottomAlignedWith(WebElement element, String readableName);

    UIValidator isHorizontallyBottomAlignedWith(List<WebElement> elements);

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

    UIValidator notSameSizeAs(WebElement element, String readableName);

    UIValidator notSameSizeAs(List<WebElement> elements);

    UIValidator heightBetween(int min, int max);

    UIValidator minOffset(int top, int right, int bottom, int left);

    UIValidator maxOffset(int top, int right, int bottom, int left);

    UIValidator hasCssValue(String cssProperty, String... args);

    UIValidator doesNotHaveCssValue(String cssProperty, String... args);

    UIValidator equalLeftRightOffset();

    UIValidator equalTopBottomOffset();

    UIValidator insideOf(WebElement containerElement, String readableContainerName);

    UIValidator insideOf(WebElement containerElement, String readableContainerName, Padding padding);

}
