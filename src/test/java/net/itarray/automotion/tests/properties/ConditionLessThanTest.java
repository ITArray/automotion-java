package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionLessThanTest {

    private Condition<Scalar> condition;
    private Scalar limit;
    private TestContext context;
    private Direction direction;

    @Before
    public void createProperty() {
        limit = Scalar.scalar(7);
        condition = Condition.lessThan(limit);
        context = new TestContext();
        direction = Direction.RIGHT;
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLimit() {
        assertThat(condition.isSatisfiedOn(limit.minus(1), context, direction)).isTrue();
        assertThat(condition.isSatisfiedOn(limit.minus(1), context.withTolerance(1), direction)).isTrue();
        assertThat(condition.isSatisfiedOn(limit, context.withTolerance(1), direction)).isTrue();
    }

    @Test
    public void isNotSatisfiedOnValuesEqualToTheLimit() {
        assertThat(condition.isSatisfiedOn(limit, context, direction)).isFalse();
    }

    @Test
    public void isNotSatisfiedOnValuesGreaterThanTheLimit() {
        assertThat(condition.isSatisfiedOn(limit.plus(1), context, direction)).isFalse();
        assertThat(condition.isSatisfiedOn(limit.plus(1), context.withTolerance(1), direction)).isFalse();
    }

}
