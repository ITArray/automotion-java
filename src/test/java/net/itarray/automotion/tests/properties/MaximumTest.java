package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Maximum;
import net.itarray.automotion.internal.properties.ScalarCondition;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MaximumTest {

    private ScalarCondition property;
    private Scalar limit;

    @Before
    public void createProperty() {
        limit = new Scalar(7);
        property = new Maximum(limit);
    }

    @Test
    public void isTrueForScalarsWithValuesSmallerThanTheLimit() {
        boolean result = property.evaluate(limit.minus(1));
        assertThat(result).isTrue();
    }

    @Test
    public void isTrueForScalarsWithValuesEqualToTheLimit() {
        boolean result = property.evaluate(limit);
        assertThat(result).isTrue();
    }

    @Test
    public void isFalseForScalarsWithValuesGreaterThanTheLimit() {
        boolean result = property.evaluate(limit.plus(1));
        assertThat(result).isFalse();
    }
}
