package net.itarray.automotion.internal.geometry;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;

public class ConnectedIntervals {
    private final List<Interval> components;

    public ConnectedIntervals(List<Interval> intervals) {
        this.components = Lists.newArrayList();
        ArrayList<Interval> sorted = new ArrayList<>(intervals);
        Comparator<Interval> comparator = Interval.comparator();
        Collections.sort(sorted, comparator);
        int i = 0;
        while (i < sorted.size()) {
            Interval interval = sorted.get(i);
            i++;
            while (i < sorted.size() && !interval.intersect(sorted.get(i)).isEmpty()) {
                interval = interval.span(sorted.get(i));
                i++;
            }
            components.add(interval);
        }
    }

    public int size() {
        return components.size();
    }

    public int indexOf(Interval interval) {
        for (int i = 0; i < components.size(); i++) {
            Interval component = components.get(i);
            if (!component.intersect(interval).isEmpty()) {
                return i;
            }
        }
        throw new RuntimeException(format("interval %s is not in connected intervals", interval));
    }
}
