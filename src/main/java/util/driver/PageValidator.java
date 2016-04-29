package util.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PageValidator {

    private final static Logger LOG = LoggerFactory.getLogger(PageValidator.class);


    public static boolean elementsAreAlignedHorizontally(List<WebElement> elements) {
        boolean aligned = false;

        for (int i = 1; i < elements.size(); i++) {
            WebElement elementPrevious = elements.get(i - 1);
            WebElement elementCurrent = elements.get(i);

            Dimension sizeElementPrevious = elementPrevious.getSize();

            Point positionElementPrevious = elementPrevious.getLocation();
            Point positionElementCurrent = elementCurrent.getLocation();

            if (positionElementCurrent.getX() < positionElementPrevious.getX() + sizeElementPrevious.getWidth()) {
                aligned = false;
                break;
            }

            aligned = true;
        }

        return aligned;
    }

    public static boolean elementsAreAlignedVertically(List<WebElement> elements) {
        boolean aligned = false;

        for (int i = 1; i < elements.size(); i++) {
            WebElement elementPrevious = elements.get(i - 1);
            WebElement elementCurrent = elements.get(i);

            Dimension sizeElementPrevious = elementPrevious.getSize();

            Point positionElementPrevious = elementPrevious.getLocation();
            Point positionElementCurrent = elementCurrent.getLocation();

            if (positionElementCurrent.getY() < positionElementPrevious.getY() + sizeElementPrevious.getHeight()) {
                aligned = false;
                break;
            }

            aligned = true;
        }

        return aligned;
    }

    public static boolean elementsAreAlignedProperly(List<WebElement> elements) {
        boolean aligned = false;

        for (int i = 1; i < elements.size(); i++) {
            WebElement elementPrevious = elements.get(i - 1);
            WebElement elementCurrent = elements.get(i);

            Dimension sizeElementPrevious = elementPrevious.getSize();

            Point positionElementPrevious = elementPrevious.getLocation();
            Point positionElementCurrent = elementCurrent.getLocation();

            if ((positionElementCurrent.getY() >= positionElementPrevious.getY() + sizeElementPrevious.getHeight()) ||
                    positionElementCurrent.getX() >= positionElementPrevious.getX() + sizeElementPrevious.getWidth() ||
                    ((positionElementCurrent.getX() < positionElementPrevious.getX() + sizeElementPrevious.getWidth())
                            && positionElementCurrent.getY() >= positionElementPrevious.getY() + sizeElementPrevious.getHeight())) {
                aligned = true;
            } else {
                aligned = false;
                LOG.debug("Wrong item on position: " + i + ".\nPrevious element text is: " + elementPrevious.getText() + ".\nCurrent element text is: " + elementCurrent.getText() +  ".\nCoord of previous item is [" + positionElementPrevious.getX() + "," + positionElementPrevious.getY() + "]." +
                        "\nCoord of current item is [" + positionElementCurrent.getX() + "," + positionElementCurrent.getY() + "]");
                break;
            }
        }

        return aligned;
    }
}
