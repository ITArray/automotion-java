package util.validator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import util.driver.PageValidator;
import util.general.HtmlReportBuilder;
import util.general.SystemHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static environment.EnvironmentFactory.isChrome;

public class ResponsiveValidator implements Validator {

    private static final String X = "x";
    private static final String Y = "y";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final int MIN_OFFSET = -10000;
    private static final String ERROR_KEY = "error";
    private static final String MESSAGE = "message";
    private static final String DETAILS = "details";
    private static final Object REASON = "reason";
    private static final String ELEMENT = "element";
    private static final String TARGET_AUTOMOTION_JSON = "target/automotion/json/";
    private static final String TARGET_AUTOMOTION_IMG = "target/automotion/img/";
    private final Logger LOG = Logger.getLogger(ResponsiveValidator.class);
    private WebDriver driver;
    private String rootElementReadableName;
    private WebElement rootElement;
    private WebElement leftElement;
    private WebElement rightElement;
    private WebElement topElement;
    private WebElement bottomElement;
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
    private File screenshot;
    private BufferedImage img;
    private Graphics2D g;
    private JSONArray errorMessage;
    private int leftMinMargin;
    private int leftMaxMargin;
    private int rightMinMargin;
    private int rightMaxMargin;
    private int topMinMargin;
    private int topMaxMargin;
    private int bottomMinMargin;
    private int bottomMaxMargin;

