package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Interval.interval;
import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class OverlappingIntervalsTest extends TwoIntervalsTest{

    public Interval left() {
        return interval(scalar(2), scalar(12));
    }

    public Interval right() {
        return interval(scalar(10), scalar(17));
    }

    @Test
    public void haveIntersectionBeginningAtLargerStartAndEndingAtSmallerEnd() {
        assertThat(right().intersect(left())).isEqualTo(interval(scalar(10), scalar(12)));
    }
    @Test
    public void haveSpanFromSmallestBeginToLargestEnd() {
        Interval span = interval(scalar(2), scalar(17));
        assertThat(right().span(left())).isEqualTo(span);
        assertThat(left().span(right())).isEqualTo(span);
    }
}
