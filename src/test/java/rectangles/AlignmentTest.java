package rectangles;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;
import static rectangles.DummyWebElement.createRootElement;
import static rectangles.RectangleFixture.down;
import static rectangles.RectangleFixture.up;
import static rectangles.TestAssumptions.*;

public class AlignmentTest {

    private int originX;
    private int originY;
    private int cornerX;
    private int cornerY;
    private WebElement root;

    @Before
    public void setUp() {
        originX = RectangleFixture.originX;
        originY = RectangleFixture.originY;
        cornerX = RectangleFixture.cornerX;
        cornerY = RectangleFixture.cornerY;
        root = createRootElement();
    }

    @Test
    public void isLeftAlignedWithElementsWithEqualOriginX() {
        WebElement other = createElement(originX, up(originY), up(cornerX), down(cornerY));

        assertThat(isLeftAlignedWith(root, other)).isTrue();
        assertThat(isLeftAlignedWith(root, asList(other))).isTrue();
        assertThat(areLeftAligned(asList(root, other))).isTrue();
    }

    @Test
    public void isNotLeftAlignedWithElementsWithSmallerOriginX() {
        WebElement other = createElement(down(originX), up(originY), down(cornerX), down(cornerY));

        assertThat(isLeftAlignedWith(root, other)).isFalse();
        assertThat(areLeftAligned(asList(root, other))).isFalse();
    }

    @Test
    public void isNotLeftAlignedWithElementsWithGreaterOriginX() {
        WebElement other = createElement(up(originX), down(originY), down(cornerX), up(cornerY));

        assertThat(isLeftAlignedWith(root, other)).isFalse();
        assertThat(areLeftAligned(asList(root, other))).isFalse();
    }



    @Test
    public void isTopAlignedWithElementsWithEqualOriginY() {
        WebElement other = createElement(up(originX), originY, up(cornerX), down(cornerY));

        assertThat(isTopAlignedWith(root, other)).isTrue();
        assertThat(isTopAlignedWith(root, asList(other))).isTrue();
        assertThat(areTopAligned(asList(root, other))).isTrue();
    }

    @Test
    public void isNotTopAlignedWithElementsWithSmallerOriginY() {
        WebElement other = createElement(up(originX), down(originY), down(cornerX), down(cornerY));

        assertThat(isTopAlignedWith(root, other)).isFalse();
        assertThat(isTopAlignedWith(root, asList(other))).isFalse();
        assertThat(areTopAligned(asList(root, other))).isFalse();
    }

    @Test
    public void isNotTopAlignedWithElementsWithGreaterOriginY() {
        WebElement other = createElement(down(originX), up(originY), down(cornerX), up(cornerY));

        assertThat(isTopAlignedWith(root, other)).isFalse();
        assertThat(isTopAlignedWith(root, asList(other))).isFalse();
        assertThat(areTopAligned(asList(root, other))).isFalse();
    }



    @Test
    public void isRightAlignedWithElementsWithEqualCornerX() {
        WebElement other = createElement(up(originX), up(originY), cornerX, down(cornerY));

        assertThat(isRightAlignedWith(root, asList(other))).isTrue();
        assertThat(isRightAlignedWith(root, other)).isTrue();
        assertThat(areRightAligned(asList(root, other))).isTrue();
    }

    @Test
    public void isNotRightAlignedWithElementsWithLesserCornerX() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), down(cornerY));

        assertThat(isRightAlignedWith(root, other)).isFalse();
        assertThat(isRightAlignedWith(root, asList(other))).isFalse();
        assertThat(areRightAligned(asList(root, other))).isFalse();
    }

    @Test
    public void isNotRightAlignedWithElementsWithGreaterCornerX() {
        WebElement other = createElement(up(originX), up(originY), up(cornerX), down(cornerY));

        assertThat(isRightAlignedWith(root, other)).isFalse();
        assertThat(isRightAlignedWith(root, asList(other))).isFalse();
        assertThat(areRightAligned(asList(root, other))).isFalse();
    }



    @Test
    public void isBottomAlignedWithElementsWithEqualCornerY() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), cornerY);

        assertThat(isBottomAlignedWith(root, other)).isTrue();
        assertThat(isBottomAlignedWith(root, asList(other))).isTrue();
        assertThat(areBottomAligned(asList(root, other))).isTrue();
    }

    @Test
    public void isNotBottomAlignedWithElementsWithLesserCornerY() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), down(cornerY));

        assertThat(isBottomAlignedWith(root, other)).isFalse();
        assertThat(isBottomAlignedWith(root, asList(other))).isFalse();
        assertThat(areBottomAligned(asList(root, other))).isFalse();
    }

    @Test
    public void isNotBottomAlignedWithElementsWithGreaterCornerY() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), up(cornerY));

        assertThat(isBottomAlignedWith(root, other)).isFalse();
        assertThat(isBottomAlignedWith(root, asList(other))).isFalse();
        assertThat(areBottomAligned(asList(root, other))).isFalse();
    }


}
