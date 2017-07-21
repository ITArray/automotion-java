package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LessThanTest {

    private Condition<Scalar> condition;
    private Scalar limit;
    private Context context;
    private Direction direction;

    @Before
    public void createProperty() {
        limit = new Scalar(7);
        condition = Condition.lessThan(limit);
        context = new TestContext();
        direction = Direction.RIGHT;
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLimit() {
        boolean result = condition.isSatisfiedOn(limit.minus(1), context, direction);
        assertThat(result).isTrue();
    }

    @Test
    public void isNotSatisfiedOnValuesEqualToTheLimit() {
        boolean result = condition.isSatisfiedOn(limit, context, direction);
        assertThat(result).isFalse();
    }

    @Test
    public void isNotSatisfiedOnValuesGreaterThanTheLimit() {
        boolean result = condition.isSatisfiedOn(limit.plus(1), context, direction);
        assertThat(result).isFalse();
    }

}
