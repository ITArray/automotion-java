package net.itarray.automotion.tests.geometry;

import net.itarray.automotion.internal.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VectorTest {

    private int x;
    private int y;
    private Vector vector;

    @Before
    public void createVector() {
        x = 10;
        y = 23;
        vector = new Vector(x, y);
    }

    @Test
    public void isEqualToVectorWithEqualXAndY() {
        assertThat(vector).isEqualTo(new Vector(x, y));
        assertThat(vector.hashCode()).isEqualTo(new Vector(x, y).hashCode());
    }

    @Test
    public void isNotEqualToVectorWithDifferentX() {
        assertThat(vector).isNotEqualTo(new Vector(x+1, y));
    }

    @Test
    public void isNotEqualToVectorWithDifferentY() {
        assertThat(vector).isNotEqualTo(new Vector(x, y+1));
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
