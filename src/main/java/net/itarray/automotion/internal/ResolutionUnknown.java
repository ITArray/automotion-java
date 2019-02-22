package net.itarray.automotion.internal;

import net.itarray.automotion.validation.properties.Resolution;

public class ResolutionUnknown implements Resolution {

    @Override
    public void applyTo(DriverFacade driver) {
    }

    @Override
    public Resolution queryIfUnknown(DriverFacade driver) {
        return ResolutionImpl.of(driver.getResolution());
    }

    @Override
    public int hashCode() {
        return ResolutionUnknown.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ResolutionUnknown;
    }

    @Override
    public String toString() {
        return "unkown";
    }
}
