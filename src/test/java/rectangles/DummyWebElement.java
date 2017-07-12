package rectangles;

import net.itarray.automotion.internal.geometry.Scalar;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyWebElement implements WebElement {

    private final Point location;
    private final Dimension size;
    private final Map<String, String> cssAttributesByName = new HashMap<>();

    private DummyWebElement(Point location, Dimension size) {
        this.location = location;
        this.size = size;
    }

    @Override
    public void click() {

    }

    @Override
    public void submit() {

    }

    @Override
    public void sendKeys(CharSequence... charSequences) {

    }

    @Override
    public void clear() {

    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String s) {
        return null;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(location, size);
    }

    @Override
    public String getCssValue(String propertyName) {
        return cssAttributesByName.getOrDefault(propertyName, "");
    }

    public void putCssValue(String propertyName, String propertyValue) {
        cssAttributesByName.put(propertyName, propertyValue);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }

    public static WebElement createElement(Scalar originX, Scalar originY, Scalar cornerX, Scalar cornerY) {
        return createElement(originX.getValue(), originY.getValue(), cornerX.getValue(), cornerY.getValue());
    }

    public static WebElement createElement(int originX, int originY, int cornerX, int cornerY) {
        return new DummyWebElement(
                new Point(originX, originY),
                new Dimension(cornerX-originX, cornerY-originY));
    }

    public static WebElement createRootElement() {
        return new DummyWebElement(
                new Point(RectangleFixture.originX, RectangleFixture.originY),
                new Dimension(RectangleFixture.cornerX-RectangleFixture.originX, RectangleFixture.cornerY-RectangleFixture.originY));
    }

    public static WebElement createOffsetElement(int deltaX, int deltaY) {
        return createElement(
                RectangleFixture.originX+deltaX, RectangleFixture.originY+deltaY,
                RectangleFixture.cornerX+deltaX, RectangleFixture.cornerY+deltaY);
    }

    public static WebElement createElementMovedRightBy(int deltaX) {
        return createOffsetElement(deltaX, 0);
    }

    public static WebElement createElementMovedRightByWidthPlus(int deltaX) {
        return createElementMovedRightBy(RectangleFixture.width + deltaX);
    }

    public static WebElement createElementMovedLeftBy(int deltaX) {
        return createOffsetElement(-deltaX, 0);
    }

    public static WebElement createElementMovedLeftByWidthPlus(int deltaX) {
        return createElementMovedLeftBy(RectangleFixture.width+deltaX);
    }

    public static WebElement createElementMovedDownBy(int deltaY) {
        return createOffsetElement(0, deltaY);
    }

    public static WebElement createElementMovedDownByHeightPLus(int deltaY) {
        return createElementMovedDownBy(RectangleFixture.height+deltaY);
    }

    public static WebElement createElementMovedUpBy(int deltaY) {
        return createOffsetElement(0, -deltaY);
    }

    public static WebElement createElementMovedUpByHeightPlus(int deltaY) {
        return createElementMovedUpBy(RectangleFixture.height+deltaY);
    }
}
