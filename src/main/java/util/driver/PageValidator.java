package util.driver;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @deprecated As of release 2.0, replaced by{@link net.itarray.automotion.tools.driver.PageValidator}
 */
@Deprecated
public class PageValidator {

    public static boolean elementsAreAlignedHorizontally(List<WebElement> webElements) {
        return net.itarray.automotion.tools.driver.PageValidator.elementsAreAlignedHorizontally(webElements);
    }

    public static boolean elementsAreAlignedVertically(List<WebElement> webElements) {
        return net.itarray.automotion.tools.driver.PageValidator.elementsAreAlignedVertically(webElements);
    }

    public static boolean elementsAreAlignedProperly(List<WebElement> elements) {
        return net.itarray.automotion.tools.driver.PageValidator.elementsAreAlignedProperly(elements);
    }
}
