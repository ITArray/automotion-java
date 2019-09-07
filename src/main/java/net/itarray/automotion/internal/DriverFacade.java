package net.itarray.automotion.internal;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import net.itarray.automotion.internal.geometry.Vector;
import net.itarray.automotion.tools.general.SystemHelper;
import org.openqa.grid.common.exception.CapabilityNotPresentOnTheGridException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static java.lang.Integer.parseInt;
import static net.itarray.automotion.tools.environment.EnvironmentFactory.getApp;
import static net.itarray.automotion.tools.environment.EnvironmentFactory.isFirefox;

public class DriverFacade {
    private final WebDriver driver;

    public DriverFacade(WebDriver driver) {
        this.driver = driver;
    }

    public File takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    public void takeScreenshot(File file) {
        file.getParentFile().mkdirs();

        if (!isPhantomJSDriver() && !isAppiumContext() && parseInt(getZoom().replace("%", "")) <= 100) {
            long windowYOffset = (long) executeScript("return window.pageYOffset");
            long windowXOffset = (long) executeScript("return window.pageXOffset");

            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportRetina(100,
                            Integer.parseInt(System.getProperty("headerCutPx") != null ? System.getProperty("headerCutPx") : "0"),
                            Integer.parseInt(System.getProperty("footerCutPx") != null ? System.getProperty("footerCutPx") : "0"),
                            (SystemHelper.isRetinaDisplay()) ? 2 : 1)).takeScreenshot(driver);

            try {
                ImageIO.write(screenshot.getImage(), "PNG", file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            executeScript("window.scrollTo(" + windowXOffset + ", " + windowYOffset + ")");
        } else {
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            try (OutputStream stream = new FileOutputStream(file)) {
                stream.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Vector getExtend(File screenshotName) {
        try {
            BufferedImage img = ImageIO.read(screenshotName);

            int width = img.getWidth();
            int height = img.getHeight();
            return new Vector(width, height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public WebDriver getDriver() {
        return driver;
    }

    public boolean isChromeDriver() {
        return (driver instanceof ChromeDriver) || ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase().equals("chrome");
    }

    public boolean isFirefoxDriver() {
        return (driver instanceof FirefoxDriver) || ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase().equals("firefox");
    }

    public boolean isPhantomJSDriver() {
        return (driver instanceof PhantomJSDriver);
    }

    public boolean isAppiumAndroidContext() {
        return driver instanceof AndroidDriver;
    }

    public boolean isAppiumIOSContext() {
        return driver instanceof IOSDriver;
    }

    public boolean isAppiumContext() {
        return driver instanceof AppiumDriver;
    }

    public boolean isAppiumWebContext() {
        if (!(driver instanceof AppiumDriver)) {
            return false;
        }
        return ((AppiumDriver) driver).getContext().startsWith("WEB");
    }

    public boolean isAppiumNativeMobileContext() {
        if (!(driver instanceof AppiumDriver)) {
            return false;
        }
        return ((AppiumDriver) driver).getContext().contains("NATIVE");
    }

    public Object executeScript(String script) {
        return ((JavascriptExecutor) driver).executeScript(script);
    }

    public String getZoom() {
        if (!isAppiumContext()) {
            String zoom = (String) executeScript(getZoomScript());
            if (zoom == null || zoom.equals("")) {
                zoom = "100%";
            }
            return zoom;
        } else {
            return "100%";
        }
    }

    public void setZoom(int percentage) {
        if (!isAppiumContext()) {
            if (percentage <= 0) {
                throw new IllegalArgumentException(String.format("illegal zoom percentage %s - should be greater than zero", percentage));
            }
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            if (isFirefox()) {
                jse.executeScript("document.body.style.MozTransform = 'scale(" + (percentage / 100f) + ")';");
            } else {
                jse.executeScript("document.body.style.zoom = '" + percentage + "%'");
            }
        }

    }

    private String getZoomScript() {
        if (isFirefox()) {
            return "document.body.style.MozTransform";
        } else {
            return "return document.body.style.zoom;";
        }
    }

    private long retrievePageHeight() {
        if (!isAppiumContext()) {
            if (getZoom().equals("100%")) {
                return (long) executeScript("if (self.innerHeight) {return self.innerHeight;} if (document.documentElement && document.documentElement.clientHeight) {return document.documentElement.clientHeight;}if (document.body) {return document.body.clientHeight;}");
            } else {
                return (long) executeScript("return document.getElementsByTagName('body')[0].offsetHeight");
            }
        } else {
            if (isAppiumNativeMobileContext() || isAppiumIOSContext()) {
                return driver.manage().window().getSize().getHeight();
            } else {
                return (long) executeScript("if (self.innerHeight) {return self.innerHeight;} if (document.documentElement && document.documentElement.clientHeight) {return document.documentElement.clientHeight;}if (document.body) {return document.body.clientHeight;}");
            }
        }
    }

    private long retrievePageWidth() {
        if (!isAppiumContext()) {
            if (getZoom().equals("100%")) {
                String script = "if (self.innerWidth) {return self.innerWidth;} if (document.documentElement && document.documentElement.clientWidth) {return document.documentElement.clientWidth;}if (document.body) {return document.body.clientWidth;}";
                return (long) executeScript(script);
            } else {
                return (long) executeScript("return document.getElementsByTagName('body')[0].offsetWidth");
            }
        } else {
            if (isAppiumNativeMobileContext() || isAppiumIOSContext()) {
                return driver.manage().window().getSize().getWidth();
            } else {
                return (long) executeScript("if (self.innerWidth) {return self.innerWidth;} if (document.documentElement && document.documentElement.clientWidth) {return document.documentElement.clientWidth;}if (document.body) {return document.body.clientWidth;}");
            }
        }
    }

    public Dimension retrievePageSize() {
        return new Dimension((int) retrievePageWidth(), (int) retrievePageHeight());
    }

    public Dimension getResolution() {
        if (isAppiumContext() && ((AppiumDriver)driver).getCapabilities().getCapability("app") == null) {
            Object res = ((RemoteWebDriver) driver).getCapabilities().getCapability("deviceScreenSize");
            if (res != null) {
                String resolution = String.valueOf(res);
                int width = parseInt(resolution.split("x")[0]);
                int height = parseInt(resolution.split("x")[1]);

                return new Dimension(width, height);
            } else {
                throw new CapabilityNotPresentOnTheGridException("Capability 'deviceScreenSize' is not found. Please, add this capability as e.g: deviceScreenSize=800x1200");
            }
        } else {
            return driver.manage().window().getSize();
        }
    }

    public void setResolution(Dimension resolution) {
        driver.manage().window().setSize(resolution);
    }
}
