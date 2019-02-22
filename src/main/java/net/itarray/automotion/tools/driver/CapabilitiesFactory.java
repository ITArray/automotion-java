package net.itarray.automotion.tools.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static net.itarray.automotion.tools.environment.EnvironmentFactory.*;

public class CapabilitiesFactory {

    private static DesiredCapabilities capabilities = new DesiredCapabilities();

    public static DesiredCapabilities getCapabilities() {
        if (isAndroid()) {
            capabilities = getAndroidCapabilities();
        } else if (isIOS()) {
            capabilities = getIOSCapabilities();
        } else if (isWindows()) {
            capabilities = getWindowsCapabilities();
        } else if (isRemote()) {
            capabilities = getRemoteDriverCapabilities();
        } else if (isHeadless()) {
            capabilities = getPhantomJSCapabilities();
        }

        return capabilities;
    }

    public static DesiredCapabilities updateCapabilities(DesiredCapabilities desiredCapabilities, Map<String, Object> mapCapabilities) {
        if (mapCapabilities.size() > 0) {
            for (Map.Entry<String, Object> capability : mapCapabilities.entrySet()) {
                setCapability(capability.getKey(), capability.getValue());
            }
        }

        return desiredCapabilities;
    }

    private static DesiredCapabilities getCommonMobileCapabilities() {
        setCapability("automationName", getAutomationName());
        setCapability("platformVersion", getPlatformVersion());
        setCapability("deviceName", getDevice());
        setCapability("deviceOrientation", "portrait");
        setCapability("app", getApp());
        setCapability("browserName", getMobileBrowser());
        setCapability("appiumVersion", getAppiumVersion());
        setCapability("name", getName());
        setCapability("newCommandTimeout", getNewCommandTimeout());

        return capabilities;
    }

    private static DesiredCapabilities getAndroidCapabilities() {
        capabilities = getCommonMobileCapabilities();
        setCapability("platformName", "Android");
        setCapability("appActivity", getAppActivity());
        setCapability("appPackage", getAppPackage());

        return capabilities;
    }

    private static DesiredCapabilities getIOSCapabilities() {
        capabilities = getCommonMobileCapabilities();
        setCapability("platformName", "iOS");
        setCapability("udid", getUDIDDevice());
        setCapability("waitForAppScript", "true");

        return capabilities;
    }

    private static DesiredCapabilities getWindowsCapabilities() {
        capabilities = getCommonMobileCapabilities();
        setCapability("platformName", "Windows");

        return capabilities;
    }

    private static DesiredCapabilities getPhantomJSCapabilities() {
        capabilities.setJavascriptEnabled(true);
        setCapability("takesScreenshot", true);
        setCapability("browserName", "PhantomJS");
        setCapability("browser", "phantomjs");
        setCapability("phantomjs.page.settings.userAgent", getUserAgent());
        setCapability("acceptSslCerts", true);
        setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=no", "--ignore-ssl-errors=yes","--ignore-ssl-errors=true","--ssl-protocol=tlsv1"});
        setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                getPhantomJsPath()
        );

        return capabilities;
    }

    private static DesiredCapabilities getRemoteDriverCapabilities() {
        if (isFirefox()) {
            capabilities = DesiredCapabilities.firefox();
        } else if (isChrome()) {
            capabilities = DesiredCapabilities.chrome();
        } else if (isSafari()) {
            capabilities = DesiredCapabilities.safari();
        } else if (isInternetExplorer()) {
            capabilities = DesiredCapabilities.internetExplorer();
        } else if (isEDGE()) {
            capabilities = DesiredCapabilities.edge();
        }

        if (getMobileDeviveEmulation() != null) {
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", getMobileDeviveEmulation());

            Map<String, Object> chromeOptions = new HashMap<>();
            chromeOptions.put("mobileEmulation", mobileEmulation);
            chromeOptions.put("args", Arrays.asList("enable-extensions",
                    "test-type", "no-default-browser-check", "ignore-certificate-errors"));

            setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }

        if (getSlDesktopPlatform() != null) {
            setCapability("platform", getSlDesktopPlatform());
            setCapability("version", getSlBrowserVersion());
            setCapability("screenResolution", getSlDesktopResolution());
        }

        return capabilities;
    }
    
    private static void setCapability(String key, Object value) {
        if (value != null) {
            capabilities.setCapability(key, value);
        }
    }
}
