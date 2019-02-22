package net.itarray.automotion.internal;

import java.awt.*;

public class DrawingConfiguration {
    private Color rootColor = new Color(255, 0, 0, 255);
    private Color highlightedElementsColor = new Color(255, 0, 255, 255);
    private Color linesColor = Color.ORANGE;

    public void setRootColor(Color rootColor) {
        this.rootColor = rootColor;
    }

    public void setHighlightedElementsColor(Color highlightedElementsColor) {
        this.highlightedElementsColor = highlightedElementsColor;
    }

    public void setLinesColor(Color linesColor) {
        this.linesColor = linesColor;
    }

    public void setHighlightedElementStyle(TransformedGraphics graphics) {
        graphics.setColor(highlightedElementsColor);
        graphics.setStroke(new BasicStroke(2));
    }

    public void setRootElementStyle(TransformedGraphics graphics) {
        graphics.setColor(rootColor);
        graphics.setStroke(new BasicStroke(2));
    }

    public void setLinesStyle(TransformedGraphics graphics) {
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        graphics.setStroke(dashed);
        graphics.setColor(linesColor);
    }
}
