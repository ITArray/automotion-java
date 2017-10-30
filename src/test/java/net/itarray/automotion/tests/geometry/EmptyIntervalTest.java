package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Interval.interval;
import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class EmptyIntervalTest {

    private Scalar begin;
    private Scalar end;
    private Interval interval;

    @Before
    public void setUp() {
        begin = scalar(17);
        end = scalar(10);
        interval = interval(begin, end);
    }

    @Test
    public void isEmpty() {
        assertThat(interval.isEmpty()).isTrue();
    }

    @Test
    public void isEqualToIntervalsOtherEmptyIntervals() {
        assertThat(interval).isEqualTo(interval(scalar(100), scalar(99)));
        assertThat(interval.hashCode()).isEqualTo(interval(scalar(100), scalar(99)).hashCode());
    }

    @Test
    public void isNotEqualToNonEmptyIntervals() {
        assertThat(interval).isNotEqualTo(interval(scalar(10), scalar(20)));
    }

}
