package net.itarray.automotion.validation.properties;

import net.itarray.automotion.internal.DriverFacade;

public interface Zoom {
    void applyTo(DriverFacade driver);
    double getFactor(DriverFacade driver);
    Zoom queryIfUnkown(DriverFacade driver);
}
