package net.itarray.automotion.internal.geometry;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;

public class Partition <T> {

    private final List<List<T>> lists;

    public Partition(List<T> objects, Function<T, Scalar> property) {
        lists = new ArrayList<>();

        List<T> sorted = new ArrayList<>(objects);
        sorted.sort(Comparator.comparing(property));

        int j = 0;
        while (j < sorted.size()) {
            List<T> currentPart = Lists.newArrayList();
            lists.add(currentPart);
            do {
                currentPart.add(sorted.get(j));
                j++;
            } while (j < sorted.size() && continues(property, sorted.get(j), sorted.get(j-1)));
        }
    }

    public boolean continues(Function<T, Scalar> property, T candidate, T last) {
        Scalar lastValue = property.apply(last);
        Scalar candidateValue = property.apply(candidate);
        return candidateValue.minus(lastValue).isLessOrEqualTo(scalar(1));
    }

    public List<List<T>> getPartitions() {
        return lists;
    }


    private <T> List<T> asList(T... objects) {
        return Lists.newArrayList(objects);
    }

}
