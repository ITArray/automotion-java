package util.validator;

import http.helpers.Helper;
import net.itarray.automotion.Element;
import net.itarray.automotion.internal.DrawableScreenshot;
import net.itarray.automotion.internal.DrawingConfiguration;
import net.itarray.automotion.internal.Errors;
import net.itarray.automotion.internal.OffsetLineCommands;
import net.itarray.automotion.internal.SimpleTransform;
import net.itarray.automotion.internal.Zoom;
import net.itarray.automotion.internal.DriverFacade;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import util.general.HtmlReportBuilder;
import util.validator.properties.Padding;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import static environment.EnvironmentFactory.*;
import static util.general.SystemHelper.isRetinaDisplay;
import static util.validator.Constants.*;
import static util.validator.ResponsiveUIValidator.Units.PX;

public class ResponsiveUIValidator {
    static final int MIN_OFFSET = -10000;
    private final static Logger LOG = Logger.getLogger(ResponsiveUIValidator.class);

    private final DriverFacade driver;

    private static Element rootElement;

    static long startTime;
    private static boolean isMobileTopBar = false;
    private static boolean withReport = false;
    private static String scenarioName = "Default";
    private static DrawingConfiguration drawingConfiguration = new DrawingConfiguration();
    private static String currentZoom = "100%";
    private static List<String> jsonFiles = new ArrayList<>();
    protected static Errors errors;
    private OffsetLineCommands offsetLineCommands = new OffsetLineCommands();
    String rootElementReadableName = "Root Element";
    ResponsiveUIValidator.Units units = PX;
    private Dimension pageSize;

    public ResponsiveUIValidator(WebDriver driver) {
        this(new DriverFacade(driver));
    }

    protected ResponsiveUIValidator(DriverFacade driver) {
        this.driver = driver;
        ResponsiveUIValidator.errors = new Errors();
        currentZoom = driver.getZoom();
        pageSize = driver.retrievePageSize();
    }

    protected static Element getRootElement() {
        return rootElement;
    }

    protected static void setRootElement(Element element) {
        ResponsiveUIValidator.rootElement = element;
    }

    /**
     * Set color for main element. This color will be used for highlighting element in results
     *
     * @param color
     */
    public void setColorForRootElement(Color color) {
        drawingConfiguration.setRootColor(color);
    }

    /**
     * Set color for compared elements. This color will be used for highlighting elements in results
     *
     * @param color
     */
    public void setColorForHighlightedElements(Color color) {
        drawingConfiguration.setHighlightedElementsColor(color);
    }

    /**
     * Set color for grid lines. This color will be used for the lines of alignment grid in results
     *
     * @param color
     */
    public void setLinesColor(Color color) {
        drawingConfiguration.setLinesColor(color);
    }

    /**
     * Set top bar mobile offset. Applicable only for native mobile testing
     *
     * @param state
     */
    public void setTopBarMobileOffset(boolean state) {
        isMobileTopBar = state;
    }

    /**
     * Method that defines start of new validation. Needs to be called each time before calling findElement(), findElements()
     *
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator init() {
        return new ResponsiveUIValidator(driver);
    }

    /**
     * Method that defines start of new validation with specified name of scenario. Needs to be called each time before calling findElement(), findElements()
     *
     * @param scenarioName
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator init(String scenarioName) {
        ResponsiveUIValidator.scenarioName = scenarioName;
        return new ResponsiveUIValidator(driver);
    }

    /**
     * Main method to specify which element we want to validate (can be called only findElement() OR findElements() for single validation)
     *
     * @param element
     * @param readableNameOfElement
     * @return UIValidator
     */
    public UIValidator findElement(WebElement element, String readableNameOfElement) {
        return new UIValidator(driver, element, readableNameOfElement);
    }

    /**
     * Main method to specify the list of elements that we want to validate (can be called only findElement() OR findElements() for single validation)
     *
     * @param elements
     * @return ResponsiveUIChunkValidator
     */
    public ResponsiveUIChunkValidator findElements(java.util.List<WebElement> elements) {
        return new ResponsiveUIChunkValidator(driver, elements);
    }

