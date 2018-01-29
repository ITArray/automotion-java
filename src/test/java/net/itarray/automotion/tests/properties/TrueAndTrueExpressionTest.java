package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.properties.ConstantExpression;
import net.itarray.automotion.validation.properties.Expression;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrueAndTrueExpressionTest {

    private Expression<Boolean> leftAndRight;

    @Before
    public void setUp() {
        Expression<Boolean> left = new ConstantExpression<>(true);
        Expression<Boolean> right = new ConstantExpression<Boolean>(true);
        leftAndRight = Expression.and(left, right);
    }

    @Test
    public void evaluatesToFalse() {
        TestContext testContext = new TestContext();
        Boolean result = leftAndRight.evaluateIn(testContext, Direction.RIGHT);
        assertThat(result).isTrue();
    }
}
