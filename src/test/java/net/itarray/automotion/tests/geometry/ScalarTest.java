package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.tests.properties.TestContext;
import net.itarray.automotion.validation.properties.Condition;
import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScalarTest {

    private int value;
    private Scalar scalar;

    @Before
    public void setUp() {
        value = 13;
        scalar = new Scalar(value);
    }

    @Test
    public void isEqualToScalarsWithEqualValue() {
        assertThat(scalar).isEqualTo(new Scalar(value));
        assertThat(scalar.hashCode()).isEqualTo(new Scalar(value).hashCode());
    }

    @Test
    public void isNotEqualToScalarsWithDifferentValue() {
        assertThat(scalar).isNotEqualTo(new Scalar(value+1));
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(scalar).isNotEqualTo(new Object());
    }

    @Test
    public void toStringWithUnitsAppendsTheUnitsToToString() {
        assertThat(scalar.toStringWithUnits("px")).isEqualTo("13px");
    }

    @Test
    public void getintValueReturnsTheConstructorParameterForInts() {
        assertThat(scalar.intValue()).isEqualTo(value);
    }

    @Test
    public void plusIntReturnsAScalarWithValueEqualToTheSumOfValueAndTheAddend() {
        int addend = 2;
        assertThat(scalar.plus(addend)).isEqualTo(new Scalar(value + addend));
    }

    @Test
    public void plusScalarReturnsAScalarWithValueEqualToTheSumOfValueAndTheAddend() {
        Scalar addend = new Scalar(2);
        assertThat(scalar.plus(addend)).isEqualTo(new Scalar(value + 2));
    }

    @Test
    public void minusIntReturnsAScalarWithValueEqualToTheDifferenceOfValueAndTheSubtrahend() {
        int addend = 2;
        assertThat(scalar.minus(addend)).isEqualTo(new Scalar(value - addend));
    }

    @Test
    public void minusScalarReturnsAScalarWithValueEqualToTheDifferenceOfValueAndTheSubtrahend() {
        Scalar addend = new Scalar(2);
        assertThat(scalar.minus(addend)).isEqualTo(new Scalar(value - 2));
    }

    @Test
    public void timesScalarReturnsAScalarWithValueEqualToTheProductOfValueAndTheMultiplicator() {
        Scalar multiplicator = new Scalar(2);
        assertThat(scalar.times(multiplicator)).isEqualTo(new Scalar(value * 2));
    }

    @Test
    public void byScalarReturnsAScalarWithValueEqualToTheQuotientOfValueAndTheDivisor() {
        Scalar divisor = new Scalar(2);
        assertThat(scalar.by(divisor)).isEqualTo(new Scalar(new Fraction(value, 2)));
    }

    @Test
    public void negatedReturnsAScalarWithValueEqualToNegatedValue() {
        assertThat(scalar.negated()).isEqualTo(new Scalar(-value));
    }

    @Test
    public void absReturnsAScalarWithValueEqualToAbosulteValue() {
        assertThat(scalar.abs()).isEqualTo(new Scalar(value));
        assertThat(scalar.negated().abs()).isEqualTo(new Scalar(value));
        assertThat(new Scalar(0).abs()).isEqualTo(new Scalar(0));
    }

    @Test
    public void isCompareToWorks() {
        assertThat(scalar.compareTo(new Scalar(value-1))).isGreaterThan(0);
        assertThat(scalar.compareTo(new Scalar(value))).isEqualTo(0);
        assertThat(scalar.compareTo(new Scalar(value+1))).isLessThan(0);
    }

    @Test
    public void isLessOrEqualToWorks() {
        assertThat(scalar.isLessOrEqualTo(new Scalar(value-1))).isFalse();
        assertThat(scalar.isLessOrEqualTo(new Scalar(value))).isTrue();
        assertThat(scalar.isLessOrEqualTo(new Scalar(value+1))).isTrue();
    }

    @Test
    public void isGreaterOrEqualToWorks() {
        assertThat(scalar.isGreaterOrEqualTo(new Scalar(value-1))).isTrue();
        assertThat(scalar.isGreaterOrEqualTo(new Scalar(value))).isTrue();
        assertThat(scalar.isGreaterOrEqualTo(new Scalar(value+1))).isFalse();
    }

    @Test
    public void isLessThanWorks() {
        assertThat(scalar.isLessThan(new Scalar(value-1))).isFalse();
        assertThat(scalar.isLessThan(new Scalar(value))).isFalse();
        assertThat(scalar.isLessThan(new Scalar(value+1))).isTrue();
    }

    @Test
    public void isGreaterThanWorks() {
        assertThat(scalar.isGreaterThan(new Scalar(value-1))).isTrue();
        assertThat(scalar.isGreaterThan(new Scalar(value))).isFalse();
        assertThat(scalar.isGreaterThan(new Scalar(value+1))).isFalse();
    }

    @Test
    public void shouldName() {
        TestContext context = new TestContext();
        Direction direction = Direction.RIGHT;
        assertThat(scalar.satisfies(Condition.greaterOrEqualTo(value), context, direction)).isTrue();
        assertThat(scalar.satisfies(Condition.greaterOrEqualTo(value+1), context, direction)).isFalse();
    }
}
