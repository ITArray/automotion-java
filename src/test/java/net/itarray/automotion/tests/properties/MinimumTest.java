package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Minimum;
import net.itarray.automotion.internal.properties.ScalarCondition;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MinimumTest {

    private ScalarCondition property;
    private Scalar limit;

    @Before
    public void createProperty() {
        limit = new Scalar(7);
        property = new Minimum(limit);
    }

    @Test
    public void isFalseForScalarsWithValuesSmallerThanTheLimit() {
        boolean result = property.isSatisfiedOn(limit.minus(1));
        assertThat(result).isFalse();
    }

    @Test
    public void isTrueForScalarsWithValuesEqualToTheLimit() {
        boolean result = property.isSatisfiedOn(limit);
        assertThat(result).isTrue();
    }

    @Test
    public void isTrueForScalarsWithValuesGreaterThanTheLimit() {
        boolean result = property.isSatisfiedOn(limit.plus(1));
        assertThat(result).isTrue();
    }
}
