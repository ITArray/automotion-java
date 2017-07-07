package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.Scalar;
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
    public void getValueReturnsTheConstructorParameter() {
        assertThat(scalar.getValue()).isEqualTo(value);
    }

    @Test
    public void plusIntReturnsAScalarWithValueEqualToTheSumOfValueAndTheAddend() {
        int addend = 2;
        assertThat(scalar.plus(addend)).isEqualTo(new Scalar(value + addend));
    }

    @Test
    public void plusScalarReturnsAScalarWithValueEqualToTheSumOfValueAndTheAddend() {
        Scalar addend = new Scalar(2);
        assertThat(scalar.plus(addend)).isEqualTo(new Scalar(value + addend.getValue()));
    }

    @Test
    public void minusIntReturnsAScalarWithValueEqualToTheDifferenceOfValueAndTheSubtrahend() {
        int addend = 2;
        assertThat(scalar.minus(addend)).isEqualTo(new Scalar(value - addend));
    }

    @Test
    public void minusScalarReturnsAScalarWithValueEqualToTheDifferenceOfValueAndTheSubtrahend() {
        Scalar addend = new Scalar(2);
        assertThat(scalar.minus(addend)).isEqualTo(new Scalar(value - addend.getValue()));
    }
}
