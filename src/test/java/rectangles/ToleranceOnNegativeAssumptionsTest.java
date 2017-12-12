package rectangles;

import org.assertj.core.api.AbstractBooleanAssert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.TestAssumptions.*;

public class ToleranceOnNegativeAssumptionsTest {

    private final int x = 100;
    private final int y = 1000;
    private final int width = 200;
    private final int height = 300;
    private WebElement root;

    @Before
    public void setUp() {
        root = createElement(x, y);
    }

    public WebElement createElement(int x, int y) {
        return DummyWebElement.createElement(x, y, x + width, y + height);
    }

    @Test
    public void isNotOverlappingWithTolerance() {
        assertThatNotOverlappingWithTolerance(x+width, y).isTrue();
        assertThatNotOverlappingWithTolerance(x+width-1, y).isTrue();
        assertThatNotOverlappingWithTolerance(x+width-2, y).isFalse();
        assertThatNotOverlappingWithTolerance(x-width, y).isTrue();
        assertThatNotOverlappingWithTolerance(x-width+1, y).isTrue();
        assertThatNotOverlappingWithTolerance(x-width+2, y).isFalse();
        assertThatNotOverlappingWithTolerance(x, y+height).isTrue();
        assertThatNotOverlappingWithTolerance(x, y+height-1).isTrue();
        assertThatNotOverlappingWithTolerance(x, y+height-2).isFalse();
        assertThatNotOverlappingWithTolerance(x, y-height).isTrue();
        assertThatNotOverlappingWithTolerance(x, y-height+1).isTrue();
        assertThatNotOverlappingWithTolerance(x, y-height+2).isFalse();
    }

    @Test
    public void hasDifferentSizeAsWithTolerance() {
        assertThatHasDifferentSizeWithTolerance(-2, 0).isTrue();
        assertThatHasDifferentSizeWithTolerance(-1, 0).isFalse();
        assertThatHasDifferentSizeWithTolerance( 0, 0).isFalse();
        assertThatHasDifferentSizeWithTolerance(+1, 0).isFalse();
        assertThatHasDifferentSizeWithTolerance(0, +2).isTrue();
        assertThatHasDifferentSizeWithTolerance(0, -2).isTrue();
        assertThatHasDifferentSizeWithTolerance(0, -1).isFalse();
        assertThatHasDifferentSizeWithTolerance(0, +1).isFalse();
        assertThatHasDifferentSizeWithTolerance(0, +2).isTrue();
    }

    public AbstractBooleanAssert<?> assertThatHasDifferentSizeWithTolerance(int deltaWidth, int deltaHeight) {
        WebElement element = DummyWebElement.createElement(x, y, x + width + deltaWidth, y + height + deltaHeight);
        return assertThat(hasDifferentSizeAs(root, element, 1) &&
                hasDifferentSizeAs(root, asList(element), 1) &&
                haveDifferentSizes(asList(root, element), 1));
    }

    private AbstractBooleanAssert<?> assertThatNotOverlappingWithTolerance(int x, int y) {
        WebElement element = createElement(x, y);
        return assertThat(isNotOverlapping(root, element, 1) &&
                doNotOverlap(asList(root, element), 1) &&
                isNotOverlapping(root, asList(element), 1));
    }


}
