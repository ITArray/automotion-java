package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.ConstantExpression;
import net.itarray.automotion.validation.properties.Condition;
import org.junit.Before;

public abstract class ScalarConditionTest {
    protected Condition<Scalar> condition;
    private TestContext context;
    private Direction direction;

    @Before
    public void createEnvironment() {
        context = new TestContext();
        direction = Direction.RIGHT;
    }

    protected boolean satisfiedOn(Scalar value) {
        return condition.isSatisfiedOn(new ConstantExpression<>(value), context, direction);
    }

    protected boolean satisfiedOnWithTolerance(Scalar value) {
        return condition.isSatisfiedOn(new ConstantExpression<>(value), context.withTolerance(1), direction);
    }
}
