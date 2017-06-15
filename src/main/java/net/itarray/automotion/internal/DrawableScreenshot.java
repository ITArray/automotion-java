package net.itarray.automotion.internal;

import net.itarray.automotion.Element;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import util.validator.ResponsiveUIValidator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static util.validator.Constants.*;

public class DrawableScreenshot {

    private final static Logger LOG = Logger.getLogger(ResponsiveUIValidator.class);

    public File screenshot;
    private BufferedImage img;
    private TransformedGraphics graphics;

    public DrawableScreenshot(DriverFacade driver, SimpleTransform transform) {
        screenshot = driver.takeScreenshot();
        try {
            img = ImageIO.read(screenshot);
            Graphics2D g = img.createGraphics();

            graphics = new TransformedGraphics(g, transform);
        } catch (Exception e) {
            LOG.error("Failed to create screenshot file: " + e.getMessage());;
        }
    }

    public void drawScreenshot(Element rootElement, String rootElementReadableName, Errors errors, OffsetLineCommands offsetLineCommands, DrawingConfiguration drawingConfiguration) {
        if (img != null) {

            drawRootElement(rootElement, drawingConfiguration.rootColor);

            offsetLineCommands.draw(graphics, img, drawingConfiguration.linesColor, rootElement);

            for (Object obj : errors.getMessages()) {
                JSONObject det = (JSONObject) obj;
                JSONObject details = (JSONObject) det.get(REASON);
                JSONObject numE = (JSONObject) details.get(ELEMENT);

                if (numE != null) {
                    int x = (int) (float) numE.get(X);
                    int y = (int) (float) numE.get(Y);
                    int width = (int) (float) numE.get(WIDTH);
                    int height = (int) (float) numE.get(HEIGHT);

                    graphics.setColor(drawingConfiguration.highlightedElementsColor);
                    graphics.setStroke(new BasicStroke(2));
                    graphics.drawRectByExtend(x, y, width, height);
                }
            }

            try {
                ImageIO.write(img, "png", screenshot);
                File file = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replace(" ", "") + "-" + screenshot.getName());
                FileUtils.copyFile(screenshot, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            LOG.error("Taking of screenshot was failed for some reason.");
        }
    }

    private void drawRootElement(Element rootElement, Color rootColor) {
        graphics.setColor(rootColor);
        graphics.setStroke(new BasicStroke(2));
        int x = rootElement.getX();
        int y = rootElement.getY();
        graphics.drawRectByExtend(x, y, rootElement.getWidth(), rootElement.getHeight());
    }

}
