package rectangles;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;
import static rectangles.RectangleFixture.down;
import static rectangles.RectangleFixture.up;
import static rectangles.TestAssumptions.*;

public class PositionTest {
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
        originX = RectangleFixture.originX;
        originY = RectangleFixture.originY;
        cornerX = RectangleFixture.cornerX;
        cornerY = RectangleFixture.cornerY;
        width = cornerX-originX;
        height= cornerY-originY;
        root = DummyWebElement.createRootElement();
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

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusMinMargin() {
        WebElement other = createMovedDownElement(height+minMargin);

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedDownElement(height+margin);

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusMaxMargin() {
        WebElement other = createMovedDownElement(height+maxMargin);

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveBottomElementsInMarginsMovedDownByMoreThanHeightPlusMaxMargin() {
        WebElement other = createMovedDownElement(height+maxMargin+1);

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void doesNotHaveBottomElementsMovedDownByLessThanHeight() {
        WebElement other = createMovedDownElement(height-1);

        assertThat(withBottomElement(other, root)).isFalse();
    }

    @Test
    public void hasBottomElementsMovedDownByHeight() {
        WebElement other = createMovedDownElement(height);

        assertThat(withBottomElement(other, root)).isTrue();
    }

    @Test
    public void hasBottomElementsMovedDownByMoreThanHeight() {
        WebElement other = createMovedDownElement(height+1);

        assertThat(withBottomElement(other, root)).isTrue();
    }

    /*
     * top
     */

    @Test
    public void doesNotHaveTopElementsInMarginMovedUpByLessThanHeightPlusMinMargin() {
        WebElement other = createMovedUpElement(height+minMargin-1);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusMinMargin() {
        WebElement other = createMovedUpElement(height+minMargin);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedUpElement(height+margin);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusMaxMargin() {
        WebElement other = createMovedUpElement(height+maxMargin);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveTopElementsInMarginMovedUpByMoreThanHeightPlusMaxMargin() {
        WebElement other = createMovedUpElement(height+maxMargin+1);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasTopElementsMovedUpByLessHeight() {
        WebElement other = createMovedUpElement(height-1);

        assertThat(withTopElement(root, other)).isFalse();
    }

    @Test
    public void hasTopElementsMovedUpByHeight() {
        WebElement other = createMovedUpElement(height);

        assertThat(withTopElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveTopElementsMovedUpByMoreThanHeight() {
        WebElement other = createMovedUpElement(height+1);

        assertThat(withTopElement(root, other)).isTrue();
    }

    /*
     * right
     */

    @Test
    public void doesNotHaveRightElementsInMarginsMovedRightByLessThanWidthPlusMinMargin() {
        WebElement other = createMovedRightElement(width+minMargin-1);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusMinMargin() {
        WebElement other = createMovedRightElement(width+minMargin);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedRightElement(width+margin);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusMasMargin() {
        WebElement other = createMovedRightElement(width+maxMargin);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveRightElementsInMarginsMovedRightByMoreThanWidthPlusMaxMargin() {
        WebElement other = createMovedRightElement(width+maxMargin+1);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void doesNotHaveRightElementsMovedRightByLessThanWidth() {
        WebElement other = createMovedRightElement(width-1);

        assertThat(withRightElement(root, other)).isFalse();
    }

    @Test
    public void hasRightElementsMovedRightByWidth() {
        WebElement other = createMovedRightElement(width);

        assertThat(withRightElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveRightElementsMovedRightByMoreThanWidth() {
        WebElement other = createMovedRightElement(width+1);

        assertThat(withRightElement(root, other)).isTrue();
    }

    /*
     * left
     */

    @Test
    public void doesNotHaveLeftElementsInMarginsMovedLeftByLessThanWidthPlusMinMargin() {
        WebElement other = createMovedLeftElement(width+minMargin-1);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusMinMargin() {
        WebElement other = createMovedLeftElement(width+minMargin);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedLeftElement(width+margin);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusMasMargin() {
        WebElement other = createMovedLeftElement(width+maxMargin);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveLeftElementsInMarginsMovedLeftByMoreThanWidthPlusMaxMargin() {
        WebElement other = createMovedLeftElement(width+maxMargin+1);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void doesNotHaveLeftElementsMovedLeftByLessThanWidth() {
        WebElement other = createMovedLeftElement(width-1);

        assertThat(withLeftElement(root, other)).isFalse();
    }

    @Test
    public void hasLeftElementsMovedLeftByWidth() {
        WebElement other = createMovedLeftElement(width);

        assertThat(withLeftElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveLeftElementsMovedLeftByMoreThanWidth() {
        WebElement other = createMovedLeftElement(width+1);

        assertThat(withLeftElement(root, other)).isTrue();
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


}
