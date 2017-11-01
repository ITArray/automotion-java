package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Interval.interval;
import static org.assertj.core.api.Assertions.assertThat;

public class DisjointIntervalsTest extends TwoIntervalsTest{

    public Interval left() {
        return interval(2, 8);
    }

    public Interval right() {
        return interval(10, 17);
    }

    @Test
    public void haveEmptyIntersection() {
        assertThat(right().intersect(left()).isEmpty()).isTrue();
    }

    @Test
    public void haveSpanFromSmallestBeginToLargestEnd() {
        Interval span = interval(2, 17);
        assertThat(right().span(left())).isEqualTo(span);
        assertThat(left().span(right())).isEqualTo(span);
    }
}
