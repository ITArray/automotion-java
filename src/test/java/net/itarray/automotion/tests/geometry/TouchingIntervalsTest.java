package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Interval.interval;
import static org.assertj.core.api.Assertions.assertThat;

public class TouchingIntervalsTest extends TwoIntervalsTest{

    public Interval left() {
        return interval(new Scalar(2), new Scalar(10));
    }

    public Interval right() {
        return interval(new Scalar(10), new Scalar(17));
    }

    @Test
    public void haveEmptyIntersection() {
        assertThat(right().intersect(left()).isEmpty()).isTrue();
    }

    @Test
    public void haveSpanFromSmallestBeginToLargestEnd() {
        Interval span = interval(new Scalar(2), new Scalar(17));
        assertThat(right().span(left())).isEqualTo(span);
        assertThat(left().span(right())).isEqualTo(span);
    }
}
