package util.validator;

import net.itarray.automotion.internal.DrawingConfiguration;
import net.itarray.automotion.internal.Scenario;
import net.itarray.automotion.internal.DriverFacade;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import util.general.HtmlReportBuilder;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static util.validator.ResponsiveUIValidator.Units.PX;

public class ResponsiveUIValidator {

    protected final DriverFacade driver;

    protected static boolean withReport = false;
    protected static List<String> jsonFiles = new ArrayList<>();
    protected ResponsiveUIValidator.Units units = PX;

    private boolean mobileTopBarOffset = false;
    private final DrawingConfiguration drawingConfiguration = new DrawingConfiguration();

    public ResponsiveUIValidator(WebDriver driver) {
        this(new DriverFacade(driver));
    }

    protected ResponsiveUIValidator(DriverFacade driver) {
        this.driver = driver;
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

    protected DrawingConfiguration getDrawingConfiguration() {
        return drawingConfiguration;
    }

    /**
     * Set top bar mobile offset. Applicable only for native mobile testing
     *
     * @param state
     */
    public void setTopBarMobileOffset(boolean state) {
        mobileTopBarOffset = state;
    }

    public boolean isMobileTopBarOffset() {
        return mobileTopBarOffset;
    }

    /**
     * Method that defines start of new validation. Needs to be called each time before calling findElement(), findElements()
     *
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator init() {
        return new Scenario(driver, "Default", this);
    }

    /**
     * Method that defines start of new validation with specified name of scenario. Needs to be called each time before calling findElement(), findElements()
     *
     * @param scenarioName
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator init(String scenarioName) {
        return new Scenario(driver, scenarioName, this);
    }

    /**
     * Main method to specify which element we want to validate (can be called only findElement() OR findElements() for single validation)
     *
     * @param element
     * @param readableNameOfElement
     * @return UIValidator
     */
    public UIValidator findElement(WebElement element, String readableNameOfElement) {
        return init().findElement(element, readableNameOfElement);
    }

    /**
     * Main method to specify the list of elements that we want to validate (can be called only findElement() OR findElements() for single validation)
     *
     * @param elements
     * @return ResponsiveUIChunkValidator
     */
    public ResponsiveUIChunkValidator findElements(java.util.List<WebElement> elements) {
        return init().findElements(elements);
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
        return true; // nothing to validate in a factory or scenario
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

    public enum Units {
        PX,
        PERCENT
    }

}
