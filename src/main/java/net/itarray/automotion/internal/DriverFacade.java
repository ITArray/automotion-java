package net.itarray.automotion.internal;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static environment.EnvironmentFactory.*;

public class DriverFacade {
    private final WebDriver driver;

    public DriverFacade(WebDriver driver) {
        this.driver = driver;
    }

    public File takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
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
        String zoom = (String) executeScript(getZoomScript());
        if (zoom == null || zoom.equals("")) {
            zoom = "100%";
        }
        return zoom;
    }

    private String getZoomScript() {
        if (isFirefox()) {
            return "document.body.style.MozTransform";
        } else {
            return "return document.body.style.zoom;";
        }
    }

    private long retrievePageHeight() {
        if (!isMobile()) {
            if (getZoom().equals("100%")) {
                return (long) executeScript("if (self.innerHeight) {return self.innerHeight;} if (document.documentElement && document.documentElement.clientHeight) {return document.documentElement.clientHeight;}if (document.body) {return document.body.clientHeight;}");
            } else {
                return (long) executeScript("return document.getElementsByTagName('body')[0].offsetHeight");
            }
        } else {
            if (isAppiumNativeMobileContext() || isIOS()) {
                return driver.manage().window().getSize().getHeight();
            } else {
                return (long) executeScript("if (self.innerHeight) {return self.innerHeight;} if (document.documentElement && document.documentElement.clientHeight) {return document.documentElement.clientHeight;}if (document.body) {return document.body.clientHeight;}");
            }
        }
    }

    private long retrievePageWidth() {
        if (!isMobile()) {
            if (getZoom().equals("100%")) {
                String script = "if (self.innerWidth) {return self.innerWidth;} if (document.documentElement && document.documentElement.clientWidth) {return document.documentElement.clientWidth;}if (document.body) {return document.body.clientWidth;}";
                return (long) executeScript(script);
            } else {
                return (long) executeScript("return document.getElementsByTagName('body')[0].offsetWidth");
            }
        } else {
            if (isAppiumNativeMobileContext() || isIOS()) {
                return driver.manage().window().getSize().getWidth();
            } else {
                return (long) executeScript("if (self.innerWidth) {return self.innerWidth;} if (document.documentElement && document.documentElement.clientWidth) {return document.documentElement.clientWidth;}if (document.body) {return document.body.clientWidth;}");
            }
        }
    }


    public Dimension retrievePageSize() {
        return new Dimension((int) retrievePageWidth(), (int) retrievePageHeight());
    }
}
