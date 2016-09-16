package util.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static environment.EnvironmentFactory.*;
import static java.lang.Thread.sleep;

public class DriverHelper {

    private final static Logger LOG = LoggerFactory.getLogger(DriverHelper.class);

    public static void sendKeys(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);

        LOG.info("Send text: " + text);
    }

    public static void sendKeysFullClear(AndroidDriver driver, MobileElement element, String text) {
        MobileHelper.clearField(driver, element).sendKeys(text);
        driver.pressKeyCode(84);

        LOG.info("Send text: " + text);
    }

    public static void scrollDownWeb(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,1000)", "");
    }

    public static void scrollUpWeb(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,-1000)", "");
    }

    public static void scrollDownMobile(AppiumDriver driver) {
        scrollDownMobile(driver, 1000);
    }

    public static void scrollUpMobile(AppiumDriver driver) {
        scrollUpMobile(driver, 1000);
    }

    public static void scrollDownMobile(AppiumDriver driver, int duration) {
        Dimension dimensions = driver.manage().window().getSize();
        int screenHeightStart = dimensions.getHeight() / 2;
        int screenWidthStart = dimensions.getWidth() / 2;

        driver.swipe(screenWidthStart, screenHeightStart, screenWidthStart, 0, duration);
    }

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
            }catch (Exception e){
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

    public static void clickByLocation(AppiumDriver driver, MobileElement element, ClickPoint clickPoint) {
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

    public static void clickJQuery(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        if (!element.getAttribute("id").equals("")) {
            executor.executeScript(String.format("$('#%s').click()", element.getAttribute("id")));
        } else if (!element.getAttribute("class").equals("")) {
            executor.executeScript(String.format("$('%s.%s').click()", element.getTagName(), element.getAttribute("class")));
        }
    }

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