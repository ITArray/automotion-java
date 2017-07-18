package net.itarray.automotion.tests.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.properties.ScalarConstant;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScalarConstantTest {

    private Scalar value;
    private ScalarConstant constant;

    @Before
    public void createConstant() {
        value = new Scalar(13);
        constant = new ScalarConstant(value);
    }

    @Test
    public void evaluatesToItsValueInAnyContext() {
        assertThat(constant.evaluateIn(new TestContext(), Direction.RIGHT)).isEqualTo(value);
    }

    @Test
    public void isEqualToScalarConstantsWithEqualValue() {
        ScalarConstant equal = new ScalarConstant(value);
        assertThat(constant).isEqualTo(equal);
        assertThat(constant.hashCode()).isEqualTo(equal.hashCode());
    }

    @Test
    public void isNotEqualToScalarConstantsWithDifferentValue() {
        ScalarConstant different = new ScalarConstant(value.plus(1));
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
}
