package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.PixelConstant;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class PixelConstantTest {

    private Scalar value;
    private PixelConstant constant;

    @Before
    public void createConstant() {
        value = scalar(13);
        constant = new PixelConstant(value);
    }

    @Test
    public void evaluatesToItsValueInAnyContext() {
        assertThat(constant.evaluateIn(new TestContext(), Direction.RIGHT)).isEqualTo(value);
    }

    @Test
    public void isEqualToScalarConstantsWithEqualValue() {
        PixelConstant equal = new PixelConstant(value);
        assertThat(constant).isEqualTo(equal);
        assertThat(constant.hashCode()).isEqualTo(equal.hashCode());
    }

    @Test
    public void isNotEqualToScalarConstantsWithDifferentValue() {
        PixelConstant different = new PixelConstant(value.plus(1));
        assertThat(constant).isNotEqualTo(different);
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(constant).isNotEqualTo(new Object());
    }

    @Test
    public void isNotEqualToNull() {
        assertThat(constant).isNotEqualTo(null);
    }

    @Test
    public void describesItselfAsPixelConstant() {
        assertThat(constant.getDescription(new TestContext(), Direction.RIGHT)).isEqualTo("13px");
    }
}
