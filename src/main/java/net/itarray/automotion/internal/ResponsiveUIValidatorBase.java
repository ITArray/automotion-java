package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Vector;
import net.itarray.automotion.tools.helpers.Helper;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.Units;
import org.json.simple.JSONObject;
import org.openqa.selenium.Dimension;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import static net.itarray.automotion.validation.Constants.*;

public abstract class ResponsiveUIValidatorBase {

    protected final Errors errors;
    protected final UIElement page;
    private final long startTime;
    protected final UISnapshot snapshot;
    private final DriverFacade driver;
    private final double zoomFactor;
    protected DrawableScreenshot drawableScreenshot;

    protected ResponsiveUIValidatorBase(UISnapshot snapshot) {
        this.snapshot = snapshot;
        this.driver = snapshot.getResponsiveUIValidator().getDriver();
        this.errors = new Errors();
        this.zoomFactor = snapshot.getZoomFactor();
        Dimension dimension = this.driver.retrievePageSize();
        this.page = UIElement.asElement(new net.itarray.automotion.internal.geometry.Rectangle(0, 0, dimension.getWidth(), dimension.getHeight()), "page");
        this.startTime = System.currentTimeMillis();
    }

    protected void doSnapshot() {
        File screenshotName = snapshot.takeScreenshot();
        Vector extend = driver.getExtend(screenshotName);
        this.drawableScreenshot = new DrawableScreenshot(extend, getTransform(), getDrawingConfiguration(), getNameOfToBeValidated(), screenshotName);
        if (isWithReport()) {
            drawRootElement(drawableScreenshot);
        }

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

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units)} ()}
     */
    @Deprecated()
    protected ResponsiveUIValidatorBase setUnits(Units units) {
        snapshot.getResponsiveUIValidator().changeMetricsUnitsTo(units);
        return this;
    }

    protected ResponsiveUIValidator getReport() {
        return snapshot.getResponsiveUIValidator();
    }

    public boolean isWithReport() {
        return getReport().isWithReport();
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
        if (isWithReport()) {
            drawOffsets(drawableScreenshot);
        }

        if (isWithReport()) {
            for (Object obj : errors.getMessages()) {
                JSONObject det = (JSONObject) obj;
                JSONObject details = (JSONObject) det.get(REASON);
                JSONObject numE = (JSONObject) details.get(ELEMENT);

                if (numE != null) {
                    int x = (int) (float) numE.get(X);
                    int y = (int) (float) numE.get(Y);
                    int width = (int) (float) numE.get(WIDTH);
                    int height = (int) (float) numE.get(HEIGHT);

                    drawableScreenshot.drawRectangle(x, y, width, height);
                }
            }
        }

        if (isWithReport()) {
            drawableScreenshot.saveDrawing();
        }

        if (isWithReport()) {
            writeResults(drawableScreenshot);
        }
    }

    private SimpleTransform getTransform() {
        return new SimpleTransform(getYOffset(), getScaleFactor());
    }

    private double getScaleFactor() {
        double factor;
        if (getDriver().isAppiumContext()) {
            factor = getReport().getRetinaScaleFactor();
        } else {
            factor = zoomFactor;
            if (getDriver().isChromeDriver()) {
                factor = factor * getReport().getRetinaScaleFactor();
            }
        }

        return factor;
    }

    private int getYOffset() {
        if (getDriver().isAppiumContext() && getDriver().isAppiumWebContext() && getReport().isMobileTopBarOffsetState()) {
            if (getDriver().isAppiumAndroidContext() || getDriver().isAppiumIOSContext()) {
                return (int) getReport().getMobileTopBarOffsetState();
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
        jsonResults.put(SCREENSHOT, drawableScreenshot.getScreenshotName().getName());
        jsonResults.put(DRAWINGS, drawableScreenshot.getDrawingsOutput().getName());

        long ms = System.currentTimeMillis();
        String uuid = Helper.getGeneratedStringWithLength(7);
        String jsonFileName = getNameOfToBeValidated().replace(" ", "") + "-automotion" + ms + uuid + ".json";
        File jsonFile = new File(TARGET_AUTOMOTION_JSON + jsonFileName);
        jsonFile.getParentFile().mkdirs();
        try (
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(jsonFile), StandardCharsets.UTF_8);
                Writer writer = new BufferedWriter(outputStreamWriter)) {
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
