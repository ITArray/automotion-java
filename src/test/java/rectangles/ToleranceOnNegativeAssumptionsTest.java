package rectangles;

import com.google.common.collect.Lists;
import org.assertj.core.api.AbstractBooleanAssert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.TestAssumptions.doNotOverlap;
import static rectangles.TestAssumptions.isNotOverlapping;

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

    private AbstractBooleanAssert<?> assertThatNotOverlappingWithTolerance(int x, int y) {
        return assertThat(isNotOverlapping(root, createElement(x, y), 1) && doNotOverlap(asList(root, createElement(x, y)), 1));
    }


}
