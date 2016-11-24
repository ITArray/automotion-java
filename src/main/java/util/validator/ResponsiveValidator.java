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
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponsiveValidator implements Validator {

    private static final String X = "x";
    private static final String Y = "y";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final int MIN_OFFSET = -10000;
    private static final String ERROR_KEY = "error";
    private static final String MESSAGE = "message";
    private static final String DETAILS = "details";
    private final Logger LOG = Logger.getLogger(ResponsiveValidator.class);
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
    private HashMap<WebElement, String> offsetTopElements = new HashMap<>();
    private HashMap<WebElement, String> offsetBottomElements = new HashMap<>();
    private int minWidth = MIN_OFFSET;
    private int maxWidth = MIN_OFFSET;
    private int minHeight = MIN_OFFSET;
    private int maxHeight = MIN_OFFSET;
    private int minTopOffset = MIN_OFFSET;
    private int minRightOffset = MIN_OFFSET;
    private int minBottomOffset = MIN_OFFSET;
    private int minLeftOffset = MIN_OFFSET;
    private int maxTopOffset = MIN_OFFSET;
    private int maxRightOffset = MIN_OFFSET;
    private int maxBottomOffset = MIN_OFFSET;
    private int maxLeftOffset = MIN_OFFSET;
    private int xRoot;
    private int yRoot;
    private int widthRoot;
    private int heightRoot;
    private boolean withReport = false;
    private int pageWidth;
    private int pageHeight;
    private int elementRightOffset;
    private int elementBottomOffset;
    private String readableContainerName;
    private JSONObject jsonResults;
    private File map;
    private BufferedImage img;
    private Graphics2D g;
    private JSONArray errorMessage;
    private int counterDetails = 0;

    public ResponsiveValidator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public ResponsiveValidator findElement(WebElement element, String readableNameOfElement) {
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
    public ResponsiveValidator withLeftElement(WebElement element) {
        leftElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator withRightElement(WebElement element) {
        rightElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator withAboveElement(WebElement element) {
        aboveElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator withBelowElement(WebElement element) {
        belowElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator insideOf(WebElement element, String readableContainerName) {
        containerElement = element;
        this.readableContainerName = readableContainerName;
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
    public ResponsiveValidator sameOffsetTopAs(WebElement element, String readableName) {
        offsetTopElements.put(element, readableName);
        return this;
    }

    @Override
    public ResponsiveValidator sameOffsetBottomAs(WebElement element, String readableName) {
        offsetBottomElements.put(element, readableName);
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
    public ResponsiveValidator widthBetween(int min, int max) {
        minWidth = min;
        maxWidth = max;
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
    public ResponsiveValidator heightBetween(int min, int max) {
        minHeight = min;
        maxHeight = max;
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
        withReport = true;
        return this;
    }

    @Override
    public boolean validate() {
        jsonResults = new JSONObject();
        jsonResults.put(ERROR_KEY, false);

        if (rootElement != null) {
            errorMessage = new JSONArray();

            if (leftElement != null) {
                validateLeftElement();
            }
            if (rightElement != null) {
                validateRightElement();
            }
            if (aboveElement != null) {
                validateAboveElement();
            }
            if (belowElement != null) {
                validateBelowElement();
            }
            if (containerElement != null) {
                validateInsideOfContainer();
            }
            if (minWidth > MIN_OFFSET) {
                validateMinWidth();
            }
            if (maxWidth > MIN_OFFSET) {
                validateMaxWidth();
            }
            if (minHeight > MIN_OFFSET) {
                validateMinHeight();
            }
            if (maxHeight > MIN_OFFSET) {
                validateMaxHeight();
            }
            if (minTopOffset > MIN_OFFSET && minRightOffset > MIN_OFFSET && minBottomOffset > MIN_OFFSET && minLeftOffset > MIN_OFFSET) {
                validateMinOffset();
            }
            if (maxTopOffset > MIN_OFFSET && maxRightOffset > MIN_OFFSET && maxBottomOffset > MIN_OFFSET && maxLeftOffset > MIN_OFFSET) {
                validateMaxOffset();
            }
            if (!overlapElements.isEmpty()) {
                validateOverlappingWithElements();
            }
            if (!offsetLeftElements.isEmpty()) {
                validateLeftOffsetForElements();
            }
            if (!offsetRightElements.isEmpty()) {
                validateRightOffsetForElements();
            }
            if (!offsetTopElements.isEmpty()) {
                validateTopOffsetForElements();
            }
            if (!offsetBottomElements.isEmpty()) {
                validateBottomOffsetForElements();
            }

            if (!errorMessage.isEmpty()) {
                jsonResults.put(ERROR_KEY, true);
                jsonResults.put(DETAILS, errorMessage);
            }

            if (withReport) {
                try {
                    map = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    img = ImageIO.read(map);
                } catch (Exception e) {
                    LOG.error("Failed to create map file: " + e.getMessage());
                }

                if (!errorMessage.isEmpty()) {
                    JSONObject rootDetails = new JSONObject();
                    rootDetails.put(X, xRoot);
                    rootDetails.put(Y, yRoot);
                    rootDetails.put(WIDTH, widthRoot);
                    rootDetails.put(HEIGHT, heightRoot);

                    jsonResults.put("rootElement", rootDetails);
                    jsonResults.put("screenshot", map.getName());
                }

                try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("target/automotion/automotion-" + System.currentTimeMillis() + ".json"), StandardCharsets.UTF_8))) {
                    writer.write(jsonResults.toJSONString());
                } catch (IOException ex) {
                    LOG.error("Cannot create json report: " + ex.getMessage());
                }
                try {
                    File file = new File("target/automotion/automotion-" + System.currentTimeMillis() + ".json");
                    if (file.getParentFile().mkdirs()) {
                        if (file.createNewFile()) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                            writer.write(jsonResults.toJSONString());
                            writer.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if ((boolean) jsonResults.get(ERROR_KEY)) {
                    drawScreenshot();
                }
            }
        } else {
            jsonResults.put(ERROR_KEY, true);
            jsonResults.put(DETAILS, "Set root web element");
        }

        return !((boolean) jsonResults.get(ERROR_KEY));
    }

    private void drawScreenshot() {
        g = img.createGraphics();

        drawElementRect(Color.RED, rootElement);

        try {
            ImageIO.write(img, "png", map);
            File file = new File("target/automotion/" + map.getName());
            FileUtils.copyFile(map, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateRightOffsetForElements() {
        for (Map.Entry<WebElement, String> entry : offsetRightElements.entrySet()) {
            if (!elementsHasEqualLeftRightOffset(false, entry.getKey())) {
                putJsonDetailsWithElement(String.format("Element '%s' has not the same right offset as element '%s'", rootElementReadableName, entry.getValue()), entry.getKey());
            }
        }
        offsetRightElements.clear();
    }

    private void validateLeftOffsetForElements() {
        for (Map.Entry<WebElement, String> entry : offsetLeftElements.entrySet()) {
            if (!elementsHasEqualLeftRightOffset(true, entry.getKey())) {
                putJsonDetailsWithElement(String.format("Element '%s' has not the same left offset as element '%s'", rootElementReadableName, entry.getValue()), entry.getKey());
            }
        }
        offsetLeftElements.clear();
    }

    private void validateTopOffsetForElements() {
        for (Map.Entry<WebElement, String> entry : offsetTopElements.entrySet()) {
            if (!elementsHasEqualTopBottomOffset(true, entry.getKey())) {
                putJsonDetailsWithElement(String.format("Element '%s' has not the same top offset as element '%s'", rootElementReadableName, entry.getValue()), entry.getKey());
            }
        }
        offsetTopElements.clear();
    }

    private void validateBottomOffsetForElements() {
        for (Map.Entry<WebElement, String> entry : offsetBottomElements.entrySet()) {
            if (!elementsHasEqualTopBottomOffset(false, entry.getKey())) {
                putJsonDetailsWithElement(String.format("Element '%s' has not the same bottom offset as element '%s'", rootElementReadableName, entry.getValue()), entry.getKey());
            }
        }
        offsetBottomElements.clear();
    }

    private void validateOverlappingWithElements() {
        for (Map.Entry<WebElement, String> entry : overlapElements.entrySet()) {
            if (elementsAreOverlapped(entry.getKey())) {
                putJsonDetailsWithElement(String.format("Element '%s' is overlapped with element '%s' but should not", rootElementReadableName, entry.getValue()), entry.getKey());
            }
        }
        overlapElements.clear();
    }

    private void validateMaxOffset() {
        if (xRoot > maxLeftOffset) {
            putJsonDetailsWithoutElement(String.format("Expected max left offset of element  '%s' is: %spx. Actual left offset is: %spx", rootElementReadableName, maxLeftOffset, xRoot));
        }
        if (yRoot > maxTopOffset) {
            putJsonDetailsWithoutElement(String.format("Expected max top offset of element '%s' is: %spx. Actual top offset is: %spx", rootElementReadableName, maxTopOffset, yRoot));
        }
        if (elementRightOffset > maxRightOffset) {
            putJsonDetailsWithoutElement(String.format("Expected max right offset of element  '%s' is: %spx. Actual right offset is: %spx", rootElementReadableName, maxRightOffset, elementRightOffset));
        }
        if (elementBottomOffset > maxBottomOffset) {
            putJsonDetailsWithoutElement(String.format("Expected max bottom offset of element  '%s' is: %spx. Actual bottom offset is: %spx", rootElementReadableName, maxBottomOffset, elementBottomOffset));
        }
    }

    private void validateMinOffset() {
        if (xRoot < minLeftOffset) {
            putJsonDetailsWithoutElement(String.format("Expected min left offset of element  '%s' is: %spx. Actual left offset is: %spx", rootElementReadableName, minLeftOffset, xRoot));
        }
        if (yRoot < minTopOffset) {
            putJsonDetailsWithoutElement(String.format("Expected min top offset of element  '%s' is: %spx. Actual top offset is: %spx", rootElementReadableName, minTopOffset, yRoot));
        }
        if (elementRightOffset < minRightOffset) {
            putJsonDetailsWithoutElement(String.format("Expected min top offset of element  '%s' is: %spx. Actual right offset is: %spx", rootElementReadableName, minRightOffset, elementRightOffset));
        }
        if (elementBottomOffset < minBottomOffset) {
            putJsonDetailsWithoutElement(String.format("Expected min bottom offset of element  '%s' is: %spx. Actual bottom offset is: %spx", rootElementReadableName, minBottomOffset, elementBottomOffset));
        }
    }

    private void validateMaxHeight() {
        if (heightRoot > maxHeight) {
            putJsonDetailsWithoutElement(String.format("Expected max height of element  '%s' is: %spx. Actual height is: %spx", rootElementReadableName, maxHeight, heightRoot));
        }
    }

    private void validateMinHeight() {
        if (heightRoot < minHeight) {
            putJsonDetailsWithoutElement(String.format("Expected min height of element '%s' is: %spx. Actual height is: %spx", rootElementReadableName, minHeight, heightRoot));
        }
    }

    private void validateMaxWidth() {
        if (widthRoot > maxWidth) {
            putJsonDetailsWithoutElement(String.format("Expected max width of element '%s' is: %spx. Actual width is: %spx", rootElementReadableName, maxWidth, widthRoot));
        }
    }

    private void validateMinWidth() {
        if (widthRoot < minWidth) {
            putJsonDetailsWithoutElement(String.format("Expected min width of element '%s' is: %spx. Actual width is: %spx", rootElementReadableName, minWidth, widthRoot));
        }
    }

    private void validateInsideOfContainer() {
        float xContainer = containerElement.getLocation().getX();
        float yContainer = containerElement.getLocation().getY();
        float widthContainer = containerElement.getSize().getWidth();
        float heightContainer = containerElement.getSize().getHeight();

        if (xRoot < xContainer || yRoot < yContainer || (xRoot + widthRoot) > (xContainer + widthContainer) || (yRoot + heightRoot) > (yContainer + heightContainer)) {
            putJsonDetailsWithElement(String.format("Element '%s' is not inside of '%s'", rootElementReadableName, readableContainerName), containerElement);
        }
    }

    private void validateBelowElement() {
        List<WebElement> elements = new ArrayList<>();
        elements.add(rootElement);
        elements.add(belowElement);

        if (!PageValidator.elementsAreAlignedVertically(elements)) {
            putJsonDetailsWithoutElement("Below element aligned not properly");
        }
    }

    private void validateAboveElement() {
        List<WebElement> elements = new ArrayList<>();
        elements.add(aboveElement);
        elements.add(rootElement);

        if (!PageValidator.elementsAreAlignedVertically(elements)) {
            putJsonDetailsWithoutElement("Above element aligned not properly");
        }
    }

    private void validateRightElement() {
        List<WebElement> elements = new ArrayList<>();
        elements.add(rootElement);
        elements.add(rightElement);

        if (!PageValidator.elementsAreAlignedHorizontally(elements)) {
            putJsonDetailsWithoutElement("Right element aligned not properly");
        }
    }

    private void validateLeftElement() {
        List<WebElement> elements = new ArrayList<>();
        elements.add(leftElement);
        elements.add(rootElement);

        if (!PageValidator.elementsAreAlignedHorizontally(elements)) {
            putJsonDetailsWithoutElement("Left element aligned not properly");
        }
    }

    private boolean elementsAreOverlapped(WebElement elementOverlapWith) {
        Point elLoc = elementOverlapWith.getLocation();
        Dimension elSize = elementOverlapWith.getSize();
        return (xRoot > elLoc.x && yRoot > elLoc.y && xRoot < elLoc.x + elSize.width && yRoot < elLoc.y + elSize.height)
                || (xRoot + widthRoot > elLoc.x && yRoot > elLoc.y && xRoot + widthRoot < elLoc.x + elSize.width && yRoot < elLoc.y + elSize.height)
                || (xRoot > elLoc.x && yRoot + heightRoot > elLoc.y && xRoot < elLoc.x + elSize.width && yRoot + heightRoot < elLoc.y + elSize.height)
                || (xRoot + widthRoot > elLoc.x && yRoot + heightRoot > elLoc.y && xRoot + widthRoot < elLoc.x + elSize.width && yRoot + widthRoot < elLoc.y + elSize.height);
    }

    private boolean elementsHasEqualLeftRightOffset(boolean isLeft, WebElement elementToCompare) {
        Point elLoc = elementToCompare.getLocation();
        Dimension elSize = elementToCompare.getSize();

        if (isLeft) {
            return xRoot == elLoc.getX();
        } else {
            return (pageWidth - xRoot + widthRoot) == (pageWidth - elLoc.getX() + elSize.getWidth());
        }
    }

    private boolean elementsHasEqualTopBottomOffset(boolean isTop, WebElement elementToCompare) {
        Point elLoc = elementToCompare.getLocation();
        Dimension elSize = elementToCompare.getSize();

        if (isTop) {
            return yRoot == elLoc.getY();
        } else {
            return (pageHeight - yRoot + heightRoot) == (pageHeight - elLoc.getY() + elSize.getHeight());
        }
    }

    private void drawElementRect(Color color, WebElement element) {
        g.setColor(color);
        g.setStroke(new BasicStroke(3));
        g.drawRect(element.getLocation().x, element.getLocation().y, element.getSize().width, element.getSize().height);
    }

    private void putJsonDetailsWithoutElement(String message) {
        JSONObject details = new JSONObject();
        JSONObject mes = new JSONObject();
        mes.put(MESSAGE, message);
        details.put(counterDetails, mes);
        errorMessage.add(details);
        counterDetails++;
    }

    private void putJsonDetailsWithElement(String message, WebElement element) {
        float xContainer = element.getLocation().getX();
        float yContainer = element.getLocation().getY();
        float widthContainer = element.getSize().getWidth();
        float heightContainer = element.getSize().getHeight();

        JSONObject details = new JSONObject();
        JSONObject elDetails = new JSONObject();
        elDetails.put(X, xContainer);
        elDetails.put(Y, yContainer);
        elDetails.put(WIDTH, widthContainer);
        elDetails.put(HEIGHT, heightContainer);
        JSONObject mes = new JSONObject();
        mes.put(MESSAGE, message);
        mes.put("element", elDetails);
        details.put(counterDetails, mes);
        errorMessage.add(details);
        counterDetails++;
    }

}
