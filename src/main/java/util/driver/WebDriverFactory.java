package util.driver;

import org.openqa.selenium.WebDriver;
import java.util.Map;

/**
 * @deprecated As of release 2.0, replaced by{@link net.itarray.automotion.tools.util.driver.WebDriverFactory}
 */
public class WebDriverFactory {

    private final net.itarray.automotion.tools.util.driver.WebDriverFactory delegatee;

    public WebDriverFactory() {
        delegatee = new net.itarray.automotion.tools.util.driver.WebDriverFactory();
    }

    public WebDriver getDriver() {
        return delegatee.getDriver();
    }

    public void updateCapabilities(Map<String, Object> mapCapabilities) {
        delegatee.updateCapabilities(mapCapabilities);
    }

}
