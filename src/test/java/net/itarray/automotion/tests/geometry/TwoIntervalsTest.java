package net.itarray.automotion.tests.geometry;

import com.google.common.collect.Lists;
import net.itarray.automotion.internal.geometry.ConnectedIntervals;
import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class TwoIntervalsTest {

    public abstract Interval left();

    public abstract Interval right();

    public List<Interval> leftAndRight() {
        return Lists.newArrayList(left(), right());
    }

    public List<Interval> rightAndLeft() {
        return Lists.newArrayList(right(), left());
    }

    @Test
    public void intersectIsSymmetric() {
        assertThat(left().intersect(right())).isEqualTo(right().intersect(left()));
    }

    @Test
    public void spanIsSymmetric() {
        assertThat(left().span(right())).isEqualTo(right().span(left()));
    }

    protected void assertComponentIndex(List<Interval> intervals, Interval interval, int expected) {
        ConnectedIntervals connectedIntervals = new ConnectedIntervals(intervals);
        int leftIndex = connectedIntervals.indexOf(interval);
        assertThat(leftIndex).isEqualTo(expected);
    }

}
