package environment;

public class EnvironmentFactory {

    private static final String IS_MOBILE = System.getenv("IS_MOBILE");
    private static final String IS_LOCAL = System.getenv("IS_LOCAL");
    private static final String IS_REMOTE = System.getenv("IS_REMOTE");
    private static final String PLATFORM = System.getenv("PLATFORM");
    private static final String PLATFORM_VERSION = System.getenv("PLATFORM_VERSION");
    private static final String DEVICE = System.getenv("DEVICE");
    private static final String MOBILE_DEVIVE_EMULATION = System.getenv("MOBILE_DEVIVE_EMULATION");
    private static final String NAME = System.getenv("NAME");
    private static final String AUTOMATION_NAME = System.getenv("AUTOMATION_NAME");
    private static final String APP = System.getenv("APP");
    private static final String APP_PACKAGE = System.getenv("APP_PACKAGE");
    private static final String APP_ACTIVITY = System.getenv("APP_ACTIVITY");
    private static final String EXECUTOR = System.getenv("EXECUTOR");
    private static final String BROWSER = System.getenv("BROWSER");
    private static final String MOBILE_BROWSER = System.getenv("MOBILE_BROWSER");
    private static final String UDID = System.getenv("UDID");
    private static final String APPIUM_VERSION = System.getenv("APPIUM_VERSION");
    private static final String IS_SAUCE = System.getenv("IS_SAUCE");
    private static final String NEW_COMMAND_TIMEOUT = System.getenv("NEW_COMMAND_TIMEOUT");
    private static final String SL_DESKTOP_PLATFORM = System.getenv("SL_DESKTOP_PLATFORM");
    private static final String SL_BROWSER_VERSION = System.getenv("SL_BROWSER_VERSION");
    private static final String SL_DESKTOP_RESOLUTION = System.getenv("SL_DESKTOP_RESOLUTION");

    public static boolean isMobile() {
        return IS_MOBILE != null && IS_MOBILE.toUpperCase().equals("TRUE");
    }

    public static boolean isLocal() {
        return IS_LOCAL != null && IS_LOCAL.toUpperCase().equals("TRUE");
    }

    public static boolean isRemote() {
        return IS_REMOTE != null && IS_REMOTE.toUpperCase().equals("TRUE");
    }

    public static boolean isSauce() {
        return IS_SAUCE != null && IS_SAUCE.toUpperCase().equals("TRUE");
    }

    public static boolean isFirefox() {
        return BROWSER != null && BROWSER.toUpperCase().equals("FIREFOX");
    }

    public static boolean isChrome() {
        return BROWSER != null && BROWSER.toUpperCase().equals("CHROME");
    }

    public static boolean isSafari() {
        return BROWSER != null && BROWSER.toUpperCase().equals("SAFARI");
    }

    public static boolean isInternetExplorer() {
        return BROWSER != null && BROWSER.toUpperCase().equals("IE");
    }

    public static boolean isAndroid() {
        return PLATFORM != null && PLATFORM.toUpperCase().equals("ANDROID");
    }

    public static boolean isIOS() {
        return PLATFORM != null && PLATFORM.toUpperCase().equals("IOS");
    }

    public static String getRemoteUrlPath() {
        return EXECUTOR;
    }

    public static String getPlatformVersion() {
        return PLATFORM_VERSION;
    }

    public static String getDevice() {
        return DEVICE;
    }

    public static String getMobileDeviveEmulation() {
        return MOBILE_DEVIVE_EMULATION;
    }

    public static String getName() {
        return NAME;
    }

    public static String getAppPackage() {
        return APP_PACKAGE;
    }

    public static String getAppActivity() {
        return APP_ACTIVITY;
    }

    public static String getApp() {
        return APP;
    }

    public static String getAutomationName() {
        return AUTOMATION_NAME;
    }

    public static String getAppiumVersion() {
        return APPIUM_VERSION;
    }

    public static String getUDIDDevice() {
        return UDID;
    }

    public static String getMobileBrowser() {
        return MOBILE_BROWSER;
    }

    public static String getNewCommandTimeout() {
        return NEW_COMMAND_TIMEOUT;
    }

    public static String getSlDesktopPlatform() {
        return SL_DESKTOP_PLATFORM;
    }

    public static String getSlBrowserVersion() {
        return SL_BROWSER_VERSION;
    }

    public static String getSlDesktopResolution() {
        return SL_DESKTOP_RESOLUTION;
    }
}
