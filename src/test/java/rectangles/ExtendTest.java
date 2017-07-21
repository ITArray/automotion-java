package rectangles;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createRootElement;
import static rectangles.RectangleFixture.*;
import static rectangles.TestAssumptions.*;

public class ExtendTest {
    private WebElement root;

    @Before
    public void setUp() {
        root = createRootElement();
    }

    @Test
    public void hasSameWidthAsATranslatedElement() {
        WebElement other = DummyWebElement.createElement(originX+10, originY+5, cornerX+10, cornerY+5);

        assertThat(hasEqualWidthAs(root, other)).isTrue();
        assertThat(hasEqualWidthAs(root, singletonList(other))).isTrue();
        assertThat(haveEqualWidth(asList(root, other))).isTrue();
        assertThat(haveDifferentWidths(asList(root, other))).isFalse();
    }

    @Test
    public void hasSameWidthAsAVerticallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(hasEqualWidthAs(root, other)).isTrue();
        assertThat(hasEqualWidthAs(root, singletonList(other))).isTrue();
        assertThat(haveEqualWidth(asList(root, other))).isTrue();
        assertThat(haveDifferentWidths(asList(root, other))).isFalse();
    }

    @Test
    public void doesNotHaveSameWidthAsAHorizontallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX+100, cornerY);

        assertThat(hasEqualWidthAs(root, other)).isFalse();
        assertThat(hasEqualWidthAs(root, singletonList(other))).isFalse();
        assertThat(haveEqualWidth(asList(root, other))).isFalse();
        assertThat(haveDifferentWidths(asList(root, other))).isTrue();
        assertThat(haveDifferentWidths(asList(root, other, root))).isFalse();
    }

    @Test
    public void hasSameHeightAsATranslatedElement() {
        WebElement other = DummyWebElement.createElement(originX+10, originY+5, cornerX+10, cornerY+5);

        assertThat(hasEqualHeightAs(root, other)).isTrue();
        assertThat(hasEqualHeightAs(root, singletonList(other))).isTrue();
        assertThat(haveEqualHeight(asList(root, other))).isTrue();
        assertThat(haveDifferentHeights(asList(root, other))).isFalse();
    }

    @Test
    public void hasSameHeightAsAHorizontallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX+100, cornerY);

        assertThat(hasEqualHeightAs(root, other)).isTrue();
        assertThat(hasEqualHeightAs(root, singletonList(other))).isTrue();
        assertThat(haveEqualHeight(asList(root, other))).isTrue();
        assertThat(haveDifferentHeights(asList(root, other))).isFalse();
    }

    @Test
    public void doesNotHaveSameHeightAsAVerticallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(hasEqualHeightAs(root, other)).isFalse();
        assertThat(hasEqualHeightAs(root, singletonList(other))).isFalse();
        assertThat(haveEqualHeight(asList(root, other))).isFalse();
        assertThat(haveDifferentHeights(asList(root, other))).isTrue();
        assertThat(haveDifferentHeights(asList(root, other, root))).isFalse();
    }

    @Test
    public void hasSameSizeAsATranslatedElement() {
        WebElement other = DummyWebElement.createElement(originX+10, originY+5, cornerX+10, cornerY+5);

        assertThat(hasEqualSizeAs(root, other)).isTrue();
        assertThat(hasEqualSizeAs(root, singletonList(other))).isTrue();
        assertThat(haveEqualSize(asList(root, other))).isTrue();
        assertThat(hasDifferentSizeAs(root, other)).isFalse();
        assertThat(hasDifferentSizeAs(root, singletonList(other))).isFalse();
        assertThat(haveDifferentSizes(asList(root, other))).isFalse();
    }

    @Test
    public void hasSameSizeAsItSelf() {
        WebElement other = root;

        assertThat(hasEqualSizeAs(root, other)).isTrue();
        assertThat(hasEqualSizeAs(root, singletonList(other))).isTrue();
        assertThat(haveEqualSize(asList(root, other))).isTrue();
        assertThat(hasDifferentSizeAs(root, other)).isFalse();
        assertThat(hasDifferentSizeAs(root, singletonList(other))).isFalse();
        assertThat(haveDifferentSizes(asList(root, other))).isFalse();
    }

    @Test
    public void doesNotHaveSameSizeAsAHorinzotallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(hasEqualSizeAs(root, other)).isFalse();
        assertThat(hasEqualSizeAs(root, singletonList(other))).isFalse();
        assertThat(haveEqualSize(asList(root, other))).isFalse();
        assertThat(hasDifferentSizeAs(root, other)).isTrue();
        assertThat(hasDifferentSizeAs(root, singletonList(other))).isTrue();
        assertThat(haveDifferentSizes(asList(root, other))).isTrue();
        assertThat(haveDifferentSizes(asList(root, other, root))).isFalse();
    }

    @Test
    public void doesNotHaveSameSizeAsAVerticallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(hasEqualSizeAs(root, other)).isFalse();
        assertThat(hasEqualSizeAs(root, singletonList(other))).isFalse();
        assertThat(haveEqualSize(asList(root, other))).isFalse();
        assertThat(hasDifferentSizeAs(root, other)).isTrue();
        assertThat(hasDifferentSizeAs(root, singletonList(other))).isTrue();
        assertThat(haveDifferentSizes(asList(root, other))).isTrue();
    }

    @Test
    public void hasHeightBetweenWorks() {
        assertThat(hasHeightBetween(root, height-10-1, height-1)).isFalse();
        assertThat(hasHeightBetween(root, height-10, height)).isTrue();
        assertThat(hasHeightBetween(root, height-10+1, height+1)).isTrue();
        assertThat(hasHeightBetween(root, height-1, height+10-1)).isTrue();
        assertThat(hasHeightBetween(root, height, height+10)).isTrue();
        assertThat(hasHeightBetween(root, height+1, height+10+1)).isFalse();
    }

    @Test
    public void hasWidthBetweenWorks() {
        assertThat(hasWidthBetween(root, width-10-1, width-1)).isFalse();
        assertThat(hasWidthBetween(root, width-10, width)).isTrue();
        assertThat(hasWidthBetween(root, width-10+1, width+1)).isTrue();
        assertThat(hasWidthBetween(root, width-1, width+10-1)).isTrue();
        assertThat(hasWidthBetween(root, width, width+10)).isTrue();
        assertThat(hasWidthBetween(root, width+1, width+10+1)).isFalse();
    }

    @Test
    public void hasWidthGreaterOrEqualToWork() {
        assertThat(hasWidthGreaterOrEqualTo(root, width-1)).isTrue();
        assertThat(hasWidthGreaterOrEqualTo(root, width)).isTrue();
        assertThat(hasWidthGreaterOrEqualTo(root, width+1)).isFalse();
    }

    @Test
    public void hasWidthLessOrEqualToWorks() {
        assertThat(hasWidthLessOrEqualTo(root, width-1)).isFalse();
        assertThat(hasWidthLessOrEqualTo(root, width)).isTrue();
        assertThat(hasWidthLessOrEqualTo(root, width+1)).isTrue();
    }

    @Test
    public void hasHeightGreaterOrEqualToWorks() {
        assertThat(hasHeightGreaterOrEqualTo(root, height-1)).isTrue();
        assertThat(hasHeightGreaterOrEqualTo(root, height)).isTrue();
        assertThat(hasHeightGreaterOrEqualTo(root, height+1)).isFalse();
    }

    @Test
    public void hasHeightLessOrEqualToWorks() {
        assertThat(hasHeightLessOrEqualTo(root, height-1)).isFalse();
        assertThat(hasHeightLessOrEqualTo(root, height)).isTrue();
        assertThat(hasHeightLessOrEqualTo(root, height+1)).isTrue();
    }
}
