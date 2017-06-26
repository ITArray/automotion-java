package net.itarray.automotion.internal;

import net.itarray.automotion.validation.properties.Resolution;

public class ResolutionUnkown implements Resolution{

    @Override
    public void applyTo(DriverFacade driver) {
    }

    @Override
    public Resolution queryIfUnkown(DriverFacade driver) {
        return ResolutionImpl.of(driver.getResolution());
    }

    @Override
    public int hashCode() {
        return ResolutionUnkown.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ResolutionUnkown;
    }

    @Override
    public String toString() {
        return "unkown";
    }
}
