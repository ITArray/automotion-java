package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.PixelConstant;
import net.itarray.automotion.internal.properties.BinaryExpression;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class BinaryExpressionTest {

    private BinaryExpression<Scalar, Scalar, Boolean> expression;

    @Before
    public void setUp() {
        expression = new BinaryExpression<>(
                new PixelConstant(scalar(17)),
                new PixelConstant(scalar(16)),
                Scalar::isLessOrEqualTo,
                "%s to be less or equal to %s");
    }

    @Test
    public void evaluatesToTheValueOfTheOperationAppliedToTheEvaluatedLeftAndRightExpressions() {
        Boolean result = expression.evaluateIn(new TestContext(), Direction.RIGHT);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldName() {
        String description = expression.getDescription(new TestContext(), Direction.RIGHT);
        assertThat(description).isEqualTo("17px to be less or equal to 16px");
    }
}
