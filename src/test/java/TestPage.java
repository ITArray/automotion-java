import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.BaseWebMobileElement;

import java.util.List;

/**
 * Created by ZayCo on 24/11/16.
 */
public class TestPage extends BaseWebMobileElement {

    public TestPage(WebDriver driver) {
        super(driver, 10);
    }

    public WebElement mainContainer() {
        return getWebElement(By.className("container"));
    }

    public WebElement topSlider() {
        return getWebElement(By.className("flexslider"));
    }

    public WebElement topTextBlock() {
        return getWebElement(By.className("span4"));
    }

    public WebElement gridContainer() {
        return getWebElement(By.className("clearfix"));
    }

    public List<WebElement> gridElements() {
        return getWebElements(By.className("gallery-item"));
    }
}
