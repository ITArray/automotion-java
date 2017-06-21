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

    private final Errors errors;
    private final Zoom zoom;
    private final long startTime;
    protected final Dimension pageSize;
    private final Scenario scenario;

    protected AbstractValidator(Scenario scenario, DriverFacade driver) {
        super(driver);
        this.scenario = scenario;
        this.errors = new Errors();
        this.zoom = new Zoom(driver);
        this.pageSize = driver.retrievePageSize();
        this.startTime = System.currentTimeMillis();
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
        scenario.drawMap();
        return this;
    }


    protected void addError(String message) {
        errors.add(message);
    }

    protected void addError(String message, Element element) {
        errors.add(message, element);
    }

    @Override
    public boolean validate() {

        if (errors.hasMessages()) {
            compileValidationReport();
        }

        return !errors.hasMessages();
    }

    protected abstract String getRootElementReadableName();

    private void compileValidationReport() {
        if (!isWithReport()) {
            return;
        }

        DrawableScreenshot screenshot = new DrawableScreenshot(driver, getTransform(), getDrawingConfiguration());

        drawRootElement(screenshot);

        drawOffsets(screenshot);

        screenshot.drawScreenshot(getRootElementReadableName(), errors);

        writeResults(screenshot);
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
            factor = zoom.getFactor();
            if (isRetinaDisplay() && isChrome()) {
                factor = factor * 2;
            }
        }
        return factor;
    }

    protected int getConvertedInt(int i, boolean horizontal) {
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

    private int getYOffset() {
        if (isMobile() && driver.isAppiumWebContext() && isMobileTopBarOffset()) {
            if (isIOS() || isAndroid()) {
                return 20;
            }
        }
        return 0;
    }

    protected abstract void storeRootDetails(JSONObject rootDetails);

    private void writeResults(DrawableScreenshot drawableScreenshot) {
        JSONObject jsonResults = new JSONObject();

        jsonResults.put(ERROR_KEY, errors.hasMessages());
        jsonResults.put(DETAILS, errors.getMessages());

        JSONObject rootDetails = new JSONObject();
        storeRootDetails(rootDetails);

        jsonResults.put(SCENARIO, scenario.getName());
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
            throw new RuntimeException("Cannot create json report: " + jsonFile, ex);
        }

        addJsonFile(jsonFileName);
    }

    public void addJsonFile(String jsonFileName) {
        scenario.addJsonFile(jsonFileName);
    }

    protected void drawOffsets(DrawableScreenshot screenshot) {
        throw new RuntimeException("should be overwritten");
    }

    protected void drawRootElement(DrawableScreenshot screenshot) {
        throw new RuntimeException("should be overwritten");
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
