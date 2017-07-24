package rectangles;

import net.itarray.automotion.internal.geometry.Vector;
import org.junit.Before;
import org.openqa.selenium.WebElement;

import static rectangles.DummyWebElement.createElement;

public abstract class ElementWithCenterOffsetFromCenterOfPageTest {

    protected int xOffset;
    protected int yOffset;
    protected WebElement element;

    @Before
    public void createElementX() {
        xOffset = 200;
        yOffset = 150;
        Vector delta = getCenterOffset();
        int deltaX = delta.getX().getValue();
        int deltaY = delta.getY().getValue();
        element = createElement(
                xOffset + deltaX,
                yOffset + deltaY,
                RectangleFixture.pageWidth - xOffset + deltaX,
                RectangleFixture.pageHeight - yOffset + deltaY);

    }

    protected abstract Vector getCenterOffset();
}
