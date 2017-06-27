package net.itarray.automotion.tools.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static java.lang.Thread.sleep;
import static net.itarray.automotion.tools.environment.EnvironmentFactory.*;

public class DriverHelper {

    private final static Logger LOG = LoggerFactory.getLogger(DriverHelper.class);

    /**
     * Sending the keys into web element with click and clear
     *
     * @param element
     * @param text
     */
    public static void sendKeys(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);

        LOG.info("Send text: " + text);
    }

    /**
     * Sending keys into mobile element with full clearing (iOS driver)
     *
     * @param driver
     * @param element
     * @param text
     */
    public static void sendKeysFullClear(AndroidDriver driver, MobileElement element, String text) {
        MobileHelper.clearField(driver, element).sendKeys(text);
        driver.pressKeyCode(84);

        LOG.info("Send text: " + text);
    }

    /**
     * Scroll down web page for 1000px
     *
     * @param driver
     */
    public static void scrollDownWeb(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,1000)", "");
    }

    /**
     * Scroll up web page for 1000px
     *
     * @param driver
     */
    public static void scrollUpWeb(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,-1000)", "");
    }

    /**
     * Swipe down mobile page with duration 1sec
     *
     * @param driver
     */
    public static void scrollDownMobile(AppiumDriver driver) {
        scrollDownMobile(driver, 1000);
    }

    /**
     * Swipe up mobile page with duration 1sec
     *
     * @param driver
     */
    public static void scrollUpMobile(AppiumDriver driver) {
        scrollUpMobile(driver, 1000);
    }

    /**
     * Swipe down mobile page
     *
     * @param driver
     * @param duration
     */
    public static void scrollDownMobile(AppiumDriver driver, int duration) {
        Dimension dimensions = driver.manage().window().getSize();
        int screenHeightStart = dimensions.getHeight() / 2;
        int screenWidthStart = dimensions.getWidth() / 2;

        driver.swipe(screenWidthStart, screenHeightStart, screenWidthStart, 0, duration);
    }

    /**
     * Swipe up mobile page
     *
     * @param driver
     * @param duration
     */
    public static void scrollUpMobile(AppiumDriver driver, int duration) {
        Dimension dimensions = driver.manage().window().getSize();
        int screenHeightStart = dimensions.getHeight() / 2;
        int screenWidthStart = dimensions.getWidth() / 2;

        driver.swipe(screenWidthStart, screenHeightStart, screenWidthStart, dimensions.getHeight(), duration);
    }

    public static void scrollDownMobileElement(AppiumDriver driver, MobileElement element) {
        scrollDownMobileElement(driver, element, 1000);
    }

    public static void scrollUpMobileElement(AppiumDriver driver, MobileElement element) {
        scrollUpMobileElement(driver, element, 1000);
    }

    public static void scrollDownMobileElement(AppiumDriver driver, MobileElement element, int duration) {
        Dimension dimensions = element.getSize();
        Point position = element.getLocation();
        int screenHeightStart = position.getY() + dimensions.getHeight() / 2;
        int screenWidthStart = position.getX() + dimensions.getWidth() / 2;

        driver.swipe(screenWidthStart, screenHeightStart, screenWidthStart, position.getY(), duration);

        LOG.info("Scroll down element " + element.getId());
    }

    public static void scrollUpMobileElement(AppiumDriver driver, MobileElement element, int duration) {
        Dimension dimensions = element.getSize();
        Point position = element.getLocation();
        int screenHeightStart = position.getY() + dimensions.getHeight() / 2;
        int screenHeightEnd = position.getY() + dimensions.getHeight();
        int screenWidthStart = position.getX() + dimensions.getWidth() / 2;

        driver.swipe(screenWidthStart, screenHeightStart, screenWidthStart, screenHeightEnd, duration);

        LOG.info("Scroll up element " + element.getId());
    }

    /**
     * zoom In/Out the page
     *
     * @param driver
     * @param zoomPercent
     */
    public static void zoomInOutPage(WebDriver driver, int zoomPercent) {
        if (zoomPercent > 0) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            if (isFirefox()) {
                jse.executeScript("document.body.style.MozTransform = 'scale(" + (zoomPercent / 100f) + ")';");
            } else {
                jse.executeScript("document.body.style.zoom = '" + zoomPercent + "%'");
            }
        }
    }

    public static void wait(int seconds) throws InterruptedException {
        sleep(1000 * seconds);
    }

    public static String takeScreenshot(WebDriver driver) throws Exception {
        String fullFileName = System.getProperty("user.dir")
                + "/target/reports/screenshots/screenshot_"
                + System.currentTimeMillis() + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(fullFileName));

        return fullFileName;
    }

    /**
     * Hide keyboard for iOS and Android in single method
     *
     * @param driver
     */
    public static void hideKeyboard(AppiumDriver driver) {
        if (isIOS()) {
            Dimension dimensions = driver.manage().window().getSize();
            if (getDevice().contains("iPhone")) {
                int screenHeightStart = dimensions.getHeight() - dimensions.getHeight() / 3;
                int screenWidthStart = dimensions.getWidth() / 2;

                driver.swipe(screenWidthStart, screenHeightStart, screenWidthStart, dimensions.getHeight(), 500);
            } else {
                int x = dimensions.getWidth() - 30;
                int y = dimensions.getHeight() - 30;

                driver.tap(1, x, y, 1);
            }
        } else if (isAndroid()) {
            try {
                driver.hideKeyboard();
                driver.hideKeyboard();
            } catch (Exception e) {
                LOG.error("Cannot hide a keyboard: " + e.getMessage());
            }
        }

        LOG.info("Hide keyboard");
    }

    public static void click(WebDriver driver, WebElement element) {
        waitForPageIsReady(driver);

        int count = 0;
        while (count < 10) {
            try {
                new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element)).click();
                break;
            } catch (WebDriverException ex) {
                count++;
            }
        }
    }

    /**
     * Click web element by location using clickPoint:
     * TOP_LEFT,
     * TOP_RIGHT,
     * BOTTOM_LEFT,
     * BOTTOM_RIGHT,
     * CENTER
     *
     * @param driver
     * @param element
     * @param clickPoint
     */
    public static void clickByLocation(WebDriver driver, WebElement element, ClickPoint clickPoint) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Actions actions = new Actions(driver);
        switch (clickPoint) {
            case TOP_LEFT:
                actions.moveToElement(element, 5, 5);
                break;
            case TOP_RIGHT:
                actions.moveToElement(element, size.getWidth() - 5, 5);
                break;
            case BOTTOM_LEFT:
                actions.moveToElement(element, 5, size.getHeight() - 5);
                break;
            case BOTTOM_RIGHT:
                actions.moveToElement(element, size.getWidth() - 5, size.getHeight() - 5);
                break;
            case CENTER:
                actions.moveToElement(element, size.getWidth() / 2, size.getHeight() / 2);
                break;
        }

        actions.click().build().perform();
        LOG.info("INFO", "Click on " + clickPoint + " point");
    }

    /**
     * Click mobile element by location using clickPoint:
     * TOP_LEFT,
     * TOP_RIGHT,
     * BOTTOM_LEFT,
     * BOTTOM_RIGHT,
     * CENTER
     *
     * @param driver
     * @param element
     * @param clickPoint
     */
    public static void clickByLocation(AppiumDriver driver, MobileElement element, ClickPoint clickPoint) {
        clickMobileElementByLocation(driver, element, clickPoint);
    }

    public static void clickMobileElementByLocation(AppiumDriver driver, MobileElement element, ClickPoint clickPoint) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        int x = location.getX();
        int y = location.getY();
        switch (clickPoint) {
            case TOP_LEFT:
                driver.tap(1, x + 5, y + 5, 1);
                break;
            case TOP_RIGHT:
                driver.tap(1, x + size.getWidth() - 5, y + 5, 1);
                break;
            case BOTTOM_LEFT:
                driver.tap(1, x + 5, y + size.getHeight() - 5, 1);
                break;
            case BOTTOM_RIGHT:
                driver.tap(1, x + size.getWidth() - 5, y + size.getHeight() - 5, 1);
                break;
            case CENTER:
                driver.tap(1, x + size.getWidth() / 2, y + size.getHeight() / 2, 1);
                break;
        }
        LOG.info("INFO", "Click on " + clickPoint + " point");
    }

    /**
     * Click on element using JQuery click()
     *
     * @param driver
     * @param element
     */
    public static void clickJQuery(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        if (!element.getAttribute("id").equals("")) {
            executor.executeScript(String.format("$('#%s').click()", element.getAttribute("id")));
        } else if (!element.getAttribute("class").equals("")) {
            executor.executeScript(String.format("$('%s.%s').click()", element.getTagName(), element.getAttribute("class")));
        }
    }

    /**
     * Wait for Web page is loaded
     *
     * @param driver
     * @return
     */
    public static boolean waitForPageIsReady(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) executor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return executor.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public boolean elementsArePresented(WebDriver driver, List<String> selectorsList) {
        String source = driver.getPageSource();

        for (String s : selectorsList) {
            if (!source.contains(s)) {
                return false;
            }
        }

        return true;
    }

    public enum ClickPoint {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        CENTER
    }
}