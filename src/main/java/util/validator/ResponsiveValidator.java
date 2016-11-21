package util.validator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import util.driver.PageValidator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponsiveValidator implements Validator {

    private final static Logger LOG = Logger.getLogger(ResponsiveValidator.class);
    private static final int MIN_OFFSET = -10000;
    private static final String ERROR_KEY = "error";
    private static final String REASON_KEY = "reason";
    private WebDriver driver;
    private String rootElementReadableName;
    private WebElement rootElement;
    private WebElement leftElement;
    private WebElement rightElement;
    private WebElement aboveElement;
    private WebElement belowElement;
    private WebElement containerElement;
    private HashMap<WebElement, String> overlapElements = new HashMap<>();
    private HashMap<WebElement, String> offsetLeftElements = new HashMap<>();
    private HashMap<WebElement, String> offsetRightElements = new HashMap<>();
    private int minWidth,
            maxWidth,
            minHeight,
            maxHeight,
            minTopOffset,
            minRightOffset,
            minBottomOffset,
            minLeftOffset,
            maxTopOffset,
            maxRightOffset,
            maxBottomOffset,
            maxLeftOffset = MIN_OFFSET;
    private int xRoot;
    private int yRoot;
    private int widthRoot;
    private int heightRoot;
    private boolean drawMap = false;
    private int pageWidth;
    private int pageHeight;
    private int elementRightOffset;
    private int elementBottomOffset;

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
        elementRightOffset = pageWidth - xRoot + widthRoot;
        elementBottomOffset = pageHeight - yRoot + heightRoot;
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
    public ResponsiveValidator notOverlapWith(WebElement element, String readableName) {
        overlapElements.put(element, readableName);
        return this;
    }

    @Override
    public ResponsiveValidator sameOffsetLeftAs(WebElement element, String readableName) {
        offsetLeftElements.put(element, readableName);
        return this;
    }

    @Override
    public ResponsiveValidator sameOffsetRightAs(WebElement element, String readableName) {
        offsetRightElements.put(element, readableName);
        return this;
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
    public ResponsiveValidator minOffset(int top, int right, int bottom, int left) {
        minTopOffset = top;
        minRightOffset = right;
        minBottomOffset = bottom;
        minLeftOffset = left;
        return this;
    }

    @Override
    public ResponsiveValidator maxOffset(int top, int right, int bottom, int left) {
        maxTopOffset = top;
        maxRightOffset = right;
        maxBottomOffset = bottom;
        maxLeftOffset = left;
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
        json.put(ERROR_KEY, false);

        if (rootElement != null) {
            JSONArray errorMessage = new JSONArray();

            BufferedImage img = null;
            File map = null;
            Graphics2D g = null;

            if (drawMap) {
                try {
                    map = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    img = ImageIO.read(map);
                } catch (Exception e) {
                    LOG.error("Failed to create map file: " + e.getMessage());
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
            if (minWidth > MIN_OFFSET) {
                if (widthRoot < minWidth) {
                    errorMessage.add(String.format("Expected min width of element '%s' is: %spx. Actual width is: %spx", rootElementReadableName, minWidth, widthRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (maxWidth > MIN_OFFSET) {
                if (widthRoot > maxWidth) {
                    errorMessage.add(String.format("Expected max width of element '%s' is: %spx. Actual width is: %spx", rootElementReadableName, maxWidth, widthRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (minHeight > MIN_OFFSET) {
                if (heightRoot < minHeight) {
                    errorMessage.add(String.format("Expected min height of element '%s' is: %spx. Actual height is: %spx", rootElementReadableName, minHeight, heightRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (maxHeight > MIN_OFFSET) {
                if (heightRoot > maxHeight) {
                    errorMessage.add(String.format("Expected max height of element  '%s' is: %spx. Actual height is: %spx", rootElementReadableName, maxHeight, heightRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (minTopOffset > MIN_OFFSET && minRightOffset > MIN_OFFSET && minBottomOffset > MIN_OFFSET && minLeftOffset > MIN_OFFSET) {

                if (xRoot < minLeftOffset) {
                    errorMessage.add(String.format("Expected min left offset of element  '%s' is: %spx. Actual left offset is: %spx", rootElementReadableName, minLeftOffset, xRoot));
                }
                if (yRoot < minTopOffset) {
                    errorMessage.add(String.format("Expected min top offset of element  '%s' is: %spx. Actual top offset is: %spx", rootElementReadableName, minTopOffset, yRoot));
                }
                if (elementRightOffset < minRightOffset) {
                    errorMessage.add(String.format("Expected min top offset of element  '%s' is: %spx. Actual right offset is: %spx", rootElementReadableName, minRightOffset, elementRightOffset));
                }
                if (elementBottomOffset < minBottomOffset) {
                    errorMessage.add(String.format("Expected min bottom offset of element  '%s' is: %spx. Actual bottom offset is: %spx", rootElementReadableName, minBottomOffset, elementBottomOffset));
                }
            }
            if (maxTopOffset > MIN_OFFSET && maxRightOffset > MIN_OFFSET && maxBottomOffset > MIN_OFFSET && maxLeftOffset > MIN_OFFSET) {
                if (xRoot > maxLeftOffset) {
                    errorMessage.add(String.format("Expected max left offset of element  '%s' is: %spx. Actual left offset is: %spx", rootElementReadableName, maxLeftOffset, xRoot));
                }
                if (yRoot > maxTopOffset) {
                    errorMessage.add(String.format("Expected max top offset of element '%s' is: %spx. Actual top offset is: %spx", rootElementReadableName, maxTopOffset, yRoot));
                }
                if (elementRightOffset > maxRightOffset) {
                    errorMessage.add(String.format("Expected max right offset of element  '%s' is: %spx. Actual right offset is: %spx", rootElementReadableName, maxRightOffset, elementRightOffset));
                }
                if (elementBottomOffset > maxBottomOffset) {
                    errorMessage.add(String.format("Expected max bottom offset of element  '%s' is: %spx. Actual bottom offset is: %spx", rootElementReadableName, maxBottomOffset, elementBottomOffset));
                }
            }
            if (!overlapElements.isEmpty()) {
                for (Map.Entry<WebElement, String> entry : overlapElements.entrySet()) {
                    if (elementsAreOverlapped(entry.getKey())) {
                        errorMessage.add(String.format("Element '%s' is overlapped with element '%s' but should not", rootElementReadableName, entry.getValue()));
                        if (drawMap) {
                            drawElementRect(g, Color.RED, rootElement);
                            drawElementRect(g, Color.MAGENTA, entry.getKey());
                        }
                    }
                }
            }
            if (!offsetLeftElements.isEmpty()){
                for (Map.Entry<WebElement, String> entry : offsetLeftElements.entrySet()) {
                        if (!elementsHasEqualOffset(true, entry.getKey())) {
                            errorMessage.add(String.format("Element '%s' has not the same left offset as element '%s'", rootElementReadableName, entry.getValue()));
                            if (drawMap) {
                                drawElementRect(g, Color.RED, rootElement);
                                drawElementRect(g, Color.MAGENTA, entry.getKey());
                            }
                        }
                }
            }
            if (!offsetRightElements.isEmpty()){
                for (Map.Entry<WebElement, String> entry : offsetRightElements.entrySet()) {
                    if (!elementsHasEqualOffset(false, entry.getKey())) {
                        errorMessage.add(String.format("Element '%s' has not the same right offset as element '%s'", rootElementReadableName, entry.getValue()));
                        if (drawMap) {
                            drawElementRect(g, Color.RED, rootElement);
                            drawElementRect(g, Color.MAGENTA, entry.getKey());
                        }
                    }
                }
            }

            if (!errorMessage.isEmpty()) {
                json.put(ERROR_KEY, true);
                json.put(REASON_KEY, errorMessage);
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
            json.put(ERROR_KEY, true);
            json.put(REASON_KEY, "Set root web element");
        }

        return json;
    }

    private boolean elementsAreOverlapped(WebElement elementOverlapWith) {
        Point elLoc = elementOverlapWith.getLocation();
        Dimension elSize = elementOverlapWith.getSize();
        return (xRoot > elLoc.x && yRoot > elLoc.y && xRoot < elLoc.x + elSize.width && yRoot < elLoc.y + elSize.height)
                || (xRoot + widthRoot > elLoc.x && yRoot > elLoc.y && xRoot + widthRoot < elLoc.x + elSize.width && yRoot < elLoc.y + elSize.height)
                || (xRoot > elLoc.x && yRoot + heightRoot > elLoc.y && xRoot < elLoc.x + elSize.width && yRoot + heightRoot < elLoc.y + elSize.height)
                || (xRoot + widthRoot > elLoc.x && yRoot + heightRoot > elLoc.y && xRoot + widthRoot < elLoc.x + elSize.width && yRoot + widthRoot < elLoc.y + elSize.height);
    }

    private boolean elementsHasEqualOffset(boolean isLeft, WebElement elementToCompare){
        Point elLoc = elementToCompare.getLocation();
        Dimension elSize = elementToCompare.getSize();

        if (isLeft){
            return Math.abs(xRoot - elLoc.getX()) == 3;
        }else{
            return Math.abs((pageWidth - xRoot + widthRoot) - (pageWidth - elLoc.getX() + elSize.getWidth())) == 3;
        }
    }

    private void drawElementRect(Graphics2D g, Color color, WebElement element) {
        g.setColor(color);
        g.setStroke(new BasicStroke(3));
        g.drawRect(element.getLocation().x, element.getLocation().y, element.getSize().width, element.getSize().height);
    }

}
