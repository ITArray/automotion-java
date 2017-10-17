package net.itarray.automotion.internal.geometry;

import java.util.Objects;

import static java.lang.String.format;

public class Interval {
    private final Scalar begin;
    private final Scalar end;

    public Interval(Scalar begin, Scalar end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return format("[%s, %s[", begin, end);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Interval)) {
            return false;
        }
        Interval other = (Interval) object;
        if (isEmpty()) {
            return other.isEmpty();
        }
        return begin.equals(other.begin) && end.equals(other.end);
    }

    @Override
    public int hashCode() {
        return isEmpty()? 0 : Objects.hash(begin, end);
    }

    public boolean isEmpty() {
        return end.isLessOrEqualTo(begin);
    }

    public Interval intersect(Interval interval) {
        return new Interval(
                begin.isLessOrEqualTo(interval.begin) ? interval.begin : begin,
                end.isLessOrEqualTo(interval.end) ? end : interval.end);
    }
}
