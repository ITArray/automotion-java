package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Interval.interval;
import static org.assertj.core.api.Assertions.assertThat;

public class OverlappingIntervalsTest extends TwoIntervalsTest{

    public Interval left() {
        return interval(2, 12);
    }

    public Interval right() {
        return interval(10, 17);
    }

    @Test
    public void haveIntersectionBeginningAtLargerStartAndEndingAtSmallerEnd() {
        assertThat(right().intersect(left())).isEqualTo(interval(10, 12));
    }
    @Test
    public void haveSpanFromSmallestBeginToLargestEnd() {
        Interval span = interval(2, 17);
        assertThat(right().span(left())).isEqualTo(span);
        assertThat(left().span(right())).isEqualTo(span);
    }
}
