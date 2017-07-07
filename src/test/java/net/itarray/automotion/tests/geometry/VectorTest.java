package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.Scalar;
import net.itarray.automotion.internal.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VectorTest {

    private Vector vector;
    private Scalar x;
    private Scalar y;

    @Before
    public void createVector() {
        x = new Scalar(10);
        y = new Scalar(23);
        vector = new Vector(x, y);
    }

    @Test
    public void isEqualToVectorWithEqualXAndY() {
        assertThat(vector).isEqualTo(new Vector(x, y));
        assertThat(vector.hashCode()).isEqualTo(new Vector(x, y).hashCode());
    }

    @Test
    public void isNotEqualToVectorWithDifferentX() {
        assertThat(vector).isNotEqualTo(new Vector(x.plus(1), y));
    }

    @Test
    public void isNotEqualToVectorWithDifferentY() {
        assertThat(vector).isNotEqualTo(new Vector(x, y.plus(1)));
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(vector).isNotEqualTo(new Object());
    }

    @Test
    public void getXReturnsTheFirstConstructorArgument() {
        assertThat(vector.getX()).isEqualTo(x);
    }


    @Test
    public void getYReturnsTheFirstConstructorArgument() {
        assertThat(vector.getY()).isEqualTo(y);
    }

    @Test
    public void toStringWithUnitsAppendsTheUnitsToEachCoordinate() {
        assertThat(vector.toStringWithUnits("px")).isEqualTo("10px x 23px");
    }
}
