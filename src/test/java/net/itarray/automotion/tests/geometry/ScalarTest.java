package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.tests.properties.TestContext;
import net.itarray.automotion.validation.properties.Condition;
import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class ScalarTest {

    private int value;
    private Scalar scalar;

    @Before
    public void setUp() {
        value = 13;
        scalar = scalar(value);
    }

    @Test
    public void isEqualToScalarsWithEqualValue() {
        assertThat(scalar).isEqualTo(scalar(value));
        assertThat(scalar.hashCode()).isEqualTo(scalar(value).hashCode());
    }

    @Test
    public void isNotEqualToScalarsWithDifferentValue() {
        assertThat(scalar).isNotEqualTo(scalar(value+1));
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
        assertThat(scalar.plus(addend)).isEqualTo(scalar(value + addend));
    }

    @Test
    public void plusScalarReturnsAScalarWithValueEqualToTheSumOfValueAndTheAddend() {
        Scalar addend = scalar(2);
        assertThat(scalar.plus(addend)).isEqualTo(scalar(value + 2));
    }

    @Test
    public void minusIntReturnsAScalarWithValueEqualToTheDifferenceOfValueAndTheSubtrahend() {
        int addend = 2;
        assertThat(scalar.minus(addend)).isEqualTo(scalar(value - addend));
    }

    @Test
    public void minusScalarReturnsAScalarWithValueEqualToTheDifferenceOfValueAndTheSubtrahend() {
        Scalar addend = scalar(2);
        assertThat(scalar.minus(addend)).isEqualTo(scalar(value - 2));
    }

    @Test
    public void timesScalarReturnsAScalarWithValueEqualToTheProductOfValueAndTheMultiplicator() {
        Scalar multiplicator = scalar(2);
        assertThat(scalar.times(multiplicator)).isEqualTo(scalar(value * 2));
    }

    @Test
    public void byScalarReturnsAScalarWithValueEqualToTheQuotientOfValueAndTheDivisor() {
        Scalar divisor = scalar(2);
        assertThat(scalar.by(divisor)).isEqualTo(scalar(new Fraction(value, 2)));
    }

    @Test
    public void negatedReturnsAScalarWithValueEqualToNegatedValue() {
        assertThat(scalar.negated()).isEqualTo(scalar(-value));
    }

    @Test
    public void absReturnsAScalarWithValueEqualToAbosulteValue() {
        assertThat(scalar.abs()).isEqualTo(scalar(value));
        assertThat(scalar.negated().abs()).isEqualTo(scalar(value));
        assertThat(scalar(0).abs()).isEqualTo(scalar(0));
    }

    @Test
    public void isCompareToWorks() {
        assertThat(scalar.compareTo(scalar(value-1))).isGreaterThan(0);
        assertThat(scalar.compareTo(scalar(value))).isEqualTo(0);
        assertThat(scalar.compareTo(scalar(value+1))).isLessThan(0);
    }

    @Test
    public void isLessOrEqualToWorks() {
        assertThat(scalar.isLessOrEqualTo(scalar(value-1))).isFalse();
        assertThat(scalar.isLessOrEqualTo(scalar(value))).isTrue();
        assertThat(scalar.isLessOrEqualTo(scalar(value+1))).isTrue();
    }

    @Test
    public void isGreaterOrEqualToWorks() {
        assertThat(scalar.isGreaterOrEqualTo(scalar(value-1))).isTrue();
        assertThat(scalar.isGreaterOrEqualTo(scalar(value))).isTrue();
        assertThat(scalar.isGreaterOrEqualTo(scalar(value+1))).isFalse();
    }

    @Test
    public void isLessThanWorks() {
        assertThat(scalar.isLessThan(scalar(value-1))).isFalse();
        assertThat(scalar.isLessThan(scalar(value))).isFalse();
        assertThat(scalar.isLessThan(scalar(value+1))).isTrue();
    }

    @Test
    public void isGreaterThanWorks() {
        assertThat(scalar.isGreaterThan(scalar(value-1))).isTrue();
        assertThat(scalar.isGreaterThan(scalar(value))).isFalse();
        assertThat(scalar.isGreaterThan(scalar(value+1))).isFalse();
    }

    @Test
    public void minimumWithSmallerScalarReturnsTheSmallerScalar() {
        assertThat(scalar.min(scalar.minus(1))).isEqualTo(scalar.minus(1));
    }

    @Test
    public void minimumWithEqualScalarReturnsTheScalar() {
        assertThat(scalar.min(scalar)).isEqualTo(scalar);
    }

    @Test
    public void minimumWithLargerScalarReturnsTheScalar() {
        assertThat(scalar.min(scalar.plus(1))).isEqualTo(scalar);
    }

    @Test
    public void maximumWithSmallerScalarReturnsTheScalar() {
        assertThat(scalar.max(scalar.minus(1))).isEqualTo(scalar);
    }

    @Test
    public void maximumWithEqualScalarReturnsTheScalar() {
        assertThat(scalar.max(scalar)).isEqualTo(scalar);
    }

    @Test
    public void maximumWithLargerScalarReturnsTheLargerScalar() {
        assertThat(scalar.max(scalar.plus(1))).isEqualTo(scalar.plus(1));
    }

    @Test
    public void norm() {
        assertThat(scalar.norm()).isEqualTo(scalar);
        assertThat(scalar.negated().norm()).isEqualTo(scalar);
    }
}

