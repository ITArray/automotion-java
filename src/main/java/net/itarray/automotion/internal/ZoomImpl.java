package net.itarray.automotion.internal;

import net.itarray.automotion.validation.properties.Zoom;

public class ZoomImpl implements Zoom {

    private final int percentage;

    public ZoomImpl(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public void applyTo(DriverFacade driver) {
        driver.setZoom(percentage);
    }

    @Override
    public double getFactor(DriverFacade driver) {
        return percentage / 100d;
    }

    public static Zoom of(int percentage) {
        return new ZoomImpl(percentage);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(percentage);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ZoomImpl)) {
            return false;
        }
        return percentage == (((ZoomImpl)obj).percentage);
    }

    @Override
    public String toString() {
        return String.format("%s%%", percentage);
    }

    public Zoom queryIfUnkown(DriverFacade driver) {
        return this;
    }
}
