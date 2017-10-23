package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class TwoIntervalsTest {

    public abstract Interval left();

    public abstract Interval right();

    @Test
    public void intersectIsSymmetric() {
        assertThat(left().intersect(right())).isEqualTo(right().intersect(left()));
    }

    @Test
    public void spanIsSymmetric() {
        assertThat(left().span(right())).isEqualTo(right().span(left()));
    }
}
