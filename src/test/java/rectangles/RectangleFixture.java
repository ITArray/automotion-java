package rectangles;

import com.google.common.collect.Lists;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;

public class RectangleFixture {

    public static int delta = 2;
    public static int width = 500;
    public static int height = 700;
    private static final int x0 = 100;
    public static final int originX = x0 + width;
    public static final int cornerX = originX + width;
    private static final int x3 = cornerX + width;
    private static final int y0 = 300;
    public static final int originY = y0 + height;
    public static final int cornerY = originY + height;
    private static final int y3 = cornerY + height;
    private static int[] xValues = {
            x0,
            down(originX), originX, up(originX),
            down(cornerX), cornerX, up(cornerX),
            x3};
    private static int[] yValues = {
            y0,
            down(originY), originY, up(originY),
            down(cornerY), cornerY, up(cornerY),
            y3};
    public static long windowWidth = x3 + 1000;
    public static long windowHeight = y3 + 1000;


    private static Collection<Object[]> data() {
        Rectangle2D.Double rectangle = new Rectangle2D.Double(originX, originY, cornerX - originX, cornerY - originY);
        ArrayList<Object[]> result = Lists.newArrayList();

        for (int xIndex1 = 0; xIndex1 + 1 < xValues.length; xIndex1++) {
            int otherOriginX = xValues[xIndex1];
            for (int xIndex2 = xIndex1 + 1; xIndex2 < xValues.length; xIndex2++) {
                int otherCornerX = xValues[xIndex2];
                for (int yIndex1 = 0; yIndex1 + 1 < yValues.length; yIndex1++) {
                    int otherOriginY = yValues[yIndex1];
                    for (int yIndex2 = yIndex1 + 1; yIndex2 < yValues.length; yIndex2++) {
                        int otherCornerY = yValues[yIndex2];
                        Rectangle2D.Double other = new Rectangle2D.Double(otherOriginX, otherOriginY, otherCornerX-otherOriginX, otherCornerY-otherOriginY);
                        boolean intersects = rectangle.intersects(other);
                        boolean otherContainsRoot = other.contains(rectangle);
                        Object[] values = {otherOriginX, otherOriginY, otherCornerX, otherCornerY, intersects, otherContainsRoot};
                        result.add(values);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Collection<Object[]> data = data();
        for (Object[] datum : data) {
            System.out.println(String.format("{%s, %s, %s, %s, %s, %s},", datum[0], datum[1], datum[2], datum[3], datum[4], datum[5]));
        }

    }

    public static int up(int value) {
        return value+delta;
    }

    public static int down(int value) {
        return value-delta;
    }
}
