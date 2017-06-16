package util.validator;

import http.helpers.TextFinder;
import net.itarray.automotion.Element;
import net.itarray.automotion.internal.DriverFacade;
import org.openqa.selenium.WebElement;
import util.general.SystemHelper;
import util.validator.properties.Padding;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import static net.itarray.automotion.Element.asElement;
import static net.itarray.automotion.Element.asElements;

public class UIValidator extends ResponsiveUIValidator implements Validator {

    UIValidator(DriverFacade driver, WebElement webElement, String readableNameOfElement) {
        super(driver);
        setRootElement(asElement(webElement));
        rootElementReadableName = readableNameOfElement;
        startTime = System.currentTimeMillis();
    }

    /**
     * Change units to Pixels or % (Units.PX, Units.PERCENT)
     *
     * @param units
     * @return UIValidator
     */
    @Override
    public UIValidator changeMetricsUnitsTo(Units units) {
        this.units = units;
        return this;
    }

    /**
     * Verify that element which located left to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidator withLeftElement(WebElement element) {
        validateLeftElement(asElement(element));
        return this;
    }

    /**
     * Verify that element which located left to is correct with specified margins
     *
     * @param element
     * @param minMargin
     * @param maxMargin
     * @return UIValidator
     */
    @Override
    public UIValidator withLeftElement(WebElement element, int minMargin, int maxMargin) {
        validateLeftElement(asElement(element), getConvertedInt(minMargin, true), getConvertedInt(maxMargin, true));
        return this;
    }

    /**
     * Verify that element which located right to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidator withRightElement(WebElement element) {
        validateRightElement(asElement(element));
        return this;
    }

    /**
     * Verify that element which located right to is correct with specified margins
     *
     * @param element
     * @param minMargin
     * @param maxMargin
     * @return UIValidator
     */
    @Override
    public UIValidator withRightElement(WebElement element, int minMargin, int maxMargin) {
        validateRightElement(asElement(element), getConvertedInt(minMargin, true), getConvertedInt(maxMargin, true));
        return this;
    }

    /**
     * Verify that element which located top to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidator withTopElement(WebElement element) {
        validateAboveElement(asElement(element));
        return this;
    }

    /**
     * Verify that element which located top to is correct with specified margins
     *
     * @param element
     * @param minMargin
     * @param maxMargin
     * @return UIValidator
     */
    @Override
    public UIValidator withTopElement(WebElement element, int minMargin, int maxMargin) {
        validateAboveElement(asElement(element), getConvertedInt(minMargin, false), getConvertedInt(maxMargin, false));
        return this;
    }

    /**
     * Verify that element which located bottom to is correct
     *
     * @param element
     * @return UIValidator
     */
    @Override
    public UIValidator withBottomElement(WebElement element) {
        validateBelowElement(asElement(element));
        return this;
    }

    /**
     * Verify that element which located bottom to is correct with specified margins
     *
     * @param element
     * @param minMargin
     * @param maxMargin
     * @return UIValidator
     */
    @Override
    public UIValidator withBottomElement(WebElement element, int minMargin, int maxMargin) {
        validateBelowElement(asElement(element), getConvertedInt(minMargin, false), getConvertedInt(maxMargin, false));
        return this;
    }

