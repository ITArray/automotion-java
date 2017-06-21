package net.itarray.automotion.internal;

import http.helpers.Helper;
import net.itarray.automotion.Element;
import org.json.simple.JSONObject;
import org.openqa.selenium.Dimension;
import util.validator.ResponsiveUIValidator;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import static environment.EnvironmentFactory.*;
import static environment.EnvironmentFactory.isChrome;
import static util.general.SystemHelper.isRetinaDisplay;
import static util.validator.Constants.*;
import static util.validator.Constants.TARGET_AUTOMOTION_JSON;
import static util.validator.ResponsiveUIValidator.Units.PX;

public abstract class AbstractValidator extends ResponsiveUIValidator{

    private final Scenario scenario;
    private final ResponsiveUIValidatorBase base;

    protected AbstractValidator(Scenario scenario, DriverFacade driver, ResponsiveUIValidatorBase base) {
        super(driver);
        this.scenario = scenario;
        this.base = base;
    }

    protected ResponsiveUIValidatorBase getBase() {
        return base;
    }

    @Override
    public boolean isWithReport() {
        return scenario.isWithReport();
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#drawMap()}
     */
    @Deprecated()
    public AbstractValidator drawMap() {
        getBase().drawMap();
        return this;
    }


    protected void addError(String message) {
        getBase().addError(message);
    }

    @Override
    public boolean validate() {
        return getBase().validate();
    }

    public void addJsonFile(String jsonFileName) {
        scenario.addJsonFile(jsonFileName);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setTopBarMobileOffset(boolean)}
     */
    @Deprecated()
    public void setTopBarMobileOffset(boolean state) {
        scenario.setTopBarMobileOffset(state);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setColorForRootElement(java.awt.Color)}
     */
    @Deprecated()
    public void setColorForRootElement(Color color) {
        scenario.setColorForRootElement(color);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setColorForHighlightedElements(java.awt.Color)}
     */
    @Deprecated()
    public void setColorForHighlightedElements(Color color) {
        scenario.setColorForHighlightedElements(color);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setLinesColor(java.awt.Color)}
     */
    @Deprecated()
    public void setLinesColor(Color color) {
        scenario.setLinesColor(color);
    }

    @Override
    public DrawingConfiguration getDrawingConfiguration() {
        return scenario.getDrawingConfiguration();
    }
}
