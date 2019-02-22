package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.internal.geometry.Vector;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;
import static org.assertj.core.api.Assertions.assertThat;

public class VectorTest {

    private Vector vector;
    private Scalar x;
    private Scalar y;

    @Before
    public void createVector() {
        x = scalar(5);
        y = scalar(12);
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
        assertThat(vector.toStringWithUnits("px")).isEqualTo("5px x 12px");
    }

    @Test
    public void minusReturnsAVectorWithValueEqualToTheDifferenceOfValueAndTheSubtrahendValueInBothDimensions() {
        Vector subtrahend = new Vector(2, 3);
        assertThat(vector.minus(subtrahend)).isEqualTo(new Vector(x.minus(2), y.minus(3)));
    }

    @Test
    public void plusReturnsAVectorWithValueEqualToTheSumOfValueAndTheAddendValueInBothDimensions() {
        Vector addend = new Vector(2, 3);
        assertThat(vector.plus(addend)).isEqualTo(new Vector(x.plus(2), y.plus(3)));
    }

    @Test
    public void normReturnsTheEuklideanDistanceToTheOrigin() {
        assertThat(vector.norm()).isEqualTo(scalar(13));
    }
}
