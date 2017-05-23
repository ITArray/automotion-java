package rectangles;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;
import static rectangles.TestAssumptions.*;

public class OffsetTest {

    private int xOffset;
    private int yOffset;

    @Before
    public void setUp() {
        xOffset = 200;
        yOffset = 150;
    }

    @NotNull
    private WebElement createCenteredElement(int deltaX, int deltaY) {
        return createElement(
                xOffset + deltaX,
                yOffset + deltaY,
                RectangleFixture.pageWidth - xOffset + deltaX,
                RectangleFixture.pageHeight - yOffset + deltaY);
    }

    @Test
    public void centered() {
        WebElement element = createCenteredElement(0, 0);
        assertThat(equalLeftRightOffset(element)).isTrue();
        assertThat(equalLeftRightOffset(singletonList(element))).isTrue();
        assertThat(equalTopBottomOffset(element)).isTrue();
        assertThat(equalTopBottomOffset(singletonList(element))).isTrue();
    }

    @Test
    public void leftOfCentered() {
        WebElement element = createCenteredElement(-2, 0);
        assertThat(equalLeftRightOffset(element)).isFalse();
        assertThat(equalLeftRightOffset(singletonList(element))).isFalse();
        assertThat(equalTopBottomOffset(element)).isTrue();
        assertThat(equalTopBottomOffset(singletonList(element))).isTrue();
    }

    @Test
    public void rightOfCentered() {
        WebElement element = createCenteredElement(+2, 0);
        assertThat(equalLeftRightOffset(element)).isFalse();
        assertThat(equalLeftRightOffset(singletonList(element))).isFalse();
        assertThat(equalTopBottomOffset(element)).isTrue();
        assertThat(equalTopBottomOffset(singletonList(element))).isTrue();
    }

    @Test
    public void topOfCentered() {
        WebElement element = createCenteredElement(0, -2);
        assertThat(equalLeftRightOffset(element)).isTrue();
        assertThat(equalLeftRightOffset(singletonList(element))).isTrue();
        assertThat(equalTopBottomOffset(element)).isFalse();
        assertThat(equalTopBottomOffset(singletonList(element))).isFalse();
    }

    @Test
    public void bottomOfCentered() {
        WebElement element = createCenteredElement(0, +2);
        assertThat(equalLeftRightOffset(element)).isTrue();
        assertThat(equalLeftRightOffset(singletonList(element))).isTrue();
        assertThat(equalTopBottomOffset(element)).isFalse();
        assertThat(equalTopBottomOffset(singletonList(element))).isFalse();
    }
}
