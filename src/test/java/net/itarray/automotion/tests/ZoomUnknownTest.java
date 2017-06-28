package net.itarray.automotion.tests;

import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.ZoomUnknown;
import net.itarray.automotion.validation.properties.Zoom;
import org.junit.Before;
import org.junit.Test;

import static net.itarray.automotion.validation.Literals.zoom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ZoomUnknownTest {

    private Zoom zoom;

    @Before
    public void createZoom() {
        zoom = new ZoomUnknown();
    }

    @Test
    public void isEqualToZoomUnknown() {
        assertThat(zoom).isEqualTo(new ZoomUnknown());
        assertThat(zoom.hashCode()).isEqualTo(new ZoomUnknown().hashCode());
    }

    @Test
    public void isNotEqualToKnownZoom() {
        assertThat(zoom).isNotEqualTo(zoom(60));
    }

    @Test
    public void isNotEqualToObjects() {
        assertThat(zoom).isNotEqualTo(new Object());
    }


    @Test
    public void queryIfUnknownGetTheZoomFromTheSuppliedDriver() {
        DriverFacade mockedDriver = mock(DriverFacade.class);
        when(mockedDriver.getZoom()).thenReturn("80%");
        Zoom queried = this.zoom.queryIfUnknown(mockedDriver);
        assertThat(queried).isEqualTo(zoom(80));
    }

    @Test
    public void getFactorGetTheFactorFromTheSuppliedDriver() {
        DriverFacade mockedDriver = mock(DriverFacade.class);
        when(mockedDriver.getZoom()).thenReturn("80%");
        double factor = zoom.getFactor(mockedDriver);
        assertThat(factor).isEqualTo(80/100d);
    }

    @Test
    public void applyToDoesNotInteractWithTheSuppliedDriver() {
        DriverFacade mockedDriver = mock(DriverFacade.class);
        zoom.applyTo(mockedDriver);
        verifyZeroInteractions(mockedDriver);
    }

}
