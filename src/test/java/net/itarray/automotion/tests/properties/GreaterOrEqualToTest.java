package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreaterOrEqualToTest {

    private Condition<Scalar> condition;
    private Scalar limit;

    @Before
    public void createProperty() {
        limit = new Scalar(7);
        condition = Condition.greaterOrEqualTo(limit);
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLimit() {
        boolean result = condition.isSatisfiedOn(limit.minus(1));
        assertThat(result).isFalse();
    }

    @Test
    public void isSatisfiedOnValuesEqualToTheLimit() {
        boolean result = condition.isSatisfiedOn(limit);
        assertThat(result).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLimit() {
        boolean result = condition.isSatisfiedOn(limit.plus(1));
        assertThat(result).isTrue();
    }

    @Test
    public void isEqualToLessOrEqualConditionsWithEqualLimit() {
        assertThat(condition).isEqualTo(Condition.greaterOrEqualTo(limit));
        assertThat(condition.hashCode()).isEqualTo(Condition.greaterOrEqualTo(limit).hashCode());
    }

    @Test
    public void isNotEqualToLessOrEqualConditionsWithDifferentLimit() {
        assertThat(condition).isNotEqualTo(Condition.greaterOrEqualTo(limit.plus(1)));
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(condition).isNotEqualTo(new Object());
    }

    @Test
    public void isNotEqualToNull() {
        assertThat(condition).isNotEqualTo(null);
    }
}
