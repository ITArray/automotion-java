package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.internal.properties.Conditions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LessOrEqualTest {

    private Condition<Scalar> condition;
    private Scalar limit;

    @Before
    public void createProperty() {
        limit = new Scalar(7);
        condition = Conditions.lessOrEqualTo(limit);
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
        assertThat(condition).isEqualTo(Conditions.lessOrEqualTo(limit));
        assertThat(condition.hashCode()).isEqualTo(Conditions.lessOrEqualTo(limit).hashCode());
    }

    @Test
    public void isNotEqualToLessOrEqualConditionsWithDifferentLimit() {
        assertThat(condition).isNotEqualTo(Conditions.lessOrEqualTo(limit.plus(1)));
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(condition).isNotEqualTo(new Object());
    }
}