package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.internal.properties.ConstantExpression;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Expression;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrueOrTrueExpressionTest {

    private Expression<Boolean> leftOrRight;
    private Boolean rightEvaluated;

    @Before
    public void setUp() {
        rightEvaluated = false;
        Expression<Boolean> left = new ConstantExpression<>(true);
        Expression<Boolean> right = new ConstantExpression<Boolean>(true) {
            @Override
            public <V extends MetricSpace<V>> Boolean evaluateIn(Context context, ExtendGiving<V> direction) {
                rightEvaluated = true;
                return super.evaluateIn(context, direction);
            }
        };
        leftOrRight = Expression.or(left, right);
    }

    @Test
    public void evaluatesToTrue() {
        TestContext testContext = new TestContext();
        Boolean result = leftOrRight.evaluateIn(testContext, Direction.RIGHT);
        assertThat(result).isTrue();
    }

    @Test
    public void doesNotEvaluateTheRightExpression() {
        assertThat(rightEvaluated).isFalse();
    }
}
