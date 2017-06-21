package net.itarray.automotion.tools.environment;

import environment.*;

import java.util.Arrays;

public class EnvironmentFactory {

    private static String BROWSER;

    public static boolean isMobile() {
        String IS_MOBILE = System.getenv(environment.EnvironmentConstants.IS_MOBILE) != null ? System.getenv(environment.EnvironmentConstants.IS_MOBILE) : System.getProperty(environment.EnvironmentConstants.IS_MOBILE);
        return IS_MOBILE != null && IS_MOBILE.toUpperCase().equals("TRUE");
    }

    public static boolean isLocal() {
        String IS_LOCAL = System.getenv(environment.EnvironmentConstants.IS_LOCAL) != null ? System.getenv(environment.EnvironmentConstants.IS_LOCAL) : System.getProperty(environment.EnvironmentConstants.IS_LOCAL);
        return IS_LOCAL != null && IS_LOCAL.toUpperCase().equals("TRUE");
    }

    public static boolean isRemote() {
        String IS_REMOTE = System.getenv(environment.EnvironmentConstants.IS_REMOTE) != null ? System.getenv(environment.EnvironmentConstants.IS_REMOTE) : System.getProperty(environment.EnvironmentConstants.IS_REMOTE);
        return IS_REMOTE != null && IS_REMOTE.toUpperCase().equals("TRUE");
    }

    public static boolean isHeadless() {
        String IS_HEADLESS = System.getenv(environment.EnvironmentConstants.IS_HEADLESS) != null ? System.getenv(environment.EnvironmentConstants.IS_HEADLESS) : System.getProperty(environment.EnvironmentConstants.IS_HEADLESS);
        return IS_HEADLESS != null && IS_HEADLESS.toUpperCase().equals("TRUE");
    }

    public static boolean isSauce() {
        String IS_SAUCE = System.getenv(environment.EnvironmentConstants.IS_SAUCE) != null ? System.getenv(environment.EnvironmentConstants.IS_SAUCE) : System.getProperty(environment.EnvironmentConstants.IS_SAUCE);
        return IS_SAUCE != null && IS_SAUCE.toUpperCase().equals("TRUE");
    }

