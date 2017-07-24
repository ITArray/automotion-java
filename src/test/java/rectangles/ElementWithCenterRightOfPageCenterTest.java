package rectangles;

import net.itarray.automotion.internal.geometry.Vector;
import org.junit.Test;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.TestAssumptions.*;

public class ElementWithCenterRightOfPageCenterTest extends ElementWithCenterOffsetFromCenterOfPageTest {

    @Override
    protected Vector getCenterOffset() {
        return new Vector(+2, 0);
    }

    @Test
    public void rightOfCentered() {
        assertThat(isCenteredOnPageHorizontally(element)).isFalse();
        assertThat(areCenteredOnPageVertically(singletonList(element))).isFalse();
        assertThat(isCenteredOnPageVertically(element)).isTrue();
        assertThat(areCenteredOnPageHorizontally(singletonList(element))).isTrue();
    }
}
