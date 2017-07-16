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

        assertThat(isVerticallyLeftAlignedWith(root, other)).isTrue();
        assertThat(isVerticallyLeftAlignedWith(root, asList(other))).isTrue();
        assertThat(sameLeftOffset(asList(root, other))).isTrue();
    }

    @Test
    public void isNotLeftAlignedWithElementsWithSmallerOriginX() {
        WebElement other = createElement(down(originX), up(originY), down(cornerX), down(cornerY));

        assertThat(isVerticallyLeftAlignedWith(root, other)).isFalse();
        assertThat(sameLeftOffset(asList(root, other))).isFalse();
    }

    @Test
    public void isNotLeftAlignedWithElementsWithGreaterOriginX() {
        WebElement other = createElement(up(originX), down(originY), down(cornerX), up(cornerY));

        assertThat(isVerticallyLeftAlignedWith(root, other)).isFalse();
        assertThat(sameLeftOffset(asList(root, other))).isFalse();
    }



    @Test
    public void isTopAlignedWithElementsWithEqualOriginY() {
        WebElement other = createElement(up(originX), originY, up(cornerX), down(cornerY));

        assertThat(isHorizontallyTopAlignedWith(root, other)).isTrue();
        assertThat(isHorizontallyTopAlignedWith(root, asList(other))).isTrue();
        assertThat(sameTopOffset(asList(root, other))).isTrue();
    }

    @Test
    public void isNotTopAlignedWithElementsWithSmallerOriginY() {
        WebElement other = createElement(up(originX), down(originY), down(cornerX), down(cornerY));

        assertThat(isHorizontallyTopAlignedWith(root, other)).isFalse();
        assertThat(isHorizontallyTopAlignedWith(root, asList(other))).isFalse();
        assertThat(sameTopOffset(asList(root, other))).isFalse();
    }

    @Test
    public void isNotTopAlignedWithElementsWithGreaterOriginY() {
        WebElement other = createElement(down(originX), up(originY), down(cornerX), up(cornerY));

        assertThat(isHorizontallyTopAlignedWith(root, other)).isFalse();
        assertThat(isHorizontallyTopAlignedWith(root, asList(other))).isFalse();
        assertThat(sameTopOffset(asList(root, other))).isFalse();
    }



    @Test
    public void isRightAlignedWithElementsWithEqualCornerX() {
        WebElement other = createElement(up(originX), up(originY), cornerX, down(cornerY));

        assertThat(isVerticallyRightAlignedWith(root, asList(other))).isTrue();
        assertThat(isVerticallyRightAlignedWith(root, other)).isTrue();
        assertThat(sameRightOffset(asList(root, other))).isTrue();
    }

    @Test
    public void isNotRightAlignedWithElementsWithLesserCornerX() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), down(cornerY));

        assertThat(isVerticallyRightAlignedWith(root, other)).isFalse();
        assertThat(isVerticallyRightAlignedWith(root, asList(other))).isFalse();
        assertThat(sameRightOffset(asList(root, other))).isFalse();
    }

    @Test
    public void isNotRightAlignedWithElementsWithGreaterCornerX() {
        WebElement other = createElement(up(originX), up(originY), up(cornerX), down(cornerY));

        assertThat(isVerticallyRightAlignedWith(root, other)).isFalse();
        assertThat(isVerticallyRightAlignedWith(root, asList(other))).isFalse();
        assertThat(sameRightOffset(asList(root, other))).isFalse();
    }



    @Test
    public void isBottomAlignedWithElementsWithEqualCornerY() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), cornerY);

        assertThat(isHorizontallyBottomAlignedWith(root, other)).isTrue();
        assertThat(isHorizontallyBottomAlignedWith(root, asList(other))).isTrue();
        assertThat(sameBottomOffset(asList(root, other))).isTrue();
    }

    @Test
    public void isNotBottomAlignedWithElementsWithLesserCornerY() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), down(cornerY));

        assertThat(isHorizontallyBottomAlignedWith(root, other)).isFalse();
        assertThat(isHorizontallyBottomAlignedWith(root, asList(other))).isFalse();
        assertThat(sameBottomOffset(asList(root, other))).isFalse();
    }

    @Test
    public void isNotBottomAlignedWithElementsWithGreaterCornerY() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), up(cornerY));

        assertThat(isHorizontallyBottomAlignedWith(root, other)).isFalse();
        assertThat(isHorizontallyBottomAlignedWith(root, asList(other))).isFalse();
        assertThat(sameBottomOffset(asList(root, other))).isFalse();
    }


}
