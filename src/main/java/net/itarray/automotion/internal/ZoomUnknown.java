package net.itarray.automotion.internal;

import net.itarray.automotion.validation.properties.Zoom;

public class ZoomUnknown implements Zoom {

    @Override
    public String toString() {
        return "unkown";
    }

    @Override
    public int hashCode() {
        return ZoomUnknown.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ZoomUnknown;
    }

    @Override
    public void applyTo(DriverFacade driver) {
    }

    @Override
    public double getFactor(DriverFacade driver) {
        return queryIfUnknown(driver).getFactor(driver);
    }

    public Zoom queryIfUnknown(DriverFacade driver) {
        int percentage = Integer.parseInt(driver.getZoom().replace("%", ""));
        return ZoomImpl.of(percentage);
    }
}