    /**
     * Verify that element is NOT overlapped with specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator notOverlapWith(WebElement element, String readableName) {
        validateNotOverlappingWithElements(asElement(element), readableName);
        return this;
    }

    /**
     * Verify that element is overlapped with specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator overlapWith(WebElement element, String readableName) {
        validateOverlappingWithElements(asElement(element), readableName);
        return this;
    }

    /**
     * Verify that element is NOT overlapped with every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator notOverlapWith(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateNotOverlappingWithElements(asElement(element), asElement(element).getFormattedMessage());
        }
        return this;
    }

    /**
     * Verify that element has the same left offset as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetLeftAs(WebElement element, String readableName) {
        validateLeftOffsetForElements(asElement(element), readableName);
        drawLeftOffsetLine();
        return this;
    }

    /**
     * Verify that element has the same left offset as every element is the list
     *
     * @param webElements
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetLeftAs(List<WebElement> webElements) {
        for (Element element : asElements(webElements)) {
            validateLeftOffsetForElements(element, element.getFormattedMessage());
        }
        drawLeftOffsetLine();
        return this;
    }

    /**
     * Verify that element has the same right offset as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetRightAs(WebElement element, String readableName) {
        validateRightOffsetForElements(asElement(element), readableName);
        drawRightOffsetLine();
        return this;
    }

    /**
     * Verify that element has the same right offset as every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetRightAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateRightOffsetForElements(asElement(element), asElement(element).getFormattedMessage());
        }
        drawRightOffsetLine();
        return this;
    }

    /**
     * Verify that element has the same top offset as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetTopAs(WebElement element, String readableName) {
        validateTopOffsetForElements(asElement(element), readableName);
        drawTopOffsetLine();
        return this;
    }

    /**
     * Verify that element has the same top offset as every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetTopAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateTopOffsetForElements(asElement(element), asElement(element).getFormattedMessage());
        }
        drawTopOffsetLine();
        return this;
    }

    /**
     * Verify that element has the same bottom offset as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetBottomAs(WebElement element, String readableName) {
        validateBottomOffsetForElements(asElement(element), readableName);
        drawBottomOffsetLine();
        return this;
    }

    /**
     * Verify that element has the same bottom offset as every element is the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameOffsetBottomAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateBottomOffsetForElements(asElement(element), asElement(element).getFormattedMessage());
        }
        drawBottomOffsetLine();
        return this;
    }

    /**
     * Verify that element has the same width as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator sameWidthAs(WebElement element, String readableName) {
        validateSameWidth(asElement(element), readableName);
        return this;
    }

    /**
     * Verify that element has the same width as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameWidthAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateSameWidth(asElement(element), asElement(element).getFormattedMessage());
        }
        return this;
    }

    /**
     * Verify that width of element is not less than specified
     *
     * @param width
     * @return UIValidator
     */
    @Override
    public UIValidator minWidth(int width) {
        validateMinWidth(getConvertedInt(width, true));
        return this;
    }

    /**
     * Verify that width of element is not bigger than specified
     *
     * @param width
     * @return UIValidator
     */
    @Override
    public UIValidator maxWidth(int width) {
        validateMaxWidth(getConvertedInt(width, true));
        return this;
    }

    /**
     * Verify that width of element is in range
     *
     * @param min
     * @param max
     * @return UIValidator
     */
    @Override
    public UIValidator widthBetween(int min, int max) {
        validateMinWidth(getConvertedInt(min, true));
        validateMaxWidth(getConvertedInt(max, true));
        return this;
    }

    /**
     * Verify that element has the same height as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator sameHeightAs(WebElement element, String readableName) {
        validateSameHeight(asElement(element), readableName);
        return this;
    }

    /**
     * Verify that element has the same height as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameHeightAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateSameHeight(asElement(element), asElement(element).getFormattedMessage());
        }
        return this;
    }

    /**
     * Verify that height of element is not less than specified
     *
     * @param height
     * @return UIValidator
     */
    @Override
    public UIValidator minHeight(int height) {
        validateMinHeight(getConvertedInt(height, false));
        return this;
    }

    /**
     * Verify that height of element is not bigger than specified
     *
     * @param height
     * @return UIValidator
     */
    @Override
    public UIValidator maxHeight(int height) {
        validateMaxHeight(getConvertedInt(height, false));
        return this;
    }

    /**
     * Verify that element has the same size as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator sameSizeAs(WebElement element, String readableName) {
        validateSameSize(asElement(element), readableName);
        return this;
    }

    /**
     * Verify that element has the same size as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator sameSizeAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateSameSize(asElement(element), asElement(element).getFormattedMessage());
        }
        return this;
    }

    /**
     * Verify that element has not the same size as specified element
     *
     * @param element
     * @param readableName
     * @return UIValidator
     */
    @Override
    public UIValidator notSameSizeAs(WebElement element, String readableName) {
        validateNotSameSize(asElement(element), readableName);
        return this;
    }

