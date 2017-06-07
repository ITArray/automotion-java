package net.itarry.automotion.tests;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static net.itarry.automotion.Zoom.applyZoom;

public class ZoomTest {

    @Test
    public void applyZoomShouldWork() {
        assertThat(applyZoom(100, 80)).isEqualTo(80);
        assertThat(applyZoom(100, 100)).isEqualTo(100);
        assertThat(applyZoom(100, 120)).isEqualTo(120);
        assertThat(applyZoom(200, 80)).isEqualTo(160);
        assertThat(applyZoom(200, 100)).isEqualTo(200);
        assertThat(applyZoom(200, 120)).isEqualTo(240);
    }
}
