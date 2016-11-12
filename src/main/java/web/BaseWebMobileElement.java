package web;

import com.google.common.base.Function;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class BaseWebMobileElement{

    private WebDriver driver;

    protected int timeOfWaiting = 600;

    protected FluentWait<WebDriver> wait;

    public BaseWebMobileElement(WebDriver driver) {
        this.driver = driver;

        wait = new FluentWait<>(driver)
                .withTimeout(timeOfWaiting, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class);
    }

    public BaseWebMobileElement(WebDriver driver, int timeOfWaiting) {
        this.driver = driver;

        wait = new FluentWait<>(driver)
                .withTimeout(timeOfWaiting, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
        this.timeOfWaiting = timeOfWaiting;
    }

    protected WebElement getWebElement(final By by) {
        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(by);
            }
        });
    }

    protected WebElement getWebElement(ExpectedCondition<WebElement> expectedCondition) {
        return wait.until(expectedCondition);
    }

    protected MobileElement getMobileElement(final By by, int timeOfWaiting) {
        return wait.until(new Function<WebDriver, MobileElement>() {
            public MobileElement apply(WebDriver d) {
                return (MobileElement) d.findElement(by);
            }
        });
    }

    protected MobileElement getMobileElement(final By by) {
        return wait.until(new Function<WebDriver, MobileElement>() {
            public MobileElement apply(WebDriver d) {
                return (MobileElement) d.findElement(by);
            }
        });
    }


    protected List<WebElement> getWebElements(final By by) {
        return wait.until(new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver d) {
                return d.findElements(by);
            }
        });
    }

    protected List<WebElement> getWebElements(ExpectedCondition<List<WebElement>> expectedCondition) {
        return wait.until(expectedCondition);
    }

    public boolean isElementInvisible(By by) {
        List elements = driver.findElements(by);

        return elements.size() == 0;
    }
}
