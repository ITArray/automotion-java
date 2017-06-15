package net.itarray.automotion.internal;

import java.awt.*;

public class TransformedGraphics {
    private final Graphics2D g;
    private final SimpleTransform transform;

    public TransformedGraphics(Graphics2D g, SimpleTransform transform) {
        this.g = g;
        this.transform = transform;
    }

    public void drawRectByExtend(int x, int y, int width, int height) {
        drawRectByCorner(x, y, x + width, y + height);
    }

    private void drawRectByCorner(int x, int y, int cornerX, int cornerY) {
        int transformedX = transform.transformX(x);
        int transformedY = transform.transformY(y);
        int transformedCornerX = transform.transformX(cornerX);
        int transformedCornerY = transform.transformY(cornerY);
        int transformedWidth = transformedCornerX - transformedX;
        int transformedHeight = transformedCornerY - transformedY;
        g.drawRect(transformedX, transformedY, transformedWidth, transformedHeight);
    }

    public void drawVerticalLine(int x, int height) {
        int transformedX = transform.transformX(x);
        int transformedHeight = transform.transformY(height) - transform.transformY(0);
        g.drawLine(transformedX, 0, transformedX, transformedHeight);
    }

    public void drawHorizontalLine(int y, int width) {
        int transformedY = transform.transformY(y);
        int transformedWidth = transform.transformX(width) - transform.transformX(0);
        g.drawLine(0, transformedY, transformedWidth, transformedY);
    }


}
