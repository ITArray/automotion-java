package net.itarray.automotion.validation.properties;

import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.ZoomImpl;

public interface Zoom {
    void applyTo(DriverFacade driver);
    double getFactor(DriverFacade driver);
    Zoom queryIfUnknown(DriverFacade driver);

    static Zoom zoom(int percentage) {
        return ZoomImpl.of(percentage);
    }

}
