package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Range;
import net.itarray.automotion.internal.properties.ScalarCondition;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RangeTest {

    private ScalarCondition property;
    private Scalar lowerLimit;
    private Scalar upperLimit;

    @Before
    public void createProperty() {
        lowerLimit = new Scalar(7);
        upperLimit = new Scalar(10);
        property = new Range(lowerLimit, upperLimit);
    }

    @Test
    public void isFalseForScalarsWithValuesSmallerThanTheLowerLimit() {
        boolean result = property.evaluate(lowerLimit.minus(1));
        assertThat(result).isFalse();
    }

    @Test
    public void isTrueForScalarsWithValuesEqualToTheLowerLimit() {
        boolean result = property.evaluate(lowerLimit);
        assertThat(result).isTrue();
    }

    @Test
    public void isTrueForScalarsWithValuesGreaterThanTheLowerLimit() {
        boolean result = property.evaluate(lowerLimit.plus(1));
        assertThat(result).isTrue();
    }

    @Test
    public void isTrueForScalarsWithValuesSmallerThanTheLimit() {
        boolean result = property.evaluate(upperLimit.minus(1));
        assertThat(result).isTrue();
    }

    @Test
    public void isTrueForScalarsWithValuesEqualToTheLimit() {
        boolean result = property.evaluate(upperLimit);
        assertThat(result).isTrue();
    }

    @Test
    public void isFalseForScalarsWithValuesGreaterThanTheLimit() {
        boolean result = property.evaluate(upperLimit.plus(1));
        assertThat(result).isFalse();
    }

}