    /**
     * Change units to Pixels or % (Units.PX, Units.PERCENT)
     *
     * @param units
     * @return UIValidator
     */
    public ResponsiveUIValidator changeMetricsUnitsTo(Units units) {
        this.units = units;
        return this;
    }

    /**
     * Methods needs to be called to collect all the results in JSON file and screenshots
     *
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator drawMap() {
        withReport = true;
        return this;
    }

    /**
     * Call method to summarize and validate the results (can be called with drawMap(). In this case result will be only True or False)
     *
     * @return boolean
     */
    public boolean validate() {

        if (errors.hasMessages()) {
            compileValidationReport();
        }

        return !errors.hasMessages();
    }

    private void compileValidationReport() {
        if (!withReport) {
            return;
        }

        DrawableScreenshot screenshot = new DrawableScreenshot(driver, getTransform(), drawingConfiguration);

        screenshot.drawScreenshot(rootElement, rootElementReadableName, errors, offsetLineCommands);

        writeResults(screenshot);
    }

    private void writeResults(DrawableScreenshot drawableScreenshot) {
        JSONObject jsonResults = new JSONObject();

        jsonResults.put(ERROR_KEY, errors.hasMessages());
        jsonResults.put(DETAILS, errors.getMessages());

        JSONObject rootDetails = new JSONObject();
        if (rootElement != null) {
            rootDetails.put(X, rootElement.getX());
            rootDetails.put(Y, rootElement.getY());
            rootDetails.put(WIDTH, rootElement.getWidth());
            rootDetails.put(HEIGHT, rootElement.getHeight());
        }

        jsonResults.put(SCENARIO, scenarioName);
        jsonResults.put(ROOT_ELEMENT, rootDetails);
        jsonResults.put(TIME_EXECUTION, String.valueOf(System.currentTimeMillis() - startTime) + " milliseconds");
        jsonResults.put(ELEMENT_NAME, rootElementReadableName);
        jsonResults.put(SCREENSHOT, drawableScreenshot.getOutput().getName());

        long ms = System.currentTimeMillis();
        String uuid = Helper.getGeneratedStringWithLength(7);
        String jsonFileName = rootElementReadableName.replace(" ", "") + "-automotion" + ms + uuid + ".json";
        File jsonFile = new File(TARGET_AUTOMOTION_JSON + jsonFileName);
        jsonFile.getParentFile().mkdirs();
        try (
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(jsonFile), StandardCharsets.UTF_8);
                Writer writer = new BufferedWriter(outputStreamWriter))
        {
            writer.write(jsonResults.toJSONString());
        } catch (IOException ex) {
            LOG.error("Cannot create json report: " + ex.getMessage());
        }

