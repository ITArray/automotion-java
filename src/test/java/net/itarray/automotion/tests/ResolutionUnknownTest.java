package net.itarray.automotion.tests;

import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.ResolutionImpl;
import net.itarray.automotion.internal.ResolutionUnknown;
import net.itarray.automotion.validation.properties.Resolution;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ResolutionUnknownTest {

    private Resolution resolution;

    @Before
    public void createResolution() {
        resolution = new ResolutionUnknown();
    }

    @Test
    public void isEqualToResolutionUnknown() {
        assertThat(resolution).isEqualTo(new ResolutionUnknown());
        assertThat(resolution.hashCode()).isEqualTo(new ResolutionUnknown().hashCode());
    }

    @Test
    public void isNotEqualToKnownResolution() {
        assertThat(resolution).isNotEqualTo(ResolutionImpl.of(100, 200));
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(resolution).isNotEqualTo(new Object());
    }


    @Test
    public void applyToDoesNotInteractWithTheSuppliedDriver() {
        DriverFacade driver = mock(DriverFacade.class);
        resolution.applyTo(driver);
        verifyZeroInteractions(driver);
    }


    @Test
    public void queryIfUnknownGetsTheResolutionFromTheSuppliedDriver() {
        DriverFacade driver = mock(DriverFacade.class);
        when(driver.getResolution()).thenReturn(new Dimension(100, 200));
        Resolution queried = this.resolution.queryIfUnknown(driver);
        assertThat(queried).isEqualTo(ResolutionImpl.of(100, 200));
    }
}