    public ResponsiveValidator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public ResponsiveValidator init() {
        return new ResponsiveValidator(driver);
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
    public ResponsiveValidator withLeftElement(WebElement element, int minMargin, int maxMargin) {
        leftElement = element;
        leftMinMargin = minMargin;
        leftMaxMargin = maxMargin;

        return this;
    }

    @Override
    public ResponsiveValidator withRightElement(WebElement element) {
        rightElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator withRightElement(WebElement element, int minMargin, int maxMargin) {
        rightElement = element;
        rightMinMargin = minMargin;
        rightMaxMargin = maxMargin;
        return this;
    }

    @Override
    public ResponsiveValidator withTopElement(WebElement element) {
        topElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator withTopElement(WebElement element, int minMargin, int maxMargin) {
        topElement = element;
        topMinMargin = minMargin;
        topMaxMargin = maxMargin;
        return this;
    }

    @Override
    public ResponsiveValidator withBottomElement(WebElement element) {
        bottomElement = element;
        return this;
    }

    @Override
    public ResponsiveValidator withBottomElement(WebElement element, int minMargin, int maxMargin) {
        bottomElement = element;
        bottomMinMargin = minMargin;
        bottomMaxMargin = maxMargin;
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
                if (leftMinMargin > 0) {
                    validateLeftElementWithMargin();
                } else {
                    validateLeftElement();
                }
            }
            if (rightElement != null) {
                if (rightMinMargin > 0) {
                    validateRightElementWithMargin();
                } else {
                    validateRightElement();
                }
            }
            if (topElement != null) {
                if (topMinMargin > 0) {
                    validateAboveElementWithMargin();
                } else {
                    validateAboveElement();
                }
            }
            if (bottomElement != null) {
                if (bottomMinMargin > 0) {
                    validateBelowElementWithMargin();
                } else {
                    validateBelowElement();
                }
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

            if (withReport && !errorMessage.isEmpty()) {
                try {
                    screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    img = ImageIO.read(screenshot);
                } catch (Exception e) {
                    LOG.error("Failed to create screenshot file: " + e.getMessage());
                }

                if (!errorMessage.isEmpty()) {
                    JSONObject rootDetails = new JSONObject();
                    rootDetails.put(X, xRoot);
                    rootDetails.put(Y, yRoot);
                    rootDetails.put(WIDTH, widthRoot);
                    rootDetails.put(HEIGHT, heightRoot);

                    jsonResults.put("rootElement", rootDetails);
                    jsonResults.put("elementName", rootElementReadableName);
                    jsonResults.put("screenshot", rootElementReadableName.replace(" ", "") + "-" + screenshot.getName());
                }

                long ms = System.currentTimeMillis();
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TARGET_AUTOMOTION_JSON + rootElementReadableName.replace(" ", "") + "-automotion" + ms + ".json"), StandardCharsets.UTF_8))) {
                    writer.write(jsonResults.toJSONString());
                } catch (IOException ex) {
                    LOG.error("Cannot create html report: " + ex.getMessage());
                }
                try {
                    File file = new File(TARGET_AUTOMOTION_JSON + rootElementReadableName.replace(" ", "") + "-automotion" + ms + ".json");
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

    @Override
    public void generateReport() {
        try {
            new HtmlReportBuilder().buildReport();
        } catch (IOException | ParseException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void drawScreenshot() {
        g = img.createGraphics();

        drawRoot(Color.RED);

        for (Object obj : errorMessage) {
            JSONObject det = (JSONObject) obj;
            JSONObject details = (JSONObject) det.get(REASON);
            JSONObject numE = (JSONObject) details.get(ELEMENT);

            if (numE != null) {
                float x = (float) numE.get(X);
                float y = (float) numE.get(Y);
                float width = (float) numE.get(WIDTH);
                float height = (float) numE.get(HEIGHT);

                g.setColor(Color.MAGENTA);
                g.setStroke(new BasicStroke(2));
                if (SystemHelper.isRetinaDisplay(g) && isChrome()) {
                    g.drawRect(2 * (int) x, 2 * (int) y, 2 * (int) width, 2 * (int) height);
                } else {
                    g.drawRect((int) x, (int) y, (int) width, (int) height);
                }
            }
        }

        try {
            ImageIO.write(img, "png", screenshot);
            File file = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replace(" ", "") + "-" + screenshot.getName());
            FileUtils.copyFile(screenshot, file);
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

    private void validateBelowElementWithMargin() {
        int yBelowElement = bottomElement.getLocation().getY();
        int marginBetweenRoot = yBelowElement - yRoot + heightRoot;
        if (marginBetweenRoot < bottomMinMargin || marginBetweenRoot > bottomMaxMargin) {
            putJsonDetailsWithElement(String.format("Below element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", bottomMinMargin, bottomMaxMargin, marginBetweenRoot), bottomElement);
        }
    }

    private void validateBelowElement() {
        List<WebElement> elements = new ArrayList<>();
        elements.add(rootElement);
        elements.add(bottomElement);

        if (!PageValidator.elementsAreAlignedVertically(elements)) {
            putJsonDetailsWithoutElement("Below element aligned not properly");
        }
    }

    private void validateAboveElementWithMargin() {
        int yAboveElement = topElement.getLocation().getY();
        int heightAboveElement = topElement.getSize().getHeight();
        int marginBetweenRoot = yRoot - yAboveElement + heightAboveElement;
        if (marginBetweenRoot < topMinMargin || marginBetweenRoot > topMaxMargin) {
            putJsonDetailsWithElement(String.format("Above element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", topMinMargin, topMaxMargin, marginBetweenRoot), topElement);
        }
    }

    private void validateAboveElement() {
        List<WebElement> elements = new ArrayList<>();
        elements.add(topElement);
        elements.add(rootElement);

        if (!PageValidator.elementsAreAlignedVertically(elements)) {
            putJsonDetailsWithoutElement("Above element aligned not properly");
        }
    }

    private void validateRightElementWithMargin() {
        int xRightElement = rightElement.getLocation().getX();
        int marginBetweenRoot = xRightElement - xRoot + widthRoot;
        if (marginBetweenRoot < rightMinMargin || marginBetweenRoot > rightMaxMargin) {
            putJsonDetailsWithElement(String.format("Right element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", rightMinMargin, rightMaxMargin, marginBetweenRoot), rightElement);
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

    private void validateLeftElementWithMargin() {
        int xLeftElement = leftElement.getLocation().getX();
        int widthLeftElement = leftElement.getSize().getWidth();
        int marginBetweenRoot = xRoot - xLeftElement + widthLeftElement;
        if (marginBetweenRoot < leftMinMargin || marginBetweenRoot > leftMaxMargin) {
            putJsonDetailsWithElement(String.format("Left element aligned not properly. Expected margin should be between %spx and %spx. Actual margin is %spx", leftMinMargin, leftMaxMargin, marginBetweenRoot), leftElement);
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

    private void drawRoot(Color color) {
        g.setColor(color);
        g.setStroke(new BasicStroke(2));
        if (SystemHelper.isRetinaDisplay(g) && isChrome()) {
            g.drawRect(2 * xRoot, 2 * yRoot, 2 * widthRoot, 2 * heightRoot);
        } else {
            g.drawRect(xRoot, yRoot, widthRoot, heightRoot);
        }
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.ORANGE);
        if (SystemHelper.isRetinaDisplay(g) && isChrome()) {
            g.drawLine(0, 2 * yRoot, 2 * pageWidth, 2 * yRoot);
            g.drawLine(0, 2 * (yRoot + heightRoot), 2 * pageWidth, 2 * (yRoot + heightRoot));
            g.drawLine(2 * xRoot, 0, 2 * xRoot, 2 * pageHeight);
            g.drawLine(2 * (xRoot + widthRoot), 0, 2 * (xRoot + widthRoot), 2 * pageHeight);
        } else {
            g.drawLine(0, yRoot, pageWidth, yRoot);
            g.drawLine(0, yRoot + heightRoot, pageWidth, yRoot + heightRoot);
            g.drawLine(xRoot, 0, xRoot, pageHeight);
            g.drawLine(xRoot + widthRoot, 0, xRoot + widthRoot, pageHeight);
        }
    }

    private void putJsonDetailsWithoutElement(String message) {
        JSONObject details = new JSONObject();
        JSONObject mes = new JSONObject();
        mes.put(MESSAGE, message);
        details.put(REASON, mes);
        errorMessage.add(details);
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
        mes.put(ELEMENT, elDetails);
        details.put(REASON, mes);
        errorMessage.add(details);
    }
}
