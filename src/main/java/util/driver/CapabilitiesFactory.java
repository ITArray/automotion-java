package util.driver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static environment.EnvironmentFactory.*;

public class CapabilitiesFactory {

    private static DesiredCapabilities capabilities = new DesiredCapabilities();

    public static DesiredCapabilities getCapabilities() {
        if (isAndroid()) {
            capabilities = getAndroidCapabilities();
        } else if (isIOS()) {
            capabilities = getIOSCapabilities();
        } else if (isRemote()) {
            capabilities = getRemoteDriverCapabilities();
        }

        return capabilities;
    }

    public static DesiredCapabilities updateCapabilities(DesiredCapabilities desiredCapabilities, Map<String, Object> mapCapabilities) {
        if (mapCapabilities.size() > 0) {
            for (Map.Entry<String, Object> capability : mapCapabilities.entrySet()) {
                desiredCapabilities.setCapability(capability.getKey(), capability.getValue());
            }
        }

        return desiredCapabilities;
    }

    private static DesiredCapabilities getCommonMobileCapabilities() {
        capabilities.setCapability("automationName", getAutomationName());
        capabilities.setCapability("platformVersion", getPlatformVersion());
        capabilities.setCapability("deviceName", getDevice());
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("app", getApp());
        capabilities.setCapability("browserName", getMobileBrowser());
        capabilities.setCapability("appiumVersion", getAppiumVersion());
        capabilities.setCapability("name", getName());
        capabilities.setCapability("newCommandTimeout", getNewCommandTimeout());

        return capabilities;
    }

    private static DesiredCapabilities getAndroidCapabilities() {
        capabilities = getCommonMobileCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appActivity", getAppActivity());
        capabilities.setCapability("appPackage", getAppPackage());

        return capabilities;
    }

    private static DesiredCapabilities getIOSCapabilities() {
        capabilities = getCommonMobileCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("udid", getUDIDDevice());
        capabilities.setCapability("waitForAppScript", true);


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
        }

        capabilities.setCapability("platform", getSlDesktopPlatform());
        capabilities.setCapability("version", getSlBrowserVersion());
        capabilities.setCapability("screenResolution", getSlDesktopResolution());

        return capabilities;
    }


}
