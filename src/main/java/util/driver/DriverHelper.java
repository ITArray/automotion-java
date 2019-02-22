package util.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @deprecated As of release 2.0, replaced by{@link net.itarray.automotion.tools.driver.DriverHelper}
 */
@Deprecated
public class DriverHelper {

    private final static Logger LOG = LoggerFactory.getLogger(DriverHelper.class);

    /**
     * Sending the keys into web element with click and clear
     *
     * @param element
     * @param text
     */
    public static void sendKeys(WebElement element, String text) {
        net.itarray.automotion.tools.driver.DriverHelper.sendKeys(element, text);
    }

    /**
     * Sending keys into mobile element with full clearing (iOS driver)
     *
     * @param driver
     * @param element
     * @param text
     */
    public static void sendKeysFullClear(AndroidDriver driver, MobileElement element, String text) {
        net.itarray.automotion.tools.driver.DriverHelper.sendKeysFullClear(driver, element, text);
    }

    /**
     * Scroll down web page for 1000px
     *
     * @param driver
     */
    public static void scrollDownWeb(WebDriver driver) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollDownWeb(driver);
    }

    /**
     * Scroll up web page for 1000px
     *
     * @param driver
     */
    public static void scrollUpWeb(WebDriver driver) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollUpWeb(driver);
    }

    /**
     * Swipe down mobile page with duration 1sec
     *
     * @param driver
     */
    public static void scrollDownMobile(AppiumDriver driver) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollDownMobile(driver);
    }

    /**
     * Swipe up mobile page with duration 1sec
     *
     * @param driver
     */
    public static void scrollUpMobile(AppiumDriver driver) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollUpMobile(driver);
    }

    /**
     * Swipe down mobile page
     *
     * @param driver
     * @param duration
     */
    public static void scrollDownMobile(AppiumDriver driver, int duration) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollDownMobile(driver, duration);
    }

    /**
     * Swipe up mobile page
     *
     * @param driver
     * @param duration
     */
    public static void scrollUpMobile(AppiumDriver driver, int duration) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollUpMobile(driver, duration);
    }

    public static void scrollDownMobileElement(AppiumDriver driver, MobileElement element) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollDownMobileElement(driver, element);
    }

    public static void scrollUpMobileElement(AppiumDriver driver, MobileElement element) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollUpMobileElement(driver, element);
    }

    public static void scrollDownMobileElement(AppiumDriver driver, MobileElement element, int duration) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollDownMobileElement(driver, element, duration);
    }

    public static void scrollUpMobileElement(AppiumDriver driver, MobileElement element, int duration) {
        net.itarray.automotion.tools.driver.DriverHelper.scrollUpMobileElement(driver, element, duration);
    }

    /**
     * zoom In/Out the page
     *
     * @param driver
     * @param zoomPercent
     */
    public static void zoomInOutPage(WebDriver driver, int zoomPercent) {
        net.itarray.automotion.tools.driver.DriverHelper.zoomInOutPage(driver, zoomPercent);
    }

    public static void wait(int seconds) throws InterruptedException {
        net.itarray.automotion.tools.driver.DriverHelper.wait(seconds);
    }

    public static String takeScreenshot(WebDriver driver) throws Exception {
        return net.itarray.automotion.tools.driver.DriverHelper.takeScreenshot(driver);
    }

    /**
     * Hide keyboard for iOS and Android in single method
     *
     * @param driver
     */
    public static void hideKeyboard(AppiumDriver driver) {
        net.itarray.automotion.tools.driver.DriverHelper.hideKeyboard(driver);
    }

    public static void click(WebDriver driver, WebElement element) {
        net.itarray.automotion.tools.driver.DriverHelper.click(driver, element);
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
        net.itarray.automotion.tools.driver.DriverHelper.clickByLocation(driver, element, convert(clickPoint));
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
        net.itarray.automotion.tools.driver.DriverHelper.clickByLocation(driver, element, convert(clickPoint));
    }

    private static net.itarray.automotion.tools.driver.DriverHelper.ClickPoint convert(ClickPoint clickPoint) {
        switch (clickPoint) {
            case CENTER:
                return net.itarray.automotion.tools.driver.DriverHelper.ClickPoint.CENTER;
            case BOTTOM_LEFT:
                return net.itarray.automotion.tools.driver.DriverHelper.ClickPoint.BOTTOM_LEFT;
            case BOTTOM_RIGHT:
                return net.itarray.automotion.tools.driver.DriverHelper.ClickPoint.BOTTOM_RIGHT;
            case TOP_LEFT:
                return net.itarray.automotion.tools.driver.DriverHelper.ClickPoint.TOP_LEFT;
            case TOP_RIGHT:
                return net.itarray.automotion.tools.driver.DriverHelper.ClickPoint.TOP_RIGHT;
            default:
                throw new RuntimeException("Should not happen");
        }
    }
    /**
     * Click on element using JQuery click()
     *
     * @param driver
     * @param element
     */
    public static void clickJQuery(WebDriver driver, WebElement element) {
        net.itarray.automotion.tools.driver.DriverHelper.clickJQuery(driver, element);
    }

    /**
     * Wait for Web page is loaded
     *
     * @param driver
     * @return
     */
    public static boolean waitForPageIsReady(WebDriver driver) {
        return net.itarray.automotion.tools.driver.DriverHelper.waitForPageIsReady(driver);
    }

    public boolean elementsArePresented(WebDriver driver, List<String> selectorsList) {
        return net.itarray.automotion.tools.driver.DriverHelper.waitForPageIsReady(driver);
    }

    public enum ClickPoint {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        CENTER
    }
}