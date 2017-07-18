package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.Context;
import net.itarray.automotion.internal.properties.Expression;
import net.itarray.automotion.internal.properties.PagePercentage;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PagePercentageTest {

    private Scalar value;
    private Expression<Scalar> pagePercentage;

    @Before
    public void setUp() {
        value = new Scalar(27);
        pagePercentage = new PagePercentage(value);
    }

    @Test
    public void shouldName() {
        Context context = new TestContext(new Rectangle(0, 0, 200, 150));
        Direction direction = Direction.RIGHT;
        assertThat(pagePercentage.evaluateIn(context, direction)).isEqualTo(new Scalar(54));
    }

}
