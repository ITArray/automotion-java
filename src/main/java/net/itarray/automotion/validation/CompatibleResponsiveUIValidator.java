package net.itarray.automotion.validation;

import net.itarray.automotion.internal.DrawingConfiguration;
import net.itarray.automotion.internal.DriverFacade;
import util.validator.ResponsiveUIValidator;

import java.awt.*;

/**
 * this is the deprecated {@link util.validator.ResponsiveUIValidator}
 * minus:
 *  - {@link util.validator.ResponsiveUIValidator#findElement(org.openqa.selenium.WebElement, String)}
 *  - {@link util.validator.ResponsiveUIValidator#findElements(java.util.List)}
 *  - {@link util.validator.ResponsiveUIValidator#validate()}
 *
 */
public class CompatibleResponsiveUIValidator {

    private final Report report;

    public CompatibleResponsiveUIValidator(Report report) {
        this.report = report;
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
        report.generateReport();
    }

    public void generateReport(String name) {
        report.generateReport(name);
    }

    public ResponsiveUIValidator.Units getUnits() {
        return report.getUnits();
    }

    public CompatibleResponsiveUIValidator changeMetricsUnitsTo(ResponsiveUIValidator.Units units) {
        report.setUnits(units);
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
}
