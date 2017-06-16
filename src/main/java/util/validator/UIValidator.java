package util.validator;

import http.helpers.TextFinder;
import net.itarray.automotion.Element;
import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.OffsetLineCommands;
import org.openqa.selenium.WebElement;
import util.general.SystemHelper;
import util.validator.properties.Padding;

import java.awt.geom.Rectangle2D;
import java.util.List;

import static net.itarray.automotion.Element.asElement;
import static net.itarray.automotion.Element.asElements;

public class UIValidator extends ResponsiveUIValidator implements Validator {


    private final OffsetLineCommands offsetLineCommands = new OffsetLineCommands();
    private final String rootElementReadableName;

    UIValidator(DriverFacade driver, WebElement webElement, String readableNameOfElement) {
        super(driver);
        setRootElement(asElement(webElement));
        this.rootElementReadableName = readableNameOfElement;
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
            errors.add(String.format("Element '%s' does not have css property '%s'", getRootElementReadableName(), cssProperty));
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
            errors.add(String.format("Element '%s' does not have css property '%s'", getRootElementReadableName(), cssProperty));
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
        validateEqualLeftRightOffset(getRootElement(), getRootElementReadableName());
        return this;
    }

    /**
     * Verify that element has equal top and bottom offset (aligned vertically in center)
     *
     * @return UIValidator
     */
    @Override
    public UIValidator equalTopBottomOffset() {
        validateEqualTopBottomOffset(getRootElement(), getRootElementReadableName());
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

    private void validateRightOffsetForElements(Element element, String readableName) {
        if (!getRootElement().hasEqualRightOffsetAs(element)) {
            errors.add(String.format("Element '%s' has not the same right offset as element '%s'", getRootElementReadableName(), readableName), element);
        }
    }

    private void validateLeftOffsetForElements(Element element, String readableName) {
        if (!getRootElement().hasEqualLeftOffsetAs(element)) {
            errors.add(String.format("Element '%s' has not the same left offset as element '%s'", getRootElementReadableName(), readableName), element);
        }
    }

    private void validateTopOffsetForElements(Element element, String readableName) {
        if (!getRootElement().hasEqualTopOffsetAs(element)) {
            errors.add(String.format("Element '%s' has not the same top offset as element '%s'", getRootElementReadableName(), readableName), element);
        }
    }

    private void validateBottomOffsetForElements(Element element, String readableName) {
        if (!getRootElement().hasEqualBottomOffsetAs(element)) {
            errors.add(String.format("Element '%s' has not the same bottom offset as element '%s'", getRootElementReadableName(), readableName), element);
        }
    }

    private void validateNotOverlappingWithElements(Element element, String readableName) {
        if (getRootElement().overlaps(element)) {
            errors.add(String.format("Element '%s' is overlapped with element '%s' but should not", getRootElementReadableName(), readableName), element);
        }
    }

    private void validateOverlappingWithElements(Element element, String readableName) {
        if (!getRootElement().overlaps(element)) {
            errors.add(String.format("Element '%s' is not overlapped with element '%s' but should be", getRootElementReadableName(), readableName), element);
        }
    }

    private void validateMaxOffset(int top, int right, int bottom, int left) {
        int rootElementRightOffset = getRootElement().getRightOffset(pageSize);
        int rootElementBottomOffset = getRootElement().getBottomOffset(pageSize);
        if (getRootElement().getX() > left) {
            errors.add(String.format("Expected max left offset of element  '%s' is: %spx. Actual left offset is: %spx", getRootElementReadableName(), left, getRootElement().getX()));
        }
        if (getRootElement().getY() > top) {
            errors.add(String.format("Expected max top offset of element '%s' is: %spx. Actual top offset is: %spx", getRootElementReadableName(), top, getRootElement().getY()));
        }
        if (rootElementRightOffset > right) {
            errors.add(String.format("Expected max right offset of element  '%s' is: %spx. Actual right offset is: %spx", getRootElementReadableName(), right, rootElementRightOffset));
        }
        if (rootElementBottomOffset > bottom) {
            errors.add(String.format("Expected max bottom offset of element  '%s' is: %spx. Actual bottom offset is: %spx", getRootElementReadableName(), bottom, rootElementBottomOffset));
        }
    }

    private void validateMinOffset(int top, int right, int bottom, int left) {
        int rootElementRightOffset = getRootElement().getRightOffset(pageSize);
        int rootElementBottomOffset = getRootElement().getBottomOffset(pageSize);
        if (getRootElement().getX() < left) {
            errors.add(String.format("Expected min left offset of element  '%s' is: %spx. Actual left offset is: %spx", getRootElementReadableName(), left, getRootElement().getX()));
        }
        if (getRootElement().getY() < top) {
            errors.add(String.format("Expected min top offset of element  '%s' is: %spx. Actual top offset is: %spx", getRootElementReadableName(), top, getRootElement().getY()));
        }
        if (rootElementRightOffset < right) {
            errors.add(String.format("Expected min top offset of element  '%s' is: %spx. Actual right offset is: %spx", getRootElementReadableName(), right, rootElementRightOffset));
        }
        if (rootElementBottomOffset < bottom) {
            errors.add(String.format("Expected min bottom offset of element  '%s' is: %spx. Actual bottom offset is: %spx", getRootElementReadableName(), bottom, rootElementBottomOffset));
        }
    }

    private void validateMaxHeight(int height) {
        if (!getRootElement().hasMaxHeight(height)) {
            errors.add(String.format("Expected max height of element  '%s' is: %spx. Actual height is: %spx", getRootElementReadableName(), height, getRootElement().getHeight()));
        }
    }

    private void validateMinHeight(int height) {
        if (!getRootElement().hasMinHeight(height)) {
            errors.add(String.format("Expected min height of element '%s' is: %spx. Actual height is: %spx", getRootElementReadableName(), height, getRootElement().getHeight()));
        }
    }

    private void validateMaxWidth(int width) {
        if (!getRootElement().hasMaxWidth(width)) {
            errors.add(String.format("Expected max width of element '%s' is: %spx. Actual width is: %spx", getRootElementReadableName(), width, getRootElement().getWidth()));
        }
    }

    private void validateMinWidth(int width) {
        if (!getRootElement().hasMinWidth(width)) {
            errors.add(String.format("Expected min width of element '%s' is: %spx. Actual width is: %spx", getRootElementReadableName(), width, getRootElement().getWidth()));
        }
    }

    private void validateSameWidth(Element element, String readableName) {
        if (!getRootElement().hasSameWidthAs(element)) {
            errors.add(String.format("Element '%s' has not the same width as %s. Width of '%s' is %spx. Width of element is %spx", getRootElementReadableName(), readableName, getRootElementReadableName(), getRootElement().getWidth(), element.getWidth()), element);
        }
    }

    private void validateSameHeight(Element element, String readableName) {
        if (!getRootElement().hasSameHeightAs(element)) {
            errors.add(String.format("Element '%s' has not the same height as %s. Height of '%s' is %spx. Height of element is %spx", getRootElementReadableName(), readableName, getRootElementReadableName(), getRootElement().getHeight(), element.getHeight()), element);
        }
    }

    private void validateSameSize(Element element, String readableName) {
        if (!getRootElement().hasSameSizeAs(element)) {
            errors.add(String.format("Element '%s' has not the same size as %s. Size of '%s' is %spx x %spx. Size of element is %spx x %spx", getRootElementReadableName(), readableName, getRootElementReadableName(), getRootElement().getWidth(), getRootElement().getHeight(), element.getWidth(), element.getHeight()), element);
        }
    }

    private void validateNotSameSize(Element element, String readableName) {
        if (!element.hasEqualWebElement(getRootElement())) {
            int h = element.getHeight();
            int w = element.getWidth();
            if (h == getRootElement().getHeight() && w == getRootElement().getWidth()) {
                errors.add(String.format("Element '%s' has the same size as %s. Size of '%s' is %spx x %spx. Size of element is %spx x %spx", getRootElementReadableName(), readableName, getRootElementReadableName(), getRootElement().getWidth(), getRootElement().getHeight(), w, h), element);
            }
        }
    }

    private void validateBelowElement(Element element, int minMargin, int maxMargin) {
        int marginBetweenRoot = element.getY() - getRootElement().getCornerY();
        if (marginBetweenRoot < minMargin || marginBetweenRoot > maxMargin) {
            errors.add(String.format("Below element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", minMargin, maxMargin, marginBetweenRoot), element);
        }
    }

    private void validateBelowElement(Element belowElement) {
        if (!getRootElement().hasBelowElement(belowElement)) {
            errors.add("Below element aligned not properly", belowElement);
        }
    }

    private void validateAboveElement(Element element, int minMargin, int maxMargin) {
        int marginBetweenRoot = getRootElement().getY() - element.getCornerY();
        if (marginBetweenRoot < minMargin || marginBetweenRoot > maxMargin) {
            errors.add(String.format("Above element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", minMargin, maxMargin, marginBetweenRoot), element);
        }
    }

    private void validateAboveElement(Element aboveElement) {
        if (!getRootElement().hasAboveElement(aboveElement)) {
            errors.add("Above element aligned not properly", aboveElement);
        }
    }

    private void validateRightElement(Element element, int minMargin, int maxMargin) {
        int marginBetweenRoot = element.getX() - getRootElement().getCornerX();
        if (marginBetweenRoot < minMargin || marginBetweenRoot > maxMargin) {
            errors.add(String.format("Right element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", minMargin, maxMargin, marginBetweenRoot), element);
        }
    }

    private void validateRightElement(Element rightElement) {
        if (!getRootElement().hasRightElement(rightElement)) {
            errors.add("Right element aligned not properly", rightElement);
        }
    }

    private void validateLeftElement(Element leftElement, int minMargin, int maxMargin) {
        int marginBetweenRoot = getRootElement().getX() - leftElement.getCornerX();
        if (marginBetweenRoot < minMargin || marginBetweenRoot > maxMargin) {
            errors.add(String.format("Left element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", minMargin, maxMargin, marginBetweenRoot), leftElement);
        }
    }

    private void validateLeftElement(Element leftElement) {
        if (!getRootElement().hasLeftElement(leftElement)) {
            errors.add("Left element aligned not properly", leftElement);
        }
    }

    private void validateEqualLeftRightOffset(Element element, String rootElementReadableName) {
        if (!element.hasEqualLeftRightOffset(pageSize)) {
            errors.add(String.format("Element '%s' has not equal left and right offset. Left offset is %dpx, right is %dpx", rootElementReadableName, element.getX(), element.getRightOffset(pageSize)), element);
        }
    }

    private void validateEqualTopBottomOffset(Element element, String rootElementReadableName) {
        if (!element.hasEqualTopBottomOffset(pageSize)) {
            errors.add(String.format("Element '%s' has not equal top and bottom offset. Top offset is %dpx, bottom is %dpx", rootElementReadableName, element.getY(), element.getBottomOffset(pageSize)), element);
        }
    }

    private void validateInsideOfContainer(Element containerElement, String readableContainerName) {
        Rectangle2D.Double elementRectangle = containerElement.rectangle();
        if (!elementRectangle.contains(getRootElement().rectangle())) {
            errors.add(String.format("Element '%s' is not inside of '%s'", getRootElementReadableName(), readableContainerName), containerElement);
        }
    }

    private void validateInsideOfContainer(Element element, String readableContainerName, Padding padding) {
        int top = getConvertedInt(padding.getTop(), false);
        int right = getConvertedInt(padding.getRight(), true);
        int bottom = getConvertedInt(padding.getBottom(), false);
        int left = getConvertedInt(padding.getLeft(), true);

        Rectangle2D.Double paddedRootRectangle = new Rectangle2D.Double(
                getRootElement().getX() - left,
                getRootElement().getY() - top,
                getRootElement().getWidth() + left + right,
                getRootElement().getHeight() + top + bottom);

        int paddingTop = getRootElement().getY() - element.getY();
        int paddingLeft = getRootElement().getX() - element.getX();
        int paddingBottom = element.getCornerY() - getRootElement().getCornerY();
        int paddingRight = element.getCornerX() - getRootElement().getCornerX();

        if (!element.rectangle().contains(paddedRootRectangle)) {
            errors.add(String.format("Padding of element '%s' is incorrect. Expected padding: top[%d], right[%d], bottom[%d], left[%d]. Actual padding: top[%d], right[%d], bottom[%d], left[%d]",
                    getRootElementReadableName(), top, right, bottom, left, paddingTop, paddingRight, paddingBottom, paddingLeft), element);
        }
    }

    @Override
    protected OffsetLineCommands getOffsetLineCommands() {
        return offsetLineCommands;
    }

    @Override
    protected String getRootElementReadableName() {
        return rootElementReadableName;
    }


    private void drawLeftOffsetLine() {
        getOffsetLineCommands().drawLeftOffsetLine();
    }

    private void drawRightOffsetLine() {
        getOffsetLineCommands().drawRightOffsetLine();
    }

    private void drawTopOffsetLine() {
        getOffsetLineCommands().drawTopOffsetLine();
    }

    private void drawBottomOffsetLine() {
        getOffsetLineCommands().drawBottomOffsetLine();
    }


}