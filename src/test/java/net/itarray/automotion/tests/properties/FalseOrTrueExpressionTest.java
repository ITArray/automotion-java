package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.properties.ConstantExpression;
import net.itarray.automotion.validation.properties.Expression;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FalseOrTrueExpressionTest {

    private Expression<Boolean> leftOrRight;

    @Before
    public void setUp() {
        Expression<Boolean> left = new ConstantExpression<>(false);
        Expression<Boolean> right = new ConstantExpression<Boolean>(true);
        leftOrRight = Expression.or(left, right);
    }

    @Test
    public void evaluatesToTrue() {
        TestContext testContext = new TestContext();
        Boolean result = leftOrRight.evaluateIn(testContext, Direction.RIGHT);
        assertThat(result).isTrue();
    }
}
