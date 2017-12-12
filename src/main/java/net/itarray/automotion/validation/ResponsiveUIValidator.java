package net.itarray.automotion.validation;

import net.itarray.automotion.internal.DrawingConfiguration;
import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.HtmlReportBuilder;
import net.itarray.automotion.internal.ResolutionImpl;
import net.itarray.automotion.internal.ZoomUnknown;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Resolution;
import net.itarray.automotion.validation.properties.Zoom;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;

public class ResponsiveUIValidator {

    protected final DriverFacade driver;

    private boolean withReport = true;
    private final List<String> jsonFiles = new ArrayList<>();
    private net.itarray.automotion.validation.Units units = net.itarray.automotion.validation.Units.PX;

    private boolean mobileTopBarOffsetState = false;
    private double mobileTopBarOffset = 20;
    private final DrawingConfiguration drawingConfiguration = new DrawingConfiguration();
    private double scaleFactor = 1;
    private Scalar tolerance = scalar(0);

    public ResponsiveUIValidator(WebDriver driver) {
        this(new DriverFacade(driver));
        drawMap();
    }

    public ResponsiveUIValidator(DriverFacade driver) {
        this.driver = driver;
    }

    public UISnapshot snapshot(String name) {
        return snapshot(name, getResolution());
    }

    public UISnapshot snapshot(String name, Resolution resolution) {
        return snapshot(name, resolution, new ZoomUnknown());
    }

    public UISnapshot snapshot(String name, Resolution resolution, Zoom zoom) {
        return new UISnapshot(this, name, resolution, zoom);
    }

    public UISnapshot snapshot(String name, Zoom zoom) {
        return snapshot(name, getResolution(), zoom);
    }

    private Resolution getResolution() {
        return ResolutionImpl.of(driver.getResolution());
    }

    private Zoom getZoom() {
        return new ZoomUnknown().queryIfUnknown(driver);
    }

    public UISnapshot snapshot() {
        return snapshot("Default");
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link ResponsiveUIValidator#snapshot(String)}
     */
    @Deprecated
    public UISnapshot init(String name) {
        return snapshot(name);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link ResponsiveUIValidator#snapshot()}
     */
    @Deprecated
    public UISnapshot init() {
        return snapshot();
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

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Expression#percent(int, net.itarray.automotion.internal.properties.PercentReference)}
     */
    @Deprecated
    public ResponsiveUIValidator changeMetricsUnitsTo(net.itarray.automotion.validation.Units units) {
        this.units = units;
        return this;
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Expression#percent(int, net.itarray.automotion.internal.properties.PercentReference)}
     */
    @Deprecated
    public ResponsiveUIValidator changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units) {
        return changeMetricsUnitsTo(units.asNewUnits());
    }

    /**
     * Set top bar mobile offset. Applicable only for native mobile testing
     *
     * @param state
     */
    public void setTopBarMobileOffset(boolean state) {
        mobileTopBarOffsetState = state;
    }

    /**
     * Set top bar mobile offset. Applicable only for native mobile testing
     *
     * @param state
     * @param offset
     */
    public void setTopBarMobileOffset(boolean state, double offset) {
        mobileTopBarOffsetState = state;
        mobileTopBarOffset = offset;
    }

    /**
     * Set retina scale factor. By default is 1
     *
     * @param factor
     */
    public void setRetinaScaleFactor(double factor) {
        scaleFactor = factor;
    }

    /**
     * Get retina scale factor. By default is 1
     *
     */
    public double getRetinaScaleFactor() {
        return scaleFactor;
    }

    public boolean isMobileTopBarOffsetState() {
        return mobileTopBarOffsetState;
    }

    public double getMobileTopBarOffsetState() {
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

    /**
     * Set the tolerance (in pixels) to be used in all verifications.
     *
     * @param tolerance the tolerance (in pixels) to be used in all verifications
     * @return
     */
    public ResponsiveUIValidator withTolerance(int tolerance) {
        if (tolerance < 0) {
            throw new IllegalArgumentException("tolerance needs to be greater or equal to zero, tolerance supplied was " + tolerance);
        }
        this.tolerance = scalar(tolerance);
        return this;
    }

    public Scalar getTolerance() {
        return tolerance;
    }

    public DrawingConfiguration getDrawingConfiguration() {
        return drawingConfiguration;
    }

    public DriverFacade getDriver() {
        return driver;
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Expression#percent(int, net.itarray.automotion.internal.properties.PercentReference)}
     */
    public static class Units {
        public static net.itarray.automotion.validation.Units PX = net.itarray.automotion.validation.Units.PX;
        public static net.itarray.automotion.validation.Units PERCENT = net.itarray.automotion.validation.Units.PERCENT;
    }


}
