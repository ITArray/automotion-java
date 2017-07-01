package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.Direction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @Test
    public void upIsDownsOposite() {
        assertThat(Direction.DOWN.oposite()).isEqualTo(Direction.UP);
    }

    @Test
    public void downIsUpsOposite() {
        assertThat(Direction.UP.oposite()).isEqualTo(Direction.DOWN);
    }

    @Test
    public void leftIsRightsOposite() {
        assertThat(Direction.RIGHT.oposite()).isEqualTo(Direction.LEFT);
    }

    @Test
    public void rightIsLeftsOposite() {
        assertThat(Direction.LEFT.oposite()).isEqualTo(Direction.RIGHT);
    }
}
