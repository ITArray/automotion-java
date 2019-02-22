package net.itarray.automotion.validation.properties;

import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.ResolutionImpl;

public interface Resolution {
    void applyTo(DriverFacade driver);
    Resolution queryIfUnknown(DriverFacade driver);

    static Resolution resolution(String widthXHeight) {
        return ResolutionImpl.of(widthXHeight);
    }

    static Resolution resolution(int width, int height) {
        return ResolutionImpl.of(width, height);
    }
}
