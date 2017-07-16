package rectangles;

import net.itarray.automotion.internal.geometry.Vector;
import org.junit.Test;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.TestAssumptions.*;

public class ElementCenteredOnPageTest extends ElementWithCenterOffsetFromCenterOfPageTest {

    @Override
    protected Vector getCenterOffset() {
        return new Vector(0, 0);
    }

    @Test
    public void topOffset() {
        assertThat(minOffset(element, yOffset-1, xOffset, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset+1, xOffset, yOffset, xOffset)).isFalse();

        assertThat(maxOffset(element, yOffset-1, xOffset, yOffset, xOffset)).isFalse();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(maxOffset(element, yOffset+1, xOffset, yOffset, xOffset)).isTrue();
    }

    @Test
    public void rightOffset() {
        assertThat(minOffset(element, yOffset, xOffset-1, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset+1, yOffset, xOffset)).isFalse();

        assertThat(maxOffset(element, yOffset, xOffset-1, yOffset, xOffset)).isFalse();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(maxOffset(element, yOffset, xOffset+1, yOffset, xOffset)).isTrue();
    }

    public void bottomOffset() {
        assertThat(minOffset(element, yOffset, xOffset, yOffset-1, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset+1, xOffset)).isFalse();

        assertThat(maxOffset(element, yOffset, xOffset, yOffset-1, xOffset)).isFalse();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset+1, xOffset)).isTrue();
    }

    public void leftOffset() {
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset-1)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset+1)).isFalse();

        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset-1)).isFalse();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset+1)).isTrue();
    }

    @Test
    public void centered() {
        assertThat(equalLeftRightOffset(element)).isTrue();
        assertThat(equalLeftRightOffset(singletonList(element))).isTrue();
        assertThat(equalTopBottomOffset(element)).isTrue();
        assertThat(equalTopBottomOffset(singletonList(element))).isTrue();
    }

}