        jsonFiles.add(jsonFileName);
    }

    /**
     * Call method to generate HTML report
     */
    public void generateReport() {
        if (withReport && !jsonFiles.isEmpty()) {
            try {
                new HtmlReportBuilder().buildReport(jsonFiles);
            } catch (IOException | ParseException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Call method to generate HTML report with specified file report name
     *
     * @param name
     */
    public void generateReport(String name) {
        if (withReport && !jsonFiles.isEmpty()) {
            try {
                new HtmlReportBuilder().buildReport(name, jsonFiles);
            } catch (IOException | ParseException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void validateRightOffsetForElements(Element element, String readableName) {
        if (!rootElement.hasEqualRightOffsetAs(element)) {
            errors.add(String.format("Element '%s' has not the same right offset as element '%s'", rootElementReadableName, readableName), element);
        }
    }

    void validateLeftOffsetForElements(Element element, String readableName) {
        if (!rootElement.hasEqualLeftOffsetAs(element)) {
            errors.add(String.format("Element '%s' has not the same left offset as element '%s'", rootElementReadableName, readableName), element);
        }
    }

    void validateTopOffsetForElements(Element element, String readableName) {
        if (!rootElement.hasEqualTopOffsetAs(element)) {
            errors.add(String.format("Element '%s' has not the same top offset as element '%s'", rootElementReadableName, readableName), element);
        }
    }

    void validateBottomOffsetForElements(Element element, String readableName) {
        if (!rootElement.hasEqualBottomOffsetAs(element)) {
            errors.add(String.format("Element '%s' has not the same bottom offset as element '%s'", rootElementReadableName, readableName), element);
        }
    }

    void validateNotOverlappingWithElements(Element element, String readableName) {
        if (rootElement.overlaps(element)) {
            errors.add(String.format("Element '%s' is overlapped with element '%s' but should not", rootElementReadableName, readableName), element);
        }
    }

    void validateOverlappingWithElements(Element element, String readableName) {
        if (!rootElement.overlaps(element)) {
            errors.add(String.format("Element '%s' is not overlapped with element '%s' but should be", rootElementReadableName, readableName), element);
        }
    }

    void validateMaxOffset(int top, int right, int bottom, int left) {
        int rootElementRightOffset = getRootElement().getRightOffset(pageSize);
        int rootElementBottomOffset = rootElement.getBottomOffset(pageSize);
        if (rootElement.getX() > left) {
            errors.add(String.format("Expected max left offset of element  '%s' is: %spx. Actual left offset is: %spx", rootElementReadableName, left, rootElement.getX()));
        }
        if (rootElement.getY() > top) {
            errors.add(String.format("Expected max top offset of element '%s' is: %spx. Actual top offset is: %spx", rootElementReadableName, top, rootElement.getY()));
        }
        if (rootElementRightOffset > right) {
            errors.add(String.format("Expected max right offset of element  '%s' is: %spx. Actual right offset is: %spx", rootElementReadableName, right, rootElementRightOffset));
        }
        if (rootElementBottomOffset > bottom) {
            errors.add(String.format("Expected max bottom offset of element  '%s' is: %spx. Actual bottom offset is: %spx", rootElementReadableName, bottom, rootElementBottomOffset));
        }
    }

    void validateMinOffset(int top, int right, int bottom, int left) {
        int rootElementRightOffset = getRootElement().getRightOffset(pageSize);
        int rootElementBottomOffset = rootElement.getBottomOffset(pageSize);
        if (rootElement.getX() < left) {
            errors.add(String.format("Expected min left offset of element  '%s' is: %spx. Actual left offset is: %spx", rootElementReadableName, left, rootElement.getX()));
        }
        if (rootElement.getY() < top) {
            errors.add(String.format("Expected min top offset of element  '%s' is: %spx. Actual top offset is: %spx", rootElementReadableName, top, rootElement.getY()));
        }
        if (rootElementRightOffset < right) {
            errors.add(String.format("Expected min top offset of element  '%s' is: %spx. Actual right offset is: %spx", rootElementReadableName, right, rootElementRightOffset));
        }
        if (rootElementBottomOffset < bottom) {
            errors.add(String.format("Expected min bottom offset of element  '%s' is: %spx. Actual bottom offset is: %spx", rootElementReadableName, bottom, rootElementBottomOffset));
        }
    }

    void validateMaxHeight(int height) {
        if (!rootElement.hasMaxHeight(height)) {
            errors.add(String.format("Expected max height of element  '%s' is: %spx. Actual height is: %spx", rootElementReadableName, height, rootElement.getHeight()));
        }
    }

    void validateMinHeight(int height) {
        if (!rootElement.hasMinHeight(height)) {
            errors.add(String.format("Expected min height of element '%s' is: %spx. Actual height is: %spx", rootElementReadableName, height, rootElement.getHeight()));
        }
    }

    void validateMaxWidth(int width) {
        if (!rootElement.hasMaxWidth(width)) {
            errors.add(String.format("Expected max width of element '%s' is: %spx. Actual width is: %spx", rootElementReadableName, width, rootElement.getWidth()));
        }
    }

    void validateMinWidth(int width) {
        if (!rootElement.hasMinWidth(width)) {
            errors.add(String.format("Expected min width of element '%s' is: %spx. Actual width is: %spx", rootElementReadableName, width, rootElement.getWidth()));
        }
    }

    void validateSameWidth(Element element, String readableName) {
        if (!rootElement.hasSameWidthAs(element)) {
            errors.add(String.format("Element '%s' has not the same width as %s. Width of '%s' is %spx. Width of element is %spx", rootElementReadableName, readableName, rootElementReadableName, rootElement.getWidth(), element.getWidth()), element);
        }
    }

    void validateSameHeight(Element element, String readableName) {
        if (!rootElement.hasSameHeightAs(element)) {
            errors.add(String.format("Element '%s' has not the same height as %s. Height of '%s' is %spx. Height of element is %spx", rootElementReadableName, readableName, rootElementReadableName, rootElement.getHeight(), element.getHeight()), element);
        }
    }

    void validateSameSize(Element element, String readableName) {
        if (!rootElement.hasSameSizeAs(element)) {
            errors.add(String.format("Element '%s' has not the same size as %s. Size of '%s' is %spx x %spx. Size of element is %spx x %spx", rootElementReadableName, readableName, rootElementReadableName, rootElement.getWidth(), rootElement.getHeight(), element.getWidth(), element.getHeight()), element);
        }
    }

    void validateNotSameSize(Element element, String readableName) {
        if (!element.hasEqualWebElement(getRootElement())) {
            int h = element.getHeight();
            int w = element.getWidth();
            if (h == rootElement.getHeight() && w == rootElement.getWidth()) {
                errors.add(String.format("Element '%s' has the same size as %s. Size of '%s' is %spx x %spx. Size of element is %spx x %spx", rootElementReadableName, readableName, rootElementReadableName, rootElement.getWidth(), rootElement.getHeight(), w, h), element);
            }
        }
    }

    void validateBelowElement(Element element, int minMargin, int maxMargin) {
        int marginBetweenRoot = element.getY() - rootElement.getCornerY();
        if (marginBetweenRoot < minMargin || marginBetweenRoot > maxMargin) {
            errors.add(String.format("Below element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", minMargin, maxMargin, marginBetweenRoot), element);
        }
    }

    void validateBelowElement(Element belowElement) {
        if (!getRootElement().hasBelowElement(belowElement)) {
            errors.add("Below element aligned not properly", belowElement);
        }
    }

    void validateAboveElement(Element element, int minMargin, int maxMargin) {
        int marginBetweenRoot = rootElement.getY() - element.getCornerY();
        if (marginBetweenRoot < minMargin || marginBetweenRoot > maxMargin) {
            errors.add(String.format("Above element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", minMargin, maxMargin, marginBetweenRoot), element);
        }
    }

    void validateAboveElement(Element aboveElement) {
        if (!getRootElement().hasAboveElement(aboveElement)) {
            errors.add("Above element aligned not properly", aboveElement);
        }
    }

    void validateRightElement(Element element, int minMargin, int maxMargin) {
        int marginBetweenRoot = element.getX() - rootElement.getCornerX();
        if (marginBetweenRoot < minMargin || marginBetweenRoot > maxMargin) {
            errors.add(String.format("Right element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", minMargin, maxMargin, marginBetweenRoot), element);
        }
    }

    void validateRightElement(Element rightElement) {
        if (!getRootElement().hasRightElement(rightElement)) {
            errors.add("Right element aligned not properly", rightElement);
        }
    }

    void validateLeftElement(Element leftElement, int minMargin, int maxMargin) {
        int marginBetweenRoot = rootElement.getX() - leftElement.getCornerX();
        if (marginBetweenRoot < minMargin || marginBetweenRoot > maxMargin) {
            errors.add(String.format("Left element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", minMargin, maxMargin, marginBetweenRoot), leftElement);
        }
    }

    void validateLeftElement(Element leftElement) {
        if (!getRootElement().hasLeftElement(leftElement)) {
            errors.add("Left element aligned not properly", leftElement);
        }
    }

    void validateEqualLeftRightOffset(Element element, String rootElementReadableName) {
        if (!element.hasEqualLeftRightOffset(pageSize)) {
            errors.add(String.format("Element '%s' has not equal left and right offset. Left offset is %dpx, right is %dpx", rootElementReadableName, element.getX(), element.getRightOffset(pageSize)), element);
        }
    }

    void validateEqualTopBottomOffset(Element element, String rootElementReadableName) {
        if (!element.hasEqualTopBottomOffset(pageSize)) {
            errors.add(String.format("Element '%s' has not equal top and bottom offset. Top offset is %dpx, bottom is %dpx", rootElementReadableName, element.getY(), element.getBottomOffset(pageSize)), element);
        }
    }

    void validateEqualLeftRightOffset(List<Element> elements) {
        for (Element element : elements) {
            if (!element.hasEqualLeftRightOffset(pageSize)) {
                errors.add(String.format("Element '%s' has not equal left and right offset. Left offset is %dpx, right is %dpx", element.getFormattedMessage(), element.getX(), element.getRightOffset(pageSize)), element);
            }
        }
    }

    void validateEqualTopBottomOffset(List<Element> elements) {
        for (Element element : elements) {
            if (!element.hasEqualTopBottomOffset(pageSize)) {
                errors.add(String.format("Element '%s' has not equal top and bottom offset. Top offset is %dpx, bottom is %dpx", element.getFormattedMessage(), element.getY(), element.getBottomOffset(pageSize)), element);
            }
        }
    }

    private int getYOffset() {
        if (isMobile() && driver.isAppiumWebContext() && isMobileTopBar) {
            if (isIOS() || isAndroid()) {
                return 20;
            }
        }
        return 0;
    }

    private SimpleTransform getTransform() {
        return new SimpleTransform(getYOffset(), getScaleFactor());
    }

    private double getScaleFactor() {
        double factor = 1;
        if (isMobile()) {
            if (isIOS() && isIOSDevice()) {
                factor = 2;
            }
        } else {
            int zoom = Integer.parseInt(currentZoom.replace("%", ""));
            factor = Zoom.getFactor(zoom);
            if (isRetinaDisplay() && isChrome()) {
                factor = factor * 2;
            }
        }
        return factor;
    }

    int getConvertedInt(int i, boolean horizontal) {
        if (units.equals(PX)) {
            return i;
        } else {
            if (horizontal) {
                return (i * pageSize.getWidth()) / 100;
            } else {
                return (i * pageSize.getHeight()) / 100;
            }
        }
    }

    void validateInsideOfContainer(Element containerElement, String readableContainerName) {
        Rectangle2D.Double elementRectangle = containerElement.rectangle();
        if (!elementRectangle.contains(rootElement.rectangle())) {
            errors.add(String.format("Element '%s' is not inside of '%s'", rootElementReadableName, readableContainerName), containerElement);
        }
    }

    void validateInsideOfContainer(Element containerElement, String readableContainerName, List<Element> elements) {
        Rectangle2D.Double elementRectangle = containerElement.rectangle();
        for (Element element : elements) {
            if (!elementRectangle.contains(element.rectangle())) {
                errors.add(String.format("Element is not inside of '%s'", readableContainerName), containerElement);
            }
        }
    }

    void validateInsideOfContainer(Element element, String readableContainerName, Padding padding) {
        int top = getConvertedInt(padding.getTop(), false);
        int right = getConvertedInt(padding.getRight(), true);
        int bottom = getConvertedInt(padding.getBottom(), false);
        int left = getConvertedInt(padding.getLeft(), true);

        Rectangle2D.Double paddedRootRectangle = new Rectangle2D.Double(
                rootElement.getX() - left,
                rootElement.getY() - top,
                rootElement.getWidth() + left + right,
                rootElement.getHeight() + top + bottom);

        int paddingTop = rootElement.getY() - element.getY();
        int paddingLeft = rootElement.getX() - element.getX();
        int paddingBottom = element.getCornerY() - rootElement.getCornerY();
        int paddingRight = element.getCornerX() - rootElement.getCornerX();

        if (!element.rectangle().contains(paddedRootRectangle)) {
            errors.add(String.format("Padding of element '%s' is incorrect. Expected padding: top[%d], right[%d], bottom[%d], left[%d]. Actual padding: top[%d], right[%d], bottom[%d], left[%d]",
                                rootElementReadableName, top, right, bottom, left, paddingTop, paddingRight, paddingBottom, paddingLeft), element);
        }
    }

    protected void drawLeftOffsetLine() {
        offsetLineCommands.drawLeftOffsetLine();;
    }

    protected void drawRightOffsetLine() {
        offsetLineCommands.drawRightOffsetLine();;
    }

    protected void drawTopOffsetLine() {
        offsetLineCommands.drawTopOffsetLine();;
    }

    protected void drawBottomOffsetLine() {
        offsetLineCommands.drawBottomOffsetLine();;
    }

    public enum Units {
        PX,
        PERCENT
    }

}
