package net.itarray.automotion.tests;

import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.ZoomUnknown;
import net.itarray.automotion.validation.properties.Zoom;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.validation.properties.Zoom.zoom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ZoomTest {

    private Zoom zoom;

    @Before
    public void createZoom() {
        zoom = zoom(80);
    }

    @Test
    public void isEqualToZoomWithEqualPercentage() {
        assertThat(zoom).isEqualTo(zoom(80));
        assertThat(zoom.hashCode()).isEqualTo(zoom(80).hashCode());
    }

    @Test
    public void isNotEqualToZoomWithDifferentPercentage() {
        assertThat(zoom).isNotEqualTo(zoom(60));
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(zoom).isNotEqualTo(new Object());
    }

    @Test
    public void isNotEqualToUnknownZoom() {
        assertThat(zoom).isNotEqualTo(new ZoomUnknown());
    }

    @Test
    public void queryIfUnknownDoesNotInteractWithTheSuppliedDriver() {
        DriverFacade mockedDriver = mock(DriverFacade.class);
        Zoom queried = this.zoom.queryIfUnknown(mockedDriver);
        verifyZeroInteractions(mockedDriver);
        assertThat(queried).isEqualTo(zoom);
    }

    @Test
    public void getFactorDoesNotInteractWithTheSuppliedDriver() {
        DriverFacade mockedDriver = mock(DriverFacade.class);
        double factor = zoom.getFactor(mockedDriver);
        verifyZeroInteractions(mockedDriver);
        assertThat(factor).isEqualTo(0.8d);
    }

    @Test
    public void applyToSetsTheZoomOnTheSuppliedDriver() {
        DriverFacade mockedDriver = mock(DriverFacade.class);
        zoom.applyTo(mockedDriver);
        verify(mockedDriver).setZoom(80);
    }
}
