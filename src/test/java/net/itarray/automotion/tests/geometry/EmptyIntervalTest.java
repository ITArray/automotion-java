package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyIntervalTest {

    private Scalar begin;
    private Scalar end;
    private Interval interval;

    @Before
    public void setUp() {
        begin = new Scalar(17);
        end = new Scalar(10);
        interval = new Interval(begin, end);
    }

    @Test
    public void isEmpty() {
        assertThat(interval.isEmpty()).isTrue();
    }

    @Test
    public void isEqualToIntervalsOtherEmptyIntervals() {
        assertThat(interval).isEqualTo(new Interval(new Scalar(100), new Scalar(99)));
        assertThat(interval.hashCode()).isEqualTo(new Interval(new Scalar(100), new Scalar(99)).hashCode());
    }

    @Test
    public void isNotEqualToNonEmptyIntervals() {
        assertThat(interval).isNotEqualTo(new Interval(new Scalar(10), new Scalar(20)));
    }

}
