package net.itarray.automotion.validation;

import net.itarray.automotion.internal.DrawingConfiguration;
import net.itarray.automotion.internal.DriverFacade;
import org.openqa.selenium.WebDriver;

import java.awt.*;

/**
 * this is the deprecated {@link util.validator.ResponsiveUIValidator}
 * minus:
 *  - {@link util.validator.ResponsiveUIValidator#findElement(org.openqa.selenium.WebElement, String)}
 *  - {@link util.validator.ResponsiveUIValidator#findElements(java.util.List)}
 *  - {@link util.validator.ResponsiveUIValidator#validate()}
 *
 */
public class ResponsiveUIValidator {

    private final Report report;

    public ResponsiveUIValidator(Report report) {
        this.report = report;
    }

    public ResponsiveUIValidator(WebDriver driver) {
        this(new Report(driver));
    }

    public Scene init(String name) {
        return report.scene(name);
    }

    public Scene init() {
        return report.scene();
    }

    public boolean isWithReport() {
        return report.isWithReport();
    }

    public void drawMap() {
        report.drawMap();
    }

    public void addJsonFile(String jsonFileName) {
        report.addJsonFile(jsonFileName);
    }

    public void generateReport() {
        report.generateHtml();
    }

    public void generateReport(String name) {
        report.generateHtml(name);
    }


    public ResponsiveUIValidator changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units) {
        report.setUnits(units.asNewUnits());
        return this;
    }

    public void setTopBarMobileOffset(boolean state) {
        report.setTopBarMobileOffset(state);
    }

    public boolean isMobileTopBarOffset() {
        return report.isMobileTopBarOffset();
    }

    public void setColorForRootElement(Color color) {
        report.setColorForRootElement(color);
    }

    public void setColorForHighlightedElements(Color color) {
        report.setColorForHighlightedElements(color);
    }

    public void setLinesColor(Color color) {
        report.setLinesColor(color);
    }

    public DrawingConfiguration getDrawingConfiguration() {
        return report.getDrawingConfiguration();
    }

    public DriverFacade getDriver() {
        return report.getDriver();
    }

    public static class Units {
        public static net.itarray.automotion.validation.Units PX = net.itarray.automotion.validation.Units.PX;
        public static net.itarray.automotion.validation.Units PERCENT = net.itarray.automotion.validation.Units.PERCENT;
    }
}
