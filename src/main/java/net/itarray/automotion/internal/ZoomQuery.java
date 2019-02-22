package net.itarray.automotion.internal;

public class ZoomQuery {
    private final DriverFacade driver;

    public ZoomQuery(DriverFacade driver) {

        this.driver = driver;
    }

    public int applyZoom(int value) {
        return (int) (value * getFactor());
    }

    public double getFactor() {
        String currentZoom = driver.getZoom();
        return Integer.parseInt(currentZoom.replace("%", "")) / 100d;
    }
}
