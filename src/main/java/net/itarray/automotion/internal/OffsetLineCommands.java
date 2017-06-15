package net.itarray.automotion.internal;

import net.itarray.automotion.Element;
import util.validator.ResponsiveUIValidator;

import java.awt.*;
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

    public void draw(TransformedGraphics graphics, BufferedImage img, Color linesColor, Element rootElement) {
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        graphics.setStroke(dashed);
        graphics.setColor(linesColor);
        if (drawLeftOffsetLine) {
            graphics.drawVerticalLine(rootElement.getX(), img.getHeight());
        }
        if (drawRightOffsetLine) {
            graphics.drawVerticalLine(rootElement.getCornerX(), img.getHeight());
        }
        if (drawTopOffsetLine) {
            graphics.drawHorizontalLine(rootElement.getY(), img.getWidth());
        }
        if (drawBottomOffsetLine) {
            graphics.drawHorizontalLine(rootElement.getCornerY(), img.getWidth());
        }
    }

}
