package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionLessThanTest extends ScalarConditionTest {

    private Scalar limit;

    @Before
    public void createProperty() {
        limit = Scalar.scalar(7);
        condition = Condition.lessThan(limit);
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLimit() {
        assertThat(satisfiedOn(limit.minus(1))).isTrue();
        assertThat(satisfiedOnWithTolerance(limit.minus(1))).isTrue();
        assertThat(satisfiedOnWithTolerance(limit)).isTrue();
    }

    @Test
    public void isNotSatisfiedOnValuesEqualToTheLimit() {
        assertThat(satisfiedOn(limit)).isFalse();
    }

    @Test
    public void isNotSatisfiedOnValuesGreaterThanTheLimit() {
        assertThat(satisfiedOn(limit.plus(1))).isFalse();
        assertThat(satisfiedOnWithTolerance(limit.plus(1))).isFalse();
    }
}
