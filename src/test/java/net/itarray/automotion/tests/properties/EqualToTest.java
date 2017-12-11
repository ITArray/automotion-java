package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.BinaryExpression;
import net.itarray.automotion.internal.properties.PixelConstant;
import net.itarray.automotion.validation.properties.Expression;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class EqualToTest {

    private Expression<Boolean> expression;

    @Before
    public void setUp() {
        expression = Expression.equalTo(
                new PixelConstant(scalar(17)),
                new PixelConstant(scalar(16)));
    }

    @Test
    public void evaluatesToTheValueOfTheOperationAppliedToTheEvaluatedLeftAndRightExpressions() {
        Boolean result = expression.evaluateIn(new TestContext(), Direction.RIGHT);
        assertThat(result).isFalse();
    }

    @Test
    public void evaluatesToTheValueOfTheOperationAppliedToTheEvaluatedLeftAndRightExpressionsTakingTheToleranceOfTheContextIntoAccount() {
        Boolean result = expression.evaluateIn(new TestContext().withTolerance(scalar(1)), Direction.RIGHT);
        assertThat(result).isTrue();
    }

    @Test
    public void describesItself() {
        String description = expression.getDescription(new TestContext(), Direction.RIGHT);
        assertThat(description).isEqualTo("17px to be equal to 16px");
    }

    @Test
    public void describesItselfWithTolerance() {
        String description = expression.getDescription(new TestContext().withTolerance(scalar(1)), Direction.RIGHT);
        assertThat(description).isEqualTo("17px to be equal to 16px with tolerance 1");
    }
}
