package util.validator;

import org.openqa.selenium.WebElement;

interface Validator {
    ResponsiveUIValidator init();

    ResponsiveUIValidator findElement(WebElement element, String readableNameOfElement);

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

    ResponsiveUIValidator sameOffsetLeftAs(WebElement element, String readableName);

    ResponsiveUIValidator sameOffsetRightAs(WebElement element, String readableName);

    ResponsiveUIValidator sameOffsetTopAs(WebElement element, String readableName);

    ResponsiveUIValidator sameOffsetBottomAs(WebElement element, String readableName);

    ResponsiveUIValidator sameWidthAs(WebElement element, String readableName);

    ResponsiveUIValidator minWidth(int width);

    ResponsiveUIValidator maxWidth(int width);

    ResponsiveUIValidator widthBetween(int min, int max);

    ResponsiveUIValidator sameHeightAs(WebElement element, String readableName);

    ResponsiveUIValidator minHeight(int height);

    ResponsiveUIValidator maxHeight(int height);

    ResponsiveUIValidator sameSizeAs(WebElement element, String readableName);

    ResponsiveUIValidator heightBetween(int min, int max);

    ResponsiveUIValidator minOffset(int top, int right, int bottom, int left);

    ResponsiveUIValidator maxOffset(int top, int right, int bottom, int left);

    ResponsiveUIValidator drawMap();

    boolean validate();

    void generateReport();

}
