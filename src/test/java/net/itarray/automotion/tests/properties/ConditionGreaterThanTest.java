package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class ConditionGreaterThanTest extends ScalarConditionTest {

    private Scalar limit;

    @Before
    public void createProperty() {
        limit = scalar(7);
        condition = Condition.greaterThan(limit);
    }

    @Test
    public void isNotSatisfiedOnValuesSmallerThanTheLimit() {
        assertThat(satisfiedOn(limit.minus(1))).isFalse();
        assertThat(satisfiedOnWithTolerance(limit.minus(1))).isFalse();
    }

    @Test
    public void isNotSatisfiedOnValuesEqualToTheLimit() {
        assertThat(satisfiedOn(limit)).isFalse();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLimit() {
        assertThat(satisfiedOn(limit.plus(1))).isTrue();
        assertThat(satisfiedOnWithTolerance(limit.plus(1))).isTrue();
        assertThat(satisfiedOnWithTolerance(limit)).isTrue();
    }
}
