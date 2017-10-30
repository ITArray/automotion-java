package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Interval.interval;
import static org.assertj.core.api.Assertions.assertThat;

public class EmptyIntervalTest {

    private Scalar begin;
    private Scalar end;
    private Interval interval;

    @Before
    public void setUp() {
        begin = new Scalar(17);
        end = new Scalar(10);
        interval = interval(begin, end);
    }

    @Test
    public void isEmpty() {
        assertThat(interval.isEmpty()).isTrue();
    }

    @Test
    public void isEqualToIntervalsOtherEmptyIntervals() {
        assertThat(interval).isEqualTo(interval(new Scalar(100), new Scalar(99)));
        assertThat(interval.hashCode()).isEqualTo(interval(new Scalar(100), new Scalar(99)).hashCode());
    }

    @Test
    public void isNotEqualToNonEmptyIntervals() {
        assertThat(interval).isNotEqualTo(interval(new Scalar(10), new Scalar(20)));
    }

}
