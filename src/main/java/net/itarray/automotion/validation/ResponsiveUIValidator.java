package net.itarray.automotion.validation;

import net.itarray.automotion.internal.DrawingConfiguration;
import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.HtmlReportBuilder;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ResponsiveUIValidator {

    protected final DriverFacade driver;

    private boolean withReport = true;
    private final List<String> jsonFiles = new ArrayList<>();
    private net.itarray.automotion.validation.Units units = net.itarray.automotion.validation.Units.PX;

    private boolean mobileTopBarOffset = false;
    private final DrawingConfiguration drawingConfiguration = new DrawingConfiguration();

    public ResponsiveUIValidator(WebDriver driver) {
        this(new DriverFacade(driver));
        drawMap();
    }

    public ResponsiveUIValidator(DriverFacade driver) {
        this.driver = driver;
    }

    public UISnapshot snaphost(String name) {
        return new UISnapshot(this, name);
    }

    public UISnapshot snaphost() {
        return snaphost("Default");
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link ResponsiveUIValidator#snaphost(String)}
     */
    @Deprecated
    public UISnapshot init(String name) {
        return snaphost(name);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link ResponsiveUIValidator#snaphost()}
     */
    @Deprecated
    public UISnapshot init() {
        return snaphost();
    }

    public boolean isWithReport() {
        return withReport;
    }

    public void drawMap() {
        withReport = true;
    }

    public void dontDrawMap() {
        withReport = false;
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

    public net.itarray.automotion.validation.Units getUnits() {
        return units;
    }

    public ResponsiveUIValidator changeMetricsUnitsTo(net.itarray.automotion.validation.Units units) {
        this.units = units;
        return this;
    }

    public ResponsiveUIValidator changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units) {
        return changeMetricsUnitsTo(units.asNewUnits());
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

    public static class Units {
        public static net.itarray.automotion.validation.Units PX = net.itarray.automotion.validation.Units.PX;
        public static net.itarray.automotion.validation.Units PERCENT = net.itarray.automotion.validation.Units.PERCENT;
    }


}
