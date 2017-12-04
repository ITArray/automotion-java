package net.itarray.automotion.internal.geometry;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;

public abstract class Interval {

    private Interval() {
    }

    public static Interval interval(Scalar begin, Scalar end) {
        if (begin.isGreaterOrEqualTo(end)) {
            return new Empty();
        }
        return new NonEmpty(begin, end);
    }

    public static Interval interval(int begin, int end) {
        return interval(scalar(begin), scalar(end));
    }

    private static String format(String begin, String end) {
        return String.format("[%s, %s[", begin, end);
    }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object object);

    @Override
    public abstract int hashCode();

    public abstract boolean isEmpty();

    public abstract Interval intersect(Interval interval);

    public abstract Interval intersectWithNonEmpty(NonEmpty interval);

    public abstract Interval span(Interval interval);

    public abstract Interval spanWithNonEmpty(NonEmpty interval);

    public static Comparator<Interval> comparator() {
        return new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return ((NonEmpty)o1).compareTo((NonEmpty)o2);
            }
        };
    }

    private static class Empty extends Interval {
        private Empty() {
        }

        @Override
        public String toString() {
            return format("-", "-");
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(Object object) {
            return object instanceof Empty;
        }

        public boolean isEmpty() {
            return true;
        }

        public Interval intersect(Interval interval) {
            return this;
        }

        @Override
        public Interval intersectWithNonEmpty(NonEmpty interval) {
            return this;
        }

        public Interval span(Interval interval) {
            return interval;
        }

        @Override
        public Interval spanWithNonEmpty(NonEmpty interval) {
            return interval;
        }
    }

    private static class NonEmpty extends Interval implements Comparable<NonEmpty> {
        private final Scalar begin;
        private final Scalar end;

        private NonEmpty(Scalar begin, Scalar end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public String toString() {
            return format(begin.toString(), end.toString());
        }

        @Override
        public int hashCode() {
            return Objects.hash(begin, end);
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof NonEmpty)) {
                return false;
            }
            NonEmpty other = (NonEmpty) object;
            return begin.equals(other.begin) && end.equals(other.end);
        }

        public boolean isEmpty() {
            return false;
        }

        @Override
        public Interval intersect(Interval interval) {
            return interval.intersectWithNonEmpty(this);
        }

        @Override
        public Interval intersectWithNonEmpty(NonEmpty interval) {
            return interval(
                    begin.max(interval.begin),
                    end.min(interval.end));
        }

        @Override
        public Interval span(Interval interval) {
            return interval.spanWithNonEmpty(this);
        }

        @Override
        public Interval spanWithNonEmpty(NonEmpty interval) {
            return interval(
                    begin.min(interval.begin),
                    end.max(interval.end));
        }

        @Override
        public int compareTo(NonEmpty other) {
            int c = begin.compareTo(other.begin);
            if (c != 0) {
                return c;
            }
            return end.compareTo(other.end);
        }
    }
}
