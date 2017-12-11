package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class ConditionEqualToTest {

    private Condition<Scalar> condition;
    private Scalar limit;
    private TestContext context;
    private Direction direction;

    @Before
    public void createProperty() {
        limit = scalar(7);
        condition = Condition.equalTo(limit);
        context = new TestContext();
        direction = Direction.RIGHT;
    }

    @Test
    public void isNotSatisfiedOnValuesSmallerThanTheLimit() {
        assertThat(condition.isSatisfiedOn(limit.minus(1), context, direction)).isFalse();
        assertThat(condition.isSatisfiedOn(limit.minus(2), context.withTolerance(1), direction)).isFalse();
    }

    @Test
    public void isSatisfiedOnValuesEqualToTheLimit() {
        assertThat(condition.isSatisfiedOn(limit, context, direction)).isTrue();
        assertThat(condition.isSatisfiedOn(limit.minus(1), context.withTolerance(1), direction)).isTrue();
        assertThat(condition.isSatisfiedOn(limit, context.withTolerance(1), direction)).isTrue();
        assertThat(condition.isSatisfiedOn(limit.plus(1), context.withTolerance(1), direction)).isTrue();
    }

    @Test
    public void isNotSatisfiedOnValuesGreaterThanTheLimit() {
        assertThat(condition.isSatisfiedOn(limit.plus(1), context, direction)).isFalse();
        assertThat(condition.isSatisfiedOn(limit.plus(2), context.withTolerance(1), direction)).isFalse();
    }
}
