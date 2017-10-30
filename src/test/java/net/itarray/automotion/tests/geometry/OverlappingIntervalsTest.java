package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Interval.interval;
import static org.assertj.core.api.Assertions.assertThat;

public class OverlappingIntervalsTest extends TwoIntervalsTest{

    public Interval left() {
        return interval(new Scalar(2), new Scalar(12));
    }

    public Interval right() {
        return interval(new Scalar(10), new Scalar(17));
    }

    @Test
    public void haveIntersectionBeginningAtLargerStartAndEndingAtSmallerEnd() {
        assertThat(right().intersect(left())).isEqualTo(interval(new Scalar(10), new Scalar(12)));
    }
    @Test
    public void haveSpanFromSmallestBeginToLargestEnd() {
        Interval span = interval(new Scalar(2), new Scalar(17));
        assertThat(right().span(left())).isEqualTo(span);
        assertThat(left().span(right())).isEqualTo(span);
    }
}
