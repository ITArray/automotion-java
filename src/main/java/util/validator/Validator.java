package util.validator;

import org.openqa.selenium.WebElement;

import java.util.List;

interface Validator {
    ResponsiveUIValidator init();

    ResponsiveUIValidator findElement(WebElement element, String readableNameOfElement);

    ResponsiveUIValidator findElements(List<WebElement> elements);

    ResponsiveUIValidator changeMetricsUnitsTo(ResponsiveUIValidator.Units units);

    ResponsiveUIValidator withLeftElement(WebElement element);

    ResponsiveUIValidator withLeftElement(WebElement element, int minMargin, int maxMargin);

    ResponsiveUIValidator withRightElement(WebElement element);

    ResponsiveUIValidator withRightElement(WebElement element, int minMargin, int maxMargin);

    ResponsiveUIValidator withTopElement(WebElement element);

    ResponsiveUIValidator withTopElement(WebElement element, int minMargin, int maxMargin);

    ResponsiveUIValidator withBottomElement(WebElement element);

    ResponsiveUIValidator withBottomElement(WebElement element, int minMargin, int maxMargin);

    ResponsiveUIValidator insideOf(WebElement element, String readableContainerName);

    ResponsiveUIValidator notOverlapWith(WebElement element, String readableName);

    ResponsiveUIValidator overlapWith(WebElement element, String readableName);

    ResponsiveUIValidator notOverlapWith(List<WebElement> elements);

    ResponsiveUIValidator sameOffsetLeftAs(WebElement element, String readableName);

    ResponsiveUIValidator sameOffsetLeftAs(List<WebElement> elements);

    ResponsiveUIValidator sameOffsetRightAs(WebElement element, String readableName);

    ResponsiveUIValidator sameOffsetRightAs(List<WebElement> elements);

    ResponsiveUIValidator sameOffsetTopAs(WebElement element, String readableName);

    ResponsiveUIValidator sameOffsetTopAs(List<WebElement> elements);

    ResponsiveUIValidator sameOffsetBottomAs(WebElement element, String readableName);

    ResponsiveUIValidator sameOffsetBottomAs(List<WebElement> elements);

    ResponsiveUIValidator sameWidthAs(WebElement element, String readableName);

    ResponsiveUIValidator sameWidthAs(List<WebElement> elements);

    ResponsiveUIValidator minWidth(int width);

    ResponsiveUIValidator maxWidth(int width);

    ResponsiveUIValidator widthBetween(int min, int max);

    ResponsiveUIValidator sameHeightAs(WebElement element, String readableName);

    ResponsiveUIValidator sameHeightAs(List<WebElement> elements);

    ResponsiveUIValidator minHeight(int height);

    ResponsiveUIValidator maxHeight(int height);

    ResponsiveUIValidator sameSizeAs(WebElement element, String readableName);

    ResponsiveUIValidator sameSizeAs(List<WebElement> elements);

    ResponsiveUIValidator heightBetween(int min, int max);

    ResponsiveUIValidator minOffset(int top, int right, int bottom, int left);

    ResponsiveUIValidator maxOffset(int top, int right, int bottom, int left);

    ResponsiveUIValidator withCssValue(String cssProperty, String... args);

    ResponsiveUIValidator withoutCssValue(String cssProperty, String... args);

    ResponsiveUIValidator alignedAsGrid(int horizontalGridSize);

    ResponsiveUIValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    ResponsiveUIValidator areNotOverlappedWithEachOther();

    ResponsiveUIValidator drawMap();

    boolean validate();

    void generateReport();

    void generateReport(String name);

}
