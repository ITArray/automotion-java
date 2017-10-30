package net.itarray.automotion.tests.grid;

import net.itarray.automotion.internal.geometry.Partition;
import net.itarray.automotion.internal.geometry.Scalar;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.FractionFormat;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class PartitionTest {

    @Test
    public void emptyListsProduceEmptyPartitions() {
        assertPartitions(
                asList());
    }

    @Test
    public void objectsWithDistancesLessOrEqualToOneGoIntoTheSamePartition() {
        assertPartitions(
                asList("1", "2", "20", "21", "22"),
                asList("1", "2"), asList("20", "21", "22"));
    }

    @Test
    public void singleObjectAtEnd() {
        assertPartitions(
                asList("1", "2", "20", "21", "22", "25"),
                asList("1", "2"), asList("20", "21", "22"), asList("25"));
    }

    @Test
    public void objectsAreOrderedAccordingToThereValues() {
        assertPartitions(
                asList("20", "2", "1"),
                asList("1", "2"), asList("20"));
    }

    private void assertPartitions(List<Object> objects, List<Object>... parts) {
        assertPartitions(objects, Arrays.asList(parts));

    }

    private void assertPartitions(List<Object> objects, List<List<Object>> expected) {
        Function<Object, Scalar> property = element -> ((Scalar) element);
        Partition<Object> partition = new Partition<>(objects, property);
        List<List<Object>> partitions = partition.getPartitions();
        assertThat(partitions).isEqualTo(expected);
    }


    private List<Object> asList(String... objects) {
        FractionFormat format = new FractionFormat();
        return Arrays.stream(objects).map(s->(Object) scalar(format.parse(s))).collect(Collectors.toList());
    }

    private Fraction fraction(String literal) {
        FractionFormat format = new FractionFormat();
        return format.parse(literal);
    }
}
