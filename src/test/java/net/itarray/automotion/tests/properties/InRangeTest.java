package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.InRange;
import net.itarray.automotion.internal.properties.ScalarCondition;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InRangeTest {

    private ScalarCondition property;
    private Scalar lowerLimit;
    private Scalar upperLimit;

    @Before
    public void createProperty() {
        lowerLimit = new Scalar(7);
        upperLimit = new Scalar(10);
        property = new InRange(lowerLimit, upperLimit);
    }

    @Test
    public void isFalseForScalarsWithValuesSmallerThanTheLowerLimit() {
        boolean result = property.isSatisfiedOn(lowerLimit.minus(1));
        assertThat(result).isFalse();
    }

    @Test
    public void isTrueForScalarsWithValuesEqualToTheLowerLimit() {
        boolean result = property.isSatisfiedOn(lowerLimit);
        assertThat(result).isTrue();
    }

    @Test
    public void isTrueForScalarsWithValuesGreaterThanTheLowerLimit() {
        boolean result = property.isSatisfiedOn(lowerLimit.plus(1));
        assertThat(result).isTrue();
    }

    @Test
    public void isTrueForScalarsWithValuesSmallerThanTheLimit() {
        boolean result = property.isSatisfiedOn(upperLimit.minus(1));
        assertThat(result).isTrue();
    }

    @Test
    public void isTrueForScalarsWithValuesEqualToTheLimit() {
        boolean result = property.isSatisfiedOn(upperLimit);
        assertThat(result).isTrue();
    }

    @Test
    public void isFalseForScalarsWithValuesGreaterThanTheLimit() {
        boolean result = property.isSatisfiedOn(upperLimit.plus(1));
        assertThat(result).isFalse();
    }

}
