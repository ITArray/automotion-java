package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Vector;

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

    public void draw(TransformedGraphics graphics, Vector extend, UIElement rootElement, DrawingConfiguration drawingConfiguration) {
        drawingConfiguration.setLinesStyle(graphics);
        int height = extend.getY().intValue();
        if (drawLeftOffsetLine) {
            graphics.drawVerticalLine(rootElement.getX().intValue(), height);
        }
        if (drawRightOffsetLine) {
            graphics.drawVerticalLine(rootElement.getCorner().getX().intValue(), height);
        }
        int width = extend.getX().intValue();
        if (drawTopOffsetLine) {
            graphics.drawHorizontalLine(rootElement.getY().intValue(), width);
        }
        if (drawBottomOffsetLine) {
            graphics.drawHorizontalLine(rootElement.getCorner().getY().intValue(), width);
        }
    }

}
