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
    private static final int MIN_MARGIN = -10000;
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
    private HashMap<WebElement, String> marginLeftElements = new HashMap<>();
    private HashMap<WebElement, String> marginRightElements = new HashMap<>();
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
    public ResponsiveValidator notOverlapWith(WebElement element, String readableName) {
        overlapElements.put(element, readableName);
        return this;
    }

    @Override
    public ResponsiveValidator sameMarginLeftAs(WebElement element, String readableName) {
        marginLeftElements.put(element, readableName);
        return this;
    }

    @Override
    public ResponsiveValidator sameMarginRighttAs(WebElement element, String readableName) {
        marginRightElements.put(element, readableName);
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
        json.put(ERROR_KEY, false);

        if (rootElement != null) {
            JSONArray errorMessage = new JSONArray();

            BufferedImage img = null;
            File map = null;
            Graphics g = null;

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
            if (minWidth > MIN_MARGIN) {
                if (widthRoot < minWidth) {
                    errorMessage.add(String.format("Expected min width of element '%s' is: %spx. Actual width is: %spx", rootElementReadableName, minWidth, widthRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (maxWidth > MIN_MARGIN) {
                if (widthRoot > maxWidth) {
                    errorMessage.add(String.format("Expected max width of element '%s' is: %spx. Actual width is: %spx", rootElementReadableName, maxWidth, widthRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (minHeight > MIN_MARGIN) {
                if (heightRoot < minHeight) {
                    errorMessage.add(String.format("Expected min height of element '%s' is: %spx. Actual height is: %spx", rootElementReadableName, minHeight, heightRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (maxHeight > MIN_MARGIN) {
                if (heightRoot > maxHeight) {
                    errorMessage.add(String.format("Expected max height of element  '%s' is: %spx. Actual height is: %spx", rootElementReadableName, maxHeight, heightRoot));
                    if (drawMap) {
                        drawElementRect(g, Color.RED, rootElement);
                    }
                }
            }
            if (minTopMargin > MIN_MARGIN && minRightMargin > MIN_MARGIN && minBottomMargin > MIN_MARGIN && minLeftMargin > MIN_MARGIN) {

                if (xRoot < minLeftMargin) {
                    errorMessage.add(String.format("Expected min left margin of element  '%s' is: %spx. Actual left margin is: %spx", rootElementReadableName, minLeftMargin, xRoot));
                }
                if (yRoot < minTopMargin) {
                    errorMessage.add(String.format("Expected min top margin of element  '%s' is: %spx. Actual top margin is: %spx", rootElementReadableName, minTopMargin, yRoot));
                }
                if (elementRightMargin < minRightMargin) {
                    errorMessage.add(String.format("Expected min top margin of element  '%s' is: %spx. Actual right margin is: %spx", rootElementReadableName, minRightMargin, elementRightMargin));
                }
                if (elementBottomMargin < minBottomMargin) {
                    errorMessage.add(String.format("Expected min bottom margin of element  '%s' is: %spx. Actual bottom margin is: %spx", rootElementReadableName, minBottomMargin, elementBottomMargin));
                }
            }
            if (maxTopMargin > MIN_MARGIN && maxRightMargin > MIN_MARGIN && maxBottomMargin > MIN_MARGIN && maxLeftMargin > MIN_MARGIN) {
                if (xRoot > maxLeftMargin) {
                    errorMessage.add(String.format("Expected max left margin of element  '%s' is: %spx. Actual left margin is: %spx", rootElementReadableName, maxLeftMargin, xRoot));
                }
                if (yRoot > maxTopMargin) {
                    errorMessage.add(String.format("Expected max top margin of element '%s' is: %spx. Actual top margin is: %spx", rootElementReadableName, maxTopMargin, yRoot));
                }
                if (elementRightMargin > maxRightMargin) {
                    errorMessage.add(String.format("Expected max right margin of element  '%s' is: %spx. Actual right margin is: %spx", rootElementReadableName, maxRightMargin, elementRightMargin));
                }
                if (elementBottomMargin > maxBottomMargin) {
                    errorMessage.add(String.format("Expected max bottom margin of element  '%s' is: %spx. Actual bottom margin is: %spx", rootElementReadableName, maxBottomMargin, elementBottomMargin));
                }
            }
            if (!overlapElements.isEmpty()) {
                for (Map.Entry<WebElement, String> entry : overlapElements.entrySet()) {
                    if (elementsAreOverlapped(rootElement, entry.getKey())) {
                        errorMessage.add(String.format("Element '%s' is overlapped with element '%s' but should not", rootElementReadableName, entry.getValue()));
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

    private boolean elementsAreOverlapped(WebElement rootElement, WebElement elementOverlapWith) {
        Point elLoc = elementOverlapWith.getLocation();
        Dimension elSize = elementOverlapWith.getSize();
        return (xRoot > elLoc.x && yRoot > elLoc.y && xRoot < elLoc.x + elSize.width && yRoot < elLoc.y + elSize.height)
                || (xRoot + widthRoot > elLoc.x && yRoot > elLoc.y && xRoot + widthRoot < elLoc.x + elSize.width && yRoot < elLoc.y + elSize.height)
                || (xRoot > elLoc.x && yRoot + heightRoot > elLoc.y && xRoot < elLoc.x + elSize.width && yRoot + heightRoot < elLoc.y + elSize.height)
                || (xRoot + widthRoot > elLoc.x && yRoot + heightRoot > elLoc.y && xRoot + widthRoot < elLoc.x + elSize.width && yRoot + widthRoot < elLoc.y + elSize.height);
    }

    private void drawElementRect(Graphics g, Color color, WebElement element) {
        g.setColor(color);
        g.drawRect(element.getLocation().x, element.getLocation().y, element.getSize().width, element.getSize().height);
    }

}
