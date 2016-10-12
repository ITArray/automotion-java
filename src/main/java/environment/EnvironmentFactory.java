package environment;

public class EnvironmentFactory {

    private static String BROWSER;

    public static boolean isMobile() {
        String IS_MOBILE = System.getenv("IS_MOBILE") != null ? System.getenv("IS_MOBILE") : System.getProperty("IS_MOBILE");
        return IS_MOBILE != null && IS_MOBILE.toUpperCase().equals("TRUE");
    }

    public static boolean isLocal() {
        String IS_LOCAL = System.getenv("IS_LOCAL") != null ? System.getenv("IS_LOCAL") : System.getProperty("IS_LOCAL");
        return IS_LOCAL != null && IS_LOCAL.toUpperCase().equals("TRUE");
    }

    public static boolean isRemote() {
        String IS_REMOTE = System.getenv("IS_REMOTE") != null ? System.getenv("IS_REMOTE") : System.getProperty("IS_REMOTE");
        return IS_REMOTE != null && IS_REMOTE.toUpperCase().equals("TRUE");
    }

    public static boolean isHeadless() {

        String IS_HEADLESS = System.getenv("IS_HEADLESS") != null ? System.getenv("IS_HEADLESS") : System.getProperty("IS_HEADLESS");
        return IS_HEADLESS != null && IS_HEADLESS.toUpperCase().equals("TRUE");
    }

    public static boolean isSauce() {
        String IS_SAUCE = System.getenv("IS_SAUCE") != null ? System.getenv("IS_SAUCE") : System.getProperty("IS_SAUCE");
        return IS_SAUCE != null && IS_SAUCE.toUpperCase().equals("TRUE");
    }

    public static boolean isFirefox() {
        BROWSER = System.getenv("BROWSER") != null ? System.getenv("BROWSER") : System.getProperty("BROWSER");
        return BROWSER != null && BROWSER.toUpperCase().equals("FIREFOX");
    }

    public static boolean isChrome() {
        BROWSER = System.getenv("BROWSER") != null ? System.getenv("BROWSER") : System.getProperty("BROWSER");
        return BROWSER != null && BROWSER.toUpperCase().equals("CHROME");
    }

    public static boolean isSafari() {
        BROWSER = System.getenv("BROWSER") != null ? System.getenv("BROWSER") : System.getProperty("BROWSER");
        return BROWSER != null && BROWSER.toUpperCase().equals("SAFARI");
    }

    public static boolean isInternetExplorer() {
        BROWSER = System.getenv("BROWSER") != null ? System.getenv("BROWSER") : System.getProperty("BROWSER");
        return BROWSER != null && BROWSER.toUpperCase().equals("IE");
    }

    public static boolean isAndroid() {
        String PLATFORM = System.getenv("PLATFORM") != null ? System.getenv("PLATFORM") : System.getProperty("PLATFORM");
        return PLATFORM != null && PLATFORM.toUpperCase().equals("ANDROID");
    }

    public static boolean isIOS() {
        String PLATFORM = System.getenv("PLATFORM") != null ? System.getenv("PLATFORM") : System.getProperty("PLATFORM");
        return PLATFORM != null && PLATFORM.toUpperCase().equals("IOS");
    }

    public static String getRemoteUrlPath() {
        return System.getenv("EXECUTOR") != null ? System.getenv("EXECUTOR") : System.getProperty("EXECUTOR");
    }

    public static String getPlatformVersion() {
        return System.getenv("PLATFORM_VERSION") != null ? System.getenv("PLATFORM_VERSION") : System.getProperty("PLATFORM_VERSION");
    }

    public static String getPhantomJsPath() {
        return System.getenv("PHANTOM_JS_PATH") != null ? System.getenv("PHANTOM_JS_PATH") : System.getProperty("PHANTOM_JS_PATH");
    }

    public static String getDevice() {
        return System.getenv("DEVICE") != null ? System.getenv("DEVICE") : System.getProperty("DEVICE");
    }

    public static String getMobileDeviveEmulation() {
        return System.getenv("MOBILE_DEVICE_EMULATION") != null ? System.getenv("MOBILE_DEVICE_EMULATION") : System.getProperty("MOBILE_DEVICE_EMULATION");
    }

    public static String getName() {
        return System.getenv("NAME") != null ? System.getenv("NAME") : System.getProperty("NAME");
    }

    public static String getBrowserName() {
        BROWSER = System.getenv("BROWSER") != null ? System.getenv("BROWSER") : System.getProperty("BROWSER");
        return BROWSER;
    }

    public static String getAppPackage() {
        return System.getenv("APP_PACKAGE") != null ? System.getenv("APP_PACKAGE") : System.getProperty("APP_PACKAGE");
    }

    public static String getAppActivity() {
        return System.getenv("APP_ACTIVITY") != null ? System.getenv("APP_ACTIVITY") : System.getProperty("APP_ACTIVITY");
    }

    public static String getApp() {
        return System.getenv("APP") != null ? System.getenv("APP") : System.getProperty("APP");
    }

    public static String getAutomationName() {
        return System.getenv("AUTOMATION_NAME") != null ? System.getenv("AUTOMATION_NAME") : System.getProperty("AUTOMATION_NAME");
    }

    public static String getAppiumVersion() {
        return System.getenv("APPIUM_VERSION") != null ? System.getenv("APPIUM_VERSION") : System.getProperty("APPIUM_VERSION");
    }

    public static String getUDIDDevice() {
        return System.getenv("UDID") != null ? System.getenv("UDID") : System.getProperty("UDID");
    }

    public static String getMobileBrowser() {
        return System.getenv("MOBILE_BROWSER") != null ? System.getenv("MOBILE_BROWSER") : System.getProperty("MOBILE_BROWSER");
    }

    public static String getNewCommandTimeout() {
        return System.getenv("NEW_COMMAND_TIMEOUT") != null ? System.getenv("NEW_COMMAND_TIMEOUT") : System.getProperty("NEW_COMMAND_TIMEOUT");
    }

    public static String getSlDesktopPlatform() {
        return System.getenv("SL_DESKTOP_PLATFORM") != null ? System.getenv("SL_DESKTOP_PLATFORM") : System.getProperty("SL_DESKTOP_PLATFORM");
    }

    public static String getSlBrowserVersion() {
        return System.getenv("SL_BROWSER_VERSION") != null ? System.getenv("SL_BROWSER_VERSION") : System.getProperty("SL_BROWSER_VERSION");
    }

    public static String getSlDesktopResolution() {
        return System.getenv("SL_DESKTOP_RESOLUTION") != null ? System.getenv("SL_DESKTOP_RESOLUTION") : System.getProperty("SL_DESKTOP_RESOLUTION");
    }
}
