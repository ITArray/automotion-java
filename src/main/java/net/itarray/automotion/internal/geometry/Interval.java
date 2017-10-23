package net.itarray.automotion.internal.geometry;

import java.util.Objects;

public class Interval {
    private final Scalar begin;
    private final Scalar end;

    public Interval(Scalar begin, Scalar end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return format("-", "-");
        }
        return format(begin.toString(), end.toString());
    }

    private String format(String begin, String end) {
        return String.format("[%s, %s[", begin, end);
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
                begin.max(interval.begin),
                end.min(interval.end));
    }

    public Interval span(Interval interval) {
        if (isEmpty()) {
            return interval;
        }
        if (interval.isEmpty()) {
            return this;
        }
        return new Interval(
                begin.min(interval.begin),
                end.max(interval.end));
    }
}
