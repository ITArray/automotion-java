package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DisjointIntervalsTest extends TwoIntervalsTest{

    public Interval left() {
        return new Interval(new Scalar(2), new Scalar(8));
    }

    public Interval right() {
        return new Interval(new Scalar(10), new Scalar(17));
    }

    @Test
    public void haveEmptyIntersection() {
        assertThat(right().intersect(left()).isEmpty()).isTrue();
    }

    @Test
    public void haveSpanFromSmallestBeginToLargestEnd() {
        Interval span = new Interval(new Scalar(2), new Scalar(17));
        assertThat(right().span(left())).isEqualTo(span);
        assertThat(left().span(right())).isEqualTo(span);
    }
}
