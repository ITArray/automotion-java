package web;

import org.openqa.selenium.WebDriver;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.tools.web.BaseWebMobileElement}
 */
@Deprecated
public abstract class BaseWebMobileElement extends net.itarray.automotion.tools.web.BaseWebMobileElement {

    public BaseWebMobileElement(WebDriver driver) {
        super(driver);
    }

    public BaseWebMobileElement(WebDriver driver, int timeOfWaiting) {
        super(driver, timeOfWaiting);
    }
}
