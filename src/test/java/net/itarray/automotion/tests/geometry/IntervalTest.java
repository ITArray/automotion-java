package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Interval.interval;
import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class IntervalTest {

    private Scalar begin;
    private Scalar end;
    private Interval interval;

    @Before
    public void setUp() {
        begin = scalar(10);
        end = scalar(17);
        interval = interval(begin, end);
    }

    @Test
    public void isEqualToIntervalsWithEqualBeginAndEnd() {
        assertThat(interval).isEqualTo(interval(begin, end));
        assertThat(interval.hashCode()).isEqualTo(interval(begin, end).hashCode());
    }

    @Test
    public void isNotEqualToIntervalsWithDifferentBegin() {
        assertThat(interval).isNotEqualTo(interval(begin.plus(1), end));
    }

    @Test
    public void isNotEqualToIntervalsWithDifferentEnd() {
        assertThat(interval).isNotEqualTo(interval(begin, end.plus(1)));
    }

    @Test
    public void isNotEmpty() {
        assertThat(interval.isEmpty()).isFalse();
    }

    @Test
    public void isNotEqualToEmptyIntervals() {
        assertThat(interval).isNotEqualTo(interval(begin, begin));
    }
}
