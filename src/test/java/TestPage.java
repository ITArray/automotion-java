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
        super(driver);
    }

    public WebElement header() {
        return getWebElement(By.id("navbar"));
    }

    public WebElement logo() {
        return getWebElement(By.id("main-logo"));
    }

    public WebElement topPhotos() {
        return getWebElement(By.id("top-photos"));
    }

    public WebElement newPhotos() {
        return getWebElement(By.id("new-photos"));
    }

    public WebElement myPhotos() {
        return getWebElement(By.id("my-photos"));
    }

    public WebElement footer() {
        return getWebElement(ExpectedConditions.elementToBeClickable(By.id("footer")));
    }

    public WebElement contactForm() {
        return getWebElement(By.id("contact-form"));
    }

    public List<WebElement> images() {
        return getWebElements(By.className("image"));
    }
}
