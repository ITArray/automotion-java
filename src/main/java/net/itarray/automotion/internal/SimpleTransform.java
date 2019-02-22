package net.itarray.automotion.internal;

public class SimpleTransform {

    public final int yOffset;
    public final double scaleFactor;

    public SimpleTransform(int yOffset, double scaleFactor) {
        this.yOffset = yOffset;
        this.scaleFactor = scaleFactor;
    }

    public int transformX(int x) {
        return (int) (x * scaleFactor);
    }

    public int transformY(int y) {
        return (int) ((y + yOffset) * scaleFactor);
    }
}
