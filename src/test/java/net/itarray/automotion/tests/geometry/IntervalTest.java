package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntervalTest {

    private Scalar begin;
    private Scalar end;
    private Interval interval;

    @Before
    public void setUp() {
        begin = new Scalar(10);
        end = new Scalar(17);
        interval = new Interval(begin, end);
    }

    @Test
    public void isEqualToIntervalsWithEqualBeginAndEnd() {
        assertThat(interval).isEqualTo(new Interval(begin, end));
        assertThat(interval.hashCode()).isEqualTo(new Interval(begin, end).hashCode());
    }

    @Test
    public void isNotEqualToIntervalsWithDifferentBegin() {
        assertThat(interval).isNotEqualTo(new Interval(begin.plus(1), end));
    }

    @Test
    public void isNotEqualToIntervalsWithDifferentEnd() {
        assertThat(interval).isNotEqualTo(new Interval(begin, end.plus(1)));
    }

    @Test
    public void isNotEmpty() {
        assertThat(interval.isEmpty()).isFalse();
    }

    @Test
    public void isNotEqualToEmptyIntervals() {
        assertThat(interval).isNotEqualTo(new Interval(begin, begin));
    }

    @Test
    public void intersectionWithIntervalBeforeIsEmpty() {
        Interval intersection = interval.intersect(new Interval(begin.minus(10), begin.minus(5)));
        assertThat(intersection.isEmpty()).isTrue();
    }

    @Test
    public void intersectionWithIntervalBeforeButTouchingIsEmpty() {
        Interval intersection = interval.intersect(new Interval(begin.minus(10), begin));
        assertThat(intersection.isEmpty()).isTrue();
    }

    @Test
    public void intersectionWithIntervalContainingBeginIsNotEmpty() {
        Interval intersection = interval.intersect(new Interval(begin.minus(10), begin.plus(1)));
        assertThat(intersection.isEmpty()).isFalse();
        assertThat(intersection).isEqualTo(new Interval(begin, begin.plus(1)));
    }

    @Test
    public void intersectionWithIntervalContainingAPointJustBeforeEndIsNotEmpty() {
        Interval intersection = interval.intersect(new Interval(end.minus(1), end.plus(10)));
        assertThat(intersection.isEmpty()).isFalse();
        assertThat(intersection).isEqualTo(new Interval(end.minus(1), end));
    }

    @Test
    public void intersectionWithIntervalAfterIsEmpty() {
        Interval intersection = interval.intersect(new Interval(end.plus(5), end.plus(10)));
        assertThat(intersection.isEmpty()).isTrue();
    }

    @Test
    public void intersectionWithIntervalAfterButTouchingIsEmpty() {
        Interval intersection = interval.intersect(new Interval(end, end.plus(10)));
        assertThat(intersection.isEmpty()).isTrue();
    }

}
