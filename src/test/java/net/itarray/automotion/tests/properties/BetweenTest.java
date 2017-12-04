package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class BetweenTest {

    private Condition<Scalar> condition;
    private Scalar lowerLimit;
    private Scalar upperLimit;
    private Context context;
    private Direction direction;

    @Before
    public void createProperty() {
        lowerLimit = scalar(7);
        upperLimit = scalar(10);
        condition = Condition.between(lowerLimit).and(upperLimit);
        context = new TestContext();
        direction = Direction.RIGHT;
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLowerLimit() {
        boolean result = condition.isSatisfiedOn(lowerLimit.minus(1), context, direction);
        assertThat(result).isFalse();
    }

    @Test
    public void isSatisfiedOnValuesEqualToTheLowerLimit() {
        boolean result = condition.isSatisfiedOn(lowerLimit, context, direction);
        assertThat(result).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLowerLimit() {
        boolean result = condition.isSatisfiedOn(lowerLimit.plus(1), context, direction);
        assertThat(result).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesSmallerThanTheLimit() {
        boolean result = condition.isSatisfiedOn(upperLimit.minus(1), context, direction);
        assertThat(result).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesEqualToTheLimit() {
        boolean result = condition.isSatisfiedOn(upperLimit, context, direction);
        assertThat(result).isTrue();
    }

    @Test
    public void isSatisfiedOnValuesGreaterThanTheLimit() {
        boolean result = condition.isSatisfiedOn(upperLimit.plus(1), context, direction);
        assertThat(result).isFalse();
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
