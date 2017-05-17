package rectangles;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

// this contains edge cases (touching rectangles) as well as non edge case (disjoint coordinates)
@RunWith(Parameterized.class)
public class IntersectionTest {

    private final DummyWebElement other;

    private static int delta = 2;
    private static int w = 500;
    private static int h = 700;
    private static final int x0 = 100;
    private static final int originX = x0 + w;
    private static final int cornerX = originX + w;
    private static final int x3 = cornerX + w;
    private static final int y0 = 300;
    private static final int originY = y0 + h;
    private static final int cornerY = originY + h;
    private static final int y3 = cornerY + h;
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
    private long windowWidth = x3 + 1000;
    private long windowHeight = y3 + 1000;

    private final DummyWebElement root;
    private boolean intersects;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Rectangle2D.Double rectangle = new Rectangle2D.Double(originX, originY, cornerX - originX, cornerY - originY);
        ArrayList<Object[]> result = Lists.newArrayList();

        for (int xIndex1 = 0; xIndex1 + 1 < xValues.length; xIndex1++) {
            int xValue1 = xValues[xIndex1];
            for (int xIndex2 = xIndex1 + 1; xIndex2 < xValues.length; xIndex2++) {
                int xValue2 = xValues[xIndex2];
                for (int yIndex1 = 0; yIndex1 + 1 < yValues.length; yIndex1++) {
                    int yValue1 = yValues[yIndex1];
                    for (int yIndex2 = yIndex1 + 1; yIndex2 < yValues.length; yIndex2++) {
                        int yValue2 = yValues[yIndex2];
                        Rectangle2D.Double other = new Rectangle2D.Double(xValue1, yValue1, xValue2-xValue1, yValue2-yValue1);
                        boolean intersects = rectangle.intersects(other);
                        Object[] values = {xValue1, yValue1, xValue2, yValue2, intersects};
                        result.add(values);
                    }
                }
            }
        }

        return result;
    }

    public IntersectionTest(int otherOriginX, int otherOriginY, int otherCornerX, int otherCornerY, boolean intersects) {
        this.intersects = intersects;
        root = createElement(originX, originY, cornerX, cornerY);
        other = createElement(otherOriginX, otherOriginY, otherCornerX, otherCornerY);
    }

    @Test
    public void shouldOverlap() {
        WebDriver driver = new DummyWebDriver(windowWidth, windowHeight);
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        validator.overlapWith(other, "Bla");
        assertThat(validator.validate())
                .withFailMessage("%s and %s should %soverlap " + asSvg(), toString(root), toString(other), intersects ? "": "not ")
                .isEqualTo(intersects);
    }

    @Test
    public void shouldNotOverlap() {
        WebDriver driver = new DummyWebDriver(windowWidth, windowHeight);
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        validator.notOverlapWith(other, "Bla");
        assertThat(validator.validate())
                .withFailMessage("%s and %s should %soverlap", toString(root), toString(other), intersects ? "": "not ")
                .isEqualTo(!intersects);
    }

    private static int up(int value) {
        return value+delta;
    }

    private static int down(int value) {
        return value-delta;
    }

    @NotNull
    private DummyWebElement createElement(int originX, int originY, int cornerX, int cornerY) {
        return new DummyWebElement(
                new Point(originX, originY),
                new Dimension(cornerX-originX, cornerY-originY));
    }

    public String toString(WebElement webElement) {
        return String.format("[%s@%s-%s@%s]",
                webElement.getLocation().getX(),
                webElement.getLocation().getY(),
                webElement.getLocation().getX() + webElement.getSize().getWidth(),
                webElement.getLocation().getY() + webElement.getSize().getHeight());

    }

    private String asSvg() {
        return String.format(
                "\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<svg width=\"2000\" height=\"2000\">\n" +
                "    <rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\"\n" +
                "          style=\"fill:red;;stroke-width:0;fill-opacity:0.5\" />\n" +
                "    <rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\"\n" +
                "          style=\"fill:cyan;;stroke-width:0;fill-opacity:0.5\" />\n" +
                "</svg>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n",
                root.getLocation().getX(),
                root.getLocation().getY(),
                root.getSize().getWidth(),
                root.getSize().getHeight(),
                other.getLocation().getX(),
                other.getLocation().getY(),
                other.getSize().getWidth(),
                other.getSize().getHeight()
        );
    }

}
