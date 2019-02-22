package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class ConditionBetweenTest extends ScalarConditionTest {

    private Scalar lowerLimit;
    private Scalar upperLimit;

    @Before
    public void createProperty() {
        lowerLimit = scalar(7);
        upperLimit = scalar(10);
        condition = Condition.between(lowerLimit).and(upperLimit);
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLowerLimit() {
        assertThat(satisfiedOn(lowerLimit.minus(1))).isFalse();
    }

    @Test
    public void isSatisfiedOnValuesEqualToTheLowerLimit() {
        assertThat(satisfiedOn(lowerLimit)).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLowerLimit() {
        assertThat(satisfiedOn(lowerLimit.plus(1))).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLimit() {
        assertThat(satisfiedOn(upperLimit.minus(1))).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesEqualToTheLimit() {
        assertThat(satisfiedOn(upperLimit)).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLimit() {
        assertThat(satisfiedOn(upperLimit.plus(1))).isFalse();
    }

    @Test
    public void isEqualToBetweenConditionsWithEqualUpperAndLowerLimit() {
        assertThat(condition).isEqualTo(Condition.between(lowerLimit).and(upperLimit));
    }

    @Test
    public void isNotEqualToLessOrEqualConditionsWithDifferentLowerLimit() {
        assertThat(condition).isNotEqualTo(Condition.between(lowerLimit.plus(1)).and(upperLimit));
    }

    @Test
    public void isNotEqualToLessOrEqualConditionsWithDifferentUpperLimit() {
        assertThat(condition).isNotEqualTo(Condition.between(lowerLimit).and(upperLimit.plus(1)));
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
