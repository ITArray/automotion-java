package net.itarray.automotion.validation.properties;

import net.itarray.automotion.internal.DriverFacade;

public interface Resolution {
    void applyTo(DriverFacade driver);
    Resolution queryIfUnkown(DriverFacade driver);
}
