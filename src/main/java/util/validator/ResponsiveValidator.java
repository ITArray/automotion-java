package util.validator;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import util.driver.PageValidator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResponsiveValidator implements Validator {

    private static final int MIN_MARGIN = -10000;
    private WebDriver driver;
    private String rootElementReadableName;
    private WebElement rootElement;
    private WebElement leftElement;
    private WebElement rightElement;
    private WebElement aboveElement;
    private WebElement belowElement;
    private WebElement containerElement;
    private List<WebElement> overlapElements = new ArrayList<>();
    private int minWidth,
            maxWidth,
            minHeight,
            maxHeight,
            minTopMargin,
            minRightMargin,
            minBottomMargin,
            minLeftMargin,
            maxTopMargin,
            maxRightMargin,
            maxBottomMargin,
            maxLeftMargin = MIN_MARGIN;
    private int xRoot;
    private int yRoot;
    private int widthRoot;
    private int heightRoot;
    private boolean drawMap = false;
    private int pageWidth;
    private int pageHeight;
    private int elementRightMargin;
    private int elementBottomMargin;

    public ResponsiveValidator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public ResponsiveValidator rootElement(WebElement element, String readableNameOfElement) {
        rootElement = element;
        rootElementReadableName = readableNameOfElement;
        xRoot = rootElement.getLocation().getX();
        yRoot = rootElement.getLocation().getY();
        widthRoot = rootElement.getSize().getWidth();
        heightRoot = rootElement.getSize().getHeight();
        pageWidth = driver.manage().window().getSize().getWidth();
        pageHeight = driver.manage().window().getSize().getHeight();
        elementRightMargin = pageWidth - xRoot + widthRoot;
        elementBottomMargin = pageHeight - yRoot + heightRoot;
        return this;
    }

    @Override
    public ResponsiveValidator leftElement(WebElement element) {
        leftElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator rightElement(WebElement element) {
        rightElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator aboveElement(WebElement element) {
        aboveElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator belowElement(WebElement element) {
        belowElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator inside(WebElement element) {
        containerElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator notOverlapWith(WebElement element) {
        overlapElements.add(element);
        throw new UnsupportedCommandException();
    }

    @Override
    public ResponsiveValidator minWidth(int width) {
        minWidth = width;
        return this;
    }

    @Override
    public ResponsiveValidator maxWidth(int width) {
        maxWidth = width;
        return this;
    }

    @Override
    public ResponsiveValidator minHeight(int height) {
        minHeight = height;
        return this;
    }

    @Override
    public ResponsiveValidator maxHeight(int height) {
        maxHeight = height;
        return this;
    }

    @Override
    public ResponsiveValidator minMargin(int top, int right, int bottom, int left) {
        minTopMargin = top;
        minRightMargin = right;
        minBottomMargin = bottom;
        minLeftMargin = left;
        return this;
    }

    @Override
    public ResponsiveValidator maxMargin(int top, int right, int bottom, int left) {
        maxTopMargin = top;
        maxRightMargin = right;
        maxBottomMargin = bottom;
        maxLeftMargin = left;
        return this;
    }

    @Override
    public ResponsiveValidator drawMap() {
        drawMap = true;
        return this;
    }

    @Override
    public JSONObject validate() {
        JSONObject json = new JSONObject();
        json.put("error", false);

        if (rootElement != null) {
            JSONArray errorMessage = new JSONArray();

            BufferedImage img = null;
            File map = null;
            Graphics g = null;

            if (drawMap) {
                try {
                    map = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    img = ImageIO.read(map);
                } catch (Exception ignored) {
                }

                g = img.createGraphics();
            }

            if (leftElement != null) {
                List<WebElement> elements = new ArrayList<>();
                elements.add(leftElement);
                elements.add(rootElement);

                if (!PageValidator.elementsAreAlignedHorizontally(elements)) {
                    errorMessage.add("Left element aligned not properly");
                    if (drawMap) {
                        drawElementRect(g, Color.RED, leftElement);
                    }
                }
            }
            if (rightElement != null) {
                List<WebElement> elements = new ArrayList<>();
                elements.add(rootElement);
                elements.add(rightElement);

                if (!PageValidator.elementsAreAlignedHorizontally(elements)) {
                    errorMessage.add("Right element aligned not properly");
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rightElement);
                    }
                }
            }
            if (aboveElement != null) {
                List<WebElement> elements = new ArrayList<>();
                elements.add(aboveElement);
                elements.add(rootElement);

                if (!PageValidator.elementsAreAlignedVertically(elements)) {
                    errorMessage.add("Above element aligned not properly");
                    if (drawMap) {
                        drawElementRect(g, Color.RED, aboveElement);
                    }
                }
            }
            if (belowElement != null) {
                List<WebElement> elements = new ArrayList<>();
                elements.add(rootElement);
                elements.add(belowElement);

                if (!PageValidator.elementsAreAlignedVertically(elements)) {
                    errorMessage.add("Below element aligned not properly");
                    if (drawMap) {
                        drawElementRect(g, Color.RED, belowElement);
                    }
                }
            }
            if (containerElement != null) {
                float xContainer = containerElement.getLocation().getX();
                float yContainer = containerElement.getLocation().getY();
                float widthContainer = containerElement.getSize().getWidth();
                float heightContainer = containerElement.getSize().getHeight();

                if (xRoot < xContainer || yRoot < yContainer || (xRoot + widthRoot) > (xContainer + widthContainer) || (yRoot + heightRoot) > (yContainer + heightContainer)) {
                    errorMessage.add("Element \"" + rootElementReadableName + "\" is not inside of container");
                    if (drawMap) {
                        drawElementRect(g, Color.RED, containerElement);
                        drawElementRect(g, Color.GREEN, rootElement);
                    }
                }
            }
            if (minWidth > MIN_MARGIN) {
                if (widthRoot < minWidth) {
                    errorMessage.add(String.format("Expected min width of element \"" + rootElementReadableName + "\" is: %spx. Actual width is: %spx", minWidth, widthRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (maxWidth > MIN_MARGIN) {
                if (widthRoot > maxWidth) {
                    errorMessage.add(String.format("Expected max width of element  \"" + rootElementReadableName + "\" is: %spx. Actual width is: %spx", maxWidth, widthRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (minHeight > MIN_MARGIN) {
                if (heightRoot < minHeight) {
                    errorMessage.add(String.format("Expected min height of element \"" + rootElementReadableName + "\" is: %spx. Actual height is: %spx", minHeight, heightRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (maxHeight > MIN_MARGIN) {
                if (heightRoot > maxHeight) {
                    errorMessage.add(String.format("Expected max height of element  \"" + rootElementReadableName + "\" is: %spx. Actual height is: %spx", maxHeight, heightRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (minTopMargin > MIN_MARGIN && minRightMargin > MIN_MARGIN && minBottomMargin > MIN_MARGIN && minLeftMargin > MIN_MARGIN) {

                if (xRoot < minLeftMargin){
                    errorMessage.add(String.format("Expected min left margin of element  \"" + rootElementReadableName + "\" is: %spx. Actual left margin is: %spx", minLeftMargin, xRoot));
                }
                if (yRoot < minTopMargin){
                    errorMessage.add(String.format("Expected min top margin of element  \"" + rootElementReadableName + "\" is: %spx. Actual top margin is: %spx", minTopMargin, yRoot));
                }
                if (elementRightMargin < minRightMargin){
                    errorMessage.add(String.format("Expected min top margin of element  \"" + rootElementReadableName + "\" is: %spx. Actual right margin is: %spx", minRightMargin, elementRightMargin));
                }
                if (elementBottomMargin < minBottomMargin){
                    errorMessage.add(String.format("Expected min bottom margin of element  \"" + rootElementReadableName + "\" is: %spx. Actual bottom margin is: %spx", minBottomMargin, elementBottomMargin));
                }
            }
            if (maxTopMargin > MIN_MARGIN && maxRightMargin > MIN_MARGIN && maxBottomMargin > MIN_MARGIN && maxLeftMargin > MIN_MARGIN) {
                if (xRoot > maxLeftMargin){
                    errorMessage.add(String.format("Expected max left margin of element  \"" + rootElementReadableName + "\" is: %spx. Actual left margin is: %spx", maxLeftMargin, xRoot));
                }
                if (yRoot > maxTopMargin){
                    errorMessage.add(String.format("Expected max top margin of element  \"" + rootElementReadableName + "\" is: %spx. Actual top margin is: %spx", maxTopMargin, yRoot));
                }
                if (elementRightMargin > maxRightMargin){
                    errorMessage.add(String.format("Expected max right margin of element  \"" + rootElementReadableName + "\" is: %spx. Actual right margin is: %spx", maxRightMargin, elementRightMargin));
                }
                if (elementBottomMargin > maxBottomMargin){
                    errorMessage.add(String.format("Expected max bottom margin of element  \"" + rootElementReadableName + "\" is: %spx. Actual bottom margin is: %spx", maxBottomMargin, elementBottomMargin));
                }
            }

            if (!errorMessage.isEmpty()) {
                json.put("error", true);
                json.put("reason", errorMessage);
            }

            if (drawMap) {
                if (img != null) {
                    try {
                        g.setColor(Color.WHITE);
                        ImageIO.write(img, "png", map);
                        File file = new File("target/" + map.getName());
                        FileUtils.copyFile(map, file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            json.put("error", true);
            json.put("reason", "Set root web element");
        }

        return json;
    }

    private void drawElementRect(Graphics g, Color color, WebElement element) {
        g.setColor(color);
        g.drawRect(element.getLocation().x, element.getLocation().y, element.getSize().width, element.getSize().height);
    }

}
