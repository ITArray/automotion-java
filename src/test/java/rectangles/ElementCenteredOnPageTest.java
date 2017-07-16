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
        assertThat(hasTopOffsetToPageGreaterOrEqualTo(element, yOffset-1)).isTrue();
        assertThat(hasTopOffsetToPageGreaterOrEqualTo(element, yOffset)).isTrue();
        assertThat(hasTopOffsetToPageGreaterOrEqualTo(element, yOffset+1)).isFalse();

        assertThat(maxOffset(element, yOffset-1, xOffset, yOffset, xOffset)).isFalse();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(maxOffset(element, yOffset+1, xOffset, yOffset, xOffset)).isTrue();
        assertThat(hasTopOffsetToPageLessOrEqualTo(element, yOffset-1)).isFalse();
        assertThat(hasTopOffsetToPageLessOrEqualTo(element, yOffset)).isTrue();
        assertThat(hasTopOffsetToPageLessOrEqualTo(element, yOffset+1)).isTrue();
    }

    @Test
    public void rightOffset() {
        assertThat(minOffset(element, yOffset, xOffset-1, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset+1, yOffset, xOffset)).isFalse();
        assertThat(hasRightOffsetToPageGreaterOrEqualTo(element, xOffset-1)).isTrue();
        assertThat(hasRightOffsetToPageGreaterOrEqualTo(element, xOffset)).isTrue();
        assertThat(hasRightOffsetToPageGreaterOrEqualTo(element, xOffset+1)).isFalse();

        assertThat(maxOffset(element, yOffset, xOffset-1, yOffset, xOffset)).isFalse();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(maxOffset(element, yOffset, xOffset+1, yOffset, xOffset)).isTrue();
        assertThat(hasRightOffsetToPageLessOrEqualTo(element, xOffset-1)).isFalse();
        assertThat(hasRightOffsetToPageLessOrEqualTo(element, xOffset)).isTrue();
        assertThat(hasRightOffsetToPageLessOrEqualTo(element, xOffset+1)).isTrue();
    }

    @Test
    public void bottomOffset() {
        assertThat(minOffset(element, yOffset, xOffset, yOffset-1, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset+1, xOffset)).isFalse();
        assertThat(hasBottomOffsetToPageGreaterOrEqualTo(element, yOffset-1)).isTrue();
        assertThat(hasBottomOffsetToPageGreaterOrEqualTo(element, yOffset)).isTrue();
        assertThat(hasBottomOffsetToPageGreaterOrEqualTo(element, yOffset+1)).isFalse();

        assertThat(maxOffset(element, yOffset, xOffset, yOffset-1, xOffset)).isFalse();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset+1, xOffset)).isTrue();
        assertThat(hasBottomOffsetToPageLessOrEqualTo(element, yOffset-1)).isFalse();
        assertThat(hasBottomOffsetToPageLessOrEqualTo(element, yOffset)).isTrue();
        assertThat(hasBottomOffsetToPageLessOrEqualTo(element, yOffset+1)).isTrue();
    }

    @Test
    public void leftOffset() {
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset-1)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(minOffset(element, yOffset, xOffset, yOffset, xOffset+1)).isFalse();
        assertThat(hasLeftOffsetToPageGreaterOrEqualTo(element, xOffset-1)).isTrue();
        assertThat(hasLeftOffsetToPageGreaterOrEqualTo(element, xOffset)).isTrue();
        assertThat(hasLeftOffsetToPageGreaterOrEqualTo(element, xOffset+1)).isFalse();

        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset-1)).isFalse();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset)).isTrue();
        assertThat(maxOffset(element, yOffset, xOffset, yOffset, xOffset+1)).isTrue();
        assertThat(hasLeftOffsetToPageLessOrEqualTo(element, xOffset-1)).isFalse();
        assertThat(hasLeftOffsetToPageLessOrEqualTo(element, xOffset)).isTrue();
        assertThat(hasLeftOffsetToPageLessOrEqualTo(element, xOffset+1)).isTrue();
    }

    @Test
    public void centered() {
        assertThat(equalLeftRightOffset(element)).isTrue();
        assertThat(equalLeftRightOffset(singletonList(element))).isTrue();
        assertThat(equalTopBottomOffset(element)).isTrue();
        assertThat(equalTopBottomOffset(singletonList(element))).isTrue();
    }
}
