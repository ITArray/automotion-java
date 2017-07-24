package net.itarray.automotion.internal;

import net.itarray.automotion.tools.helpers.Helper;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static net.itarray.automotion.validation.Constants.*;

public class DrawableScreenshot {

    private final File screenshot;
    private final DriverFacade driver;
    private final DrawingConfiguration drawingConfiguration;
    private BufferedImage img;
    private TransformedGraphics graphics;
    private File output;
    private File drawingsOutput;
    private BufferedImage drawings;

    public DrawableScreenshot(DriverFacade driver, SimpleTransform transform, DrawingConfiguration drawingConfiguration, String rootElementReadableName) {


        this.driver = driver;
        this.drawingConfiguration = drawingConfiguration;
        long ms = System.currentTimeMillis();
        String uuid = Helper.getGeneratedStringWithLength(7);
        this.output = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replace(" ", "")  + "-" + ms + uuid + ".png");
        drawingsOutput = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replace(" ", "") + "-draw-" + ms + uuid + ".png");
        driver.takeScreenshot(output);
        this.screenshot = output;

        try {
            img = ImageIO.read(screenshot);

            drawings = new BufferedImage(img.getWidth(), img.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = drawings.createGraphics();

            graphics = new TransformedGraphics(g2d, transform);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create screenshot file: " + screenshot, e);
        }
    }

    public File getOutput() {
        return output;
    }

    public File getDrawingsOutput() {
        return drawingsOutput;
    }

    public void drawOffsets(UIElement rootElement, OffsetLineCommands offsetLineCommands) {
        offsetLineCommands.draw(graphics, img, rootElement, drawingConfiguration);
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
            throw new RuntimeException("Writing file failed for " + screenshot , e);
        }

        drawings.getGraphics().dispose();

    }

    public void drawRootElement(UIElement rootElement) {
        drawingConfiguration.setRootElementStyle(graphics);
        int x = rootElement.getX();
        int y = rootElement.getY();
        graphics.drawRectByExtend(x, y, rootElement.getWidth(), rootElement.getHeight());
    }
}
