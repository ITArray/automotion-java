package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class ConditionGreaterOrEqualToTest {

    private Condition<Scalar> condition;
    private Scalar limit;
    private TestContext context;
    private Direction direction;

    @Before
    public void createProperty() {
        limit = scalar(7);
        condition = Condition.greaterOrEqualTo(limit);
        context = new TestContext();
        direction = Direction.RIGHT;
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLimit() {
        assertThat(condition.isSatisfiedOn(limit.minus(1), context, direction)).isFalse();
        assertThat(condition.isSatisfiedOn(limit.minus(2), context.withTolerance(1), direction)).isFalse();
    }

    @Test
    public void isSatisfiedOnValuesEqualToTheLimit() {
        assertThat(condition.isSatisfiedOn(limit, context, direction)).isTrue();
        assertThat(condition.isSatisfiedOn(limit, context.withTolerance(1), direction)).isTrue();
        assertThat(condition.isSatisfiedOn(limit.minus(1), context.withTolerance(1), direction)).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLimit() {
        assertThat(condition.isSatisfiedOn(limit.plus(1), context, direction)).isTrue();
        assertThat(condition.isSatisfiedOn(limit.plus(1), context.withTolerance(1), direction)).isTrue();
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
