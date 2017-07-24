package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Expression;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PagePercentageOrPixelsTest {
    private Expression<Scalar> condition;
    private Scalar constant;

    @Before
    public void setUp() {
        constant = new Scalar(52);
        condition = Expression.percentOrPixels(constant);
    }

    @Test
    public void evaluatesToItsValueInAnyPixelContext() {
        assertThat(condition.evaluateIn(new TestContext(), Direction.RIGHT)).isEqualTo(constant);
    }

    @Test
    public void evaluatesToItsValueInAnyPercentageContext() {
        TestContext context = new TestContext();
        context.setPixels(false);
        assertThat(condition.evaluateIn(context, Direction.RIGHT)).isEqualTo(new Scalar(104));
    }

    @Test
    public void isEqualToPagePercentageOrPixelsWithEqualConstant() {
        assertThat(condition).isEqualTo(Expression.percentOrPixels(constant));
        assertThat(condition.hashCode()).isEqualTo(Expression.percentOrPixels(constant).hashCode());
    }

    @Test
    public void isNotEqualToPagePercentageOrPixelsWithDifferentConstant() {
        assertThat(condition).isNotEqualTo(Expression.percentOrPixels(constant.plus(1)));
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
