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

        assertThat(sameWidthAs(root, other)).isTrue();
        assertThat(sameWidthAs(root, singletonList(other))).isTrue();
        assertThat(withSameWidth(asList(root, other))).isTrue();
        assertThat(withNotSameWidth(asList(root, other))).isFalse();
    }

    @Test
    public void hasSameWidthAsAVerticallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(sameWidthAs(root, other)).isTrue();
        assertThat(sameWidthAs(root, singletonList(other))).isTrue();
        assertThat(withSameWidth(asList(root, other))).isTrue();
        assertThat(withNotSameWidth(asList(root, other))).isFalse();
    }

    @Test
    public void doesNotHaveSameWidthAsAHorizontallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX+100, cornerY);

        assertThat(sameWidthAs(root, other)).isFalse();
        assertThat(sameWidthAs(root, singletonList(other))).isFalse();
        assertThat(withSameWidth(asList(root, other))).isFalse();
        assertThat(withNotSameWidth(asList(root, other))).isTrue();
    }

    @Test
    public void hasSameHeightAsATranslatedElement() {
        WebElement other = DummyWebElement.createElement(originX+10, originY+5, cornerX+10, cornerY+5);

        assertThat(sameHeightAs(root, other)).isTrue();
        assertThat(sameHeightAs(root, singletonList(other))).isTrue();
        assertThat(withSameHeight(asList(root, other))).isTrue();
        assertThat(withNotSameHeight(asList(root, other))).isFalse();
    }

    @Test
    public void hasSameHeightAsAHorizontallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX+100, cornerY);

        assertThat(sameHeightAs(root, other)).isTrue();
        assertThat(sameHeightAs(root, singletonList(other))).isTrue();
        assertThat(withSameHeight(asList(root, other))).isTrue();
        assertThat(withNotSameHeight(asList(root, other))).isFalse();
    }

    @Test
    public void doesNotHaveSameHeightAsAVerticallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(sameHeightAs(root, other)).isFalse();
        assertThat(sameHeightAs(root, singletonList(other))).isFalse();
        assertThat(withSameHeight(asList(root, other))).isFalse();
        assertThat(withNotSameHeight(asList(root, other))).isTrue();
    }

    @Test
    public void hasSameSizeAsATranslatedElement() {
        WebElement other = DummyWebElement.createElement(originX+10, originY+5, cornerX+10, cornerY+5);

        assertThat(sameSizeAs(root, other)).isTrue();
        assertThat(sameSizeAs(root, singletonList(other))).isTrue();
        assertThat(withSameSize(asList(root, other))).isTrue();
        assertThat(notSameSizeAs(root, other)).isFalse();
        assertThat(notSameSizeAs(root, singletonList(other))).isFalse();
        assertThat(withNotSameSize(asList(root, other))).isFalse();
    }

    @Test
    public void hasSameSizeAsItSelf() {
        WebElement other = root;

        assertThat(sameSizeAs(root, other)).isTrue();
        assertThat(sameSizeAs(root, singletonList(other))).isTrue();
        assertThat(withSameSize(asList(root, other))).isTrue();
        assertThat(notSameSizeAs(root, other)).isFalse();
        assertThat(notSameSizeAs(root, singletonList(other))).isFalse();
        assertThat(withNotSameSize(asList(root, other))).isFalse();
    }

    @Test
    public void doesNotHaveSameSizeAsAHorinzotallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(sameSizeAs(root, other)).isFalse();
        assertThat(sameSizeAs(root, singletonList(other))).isFalse();
        assertThat(withSameSize(asList(root, other))).isFalse();
        assertThat(notSameSizeAs(root, other)).isTrue();
        assertThat(notSameSizeAs(root, singletonList(other))).isTrue();
        assertThat(withNotSameSize(asList(root, other))).isTrue();
    }

    @Test
    public void doesNotHaveSameSizeAsAVerticallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(sameSizeAs(root, other)).isFalse();
        assertThat(sameSizeAs(root, singletonList(other))).isFalse();
        assertThat(withSameSize(asList(root, other))).isFalse();
        assertThat(notSameSizeAs(root, other)).isTrue();
        assertThat(notSameSizeAs(root, singletonList(other))).isTrue();
        assertThat(withNotSameSize(asList(root, other))).isTrue();
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
