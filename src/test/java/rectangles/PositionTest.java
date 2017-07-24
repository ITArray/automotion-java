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
    public void isNotAboveElementsInMarginsMovedDownByLessThanHeightPlusMinMargin() {
        WebElement other = createElementMovedDownByHeightPLus(minMargin-1);

        assertThat(isAbove(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void isAboveElementsInMarginsMovedDownByHeightPlusMinMargin() {
        WebElement other = createElementMovedDownByHeightPLus(minMargin);

        assertThat(isAbove(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isAboveElementsInMarginsMovedDownByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createElementMovedDownByHeightPLus(margin);

        assertThat(isAbove(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isAboveElementsInMarginsMovedDownByHeightPlusMaxMargin() {
        WebElement other = createElementMovedDownByHeightPLus(maxMargin);

        assertThat(isAbove(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isNotAboveElementsInMarginsMovedDownByMoreThanHeightPlusMaxMargin() {
        WebElement other = createElementMovedDownByHeightPLus(maxMargin+1);

        assertThat(isAbove(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void isNotAboveElementsMovedDownByLessThanHeight() {
        WebElement other = createElementMovedDownByHeightPLus(-1);

        assertThat(isAbove(other, root)).isFalse();
    }

    @Test
    public void isAboveElementsMovedDownByHeight() {
        WebElement other = createElementMovedDownByHeightPLus(0);

        assertThat(isAbove(other, root)).isTrue();
    }

    @Test
    public void isAboveElementsMovedDownByMoreThanHeight() {
        WebElement other = createElementMovedDownByHeightPLus(+1);

        assertThat(isAbove(other, root)).isTrue();
    }

    @Test
    public void isNotBelowElementsInMarginMovedUpByLessThanHeightPlusMinMargin() {
        WebElement other = createElementMovedUpByHeightPlus(minMargin-1);

        assertThat(isBelow(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void isBelowElementsInMarginMovedUpByHeightPlusMinMargin() {
        WebElement other = createElementMovedUpByHeightPlus(minMargin);

        assertThat(isBelow(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isBelowElementsInMarginMovedUpByHeightPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createElementMovedUpByHeightPlus(margin);

        assertThat(isBelow(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isBelowElementsInMarginMovedUpByHeightPlusMaxMargin() {
        WebElement other = createElementMovedUpByHeightPlus(maxMargin);

        assertThat(isBelow(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isNotBelowElementsInMarginMovedUpByMoreThanHeightPlusMaxMargin() {
        WebElement other = createElementMovedUpByHeightPlus(maxMargin+1);

        assertThat(isBelow(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void isNotBelowElementsMovedUpByLessHeight() {
        WebElement other = createElementMovedUpByHeightPlus(-1);

        assertThat(isBelow(root, other)).isFalse();
    }

    @Test
    public void isBelowElementsMovedUpByHeight() {
        WebElement other = createElementMovedUpByHeightPlus(0);

        assertThat(isBelow(root, other)).isTrue();
    }

    @Test
    public void isBelowElementsMovedUpByMoreThanHeight() {
        WebElement other = createElementMovedUpByHeightPlus(+1);

        assertThat(isBelow(root, other)).isTrue();
    }

    @Test
    public void isNotLeftOfElementsInMarginsMovedRightByLessThanWidthPlusMinMargin() {
        WebElement other = createElementMovedRightByWidthPlus(minMargin-1);

        assertThat(isLeftOf(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void isLeftOfElementsInMarginsMovedRightByWidthPlusMinMargin() {
        WebElement other = createElementMovedRightByWidthPlus(minMargin);

        assertThat(isLeftOf(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isLeftOfElementsInMarginsMovedRightByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createElementMovedRightByWidthPlus(margin);

        assertThat(isLeftOf(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isLeftOfElementsInMarginsMovedRightByWidthPlusMasMargin() {
        WebElement other = createElementMovedRightByWidthPlus(maxMargin);

        assertThat(isLeftOf(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isNotLeftOfElementsInMarginsMovedRightByMoreThanWidthPlusMaxMargin() {
        WebElement other = createElementMovedRightByWidthPlus(maxMargin+1);

        assertThat(isLeftOf(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void isNotLeftOfElementsMovedRightByLessThanWidth() {
        WebElement other = createElementMovedRightByWidthPlus(-1);

        assertThat(isLeftOf(root, other)).isFalse();
    }

    @Test
    public void isLeftOfElementsMovedRightByWidth() {
        WebElement other = createElementMovedRightByWidthPlus(0);

        assertThat(isLeftOf(root, other)).isTrue();
    }

    @Test
    public void isLeftOfElementsMovedRightByMoreThanWidth() {
        WebElement other = createElementMovedRightByWidthPlus(+1);

        assertThat(isLeftOf(root, other)).isTrue();
    }

    @Test
    public void isNotRightOfElementsInMarginsMovedLeftByLessThanWidthPlusMinMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(minMargin-1);

        assertThat(isRightOf(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void isRightOfElementsInMarginsMovedLeftByWidthPlusMinMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(minMargin);

        assertThat(isRightOf(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isRightOfElementsInMarginsMovedLeftByWidthPlusSomethingBetweenMinAndMaxMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(margin);

        assertThat(isRightOf(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isRightOfElementsInMarginsMovedLeftByWidthPlusMasMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(maxMargin);

        assertThat(isRightOf(root, other, minMargin, maxMargin)).isTrue();
    }

    @Test
    public void isNotRightOfElementsInMarginsMovedLeftByMoreThanWidthPlusMaxMargin() {
        WebElement other = createElementMovedLeftByWidthPlus(maxMargin+1);

        assertThat(isRightOf(root, other, minMargin, maxMargin)).isFalse();
    }

    @Test
    public void isNotRightOfElementsMovedLeftByLessThanWidth() {
        WebElement other = createElementMovedLeftByWidthPlus(-1);

        assertThat(isRightOf(root, other)).isFalse();
    }

    @Test
    public void isRightOfElementsMovedLeftByWidth() {
        WebElement other = createElementMovedLeftByWidthPlus(0);

        assertThat(isRightOf(root, other)).isTrue();
    }

    @Test
    public void isRightOfElementsMovedLeftByMoreThanWidth() {
        WebElement other = createElementMovedLeftByWidthPlus(+1);

        assertThat(isRightOf(root, other)).isTrue();
    }
}
