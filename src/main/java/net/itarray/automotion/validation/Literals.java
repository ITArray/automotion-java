package net.itarray.automotion.validation;

import net.itarray.automotion.validation.properties.Resolution;
import net.itarray.automotion.validation.properties.Zoom;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Zoom} or {@link net.itarray.automotion.validation.properties.Resolution}
 */
@Deprecated
public class Literals {

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Resolution#resolution(String)}
     */
    @Deprecated
    public static Resolution resolution(String widthXHeight) {
        return Resolution.resolution(widthXHeight);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Resolution#resolution(int, int)}
     */
    @Deprecated
    public static Resolution resolution(int width, int height) {
        return Resolution.resolution(width, height);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Zoom#zoom(int)}
     */
    @Deprecated
    public static Zoom zoom(int percentage) {
        return Zoom.zoom(percentage);
    }

}
