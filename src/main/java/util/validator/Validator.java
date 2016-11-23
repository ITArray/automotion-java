package util.validator;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;

interface Validator {
    ResponsiveValidator findElement(WebElement element, String readableNameOfElement);

    ResponsiveValidator withLeftElement(WebElement element);

    ResponsiveValidator withRightElement(WebElement element);

    ResponsiveValidator withAboveElement(WebElement element);

    ResponsiveValidator withBelowElement(WebElement element);

    ResponsiveValidator insideOf(WebElement element, String readableContainerName);

    ResponsiveValidator notOverlapWith(WebElement element, String readableName);

    ResponsiveValidator sameOffsetLeftAs(WebElement element, String readableName);

    ResponsiveValidator sameOffsetRightAs(WebElement element, String readableName);

    ResponsiveValidator minWidth(int width);

    ResponsiveValidator maxWidth(int width);

    ResponsiveValidator widthBetween(int min, int max);

    ResponsiveValidator minHeight(int height);

    ResponsiveValidator maxHeight(int height);

    ResponsiveValidator heightBetween(int min, int max);

    ResponsiveValidator minOffset(int top, int right, int bottom, int left);

    ResponsiveValidator maxOffset(int top, int right, int bottom, int left);

    ResponsiveValidator drawMap();

    JSONObject validate();

}
