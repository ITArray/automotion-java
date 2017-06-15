package net.itarray.automotion.internal;

public class Zoom {
    public static int applyZoom(int value, int zoom) {
        return (int) (value * getFactor(zoom));
    }

    public static double getFactor(int zoom) {
        return zoom / 100d;
    }
}
