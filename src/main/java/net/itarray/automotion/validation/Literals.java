package net.itarray.automotion.validation;

import net.itarray.automotion.internal.ZoomImpl;
import net.itarray.automotion.validation.properties.Resolution;
import net.itarray.automotion.internal.ResolutionImpl;
import net.itarray.automotion.validation.properties.Zoom;

public class Literals {

    public static Resolution resolution(String widthXheight) {
        return ResolutionImpl.of(widthXheight);
    }

    public static Resolution resolution(int width, int height) {
        return ResolutionImpl.of(width, height);
    }

    public static Zoom zoom(int percentage) {
        return ZoomImpl.of(percentage);
    }
}
