package net.itarray.automotion.tests;

import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.ResolutionUnknown;
import net.itarray.automotion.validation.properties.Resolution;
import net.itarray.automotion.internal.ResolutionImpl;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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

    @Test
    public void isNotEqualToUnkownResolution() {
        assertThat(resolution).isNotEqualTo(new ResolutionUnknown());
    }

    @Test
    public void applyToSetTheResolutionOnTheSuppliedDriver() {
        DriverFacade driver = mock(DriverFacade.class);
        resolution.applyTo(driver);
        verify(driver).setResolution(new Dimension(100, 200));
    }

    @Test
    public void queryIfUnknownDoesNotIteractWithTheSuppliedDriver() {
        DriverFacade driver = mock(DriverFacade.class);
        Resolution queried = this.resolution.queryIfUnknown(driver);
        verifyZeroInteractions(driver);
        assertThat(queried).isEqualTo(resolution);
    }
}
