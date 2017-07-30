package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.validation.properties.Expression;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.properties.PercentReference.PAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class PagePercentageTest {

    private Scalar value;
    private Expression<Scalar> expression;

    @Before
    public void setUp() {
        value = new Scalar(27);
        expression = Expression.percent(value, PAGE);
    }

    @Test
    public void evaluatesToThePercentageOfThePageRectange() {
        Context context = new TestContext(new Rectangle(0, 0, 200, 150));
        Direction direction = Direction.RIGHT;
        assertThat(expression.evaluateIn(context, direction)).isEqualTo(new Scalar(54));
    }

    @Test
    public void isEqualToPagePercentagesWithEqualPercentage() {
        assertThat(expression).isEqualTo(Expression.percent(value, PAGE));
        assertThat(expression.hashCode()).isEqualTo(Expression.percent(value, PAGE).hashCode());
    }

    @Test
    public void isNotEqualToPagePercentagesWithDifferentPercentage() {
        assertThat(expression).isNotEqualTo(Expression.percent(value.plus(1), PAGE));
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(expression).isNotEqualTo(new Object());
    }

    @Test
    public void isNotEqualToNull() {
        assertThat(expression).isNotEqualTo(null);
    }

    @Test
    public void describesItselfAsPixelConstant() {
        assertThat(expression.getDescription(new TestContext(), Direction.RIGHT)).isEqualTo("27% of page (54px)");
    }
}
