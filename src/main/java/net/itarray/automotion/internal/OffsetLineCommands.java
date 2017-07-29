package net.itarray.automotion.internal;

import java.awt.image.BufferedImage;

public class OffsetLineCommands {
    private boolean drawLeftOffsetLine = false;
    private boolean drawRightOffsetLine = false;
    private boolean drawTopOffsetLine = false;
    private boolean drawBottomOffsetLine = false;

    public void drawLeftOffsetLine() {
        drawLeftOffsetLine = true;
    }

    public void drawRightOffsetLine() {
        drawRightOffsetLine = true;
    }

    public void drawTopOffsetLine() {
        drawTopOffsetLine = true;
    }

    public void drawBottomOffsetLine() {
        drawBottomOffsetLine = true;
    }

    public void draw(TransformedGraphics graphics, BufferedImage img, UIElement rootElement, DrawingConfiguration drawingConfiguration) {
        drawingConfiguration.setLinesStyle(graphics);
        if (drawLeftOffsetLine) {
            graphics.drawVerticalLine(rootElement.getX().intValue(), img.getHeight());
        }
        if (drawRightOffsetLine) {
            graphics.drawVerticalLine(rootElement.getCorner().getX().intValue(), img.getHeight());
        }
        if (drawTopOffsetLine) {
            graphics.drawHorizontalLine(rootElement.getY().intValue(), img.getWidth());
        }
        if (drawBottomOffsetLine) {
            graphics.drawHorizontalLine(rootElement.getCorner().getY().intValue(), img.getWidth());
        }
    }

}
