package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class ConditionGreaterThanTest {

    private Condition<Scalar> condition;
    private Scalar limit;
    private Context context;
    private Direction direction;

    @Before
    public void createProperty() {
        limit = scalar(7);
        condition = Condition.greaterThan(limit);
        context = new TestContext();
        direction = Direction.RIGHT;
    }

    @Test
    public void isNotSatisfiedOnValuesSmallerThanTheLimit() {
        boolean result = condition.isSatisfiedOn(limit.minus(1), context, direction);
        assertThat(result).isFalse();
    }

    @Test
    public void isNotSatisfiedOnValuesEqualToTheLimit() {
        boolean result = condition.isSatisfiedOn(limit, context, direction);
        assertThat(result).isFalse();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLimit() {
        boolean result = condition.isSatisfiedOn(limit.plus(1), context, direction);
        assertThat(result).isTrue();
    }
}
