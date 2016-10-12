package http.helpers;

import java.util.Map;

public class EnvironmentHelper {

    public static void setEnv(Map<String, String> newenv) {
        clearAllProperties();

        for (Map.Entry entry : newenv.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    private static void clearAllProperties() {
        System.clearProperty("IS_LOCAL");
        System.clearProperty("IS_MOBILE");
        System.clearProperty("IS_REMOTE");
        System.clearProperty("IS_HEADLESS");
        System.clearProperty("PLATFORM");
        System.clearProperty("PHANTOM_JS_PATH");
        System.clearProperty("PLATFORM_VERSION");
        System.clearProperty("DEVICE");
        System.clearProperty("MOBILE_DEVICE_EMULATION");
        System.clearProperty("NAME");
        System.clearProperty("AUTOMATION_NAME");
        System.clearProperty("APP");
        System.clearProperty("APP_PACKAGE");
        System.clearProperty("APP_ACTIVITY");
        System.clearProperty("EXECUTOR");
        System.clearProperty("BROWSER");
        System.clearProperty("MOBILE_BROWSER");
        System.clearProperty("UDID");
        System.clearProperty("APPIUM_VERSION");
        System.clearProperty("IS_SAUCE");
        System.clearProperty("NEW_COMMAND_TIMEOUT");
        System.clearProperty("SL_DESKTOP_PLATFORM");
        System.clearProperty("SL_BROWSER_VERSION");
        System.clearProperty("SL_DESKTOP_RESOLUTION");
    }
}