    public static boolean isFirefox() {
        BROWSER = System.getenv(environment.EnvironmentConstants.BROWSER) != null ? System.getenv(environment.EnvironmentConstants.BROWSER) : System.getProperty(environment.EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("FIREFOX");
    }

    public static boolean isChrome() {
        BROWSER = System.getenv(environment.EnvironmentConstants.BROWSER) != null ? System.getenv(environment.EnvironmentConstants.BROWSER) : System.getProperty(environment.EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("CHROME");
    }

    public static boolean isSafari() {
        BROWSER = System.getenv(environment.EnvironmentConstants.BROWSER) != null ? System.getenv(environment.EnvironmentConstants.BROWSER) : System.getProperty(environment.EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("SAFARI");
    }

    public static boolean isInternetExplorer() {
        BROWSER = System.getenv(environment.EnvironmentConstants.BROWSER) != null ? System.getenv(environment.EnvironmentConstants.BROWSER) : System.getProperty(environment.EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("IE");
    }

    public static boolean isEDGE() {
        BROWSER = System.getenv(environment.EnvironmentConstants.BROWSER) != null ? System.getenv(environment.EnvironmentConstants.BROWSER) : System.getProperty(environment.EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("EDGE");
    }

    public static boolean isAndroid() {
        String PLATFORM = System.getenv(environment.EnvironmentConstants.PLATFORM) != null ? System.getenv(environment.EnvironmentConstants.PLATFORM) : System.getProperty(environment.EnvironmentConstants.PLATFORM);
        return PLATFORM != null && PLATFORM.toUpperCase().equals("ANDROID");
    }

    public static boolean isIOS() {
        String PLATFORM = System.getenv(environment.EnvironmentConstants.PLATFORM) != null ? System.getenv(environment.EnvironmentConstants.PLATFORM) : System.getProperty(environment.EnvironmentConstants.PLATFORM);
        return PLATFORM != null && PLATFORM.toUpperCase().equals("IOS");
    }

    public static boolean isWindows() {
        String PLATFORM = System.getenv(environment.EnvironmentConstants.PLATFORM) != null ? System.getenv(environment.EnvironmentConstants.PLATFORM) : System.getProperty(environment.EnvironmentConstants.PLATFORM);
        return PLATFORM != null && PLATFORM.toUpperCase().equals("WINDOWS");
    }

    public static String getRemoteUrlPath() {
        return System.getenv(environment.EnvironmentConstants.EXECUTOR) != null ? System.getenv(environment.EnvironmentConstants.EXECUTOR) : System.getProperty(environment.EnvironmentConstants.EXECUTOR);
    }

    public static String getPlatformVersion() {
        return System.getenv(environment.EnvironmentConstants.PLATFORM_VERSION) != null ? System.getenv(environment.EnvironmentConstants.PLATFORM_VERSION) : System.getProperty(environment.EnvironmentConstants.PLATFORM_VERSION);
    }

    public static String getPhantomJsPath() {
        return System.getenv(environment.EnvironmentConstants.PHANTOM_JS_PATH) != null ? System.getenv(environment.EnvironmentConstants.PHANTOM_JS_PATH) : System.getProperty(environment.EnvironmentConstants.PHANTOM_JS_PATH);
    }

    public static String getUserAgent() {
        return System.getenv(environment.EnvironmentConstants.USER_AGENT) != null ? System.getenv(environment.EnvironmentConstants.USER_AGENT) : System.getProperty(environment.EnvironmentConstants.USER_AGENT);
    }

    public static String getDevice() {
        return System.getenv(environment.EnvironmentConstants.DEVICE) != null ? System.getenv(environment.EnvironmentConstants.DEVICE) : System.getProperty(environment.EnvironmentConstants.DEVICE);
    }

    public static String getMobileDeviveEmulation() {
        return System.getenv(environment.EnvironmentConstants.MOBILE_DEVICE_EMULATION) != null ? System.getenv(environment.EnvironmentConstants.MOBILE_DEVICE_EMULATION) : System.getProperty(environment.EnvironmentConstants.MOBILE_DEVICE_EMULATION);
    }

    public static String getName() {
        return System.getenv(environment.EnvironmentConstants.NAME) != null ? System.getenv(environment.EnvironmentConstants.NAME) : System.getProperty(environment.EnvironmentConstants.NAME);
    }

    public static String getBrowserName() {
        BROWSER = System.getenv(environment.EnvironmentConstants.BROWSER) != null ? System.getenv(environment.EnvironmentConstants.BROWSER) : System.getProperty(environment.EnvironmentConstants.BROWSER);
        return BROWSER;
    }

    public static String getAppPackage() {
        return System.getenv(environment.EnvironmentConstants.APP_PACKAGE) != null ? System.getenv(environment.EnvironmentConstants.APP_PACKAGE) : System.getProperty(environment.EnvironmentConstants.APP_PACKAGE);
    }

    public static String getAppActivity() {
        return System.getenv(environment.EnvironmentConstants.APP_ACTIVITY) != null ? System.getenv(environment.EnvironmentConstants.APP_ACTIVITY) : System.getProperty(environment.EnvironmentConstants.APP_ACTIVITY);
    }

    public static String getApp() {
        return System.getenv(environment.EnvironmentConstants.APP) != null ? System.getenv(environment.EnvironmentConstants.APP) : System.getProperty(environment.EnvironmentConstants.APP);
    }

    public static String getAutomationName() {
        return System.getenv(environment.EnvironmentConstants.AUTOMATION_NAME) != null ? System.getenv(environment.EnvironmentConstants.AUTOMATION_NAME) : System.getProperty(environment.EnvironmentConstants.AUTOMATION_NAME);
    }

    public static String getAppiumVersion() {
        return System.getenv(environment.EnvironmentConstants.APPIUM_VERSION) != null ? System.getenv(environment.EnvironmentConstants.APPIUM_VERSION) : System.getProperty(environment.EnvironmentConstants.APPIUM_VERSION);
    }

    public static String getUDIDDevice() {
        return System.getenv(environment.EnvironmentConstants.UDID) != null ? System.getenv(environment.EnvironmentConstants.UDID) : System.getProperty(environment.EnvironmentConstants.UDID);
    }

    public static String getMobileBrowser() {
        return System.getenv(environment.EnvironmentConstants.MOBILE_BROWSER) != null ? System.getenv(environment.EnvironmentConstants.MOBILE_BROWSER) : System.getProperty(environment.EnvironmentConstants.MOBILE_BROWSER);
    }

    public static String getNewCommandTimeout() {
        return System.getenv(environment.EnvironmentConstants.NEW_COMMAND_TIMEOUT) != null ? System.getenv(environment.EnvironmentConstants.NEW_COMMAND_TIMEOUT) : System.getProperty(environment.EnvironmentConstants.NEW_COMMAND_TIMEOUT);
    }

    public static String getSlDesktopPlatform() {
        return System.getenv(environment.EnvironmentConstants.SL_DESKTOP_PLATFORM) != null ? System.getenv(environment.EnvironmentConstants.SL_DESKTOP_PLATFORM) : System.getProperty(environment.EnvironmentConstants.SL_DESKTOP_PLATFORM);
    }

    public static String getSlBrowserVersion() {
        return System.getenv(environment.EnvironmentConstants.SL_BROWSER_VERSION) != null ? System.getenv(environment.EnvironmentConstants.SL_BROWSER_VERSION) : System.getProperty(environment.EnvironmentConstants.SL_BROWSER_VERSION);
    }

    public static String getSlDesktopResolution() {
        return System.getenv(environment.EnvironmentConstants.SL_DESKTOP_RESOLUTION) != null ? System.getenv(environment.EnvironmentConstants.SL_DESKTOP_RESOLUTION) : System.getProperty(environment.EnvironmentConstants.SL_DESKTOP_RESOLUTION);
    }

    public static boolean isIOSDevice() {
        String[] iOS_RETINA_DEVICES = {
                "iPhone 4", "iPhone 4s",
                "iPhone 5", "iPhone 5s",
                "iPhone 6", "iPhone 6s",
                "iPad Mini 2",
                "iPad Mini 4",
                "iPad Air 2",
                "iPad Pro"
        };
        return Arrays.asList(iOS_RETINA_DEVICES).contains(getDevice());
    }
}
