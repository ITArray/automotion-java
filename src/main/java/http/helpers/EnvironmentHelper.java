package http.helpers;

import environment.EnvironmentConstants;

import java.util.Map;

public class EnvironmentHelper {

    public static void setEnv(Map<String, String> newenv) {
        clearAllProperties();

        for (Map.Entry entry : newenv.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    private static void clearAllProperties() {
        System.clearProperty(EnvironmentConstants.IS_LOCAL);
        System.clearProperty(EnvironmentConstants.IS_MOBILE);
        System.clearProperty(EnvironmentConstants.IS_REMOTE);
        System.clearProperty(EnvironmentConstants.IS_HEADLESS);
        System.clearProperty(EnvironmentConstants.PLATFORM);
        System.clearProperty(EnvironmentConstants.PHANTOM_JS_PATH);
        System.clearProperty(EnvironmentConstants.PLATFORM_VERSION);
        System.clearProperty(EnvironmentConstants.DEVICE);
        System.clearProperty(EnvironmentConstants.MOBILE_DEVICE_EMULATION);
        System.clearProperty(EnvironmentConstants.NAME);
        System.clearProperty(EnvironmentConstants.AUTOMATION_NAME);
        System.clearProperty(EnvironmentConstants.APP);
        System.clearProperty(EnvironmentConstants.APP_PACKAGE);
        System.clearProperty(EnvironmentConstants.APP_ACTIVITY);
        System.clearProperty(EnvironmentConstants.EXECUTOR);
        System.clearProperty(EnvironmentConstants.BROWSER);
        System.clearProperty(EnvironmentConstants.MOBILE_BROWSER);
        System.clearProperty(EnvironmentConstants.UDID);
        System.clearProperty(EnvironmentConstants.APPIUM_VERSION);
        System.clearProperty(EnvironmentConstants.IS_SAUCE);
        System.clearProperty(EnvironmentConstants.NEW_COMMAND_TIMEOUT);
        System.clearProperty(EnvironmentConstants.SL_DESKTOP_PLATFORM);
        System.clearProperty(EnvironmentConstants.SL_BROWSER_VERSION);
        System.clearProperty(EnvironmentConstants.SL_DESKTOP_RESOLUTION);
    }
}
