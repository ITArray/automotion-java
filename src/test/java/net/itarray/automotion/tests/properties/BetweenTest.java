package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.internal.properties.Conditions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BetweenTest {

    private Condition<Scalar> condition;
    private Scalar lowerLimit;
    private Scalar upperLimit;

    @Before
    public void createProperty() {
        lowerLimit = new Scalar(7);
        upperLimit = new Scalar(10);
        condition = Conditions.between(lowerLimit, upperLimit);
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLowerLimit() {
        boolean result = condition.isSatisfiedOn(lowerLimit.minus(1));
        assertThat(result).isFalse();
    }

    @Test
    public void isSatisfiedOnValuesEqualToTheLowerLimit() {
        boolean result = condition.isSatisfiedOn(lowerLimit);
        assertThat(result).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLowerLimit() {
        boolean result = condition.isSatisfiedOn(lowerLimit.plus(1));
        assertThat(result).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLimit() {
        boolean result = condition.isSatisfiedOn(upperLimit.minus(1));
        assertThat(result).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesEqualToTheLimit() {
        boolean result = condition.isSatisfiedOn(upperLimit);
        assertThat(result).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLimit() {
        boolean result = condition.isSatisfiedOn(upperLimit.plus(1));
        assertThat(result).isFalse();
    }

    @Test
    public void isEqualToBetweenConditionsWithEqualUpperAndLowerLimit() {
        assertThat(condition).isEqualTo(Conditions.between(lowerLimit, upperLimit));
    }

    @Test
    public void isNotEqualToLessOrEqualConditionsWithDifferentLowerLimit() {
        assertThat(condition).isNotEqualTo(Conditions.between(lowerLimit.plus(1), upperLimit));
    }

    @Test
    public void isNotEqualToLessOrEqualConditionsWithDifferentUpperLimit() {
        assertThat(condition).isNotEqualTo(Conditions.between(lowerLimit, upperLimit.plus(1)));
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
