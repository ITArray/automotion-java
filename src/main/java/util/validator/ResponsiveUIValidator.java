package util.validator;

import http.helpers.Helper;
import net.itarray.automotion.Element;
import net.itarray.automotion.Errors;
import net.itarray.automotion.Zoom;
import net.itarray.automotion.internal.DriverFacade;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import util.general.HtmlReportBuilder;
import util.validator.properties.Padding;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static Color rootColor = new Color(255, 0, 0, 255);
    private static Color highlightedElementsColor = new Color(255, 0, 255, 255);
    private static Color linesColor = Color.ORANGE;
    private static String currentZoom = "100%";
    private static List<String> jsonFiles = new ArrayList<>();
    protected static Errors errors;
    boolean drawLeftOffsetLine = false;
    boolean drawRightOffsetLine = false;
    boolean drawTopOffsetLine = false;
    boolean drawBottomOffsetLine = false;
    String rootElementReadableName = "Root Element";
    protected List<Element> rootElements;
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
        rootColor = color;
    }

    /**
     * Set color for compared elements. This color will be used for highlighting elements in results
     *
     * @param color
     */
    public void setColorForHighlightedElements(Color color) {
        highlightedElementsColor = color;
    }

    /**
     * Set color for grid lines. This color will be used for the lines of alignment grid in results
     *
     * @param color
     */
    public void setLinesColor(Color color) {
        linesColor = color;
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

        JSONObject jsonResults = new JSONObject();

        jsonResults.put(ERROR_KEY, errors.hasMessages());
        jsonResults.put(DETAILS, errors.getMessages());

        File screenshot = null;
        BufferedImage img = null;
        try {
            screenshot = driver.takeScreenshot();
            img = ImageIO.read(screenshot);
        } catch (Exception e) {
            LOG.error("Failed to create screenshot file: " + e.getMessage());
        }

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
        jsonResults.put(SCREENSHOT, rootElementReadableName.replace(" ", "") + "-" + screenshot.getName());

        long ms = System.currentTimeMillis();
        String uuid = Helper.getGeneratedStringWithLength(7);
        String jsonFileName = rootElementReadableName.replace(" ", "") + "-automotion" + ms + uuid + ".json";
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TARGET_AUTOMOTION_JSON + jsonFileName), StandardCharsets.UTF_8))) {
            writer.write(jsonResults.toJSONString());
        } catch (IOException ex) {
            LOG.error("Cannot create json report: " + ex.getMessage());
        }
        jsonFiles.add(jsonFileName);
        try {
            File file = new File(TARGET_AUTOMOTION_JSON + rootElementReadableName.replace(" ", "") + "-automotion" + ms + uuid + ".json");
            if (file.getParentFile().mkdirs()) {
                if (file.createNewFile()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(jsonResults.toJSONString());
                    writer.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ((boolean) jsonResults.get(ERROR_KEY)) {
            drawScreenshot(screenshot, img);
        }
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

    private void drawScreenshot(File output, BufferedImage img) {
        if (img != null) {
            Graphics2D g = img.createGraphics();

            drawRoot(rootColor, g, img);

            for (Object obj : errors.getMessages()) {
                JSONObject det = (JSONObject) obj;
                JSONObject details = (JSONObject) det.get(REASON);
                JSONObject numE = (JSONObject) details.get(ELEMENT);

                if (numE != null) {
                    int x = (int) (float) numE.get(X);
                    int y = (int) (float) numE.get(Y);
                    int width = (int) (float) numE.get(WIDTH);
                    int height = (int) (float) numE.get(HEIGHT);

                    g.setColor(highlightedElementsColor);
                    g.setStroke(new BasicStroke(2));
                    drawRectByExtend(g, x, y, width, height);
                }
            }

            try {
                ImageIO.write(img, "png", output);
                File file = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replace(" ", "") + "-" + output.getName());
                FileUtils.copyFile(output, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            LOG.error("Taking of screenshot was failed for some reason.");
        }
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

    void validateNotSameSize(Element element, String readableName) {
        if (!element.getWebElement().equals(getRootElement().getWebElement())) {
            int h = element.getHeight();
            int w = element.getWidth();
            if (h == rootElement.getHeight() && w == rootElement.getWidth()) {
                errors.add(String.format("Element '%s' has the same size as %s. Size of '%s' is %spx x %spx. Size of element is %spx x %spx", rootElementReadableName, readableName, rootElementReadableName, rootElement.getWidth(), rootElement.getHeight(), w, h), element);
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
                errors.add(String.format("Element '%s' has not equal left and right offset. Left offset is %dpx, right is %dpx", getFormattedMessage(element), element.getX(), element.getRightOffset(pageSize)), element);
            }
        }
    }

    void validateEqualTopBottomOffset(List<Element> elements) {
        for (Element element : elements) {
            if (!element.hasEqualTopBottomOffset(pageSize)) {
                errors.add(String.format("Element '%s' has not equal top and bottom offset. Top offset is %dpx, bottom is %dpx", getFormattedMessage(element), element.getY(), element.getBottomOffset(pageSize)), element);
            }
        }
    }

    private void drawRoot(Color color, Graphics2D g, BufferedImage img) {
        g.setColor(color);
        g.setStroke(new BasicStroke(2));
        drawRectByExtend(g, rootElement.getX(), rootElement.getY(), rootElement.getWidth(), rootElement.getHeight());

        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g.setStroke(dashed);
        g.setColor(linesColor);
        if (drawLeftOffsetLine) {
            drawVerticalLine(g, img, rootElement.getX());
        }
        if (drawRightOffsetLine) {
            drawVerticalLine(g, img, rootElement.getCornerX());
        }
        if (drawTopOffsetLine) {
            drawHorizontalLine(g, img, rootElement.getY());
        }
        if (drawBottomOffsetLine) {
            drawHorizontalLine(g, img, rootElement.getCornerY());
        }
    }

    private void drawRectByExtend(Graphics2D g, int x, int y, int width, int height) {
        drawRectByCorner(g, x, y, x + width, y + height);
    }

    private void drawRectByCorner(Graphics2D g, int x, int y, int cornerX, int cornerY) {
        int transformedX = transformX(x);
        int transformedY = transformY(y);
        int transformedCornerX = transformX(cornerX);
        int transformedCornerY = transformY(cornerY);
        int transformedWidth = transformedCornerX - transformedX;
        int transformedHeight = transformedCornerY - transformedY;
        g.drawRect(transformedX, transformedY, transformedWidth, transformedHeight);
    }

    private void drawVerticalLine(Graphics2D g, BufferedImage img, int x) {
        int transformedX = transformX(x);
        int transformedHeight = transformY(img.getHeight()) - transformY(0);
        g.drawLine(transformedX, 0, transformedX, transformedHeight);
    }

    private void drawHorizontalLine(Graphics2D g, BufferedImage img, int y) {
        int transformedY = transformY(y);
        int transformedWidth = transformX(img.getWidth()) - transformX(0);
        g.drawLine(0, transformedY, transformedWidth, transformedY);
    }

    private int transformX(int x) {
        return retinaValue(x);
    }

    private int transformY(int y) {
        return retinaValue(mobileY(y));
    }

    private int mobileY(int value) {
        if (isMobile() && driver.isAppiumWebContext()) {
            if (isIOS()) {
                if (isMobileTopBar) {
                    return value + 20;
                } else {
                    return value;
                }
            } else if (isAndroid()) {
                if (isMobileTopBar) {
                    return value + 20;
                } else {
                    return value;
                }
            } else {
                return value;
            }
        } else {
            return value;
        }
    }

    private int retinaValue(int value) {
        if (!isMobile()) {
            int zoom = Integer.parseInt(currentZoom.replace("%", ""));
            value = Zoom.applyZoom(value, zoom);
            if (isRetinaDisplay() && isChrome()) {
                return 2 * value;
            } else {
                return value;
            }
        } else {
            if (isIOS()) {
                String[] iOS_RETINA_DEVICES = {
                        "iPhone 4", "iPhone 4s",
                        "iPhone 5", "iPhone 5s",
                        "iPhone 6", "iPhone 6s",
                        "iPad Mini 2",
                        "iPad Mini 4",
                        "iPad Air 2",
                        "iPad Pro"
                };
                if (Arrays.asList(iOS_RETINA_DEVICES).contains(getDevice())) {
                    return 2 * value;
                } else {
                    return value;
                }
            } else {
                return value;
            }
        }
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

    String getFormattedMessage(Element element) {
        return String.format("with properties: tag=[%s], id=[%s], class=[%s], text=[%s], coord=[%s,%s], size=[%s,%s]",
                element.getWebElement().getTagName(),
                element.getWebElement().getAttribute("id"),
                element.getWebElement().getAttribute("class"),
                element.getWebElement().getText().length() < 10 ? element.getWebElement().getText() : element.getWebElement().getText().substring(0, 10) + "...",
                String.valueOf(element.getX()),
                String.valueOf(element.getY()),
                String.valueOf(element.getWidth()),
                String.valueOf(element.getHeight()));
    }

    void validateInsideOfContainer(Element containerElement, String readableContainerName) {
        Rectangle2D.Double elementRectangle = containerElement.rectangle();
        if (rootElements == null) {
            if (!elementRectangle.contains(rootElement.rectangle())) {
                errors.add(String.format("Element '%s' is not inside of '%s'", rootElementReadableName, readableContainerName), containerElement);
            }
        } else {
            for (Element element : rootElements) {
                if (!elementRectangle.contains(element.rectangle())) {
                    errors.add(String.format("Element is not inside of '%s'", readableContainerName), containerElement);
                }
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

    public enum Units {
        PX,
        PERCENT
    }

}
