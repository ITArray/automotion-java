package environment;

public class EnvironmentFactory {

    private static String BROWSER;

    public static boolean isMobile() {
        String IS_MOBILE = System.getenv(EnvironmentConstants.IS_MOBILE) != null ? System.getenv(EnvironmentConstants.IS_MOBILE) : System.getProperty(EnvironmentConstants.IS_MOBILE);
        return IS_MOBILE != null && IS_MOBILE.toUpperCase().equals("TRUE");
    }

    public static boolean isLocal() {
        String IS_LOCAL = System.getenv(EnvironmentConstants.IS_LOCAL) != null ? System.getenv(EnvironmentConstants.IS_LOCAL) : System.getProperty(EnvironmentConstants.IS_LOCAL);
        return IS_LOCAL != null && IS_LOCAL.toUpperCase().equals("TRUE");
    }

    public static boolean isRemote() {
        String IS_REMOTE = System.getenv(EnvironmentConstants.IS_REMOTE) != null ? System.getenv(EnvironmentConstants.IS_REMOTE) : System.getProperty(EnvironmentConstants.IS_REMOTE);
        return IS_REMOTE != null && IS_REMOTE.toUpperCase().equals("TRUE");
    }

    public static boolean isHeadless() {
        String IS_HEADLESS = System.getenv(EnvironmentConstants.IS_HEADLESS) != null ? System.getenv(EnvironmentConstants.IS_HEADLESS) : System.getProperty(EnvironmentConstants.IS_HEADLESS);
        return IS_HEADLESS != null && IS_HEADLESS.toUpperCase().equals("TRUE");
    }

    public static boolean isSauce() {
        String IS_SAUCE = System.getenv(EnvironmentConstants.IS_SAUCE) != null ? System.getenv(EnvironmentConstants.IS_SAUCE) : System.getProperty(EnvironmentConstants.IS_SAUCE);
        return IS_SAUCE != null && IS_SAUCE.toUpperCase().equals("TRUE");
    }

