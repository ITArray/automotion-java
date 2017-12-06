package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Vector;
import net.itarray.automotion.tools.helpers.Helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static net.itarray.automotion.validation.Constants.*;

public class DrawableScreenshot {

    private final DrawingConfiguration drawingConfiguration;
    private TransformedGraphics graphics;
    private File screenshotName;
    private File drawingsOutput;
    private BufferedImage drawings;
    private final Vector extend;

    public DrawableScreenshot(Vector extend, SimpleTransform transform, DrawingConfiguration drawingConfiguration, String rootElementReadableName, File screenshotName) {
        this.drawingConfiguration = drawingConfiguration;
        this.screenshotName = screenshotName;
        drawingsOutput = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replace(" ", "") + "-draw-" + System.currentTimeMillis() + Helper.getGeneratedStringWithLength(7) + ".png");

        try {
            this.extend = extend;
            drawings = new BufferedImage(extend.getX().intValue(), extend.getY().intValue(),
                    BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = drawings.createGraphics();

            graphics = new TransformedGraphics(g2d, transform);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create screenshot file: " + screenshotName, e);
        }
    }

    public static File takeScreenshot(DriverFacade driver, String rootElementReadableName) {
        File screenshotName = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replace(" ", "") + "-" + System.currentTimeMillis() + Helper.getGeneratedStringWithLength(7) + ".png");
        driver.takeScreenshot(screenshotName);
        return screenshotName;
    }

    public File getScreenshotName() {
        return screenshotName;
    }

    public File getDrawingsOutput() {
        return drawingsOutput;
    }

    public void drawLeftOffsetLine(UIElement rootElement, OffsetLineCommands offsetLineCommands) {
        offsetLineCommands.drawLeftOffsetLine(graphics, extend, rootElement, drawingConfiguration);
    }

    public void drawRightOffsetLine(UIElement rootElement, OffsetLineCommands offsetLineCommands) {
        offsetLineCommands.drawRightOffsetLine(graphics, extend, rootElement, drawingConfiguration);
    }

    public void drawTopOffsetLine(UIElement rootElement, OffsetLineCommands offsetLineCommands) {
        offsetLineCommands.drawTopOffsetLine(graphics, extend, rootElement, drawingConfiguration);
    }

    public void drawBottomOffsetLine(UIElement rootElement, OffsetLineCommands offsetLineCommands) {
        offsetLineCommands.drawBottomOffsetLine(graphics, extend, rootElement, drawingConfiguration);
    }

    public void drawRectangle(int x, int y, int width, int height) {
        drawingConfiguration.setHighlightedElementStyle(graphics);
        graphics.drawRectByExtend(x, y, width, height);
    }

    public void saveDrawing() {
        try {
            ImageIO.write(drawings, "png", drawingsOutput);
        } catch (IOException e) {
            throw new RuntimeException("Writing file failed for " + drawingsOutput , e);
        }

        drawings.getGraphics().dispose();
    }

    public void drawRootElement(UIElement rootElement) {
        drawingConfiguration.setRootElementStyle(graphics);
        int x = rootElement.getX().intValue();
        int y = rootElement.getY().intValue();
        graphics.drawRectByExtend(x, y, rootElement.getWidth().intValue(), rootElement.getHeight().intValue());
    }
}
