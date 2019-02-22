package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.geometry.Vector;
import net.itarray.automotion.tools.helpers.Helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static net.itarray.automotion.validation.Constants.TARGET_AUTOMOTION_IMG;

public class DrawableScreenshot {

    private final DrawingConfiguration drawingConfiguration;
    private final Vector extend;
    private TransformedGraphics graphics;
    private File screenshotName;
    private File drawingsOutput;
    private BufferedImage drawings;

    public DrawableScreenshot(Vector extend, SimpleTransform transform, DrawingConfiguration drawingConfiguration, String rootElementReadableName, File screenshotName) {
        this.drawingConfiguration = drawingConfiguration;
        this.screenshotName = screenshotName;
        File imgFolder = new File(TARGET_AUTOMOTION_IMG);
        if (!imgFolder.exists()) {
            imgFolder.mkdir();
        }
        drawingsOutput = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replaceAll("[\\W]|_", "") + "-draw-" + System.currentTimeMillis() + Helper.getGeneratedStringWithLength(7) + ".png");

        try {
            this.extend = extend;
            drawings = new BufferedImage(extend.getX().intValue(), extend.getY().intValue(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = drawings.createGraphics();

            graphics = new TransformedGraphics(g2d, transform);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create screenshot file: " + screenshotName, e);
        }
    }

    public static File takeScreenshot(DriverFacade driver, String rootElementReadableName) {
        File screenshotName = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replaceAll("[\\W]|_", "") + "-" + System.currentTimeMillis() + Helper.getGeneratedStringWithLength(7) + ".png");
        driver.takeScreenshot(screenshotName);
        return screenshotName;
    }

    public File getScreenshotName() {
        return screenshotName;
    }

    public File getDrawingsOutput() {
        return drawingsOutput;
    }

    public void drawVerticalLine(Scalar x) {
        drawingConfiguration.setLinesStyle(graphics);
        graphics.drawVerticalLine(x.intValue(), extend.getY().intValue());
    }

    public void drawHorizontalLine(Scalar y) {
        drawingConfiguration.setLinesStyle(graphics);
        graphics.drawHorizontalLine(y.intValue(), extend.getX().intValue());
    }

    public void saveDrawing() {
        try {
            if (drawings != null && drawingsOutput != null) {
                ImageIO.write(drawings, "png", drawingsOutput);
            }
        } catch (NullPointerException | IOException ignored) {}

        if (drawings != null) {
            drawings.getGraphics().dispose();
        }
    }

    public void drawRoot(UIElement rootElement) {
        drawingConfiguration.setRootElementStyle(graphics);
        basicDraw(rootElement);
    }

    public void draw(UIElement element) {
        drawingConfiguration.setHighlightedElementStyle(graphics);
        basicDraw(element);
    }

    private void basicDraw(UIElement element) {
        int x = element.getOrigin().getX().intValue();
        int y = element.getOrigin().getY().intValue();
        int width = element.getWidth().intValue();
        int height = element.getHeight().intValue();
        graphics.drawRectByExtend(x, y, width, height);
    }
}
