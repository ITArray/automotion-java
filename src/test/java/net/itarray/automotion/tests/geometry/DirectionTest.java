package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.Direction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @Test
    public void upIsDownsOposite() {
        assertThat(Direction.DOWN.opposite()).isEqualTo(Direction.UP);
    }

    @Test
    public void downIsUpsOposite() {
        assertThat(Direction.UP.opposite()).isEqualTo(Direction.DOWN);
    }

    @Test
    public void leftIsRightsOposite() {
        assertThat(Direction.RIGHT.opposite()).isEqualTo(Direction.LEFT);
    }

    @Test
    public void rightIsLeftsOposite() {
        assertThat(Direction.LEFT.opposite()).isEqualTo(Direction.RIGHT);
    }
}
