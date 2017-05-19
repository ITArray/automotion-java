package rectangles;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createRootElement;
import static rectangles.RectangleFixture.*;
import static rectangles.TestAssumptions.sameHeightAs;
import static rectangles.TestAssumptions.sameWidthAs;

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
    }

    @Test
    public void hasSameWidthAsAVerticallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(sameWidthAs(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveSameWidthAsAHorizontallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX+100, cornerY);

        assertThat(sameWidthAs(root, other)).isFalse();
    }

    @Test
    public void hasSameHeightAsATranslatedElement() {
        WebElement other = DummyWebElement.createElement(originX+10, originY+5, cornerX+10, cornerY+5);

        assertThat(sameHeightAs(root, other)).isTrue();
    }

    @Test
    public void hasSameHeightAsAHorizontallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX+100, cornerY);

        assertThat(sameHeightAs(root, other)).isTrue();
    }

    @Test
    public void doesNotHaveSameHeightAsAVerticallyStretchedElement() {
        WebElement other = DummyWebElement.createElement(originX, originY, cornerX, cornerY+100);

        assertThat(sameHeightAs(root, other)).isFalse();
    }
}
