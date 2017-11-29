package net.itarray.automotion.internal;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static net.itarray.automotion.tools.environment.EnvironmentFactory.*;

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
        byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        try (OutputStream stream = new FileOutputStream(file); ){
            stream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean isAppiumAndroidContext() {
        if ((driver instanceof AndroidDriver)) {
            return true;
        }
        return false;
    }

    public boolean isAppiumIOSContext() {
        if ((driver instanceof AndroidDriver)) {
            return true;
        }
        return false;
    }

    public boolean isAppiumContext() {
        if ((driver instanceof AppiumDriver)) {
            return true;
        }
        return false;
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

    private String getZoomScript() {
        if (isFirefox()) {
            return "document.body.style.MozTransform";
        } else {
            return "return document.body.style.zoom;";
        }
    }

    private long retrievePageHeight() {
        if (!isAppiumAndroidContext()) {
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
        if (!isAppiumAndroidContext()) {
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

    public void setResolution(Dimension resolution) {
        driver.manage().window().setSize(resolution);
    }

    public Dimension getResolution() {
        if (isAppiumContext() && getApp() == null) {
            String resolution = ((RemoteWebDriver) driver).getCapabilities().getCapability("deviceScreenSize").toString();
            int width = Integer.parseInt(resolution.split("x")[0]);
            int height = Integer.parseInt(resolution.split("x")[1]);

            return new Dimension(width, height);
        } else {
            return driver.manage().window().getSize();
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
}
