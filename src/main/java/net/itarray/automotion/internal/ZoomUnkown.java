package net.itarray.automotion.internal;

import net.itarray.automotion.validation.properties.Zoom;

public class ZoomUnkown implements Zoom {

    @Override
    public String toString() {
        return "unkown";
    }

    @Override
    public int hashCode() {
        return ZoomUnkown.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ZoomUnkown;
    }

    @Override
    public void applyTo(DriverFacade driver) {
    }

    @Override
    public double getFactor(DriverFacade driver) {
        return queryIfUnkown(driver).getFactor(driver);
    }

    public Zoom queryIfUnkown(DriverFacade driver) {
        int percentage = Integer.parseInt(driver.getZoom().replace("%", ""));
        return ZoomImpl.of(percentage);
    }
}