    /**
     * Verify that element has not the same size as every element in the list
     *
     * @param elements
     * @return UIValidator
     */
    @Override
    public UIValidator notSameSizeAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateNotSameSize(asElement(element), asElement(element).getFormattedMessage());
        }
        return this;
    }

    /**
     * Verify that height of element is in range
     *
     * @param min
     * @param max
     * @return UIValidator
     */
    @Override
    public UIValidator heightBetween(int min, int max) {
        validateMinHeight(getConvertedInt(min, false));
        validateMaxHeight(getConvertedInt(max, false));
        return this;
    }

    /**
     * Verify that min offset of element is not less than (min value is -10000)
     *
     * @param top
     * @param right
     * @param bottom
     * @param left
     * @return UIValidator
     */
    @Override
    public UIValidator minOffset(int top, int right, int bottom, int left) {
        if (getConvertedInt(top, false) > MIN_OFFSET && getConvertedInt(right, true) > MIN_OFFSET && getConvertedInt(bottom, false) > MIN_OFFSET && getConvertedInt(left, true) > MIN_OFFSET) {
            validateMinOffset(getConvertedInt(top, false), getConvertedInt(right, true), getConvertedInt(bottom, false), getConvertedInt(left, true));
        }
        return this;
    }

    /**
     * Verify that max offset of element is not bigger than (min value is -10000)
     *
     * @param top
     * @param right
     * @param bottom
     * @param left
     * @return UIValidator
     */
    @Override
    public UIValidator maxOffset(int top, int right, int bottom, int left) {
        if (getConvertedInt(top, false) > MIN_OFFSET && getConvertedInt(right, true) > MIN_OFFSET && getConvertedInt(bottom, false) > MIN_OFFSET && getConvertedInt(left, true) > MIN_OFFSET) {
            validateMaxOffset(getConvertedInt(top, false), getConvertedInt(right, true), getConvertedInt(bottom, false), getConvertedInt(left, true));
        }
        return this;
    }

    /**
     * Verify that element has correct CSS values
     *
     * @param cssProperty
     * @param args
     * @return UIValidator
     */
    @Override
    public UIValidator withCssValue(String cssProperty, String... args) {
        String cssValue = getRootElement().getCssValue(cssProperty);

        if (!cssValue.equals("")) {
            for (String val : args) {
                val = !val.startsWith("#") ? val : SystemHelper.hexStringToARGB(val);
                if (!TextFinder.textIsFound(val, cssValue)) {
                    errors.add(String.format("Expected value of '%s' is '%s'. Actual value is '%s'", cssProperty, val, cssValue));
                }
            }
        } else {
            errors.add(String.format("Element '%s' does not have css property '%s'", rootElementReadableName, cssProperty));
        }
        return this;
    }

    /**
     * Verify that concrete CSS values are absent for specified element
     *
     * @param cssProperty
     * @param args
     * @return UIValidator
     */
    @Override
    public UIValidator withoutCssValue(String cssProperty, String... args) {
        String cssValue = getRootElement().getCssValue(cssProperty);

        if (!cssValue.equals("")) {
            for (String val : args) {
                val = !val.startsWith("#") ? val : SystemHelper.hexStringToARGB(val);
                if (TextFinder.textIsFound(val, cssValue)) {
                    errors.add(String.format("CSS property '%s' should not contain value '%s'. Actual value is '%s'", cssProperty, val, cssValue));
                }
            }
        } else {
            errors.add(String.format("Element '%s' does not have css property '%s'", rootElementReadableName, cssProperty));
        }
        return this;
    }

    /**
     * Verify that element has equal left and right offsets (e.g. Bootstrap container)
     *
     * @return UIValidator
     */
    @Override
    public UIValidator equalLeftRightOffset() {
        validateEqualLeftRightOffset(getRootElement(), rootElementReadableName);
        return this;
    }

    /**
     * Verify that element has equal top and bottom offset (aligned vertically in center)
     *
     * @return UIValidator
     */
    @Override
    public UIValidator equalTopBottomOffset() {
        validateEqualTopBottomOffset(getRootElement(), rootElementReadableName);
        return this;
    }

    /**
     * Verify that element(s) is(are) located inside of specified element
     *
     * @param containerElement
     * @param readableContainerName
     * @return ResponsiveUIValidator
     */
    @Override
    public UIValidator insideOf(WebElement containerElement, String readableContainerName) {
        validateInsideOfContainer(asElement(containerElement), readableContainerName);
        return this;
    }

    @Override
    public UIValidator insideOf(WebElement containerElement, String readableContainerName, Padding padding) {
        validateInsideOfContainer(asElement(containerElement), readableContainerName, padding);
        return this;
    }

    void validateElementsAreNotOverlapped(List<Element> elements) {
        for (int firstIndex = 0; firstIndex < elements.size(); firstIndex++) {
            Element first = elements.get(firstIndex);
            for (int secondIndex = firstIndex+1; secondIndex < elements.size(); secondIndex++) {
                Element second = elements.get(secondIndex);
                if (first.overlaps(second)) {
                    errors.add("Elements are overlapped", first);
                    break;
                }
            }
        }
    }

    void validateGridAlignment(List<Element> elements, int columns, int rows) {
        ConcurrentSkipListMap<Integer, AtomicLong> map = new ConcurrentSkipListMap<>();
        for (Element element : elements) {
            Integer y = element.getY();

            map.putIfAbsent(y, new AtomicLong(0));
            map.get(y).incrementAndGet();
        }

        int mapSize = map.size();
        if (rows > 0) {
            if (mapSize != rows) {
                errors.add(String.format("Elements in a grid are not aligned properly. Looks like grid has wrong amount of rows. Expected is %d. Actual is %d", rows, mapSize));
            }
        }

        if (columns > 0) {
            int errorLastLine = 0;
            int rowCount = 1;
            for (Map.Entry<Integer, AtomicLong> entry : map.entrySet()) {
                if (rowCount <= mapSize) {
                    int actualInARow = entry.getValue().intValue();
                    if (actualInARow != columns) {
                        errorLastLine++;
                        if (errorLastLine > 1) {
                            errors.add(String.format("Elements in a grid are not aligned properly in row #%d. Expected %d elements in a row. Actually it's %d", rowCount, columns, actualInARow));
                        }
                    }
                    rowCount++;
                }
            }
        }
    }

    void validateRightOffsetForChunk(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasEqualRightOffsetAs(elementToCompare)) {
                errors.add(String.format("Element #%d has not the same right offset as element #%d", i + 1, i + 2), elementToCompare);
            }
        }
    }

    void validateLeftOffsetForChunk(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasEqualLeftOffsetAs(elementToCompare)) {
                errors.add(String.format("Element #%d has not the same left offset as element #%d", i + 1, i + 2), elementToCompare);
            }
        }
    }

    void validateTopOffsetForChunk(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasEqualTopOffsetAs(elementToCompare)) {
                errors.add(String.format("Element #%d has not the same top offset as element #%d", i + 1, i + 2), elementToCompare);
            }
        }
    }

    void validateBottomOffsetForChunk(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasEqualBottomOffsetAs(elementToCompare)) {
                errors.add(String.format("Element #%d has not the same bottom offset as element #%d", i + 1, i + 2), elementToCompare);
            }
        }
    }

    void validateSameWidth(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasSameWidthAs(elementToCompare)) {
                errors.add(String.format("Element #%d has different width. Element width is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has different width. Element width is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    void validateSameHeight(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasSameHeightAs(elementToCompare)) {
                errors.add(String.format("Element #%d has different height. Element height is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has different height. Element height is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    void validateSameSize(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasSameSizeAs(elementToCompare)) {
                errors.add(String.format("Element #%d has different size. Element size is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has different size. Element size is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    void validateNotSameSize(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (element.hasSameSizeAs(elementToCompare)) {
                errors.add(String.format("Element #%d has same size. Element size is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has same size. Element size is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    void validateNotSameWidth(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (element.hasSameWidthAs(elementToCompare)) {
                errors.add(String.format("Element #%d has same width. Element width is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has same width. Element width is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    void validateNotSameHeight(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (element.hasSameHeightAs(elementToCompare)) {
                errors.add(String.format("Element #%d has same height. Element height is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has same height. Element height is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }
}