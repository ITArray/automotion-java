package rectangles;

import net.itarray.automotion.internal.geometry.Vector;
import org.junit.Test;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.TestAssumptions.*;

public class ElementWithCenterLeftOfPageCenterTest extends ElementWithCenterOffsetFromCenterOfPageTest {

    @Override
    protected Vector getCenterOffset() {
        return new Vector(-2, 0);
    }

    @Test
    public void leftOfCentered() {
        assertThat(equalLeftRightOffset(element)).isFalse();
        assertThat(equalLeftRightOffset(singletonList(element))).isFalse();
        assertThat(equalTopBottomOffset(element)).isTrue();
        assertThat(equalTopBottomOffset(singletonList(element))).isTrue();
    }
}
