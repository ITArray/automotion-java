package rectangles;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;
import static rectangles.RectangleFixture.down;
import static rectangles.RectangleFixture.up;

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

        assertThat(TestAssumptions.withBottomElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusMinMargin() {
        WebElement other = createMovedDownElement(height+minMargin);

        assertThat(TestAssumptions.withBottomElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedDownElement(height+margin);

        assertThat(TestAssumptions.withBottomElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusMaxMargin() {
        WebElement other = createMovedDownElement(height+maxMargin);

        assertThat(TestAssumptions.withBottomElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveBottomElementsInMarginsMovedDownByMoreThanHeightPlusMaxMargin() {
        WebElement other = createMovedDownElement(height+maxMargin+1);

        assertThat(TestAssumptions.withBottomElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void doesNotHaveBottomElementsMovedDownByLessThanHeight() {
        WebElement other = createMovedDownElement(height-1);

        assertThat(TestAssumptions.withBottomElement(other, root)).isFalse();
    }

    @Test
    public void hasBottomElementsMovedDownByHeight() {
        WebElement other = createMovedDownElement(height);

        assertThat(TestAssumptions.withBottomElement(other, root)).isTrue();
    }

    @Test
    public void hasBottomElementsMovedDownByMoreThanHeight() {
        WebElement other = createMovedDownElement(height+1);

        assertThat(TestAssumptions.withBottomElement(other, root)).isTrue();
    }

    /*
     * top
     */

    @Test
    public void doesNotHaveTopElementsInMarginMovedUpByLessThanHeightPlusMinMargin() {
        WebElement other = createMovedUpElement(height+minMargin-1);

        assertThat(TestAssumptions.withTopElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusMinMargin() {
        WebElement other = createMovedUpElement(height+minMargin);

        assertThat(TestAssumptions.withTopElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedUpElement(height+margin);

        assertThat(TestAssumptions.withTopElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusMaxMargin() {
        WebElement other = createMovedUpElement(height+maxMargin);

        assertThat(TestAssumptions.withTopElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveTopElementsInMarginMovedUpByMoreThanHeightPlusMaxMargin() {
        WebElement other = createMovedUpElement(height+maxMargin+1);

        assertThat(TestAssumptions.withTopElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasTopElementsMovedUpByLessHeight() {
        WebElement other = createMovedUpElement(height-1);

        assertThat(TestAssumptions.withTopElement(root, other)).isFalse();
    }

    @Test
    public void hasTopElementsMovedUpByHeight() {
        WebElement other = createMovedUpElement(height);

        assertThat(TestAssumptions.withTopElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveTopElementsMovedUpByMoreThanHeight() {
        WebElement other = createMovedUpElement(height+1);

        assertThat(TestAssumptions.withTopElement(root, other)).isTrue();
    }

    /*
     * right
     */

    @Test
    public void doesNotHaveRightElementsInMarginsMovedRightByLessThanWidthPlusMinMargin() {
        WebElement other = createMovedRightElement(width+minMargin-1);

        assertThat(TestAssumptions.withRightElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusMinMargin() {
        WebElement other = createMovedRightElement(width+minMargin);

        assertThat(TestAssumptions.withRightElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedRightElement(width+margin);

        assertThat(TestAssumptions.withRightElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusMasMargin() {
        WebElement other = createMovedRightElement(width+maxMargin);

        assertThat(TestAssumptions.withRightElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveRightElementsInMarginsMovedRightByMoreThanWidthPlusMaxMargin() {
        WebElement other = createMovedRightElement(width+maxMargin+1);

        assertThat(TestAssumptions.withRightElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void doesNotHaveRightElementsMovedRightByLessThanWidth() {
        WebElement other = createMovedRightElement(width-1);

        assertThat(TestAssumptions.withRightElement(root, other)).isFalse();
    }

    @Test
    public void hasRightElementsMovedRightByWidth() {
        WebElement other = createMovedRightElement(width);

        assertThat(TestAssumptions.withRightElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveRightElementsMovedRightByMoreThanWidth() {
        WebElement other = createMovedRightElement(width+1);

        assertThat(TestAssumptions.withRightElement(root, other)).isTrue();
    }

    /*
     * left
     */

    @Test
    public void doesNotHaveLeftElementsInMarginsMovedLeftByLessThanWidthPlusMinMargin() {
        WebElement other = createMovedLeftElement(width+minMargin-1);

        assertThat(TestAssumptions.withLeftElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusMinMargin() {
        WebElement other = createMovedLeftElement(width+minMargin);

        assertThat(TestAssumptions.withLeftElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createMovedLeftElement(width+margin);

        assertThat(TestAssumptions.withLeftElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusMasMargin() {
        WebElement other = createMovedLeftElement(width+maxMargin);

        assertThat(TestAssumptions.withLeftElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveLeftElementsInMarginsMovedLeftByMoreThanWidthPlusMaxMargin() {
        WebElement other = createMovedLeftElement(width+maxMargin+1);

        assertThat(TestAssumptions.withLeftElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void doesNotHaveLeftElementsMovedLeftByLessThanWidth() {
        WebElement other = createMovedLeftElement(width-1);

        assertThat(TestAssumptions.withLeftElement(root, other)).isFalse();
    }

    @Test
    public void hasLeftElementsMovedLeftByWidth() {
        WebElement other = createMovedLeftElement(width);

        assertThat(TestAssumptions.withLeftElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveLeftElementsMovedLeftByMoreThanWidth() {
        WebElement other = createMovedLeftElement(width+1);

        assertThat(TestAssumptions.withLeftElement(root, other)).isTrue();
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