    public static boolean isFirefox() {
        BROWSER = System.getenv(EnvironmentConstants.BROWSER) != null ? System.getenv(EnvironmentConstants.BROWSER) : System.getProperty(EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("FIREFOX");
    }

    public static boolean isChrome() {
        BROWSER = System.getenv(EnvironmentConstants.BROWSER) != null ? System.getenv(EnvironmentConstants.BROWSER) : System.getProperty(EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("CHROME");
    }

    public static boolean isSafari() {
        BROWSER = System.getenv(EnvironmentConstants.BROWSER) != null ? System.getenv(EnvironmentConstants.BROWSER) : System.getProperty(EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("SAFARI");
    }

    public static boolean isInternetExplorer() {
        BROWSER = System.getenv(EnvironmentConstants.BROWSER) != null ? System.getenv(EnvironmentConstants.BROWSER) : System.getProperty(EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("IE");
    }

    public static boolean isEDGE() {
        BROWSER = System.getenv(EnvironmentConstants.BROWSER) != null ? System.getenv(EnvironmentConstants.BROWSER) : System.getProperty(EnvironmentConstants.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("EDGE");
    }

    public static boolean isAndroid() {
        String PLATFORM = System.getenv(EnvironmentConstants.PLATFORM) != null ? System.getenv(EnvironmentConstants.PLATFORM) : System.getProperty(EnvironmentConstants.PLATFORM);
        return PLATFORM != null && PLATFORM.toUpperCase().equals("ANDROID");
    }

    public static boolean isIOS() {
        String PLATFORM = System.getenv(EnvironmentConstants.PLATFORM) != null ? System.getenv(EnvironmentConstants.PLATFORM) : System.getProperty(EnvironmentConstants.PLATFORM);
        return PLATFORM != null && PLATFORM.toUpperCase().equals("IOS");
    }

    public static boolean isWindows() {
        String PLATFORM = System.getenv(EnvironmentConstants.PLATFORM) != null ? System.getenv(EnvironmentConstants.PLATFORM) : System.getProperty(EnvironmentConstants.PLATFORM);
        return PLATFORM != null && PLATFORM.toUpperCase().equals("WINDOWS");
    }

    public static String getRemoteUrlPath() {
        return System.getenv(EnvironmentConstants.EXECUTOR) != null ? System.getenv(EnvironmentConstants.EXECUTOR) : System.getProperty(EnvironmentConstants.EXECUTOR);
    }

    public static String getPlatformVersion() {
        return System.getenv(EnvironmentConstants.PLATFORM_VERSION) != null ? System.getenv(EnvironmentConstants.PLATFORM_VERSION) : System.getProperty(EnvironmentConstants.PLATFORM_VERSION);
    }

    public static String getPhantomJsPath() {
        return System.getenv(EnvironmentConstants.PHANTOM_JS_PATH) != null ? System.getenv(EnvironmentConstants.PHANTOM_JS_PATH) : System.getProperty(EnvironmentConstants.PHANTOM_JS_PATH);
    }

    public static String getUserAgent() {
        return System.getenv(EnvironmentConstants.USER_AGENT) != null ? System.getenv(EnvironmentConstants.USER_AGENT) : System.getProperty(EnvironmentConstants.USER_AGENT);
    }

    public static String getDevice() {
        return System.getenv(EnvironmentConstants.DEVICE) != null ? System.getenv(EnvironmentConstants.DEVICE) : System.getProperty(EnvironmentConstants.DEVICE);
    }

    public static String getMobileDeviveEmulation() {
        return System.getenv(EnvironmentConstants.MOBILE_DEVICE_EMULATION) != null ? System.getenv(EnvironmentConstants.MOBILE_DEVICE_EMULATION) : System.getProperty(EnvironmentConstants.MOBILE_DEVICE_EMULATION);
    }

    public static String getName() {
        return System.getenv(EnvironmentConstants.NAME) != null ? System.getenv(EnvironmentConstants.NAME) : System.getProperty(EnvironmentConstants.NAME);
    }

    public static String getBrowserName() {
        BROWSER = System.getenv(EnvironmentConstants.BROWSER) != null ? System.getenv(EnvironmentConstants.BROWSER) : System.getProperty(EnvironmentConstants.BROWSER);
        return BROWSER;
    }

    public static String getAppPackage() {
        return System.getenv(EnvironmentConstants.APP_PACKAGE) != null ? System.getenv(EnvironmentConstants.APP_PACKAGE) : System.getProperty(EnvironmentConstants.APP_PACKAGE);
    }

    public static String getAppActivity() {
        return System.getenv(EnvironmentConstants.APP_ACTIVITY) != null ? System.getenv(EnvironmentConstants.APP_ACTIVITY) : System.getProperty(EnvironmentConstants.APP_ACTIVITY);
    }

    public static String getApp() {
        return System.getenv(EnvironmentConstants.APP) != null ? System.getenv(EnvironmentConstants.APP) : System.getProperty(EnvironmentConstants.APP);
    }

    public static String getAutomationName() {
        return System.getenv(EnvironmentConstants.AUTOMATION_NAME) != null ? System.getenv(EnvironmentConstants.AUTOMATION_NAME) : System.getProperty(EnvironmentConstants.AUTOMATION_NAME);
    }

    public static String getAppiumVersion() {
        return System.getenv(EnvironmentConstants.APPIUM_VERSION) != null ? System.getenv(EnvironmentConstants.APPIUM_VERSION) : System.getProperty(EnvironmentConstants.APPIUM_VERSION);
    }

    public static String getUDIDDevice() {
        return System.getenv(EnvironmentConstants.UDID) != null ? System.getenv(EnvironmentConstants.UDID) : System.getProperty(EnvironmentConstants.UDID);
    }

    public static String getMobileBrowser() {
        return System.getenv(EnvironmentConstants.MOBILE_BROWSER) != null ? System.getenv(EnvironmentConstants.MOBILE_BROWSER) : System.getProperty(EnvironmentConstants.MOBILE_BROWSER);
    }

    public static String getNewCommandTimeout() {
        return System.getenv(EnvironmentConstants.NEW_COMMAND_TIMEOUT) != null ? System.getenv(EnvironmentConstants.NEW_COMMAND_TIMEOUT) : System.getProperty(EnvironmentConstants.NEW_COMMAND_TIMEOUT);
    }

    public static String getSlDesktopPlatform() {
        return System.getenv(EnvironmentConstants.SL_DESKTOP_PLATFORM) != null ? System.getenv(EnvironmentConstants.SL_DESKTOP_PLATFORM) : System.getProperty(EnvironmentConstants.SL_DESKTOP_PLATFORM);
    }

    public static String getSlBrowserVersion() {
        return System.getenv(EnvironmentConstants.SL_BROWSER_VERSION) != null ? System.getenv(EnvironmentConstants.SL_BROWSER_VERSION) : System.getProperty(EnvironmentConstants.SL_BROWSER_VERSION);
    }

    public static String getSlDesktopResolution() {
        return System.getenv(EnvironmentConstants.SL_DESKTOP_RESOLUTION) != null ? System.getenv(EnvironmentConstants.SL_DESKTOP_RESOLUTION) : System.getProperty(EnvironmentConstants.SL_DESKTOP_RESOLUTION);
    }
}
