package rectangles;

import net.itarray.automotion.internal.geometry.Vector;
import org.junit.Test;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.TestAssumptions.*;

public class ElementWithCenterBelowCenterOfPageTest extends ElementWithCenterOffsetFromCenterOfPageTest {

    @Override
    protected Vector getCenterOffset() {
        return new Vector(0, +2);
    }

    @Test
    public void bottomOfCentered() {
        assertThat(isCenteredOnPageHorizontally(element)).isTrue();
        assertThat(areCenteredOnPageVertically(singletonList(element))).isTrue();
        assertThat(isCenteredOnPageVertically(element)).isFalse();
        assertThat(areCenteredOnPageHorizontally(singletonList(element))).isFalse();
    }
}
