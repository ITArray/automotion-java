package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainedIntervalsTest extends TwoIntervalsTest{

    public Interval left() {
        return new Interval(new Scalar(12), new Scalar(15));
    }

    public Interval right() {
        return new Interval(new Scalar(20), new Scalar(20));
    }

    @Test
    public void haveEmptyIntersection() {
        assertThat(right().intersect(left()).isEmpty()).isTrue();
    }

    @Test
    public void haveSpanEqualToTheNonEmptyInterval() {
        Interval span = left();
        assertThat(right().span(left())).isEqualTo(span);
        assertThat(left().span(right())).isEqualTo(span);
    }
}
