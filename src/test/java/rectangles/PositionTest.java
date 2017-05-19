package rectangles;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;
import static rectangles.RectangleFixture.down;
import static rectangles.RectangleFixture.up;

public class PositionTest {
    private long windowWidth;
    private long windowHeight;
    private int originX;
    private int originY;
    private int cornerX;
    private int cornerY;
    private int width;
    private int height;
    private WebElement root;

    private int margin;
    private int minMargin;
    private int maxMargin;

    @Before
    public void setUp() {
        windowWidth = 1920L;
        windowHeight = 1080L;
        originX = 100;
        originY = 600;
        cornerX = 300;
        cornerY = 900;
        width = cornerX-originX;
        height= cornerY-originY;
        root = createElement(originX, originY, cornerX, cornerY);
        margin = 10;
        minMargin = down(margin);
        maxMargin = up(margin);
    }

    /*
     * bottom
     */

    @Test
    public void doesNotHaveBottomElementsInMarginsMovedDownByLessThanHeightPlusMinMargin() {
        WebElement other = createMovedDownElement(height+minMargin-1);

        assertThat(withBottomElementInMargins(other)).isFalse();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusMinMargin() {
        WebElement other = createMovedDownElement(height+minMargin);

        assertThat(withBottomElementInMargins(other)).isTrue();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedDownElement(height+margin);

        assertThat(withBottomElementInMargins(other)).isTrue();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusMaxMargin() {
        WebElement other = createMovedDownElement(height+maxMargin);

        assertThat(withBottomElementInMargins(other)).isTrue();
    }

    @Test
    public void doesNotHaveBottomElementsInMarginsMovedDownByMoreThanHeightPlusMaxMargin() {
        WebElement other = createMovedDownElement(height+maxMargin+1);

        assertThat(withBottomElementInMargins(other)).isFalse();
    }

    @Test
    public void doesNotHaveBottomElementsMovedDownByLessThanHeight() {
        WebElement other = createMovedDownElement(height-1);

        assertThat(withBottomElement(other)).isFalse();
    }

    @Test
    public void hasBottomElementsMovedDownByHeight() {
        WebElement other = createMovedDownElement(height);

        assertThat(withBottomElement(other)).isTrue();
    }

    @Test
    public void hasBottomElementsMovedDownByMoreThanHeight() {
        WebElement other = createMovedDownElement(height+1);

        assertThat(withBottomElement(other)).isTrue();
    }

    /*
     * top
     */

    @Test
    public void doesNotHaveTopElementsInMarginMovedUpByLessThanHeightPlusMinMargin() {
        WebElement other = createMovedUpElement(height+minMargin-1);

        assertThat(withTopElementInMargins(other)).isFalse();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusMinMargin() {
        WebElement other = createMovedUpElement(height+minMargin);

        assertThat(withTopElementInMargins(other)).isTrue();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedUpElement(height+margin);

        assertThat(withTopElementInMargins(other)).isTrue();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusMaxMargin() {
        WebElement other = createMovedUpElement(height+maxMargin);

        assertThat(withTopElementInMargins(other)).isTrue();
    }

    @Test
    public void doesNotHaveTopElementsInMarginMovedUpByMoreThanHeightPlusMaxMargin() {
        WebElement other = createMovedUpElement(height+maxMargin+1);

        assertThat(withTopElementInMargins(other)).isFalse();
    }

    @Test
    public void hasTopElementsMovedUpByLessHeight() {
        WebElement other = createMovedUpElement(height-1);

        assertThat(withTopElement(other)).isFalse();
    }

    @Test
    public void hasTopElementsMovedUpByHeight() {
        WebElement other = createMovedUpElement(height);

        assertThat(withTopElement(other)).isTrue();
    }

    @Test
    public void doesNotHaveTopElementsMovedUpByMoreThanHeight() {
        WebElement other = createMovedUpElement(height+1);

        assertThat(withTopElement(other)).isTrue();
    }

    /*
     * right
     */

    @Test
    public void doesNotHaveRightElementsInMarginsMovedRightByLessThanWidthPlusMinMargin() {
        WebElement other = createMovedRightElement(width+minMargin-1);

        assertThat(withRightElementInMargins(other)).isFalse();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusMinMargin() {
        WebElement other = createMovedRightElement(width+minMargin);

        assertThat(withRightElementInMargins(other)).isTrue();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedRightElement(width+margin);

        assertThat(withRightElementInMargins(other)).isTrue();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusMasMargin() {
        WebElement other = createMovedRightElement(width+maxMargin);

        assertThat(withRightElementInMargins(other)).isTrue();
    }

    @Test
    public void doesNotHaveRightElementsInMarginsMovedRightByMoreThanWidthPlusMaxMargin() {
        WebElement other = createMovedRightElement(width+maxMargin+1);

        assertThat(withRightElementInMargins(other)).isFalse();
    }

    @Test
    public void doesNotHaveRightElementsMovedRightByLessThanWidth() {
        WebElement other = createMovedRightElement(width-1);

        assertThat(withRightElement(other)).isFalse();
    }

    @Test
    public void hasRightElementsMovedRightByWidth() {
        WebElement other = createMovedRightElement(width);

        assertThat(withRightElement(other)).isTrue();
    }

    @Test
    public void doesNotHaveRightElementsMovedRightByMoreThanWidth() {
        WebElement other = createMovedRightElement(width+1);

        assertThat(withRightElement(other)).isTrue();
    }

    /*
     * left
     */

    @Test
    public void doesNotHaveLeftElementsInMarginsMovedLeftByLessThanWidthPlusMinMargin() {
        WebElement other = createMovedLeftElement(width+minMargin-1);

        assertThat(withLeftElementInMargins(other)).isFalse();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusMinMargin() {
        WebElement other = createMovedLeftElement(width+minMargin);

        assertThat(withLeftElementInMargins(other)).isTrue();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedLeftElement(width+margin);

        assertThat(withLeftElementInMargins(other)).isTrue();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusMasMargin() {
        WebElement other = createMovedLeftElement(width+maxMargin);

        assertThat(withLeftElementInMargins(other)).isTrue();
    }

    @Test
    public void doesNotHaveLeftElementsInMarginsMovedLeftByMoreThanWidthPlusMaxMargin() {
        WebElement other = createMovedLeftElement(width+maxMargin+1);

        assertThat(withLeftElementInMargins(other)).isFalse();
    }

    @Test
    public void doesNotHaveLeftElementsMovedLeftByLessThanWidth() {
        WebElement other = createMovedLeftElement(width-1);

        assertThat(withLeftElement(other)).isFalse();
    }

    @Test
    public void hasLeftElementsMovedLeftByWidth() {
        WebElement other = createMovedLeftElement(width);

        assertThat(withLeftElement(other)).isTrue();
    }

    @Test
    public void doesNotHaveLeftElementsMovedLeftByMoreThanWidth() {
        WebElement other = createMovedLeftElement(width+1);

        assertThat(withLeftElement(other)).isTrue();
    }

    /*
     * end
     */

    private WebElement createOffsetElement(int deltaX, int deltaY) {
        return createElement(originX+deltaX, originY+deltaY, cornerX+deltaX, cornerY+deltaY);
    }

    private WebElement createMovedRightElement(int deltaX) {
        return createOffsetElement(deltaX, 0);
    }

    private WebElement createMovedLeftElement(int deltaX) {
        return createOffsetElement(-deltaX, 0);
    }

    private WebElement createMovedDownElement(int deltaY) {
        return createOffsetElement(0, deltaY);
    }

    private WebElement createMovedUpElement(int deltaY) {
        return createOffsetElement(0, -deltaY);
    }

    private boolean withBottomElementInMargins(WebElement other) {
        return validate(other, (uiValidator, webElement) -> uiValidator.withBottomElement(webElement, minMargin, maxMargin));
    }

    private boolean withBottomElement(WebElement other) {
        return validate(other, UIValidator::withBottomElement);
    }

    private boolean withTopElementInMargins(WebElement other) {
        return validate(other, (uiValidator, webElement) -> uiValidator.withTopElement(webElement, minMargin, maxMargin));
    }

    private boolean withTopElement(WebElement other) {
        return validate(other, UIValidator::withTopElement);
    }

    private boolean withLeftElementInMargins(WebElement other) {
        return validate(other, (uiValidator, webElement) -> uiValidator.withLeftElement(webElement, minMargin, maxMargin));
    }

    private boolean withLeftElement(WebElement other) {
        return validate(other, UIValidator::withLeftElement);
    }

    private boolean withRightElementInMargins(WebElement other) {
        return validate(other, (uiValidator, webElement) -> uiValidator.withRightElement(webElement, minMargin, maxMargin));
    }

    private boolean withRightElement(WebElement other) {
        return validate(other, UIValidator::withRightElement);
    }

    private boolean validate(WebElement other, BiConsumer<UIValidator, WebElement> assumption) {
        WebDriver driver = new DummyWebDriver(windowWidth, windowHeight);
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator, other);
        return validator.validate();
    }


}
