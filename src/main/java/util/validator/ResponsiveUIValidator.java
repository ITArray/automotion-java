package util.validator;

import net.itarray.automotion.Report;
import net.itarray.automotion.internal.DrawingConfiguration;
import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class ResponsiveUIValidator {

    private final Report report;

    public ResponsiveUIValidator(WebDriver driver) {
        this(new DriverFacade(driver));
    }

    protected ResponsiveUIValidator(DriverFacade driver) {
        this.report = new Report(driver);
    }

    protected ResponsiveUIValidator(Report report) {
        this.report = report;
    }

    public boolean isWithReport() {
        return report.isWithReport();
    }

    public void addJsonFile(String jsonFileName) {
        report.addJsonFile(jsonFileName);
    }

    /**
     * Set color for main element. This color will be used for highlighting element in results
     *
     * @param color
     */
    public void setColorForRootElement(Color color) {
        report.setColorForRootElement(color);
    }

    /**
     * Set color for compared elements. This color will be used for highlighting elements in results
     *
     * @param color
     */
    public void setColorForHighlightedElements(Color color) {
        report.setColorForHighlightedElements(color);
    }

    /**
     * Set color for grid lines. This color will be used for the lines of alignment grid in results
     *
     * @param color
     */
    public void setLinesColor(Color color) {
        report.setLinesColor(color);
    }

    public DrawingConfiguration getDrawingConfiguration() {
        return report.getDrawingConfiguration();
    }

    /**
     * Set top bar mobile offset. Applicable only for native mobile testing
     *
     * @param state
     */
    public void setTopBarMobileOffset(boolean state) {
        report.setTopBarMobileOffset(state);
    }

    public boolean isMobileTopBarOffset() {
        return report.isMobileTopBarOffset();
    }

    /**
     * Method that defines start of new validation. Needs to be called each time before calling findElement(), findElements()
     *
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator init() {
        return new Scenario(report, "Default");
    }

    /**
     * Method that defines start of new validation with specified name of scenario. Needs to be called each time before calling findElement(), findElements()
     *
     * @param scenarioName
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator init(String scenarioName) {
        return new Scenario(report, scenarioName);
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
        report.setUnits(units);
        return this;
    }

    public Units getUnits() {
        return report.getUnits();
    }

    /**
     * Methods needs to be called to collect all the results in JSON file and screenshots
     *
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator drawMap() {
        report.drawMap();
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
        report.generateReport();
    }

    /**
     * Call method to generate HTML report with specified file report name
     *
     * @param name
     */
    public void generateReport(String name) {
        report.generateReport(name);
    }

    public DriverFacade getDriver() {
        return report.getDriver();
    }

    public enum Units {
        PX,
        PERCENT
    }

}
