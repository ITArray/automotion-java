package net.itarray.automotion;

import net.itarray.automotion.internal.DrawingConfiguration;
import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.HtmlReportBuilder;
import org.openqa.selenium.WebDriver;
import util.validator.ResponsiveUIValidator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static util.validator.ResponsiveUIValidator.Units.PX;

/**
 * This is just a working name - needs discussion
 */
public class Report {

    protected final DriverFacade driver;

    private boolean withReport = false;
    private final List<String> jsonFiles = new ArrayList<>();
    private ResponsiveUIValidator.Units units = PX;

    private boolean mobileTopBarOffset = false;
    private final DrawingConfiguration drawingConfiguration = new DrawingConfiguration();

    public Report(WebDriver driver) {
        this(new DriverFacade(driver));
    }

    public Report(DriverFacade driver) {
        this.driver = driver;
    }

    public boolean isWithReport() {
        return withReport;
    }

    public void drawMap() {
        withReport = true;
    }

    public void addJsonFile(String jsonFileName) {
        jsonFiles.add(jsonFileName);
    }
    /**
     * Call method to generate HTML report
     */
    public void generateReport() {
        generateReport("result");
    }

    /**
     * Call method to generate HTML report with specified file report name
     *
     * @param name
     */
    public void generateReport(String name) {
        if (isWithReport() && !jsonFiles.isEmpty()) {
            new HtmlReportBuilder().buildReport(name, jsonFiles);
        }
    }

    public ResponsiveUIValidator.Units getUnits() {
        return units;
    }

    public void setUnits(ResponsiveUIValidator.Units units) {
        this.units = units;
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

    public DrawingConfiguration getDrawingConfiguration() {
        return drawingConfiguration;
    }

    public DriverFacade getDriver() {
        return driver;
    }



}
