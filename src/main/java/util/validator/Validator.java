package util.validator;

import org.openqa.selenium.WebElement;

interface Validator {
    ResponsiveValidator findElement(WebElement element, String readableNameOfElement);

    ResponsiveValidator withLeftElement(WebElement element);

    ResponsiveValidator withLeftElement(WebElement element, int minMargin, int maxMargin);

    ResponsiveValidator withRightElement(WebElement element);

    ResponsiveValidator withRightElement(WebElement element, int minMargin, int maxMargin);

    ResponsiveValidator withTopElement(WebElement element);

    ResponsiveValidator withTopElement(WebElement element, int minMargin, int maxMargin);

    ResponsiveValidator withBottomElement(WebElement element);

    ResponsiveValidator withBottomElement(WebElement element, int minMargin, int maxMargin);

    ResponsiveValidator insideOf(WebElement element, String readableContainerName);

    ResponsiveValidator notOverlapWith(WebElement element, String readableName);

    ResponsiveValidator sameOffsetLeftAs(WebElement element, String readableName);

    ResponsiveValidator sameOffsetRightAs(WebElement element, String readableName);

    ResponsiveValidator sameOffsetTopAs(WebElement element, String readableName);

    ResponsiveValidator sameOffsetBottomAs(WebElement element, String readableName);

    ResponsiveValidator minWidth(int width);

    ResponsiveValidator maxWidth(int width);

    ResponsiveValidator widthBetween(int min, int max);

    ResponsiveValidator minHeight(int height);

    ResponsiveValidator maxHeight(int height);

    ResponsiveValidator heightBetween(int min, int max);

    ResponsiveValidator minOffset(int top, int right, int bottom, int left);

    ResponsiveValidator maxOffset(int top, int right, int bottom, int left);

    ResponsiveValidator drawMap();

    boolean validate();

    void generateReport();

}
