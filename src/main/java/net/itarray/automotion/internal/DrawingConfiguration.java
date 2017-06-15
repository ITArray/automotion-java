package net.itarray.automotion.internal;

import java.awt.*;

public class DrawingConfiguration {
    public Color rootColor = new Color(255, 0, 0, 255);
    public Color highlightedElementsColor = new Color(255, 0, 255, 255);
    public Color linesColor = Color.ORANGE;

    public void setRootColor(Color rootColor) {
        this.rootColor = rootColor;
    }

    public void setHighlightedElementsColor(Color highlightedElementsColor) {
        this.highlightedElementsColor = highlightedElementsColor;
    }

    public void setLinesColor(Color linesColor) {
        this.linesColor = linesColor;
    }
}
