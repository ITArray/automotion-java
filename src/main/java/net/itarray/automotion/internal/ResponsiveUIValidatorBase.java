package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.tools.helpers.Helper;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.Units;
import org.json.simple.JSONObject;
import org.openqa.selenium.Dimension;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import static net.itarray.automotion.tools.environment.EnvironmentFactory.*;
import static net.itarray.automotion.tools.general.SystemHelper.isRetinaDisplay;
import static net.itarray.automotion.validation.Constants.*;

public abstract class ResponsiveUIValidatorBase {

    protected final Errors errors;
    private final long startTime;
    private final UISnapshot snapshot;
    private final DriverFacade driver;
    private final double zoomFactor;
    protected final UIElement page;

    protected ResponsiveUIValidatorBase(UISnapshot snapshot) {
        this.snapshot = snapshot;
        this.driver = snapshot.getResponsiveUIValidator().getDriver();
        this.errors = new Errors();
        this.zoomFactor = snapshot.getZoomFactor();
        Dimension dimension = this.driver.retrievePageSize();
        page = UIElement.asElement(new net.itarray.automotion.internal.geometry.Rectangle(0, 0, dimension.getWidth(), dimension.getHeight()), "page");
        this.startTime = System.currentTimeMillis();
    }

    public Errors getErrors() {
        return errors;
    }

    public DriverFacade getDriver() {
        return driver;
    }

    public Units getUnits() {
        return getReport().getUnits();
    }

    protected ResponsiveUIValidator getReport() {
        return snapshot.getResponsiveUIValidator();
    }


    public boolean isWithReport() {
        return getReport().isWithReport();
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units)} ()}
     */
    @Deprecated()
    protected ResponsiveUIValidatorBase setUnits(Units units) {
        snapshot.getResponsiveUIValidator().changeMetricsUnitsTo(units);
        return this;
    }

    public ResponsiveUIValidatorBase drawMap() {
        getReport().drawMap();
        return this;
    }

    public ResponsiveUIValidatorBase dontDrawMap() {
        getReport().dontDrawMap();
        return this;
    }

    protected void addError(String message) {
        errors.add(message);
    }

    public boolean validate() {

        if (errors.hasMessages()) {
            compileValidationReport();
        }

        return !errors.hasMessages();
    }

    protected abstract String getNameOfToBeValidated();

    private void compileValidationReport() {
        if (!isWithReport()) {
            return;
        }

        DrawableScreenshot screenshot = new DrawableScreenshot(getDriver(), getTransform(), getDrawingConfiguration());

        drawRootElement(screenshot);

        drawOffsets(screenshot);

        screenshot.drawScreenshot(getNameOfToBeValidated(), errors);

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
            factor = zoomFactor;
            if (isRetinaDisplay() && isChrome()) {
                factor = factor * 2;
            }
        }
        return factor;
    }

    protected int getConvertedInt(int i, boolean horizontal) {
        if (getUnits().equals(net.itarray.automotion.validation.Units.PX)) {
            return i;
        } else {
            if (horizontal) {
                return (i * page.getExtend(Direction.RIGHT).getValue()) / 100;
            } else {
                return (i * page.getExtend(Direction.DOWN).getValue()) / 100;
            }
        }
    }

    private int getYOffset() {
        if (isMobile() && getDriver().isAppiumWebContext() && getReport().isMobileTopBarOffset()) {
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

        jsonResults.put(SCENARIO, snapshot.getDescription());
        jsonResults.put(ROOT_ELEMENT, rootDetails);
        jsonResults.put(TIME_EXECUTION, String.valueOf(System.currentTimeMillis() - startTime) + " milliseconds");
        jsonResults.put(ELEMENT_NAME, getNameOfToBeValidated());
        jsonResults.put(SCREENSHOT, drawableScreenshot.getOutput().getName());
        jsonResults.put(DRAWINGS, drawableScreenshot.getDrawingsOutput().getName());

        long ms = System.currentTimeMillis();
        String uuid = Helper.getGeneratedStringWithLength(7);
        String jsonFileName = getNameOfToBeValidated().replace(" ", "") + "-automotion" + ms + uuid + ".json";
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
        getReport().addJsonFile(jsonFileName);
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
        getReport().setTopBarMobileOffset(state);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setColorForRootElement(java.awt.Color)}
     */
    @Deprecated()
    public void setColorForRootElement(Color color) {
        getReport().setColorForRootElement(color);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setColorForHighlightedElements(java.awt.Color)}
     */
    @Deprecated()
    public void setColorForHighlightedElements(Color color) {
        getReport().setColorForHighlightedElements(color);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setLinesColor(java.awt.Color)}
     */
    @Deprecated()
    public void setLinesColor(Color color) {
        getReport().setLinesColor(color);
    }

    public DrawingConfiguration getDrawingConfiguration() {
        return getReport().getDrawingConfiguration();
    }

}
