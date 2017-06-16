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

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static environment.EnvironmentFactory.*;
import static util.general.SystemHelper.isRetinaDisplay;
import static util.validator.Constants.*;
import static util.validator.ResponsiveUIValidator.Units.PX;

public class ResponsiveUIValidator {
    static final int MIN_OFFSET = -10000;
    private final static Logger LOG = Logger.getLogger(ResponsiveUIValidator.class);

    private final DriverFacade driver;

    private static Element rootElement;

    private static long startTime;
    private static boolean isMobileTopBar = false;
    private static boolean withReport = false;
    private static String scenarioName = "Default";
    private static DrawingConfiguration drawingConfiguration = new DrawingConfiguration();
    private static String currentZoom = "100%";
    private static List<String> jsonFiles = new ArrayList<>();
    protected static Errors errors;
    ResponsiveUIValidator.Units units = PX;
    protected Dimension pageSize;

    public ResponsiveUIValidator(WebDriver driver) {
        this(new DriverFacade(driver));
    }

    protected ResponsiveUIValidator(DriverFacade driver) {
        this.driver = driver;
        ResponsiveUIValidator.errors = new Errors();
        currentZoom = driver.getZoom();
        pageSize = driver.retrievePageSize();
        startTime = System.currentTimeMillis();
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

        screenshot.drawScreenshot(rootElement, getRootElementReadableName(), errors, getOffsetLineCommands());

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
        jsonResults.put(ELEMENT_NAME, getRootElementReadableName());
        jsonResults.put(SCREENSHOT, drawableScreenshot.getOutput().getName());

        long ms = System.currentTimeMillis();
        String uuid = Helper.getGeneratedStringWithLength(7);
        String jsonFileName = getRootElementReadableName().replace(" ", "") + "-automotion" + ms + uuid + ".json";
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

    protected OffsetLineCommands getOffsetLineCommands() {
        throw new RuntimeException("should be overwritten");
    }

    protected String getRootElementReadableName() {
        throw new RuntimeException("should be overwritten");
    }

    public enum Units {
        PX,
        PERCENT
    }

}
