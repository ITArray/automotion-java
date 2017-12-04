package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Vector;
import net.itarray.automotion.tools.helpers.Helper;
import org.json.simple.JSONObject;

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

    public void drawOffsets(UIElement rootElement, OffsetLineCommands offsetLineCommands) {
        offsetLineCommands.draw(graphics, extend, rootElement, drawingConfiguration);
    }

    public void drawScreenshot(String rootElementReadableName, Errors errors) {
        for (Object obj : errors.getMessages()) {
            JSONObject det = (JSONObject) obj;
            JSONObject details = (JSONObject) det.get(REASON);
            JSONObject numE = (JSONObject) details.get(ELEMENT);

            if (numE != null) {
                int x = (int) (float) numE.get(X);
                int y = (int) (float) numE.get(Y);
                int width = (int) (float) numE.get(WIDTH);
                int height = (int) (float) numE.get(HEIGHT);

                drawingConfiguration.setHighlightedElementStyle(graphics);
                graphics.drawRectByExtend(x, y, width, height);
            }
        }




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
