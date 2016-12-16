package util.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static environment.EnvironmentFactory.*;
import static util.driver.CapabilitiesFactory.getCapabilities;

public class WebDriverFactory {
    private final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

    private String remoteUrlPath;
    private AppiumDriver appiumDriver;
    private WebDriver driver;
    private WebDriver webDriver;
    private RemoteWebDriver remoteWebDriver;
    private DesiredCapabilities capabilities;

    public WebDriverFactory() {
        capabilities = getCapabilities();
        LOG.info("Using capabilities: " + capabilities.toString());
        remoteUrlPath = getRemoteUrlPath();
    }

    private static void setChromeDriver() {
        Platform platform = Platform.getCurrent();
        String chromeBinary = "src/main/resources/drivers/chromedriver"
                + (platform.toString().toUpperCase().contains("WIN") ? ".exe" : "");
        System.setProperty("webdriver.chrome.driver", chromeBinary);
    }

    private static void setGeckoDriver() {
        Platform platform = Platform.getCurrent();
        String geckoBinary = "src/main/resources/drivers/geckodriver"
                + (platform.toString().toUpperCase().contains("WIN") ? ".exe" : "");
        System.setProperty("webdriver.gecko.driver", geckoBinary);
    }

    private static void setIEDriver() {
        String ieBinary = "src/main/resources/drivers/IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", ieBinary);
    }

    private static void setEdgeDriver() {
        String edgeBinary = "src/main/resources/drivers/MicrosoftWebDriver.exe";
        System.setProperty("webdriver.edge.driver", edgeBinary);
    }

    public WebDriver getDriver() {
        if (isMobile()) {
            driver = getMobileDriver();
            LOG.info("Start Mobile driver");
        } else if (isLocal()) {
            driver = getLocalWebDriver();
            LOG.info("Start Local web driver");
        } else if (isRemote()) {
            driver = getRemoteWebDriver();
            LOG.info("Start Remote driver");
        } else if (isHeadless()) {
            driver = getPhantomJSDriver();
            LOG.info("Start Phantom JS driver");
        }

        return driver;
    }

    public void updateCapabilities(Map<String, Object> mapCapabilities) {
        CapabilitiesFactory.updateCapabilities(capabilities, mapCapabilities);
    }

    private AppiumDriver getMobileDriver() {
        if (isAndroid()) {
            try {
                appiumDriver = new AndroidDriver(new URL(getRemoteUrlPath()), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (isIOS() || isWindows()) {
            try {
                appiumDriver = new IOSDriver(new URL(getRemoteUrlPath()), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return appiumDriver;
    }

    private WebDriver getLocalWebDriver() {
        if (isFirefox()) {
            setGeckoDriver();
            webDriver = new FirefoxDriver();
        } else if (isChrome()) {
            setChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--kiosk");
            options.addArguments("--disable-notifications");
            webDriver = new ChromeDriver(options);
        } else if (isSafari()) {
            webDriver = new SafariDriver();
        } else if (isInternetExplorer()) {
            setIEDriver();
            webDriver = new InternetExplorerDriver();
        } else if (isEDGE()) {
            setEdgeDriver();
            webDriver = new EdgeDriver();
        }

        return webDriver;
    }

    private WebDriver getPhantomJSDriver() {
        return new PhantomJSDriver(capabilities);
    }

    private RemoteWebDriver getRemoteWebDriver() {
        try {
            remoteWebDriver = new RemoteWebDriver(new URL(remoteUrlPath), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return remoteWebDriver;
    }
}
