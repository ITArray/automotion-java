package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.UIElement;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;

public class DirectionsInRectanglesTest {

    private UIElement rectangle;
    private int originX = 7;
    private int cornerX = 42;
    private int originY = 13;
    private int cornerY = 117;

    @Before
    public void setUp() {
        rectangle = UIElement.asElement(createElement(originX, originY, cornerX, cornerY));
    }

    @Test
    public void rightBeginIsOriginX() {
        assertThat(rectangle.getBegin(RIGHT)).isEqualTo(originX);
    }

    @Test
    public void leftBeginIsCornerX() {
        assertThat(rectangle.getBegin(LEFT)).isEqualTo(cornerX);
    }

    @Test
    public void downBeginIsOriginY() {
        assertThat(rectangle.getBegin(DOWN)).isEqualTo(originY);
    }

    @Test
    public void upBeginIsCornerY() {
        assertThat(rectangle.getBegin(UP)).isEqualTo(cornerY);
    }

    @Test
    public void rightEndIsCornerX() {
        assertThat(rectangle.getEnd(RIGHT)).isEqualTo(cornerX);
    }

    @Test
    public void leftEndIsOriginX() {
        assertThat(rectangle.getEnd(LEFT)).isEqualTo(originX);
    }

    @Test
    public void downEndIsCornerY() {
        assertThat(rectangle.getEnd(DOWN)).isEqualTo(cornerY);
    }

    @Test
    public void upEndIsOriginY() {
        assertThat(rectangle.getEnd(UP)).isEqualTo(originY);
    }

    @Test
    public void rightExtendIsWidth() {
        assertThat(rectangle.getExtend(RIGHT)).isEqualTo(35);
    }

    @Test
    public void leftExtendIsNegativeWidth() {
        assertThat(rectangle.getExtend(LEFT)).isEqualTo(-35);
    }

    @Test
    public void downExtendIsHeight() {
        assertThat(rectangle.getExtend(DOWN)).isEqualTo(104);
    }

    @Test
    public void upExtendIsNegativeHeight() {
        assertThat(rectangle.getExtend(UP)).isEqualTo(-104);
    }

}
