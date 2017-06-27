package net.itarray.automotion.tests;

import net.itarray.automotion.validation.properties.Resolution;
import net.itarray.automotion.internal.ResolutionImpl;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResolutionTest {

    private Resolution resolution;

    @Before
    public void createResolution() {
        resolution = ResolutionImpl.of(100, 200);
    }

    @Test
    public void isEqualToResolutionOfEqualWidthAndHeight() {
        assertThat(resolution).isEqualTo(ResolutionImpl.of(100, 200));
        assertThat(resolution.hashCode()).isEqualTo(ResolutionImpl.of(100, 200).hashCode());
    }

    @Test
    public void isEqualToResolutionCreateFromString() {
        assertThat(resolution).isEqualTo(ResolutionImpl.of("100x200"));
        assertThat(resolution).isEqualTo(ResolutionImpl.of("100X200"));
    }

    @Test
    public void isNotEqualToResolutionOfDifferentWidth() {
        assertThat(resolution).isNotEqualTo(ResolutionImpl.of(101, 200));
    }

    @Test
    public void isNotEqualToResolutionOfDifferentHeight() {
        assertThat(resolution).isNotEqualTo(ResolutionImpl.of(100, 201));
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(resolution).isNotEqualTo(new Object());
    }
}
