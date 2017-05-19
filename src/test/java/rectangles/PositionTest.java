package rectangles;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.*;
import static rectangles.RectangleFixture.down;
import static rectangles.RectangleFixture.up;
import static rectangles.TestAssumptions.*;

public class PositionTest {

    private WebElement root;

    private int margin;
    private int minMargin;
    private int maxMargin;

    @Before
    public void setUp() {
        root = createRootElement();
        margin = 10;
        minMargin = down(margin);
        maxMargin = up(margin);
    }

    @Test
    public void doesNotHaveBottomElementsInMarginsMovedDownByLessThanHeightPlusMinMargin() {
        WebElement other = createElementMovedDownByHeightPLus(minMargin-1);

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusMinMargin() {
        WebElement other = createElementMovedDownByHeightPLus(minMargin);

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createElementMovedDownByHeightPLus(margin);

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasBottomElementsInMarginsMovedDownByHeightPlusMaxMargin() {
        WebElement other = createElementMovedDownByHeightPLus(maxMargin);

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveBottomElementsInMarginsMovedDownByMoreThanHeightPlusMaxMargin() {
        WebElement other = createElementMovedDownByHeightPLus(maxMargin+1);

        assertThat(withBottomElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void doesNotHaveBottomElementsMovedDownByLessThanHeight() {
        WebElement other = createElementMovedDownByHeightPLus(-1);

        assertThat(withBottomElement(other, root)).isFalse();
    }

    @Test
    public void hasBottomElementsMovedDownByHeight() {
        WebElement other = createElementMovedDownByHeightPLus(0);

        assertThat(withBottomElement(other, root)).isTrue();
    }

    @Test
    public void hasBottomElementsMovedDownByMoreThanHeight() {
        WebElement other = createElementMovedDownByHeightPLus(+1);

        assertThat(withBottomElement(other, root)).isTrue();
    }

    @Test
    public void doesNotHaveTopElementsInMarginMovedUpByLessThanHeightPlusMinMargin() {
        WebElement other = createElementMovedUpByHeightPlus(minMargin-1);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusMinMargin() {
        WebElement other = createElementMovedUpByHeightPlus(minMargin);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createElementMovedUpByHeightPlus(margin);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasTopElementsInMarginMovedUpByHeightPlusMaxMargin() {
        WebElement other = createElementMovedUpByHeightPlus(maxMargin);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveTopElementsInMarginMovedUpByMoreThanHeightPlusMaxMargin() {
        WebElement other = createElementMovedUpByHeightPlus(maxMargin+1);

        assertThat(withTopElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasTopElementsMovedUpByLessHeight() {
        WebElement other = createElementMovedUpByHeightPlus(-1);

        assertThat(withTopElement(root, other)).isFalse();
    }

    @Test
    public void hasTopElementsMovedUpByHeight() {
        WebElement other = createElementMovedUpByHeightPlus(0);

        assertThat(withTopElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveTopElementsMovedUpByMoreThanHeight() {
        WebElement other = createElementMovedUpByHeightPlus(+1);

        assertThat(withTopElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveRightElementsInMarginsMovedRightByLessThanWidthPlusMinMargin() {
        WebElement other = createElementMovedRightByWidthPlus(minMargin-1);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusMinMargin() {
        WebElement other = createElementMovedRightByWidthPlus(minMargin);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createElementMovedRightByWidthPlus(margin);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasRightElementsInMarginsMovedRightByWidthPlusMasMargin() {
        WebElement other = createElementMovedRightByWidthPlus(maxMargin);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveRightElementsInMarginsMovedRightByMoreThanWidthPlusMaxMargin() {
        WebElement other = createElementMovedRightByWidthPlus(maxMargin+1);

        assertThat(withRightElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void doesNotHaveRightElementsMovedRightByLessThanWidth() {
        WebElement other = createElementMovedRightByWidthPlus(-1);

        assertThat(withRightElement(root, other)).isFalse();
    }

    @Test
    public void hasRightElementsMovedRightByWidth() {
        WebElement other = createElementMovedRightByWidthPlus(0);

        assertThat(withRightElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveRightElementsMovedRightByMoreThanWidth() {
        WebElement other = createElementMovedRightByWidthPlus(+1);

        assertThat(withRightElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveLeftElementsInMarginsMovedLeftByLessThanWidthPlusMinMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(minMargin-1);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusMinMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(minMargin);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(margin);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void hasLeftElementsInMarginsMovedLeftByWidthPlusMasMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(maxMargin);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void doesNotHaveLeftElementsInMarginsMovedLeftByMoreThanWidthPlusMaxMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(maxMargin+1);

        assertThat(withLeftElement(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void doesNotHaveLeftElementsMovedLeftByLessThanWidth() {
        WebElement other = createElementMovedLeftByWidthPlus(-1);

        assertThat(withLeftElement(root, other)).isFalse();
    }

    @Test
    public void hasLeftElementsMovedLeftByWidth() {
        WebElement other = createElementMovedLeftByWidthPlus(0);

        assertThat(withLeftElement(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveLeftElementsMovedLeftByMoreThanWidth() {
        WebElement other = createElementMovedLeftByWidthPlus(+1);

        assertThat(withLeftElement(root, other)).isTrue();
    }
}
