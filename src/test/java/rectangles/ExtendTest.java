package rectangles;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Collections;

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
    public void heightBetweenWorks() {
        assertThat(heightBetween(root, height-10-1, height-1)).isFalse();
        assertThat(heightBetween(root, height-10, height)).isTrue();
        assertThat(heightBetween(root, height-10+1, height+1)).isTrue();
        assertThat(heightBetween(root, height-1, height+10-1)).isTrue();
        assertThat(heightBetween(root, height, height+10)).isTrue();
        assertThat(heightBetween(root, height+1, height+10+1)).isFalse();
    }

    @Test
    public void widthBetweenWorks() {
        assertThat(widthBetween(root, width-10-1, width-1)).isFalse();
        assertThat(widthBetween(root, width-10, width)).isTrue();
        assertThat(widthBetween(root, width-10+1, width+1)).isTrue();
        assertThat(widthBetween(root, width-1, width+10-1)).isTrue();
        assertThat(widthBetween(root, width, width+10)).isTrue();
        assertThat(widthBetween(root, width+1, width+10+1)).isFalse();
    }

    @Test
    public void minWidthWorks() {
        assertThat(minWidth(root, width-1)).isTrue();
        assertThat(minWidth(root, width)).isTrue();
        assertThat(minWidth(root, width+1)).isFalse();
    }

    @Test
    public void maxWidthWorks() {
        assertThat(maxWidth(root, width-1)).isFalse();
        assertThat(maxWidth(root, width)).isTrue();
        assertThat(maxWidth(root, width+1)).isTrue();
    }

    @Test
    public void minHeightWorks() {
        assertThat(minHeight(root, height-1)).isTrue();
        assertThat(minHeight(root, height)).isTrue();
        assertThat(minHeight(root, height+1)).isFalse();
    }

    @Test
    public void maxHeightWorks() {
        assertThat(maxHeight(root, height-1)).isFalse();
        assertThat(maxHeight(root, height)).isTrue();
        assertThat(maxHeight(root, height+1)).isTrue();
    }
}
