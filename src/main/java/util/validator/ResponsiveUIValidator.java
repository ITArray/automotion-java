package util.validator;

import net.itarray.automotion.internal.DrawingConfiguration;
import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.ResolutionUnkown;
import net.itarray.automotion.internal.Scenario;
import net.itarray.automotion.internal.ZoomUnkown;
import net.itarray.automotion.validation.UISnapshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.ResponsiveUIValidator}
 */
@Deprecated
public class ResponsiveUIValidator {

    private final net.itarray.automotion.validation.ResponsiveUIValidator responsiveUIValidator;

    public ResponsiveUIValidator(WebDriver driver) {
        this(new DriverFacade(driver));
    }

    protected ResponsiveUIValidator(DriverFacade driver) {
        this(new net.itarray.automotion.validation.ResponsiveUIValidator(driver));
    }

    protected ResponsiveUIValidator(net.itarray.automotion.validation.ResponsiveUIValidator responsiveUIValidator) {
        this.responsiveUIValidator = responsiveUIValidator;
        responsiveUIValidator.dontDrawMap();
    }

    public boolean isWithReport() {
        return responsiveUIValidator.isWithReport();
    }

    public void addJsonFile(String jsonFileName) {
        responsiveUIValidator.addJsonFile(jsonFileName);
    }

    /**
     * Set color for main element. This color will be used for highlighting element in results
     *
     * @param color
     */
    public void setColorForRootElement(Color color) {
        responsiveUIValidator.setColorForRootElement(color);
    }

    /**
     * Set color for compared elements. This color will be used for highlighting elements in results
     *
     * @param color
     */
    public void setColorForHighlightedElements(Color color) {
        responsiveUIValidator.setColorForHighlightedElements(color);
    }

    /**
     * Set color for grid lines. This color will be used for the lines of alignment grid in results
     *
     * @param color
     */
    public void setLinesColor(Color color) {
        responsiveUIValidator.setLinesColor(color);
    }

    public DrawingConfiguration getDrawingConfiguration() {
        return responsiveUIValidator.getDrawingConfiguration();
    }

    /**
     * Set top bar mobile offset. Applicable only for native mobile testing
     *
     * @param state
     */
    public void setTopBarMobileOffset(boolean state) {
        responsiveUIValidator.setTopBarMobileOffset(state);
    }

    public boolean isMobileTopBarOffset() {
        return responsiveUIValidator.isMobileTopBarOffset();
    }

    /**
     * Method that defines start of new validation. Needs to be called each time before calling findElement(), findElements()
     *
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator init() {
        return init("Default");
    }

    /**
     * Method that defines start of new validation with specified name of scenario. Needs to be called each time before calling findElement(), findElements()
     *
     * @param scenarioName
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator init(String scenarioName) {
        return new Scenario(new UISnapshot(responsiveUIValidator, scenarioName, new ResolutionUnkown(), new ZoomUnkown()));
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
        responsiveUIValidator.changeMetricsUnitsTo(units.asNewUnits());
        return this;
    }

    /**
     * Methods needs to be called to collect all the results in JSON file and screenshots
     *
     * @return ResponsiveUIValidator
     */
    public ResponsiveUIValidator drawMap() {
        responsiveUIValidator.drawMap();
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
        responsiveUIValidator.generateReport();
    }

    /**
     * Call method to generate HTML report with specified file report name
     *
     * @param name
     */
    public void generateReport(String name) {
        responsiveUIValidator.generateReport(name);
    }

    public DriverFacade getDriver() {
        return responsiveUIValidator.getDriver();
    }

    public enum Units {
        PX {
            @Override
            public net.itarray.automotion.validation.Units asNewUnits() {
                return net.itarray.automotion.validation.Units.PX;
            }
        },
        PERCENT {
            @Override
            public net.itarray.automotion.validation.Units asNewUnits() {
                return net.itarray.automotion.validation.Units.PERCENT;
            }
        };

        public abstract net.itarray.automotion.validation.Units asNewUnits();
    }

}
