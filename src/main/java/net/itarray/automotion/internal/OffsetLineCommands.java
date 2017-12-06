package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.Vector;

public class OffsetLineCommands {

    public void drawLeftOffsetLine(TransformedGraphics graphics, Vector extend, UIElement rootElement, DrawingConfiguration drawingConfiguration) {
        drawingConfiguration.setLinesStyle(graphics);
        graphics.drawVerticalLine(rootElement.getX().intValue(), extend.getY().intValue());
    }

    public void drawRightOffsetLine(TransformedGraphics graphics, Vector extend, UIElement rootElement, DrawingConfiguration drawingConfiguration) {
        drawingConfiguration.setLinesStyle(graphics);
        graphics.drawVerticalLine(rootElement.getCorner().getX().intValue(), extend.getY().intValue());
    }

    public void drawTopOffsetLine(TransformedGraphics graphics, Vector extend, UIElement rootElement, DrawingConfiguration drawingConfiguration) {
        drawingConfiguration.setLinesStyle(graphics);
        graphics.drawHorizontalLine(rootElement.getY().intValue(), extend.getX().intValue());
    }

    public void drawBottomOffsetLine(TransformedGraphics graphics, Vector extend, UIElement rootElement, DrawingConfiguration drawingConfiguration) {
        drawingConfiguration.setLinesStyle(graphics);
        graphics.drawHorizontalLine(rootElement.getCorner().getY().intValue(), extend.getX().intValue());
    }

}
