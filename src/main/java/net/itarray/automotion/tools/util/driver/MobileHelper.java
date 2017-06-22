package net.itarray.automotion.tools.util.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class MobileHelper {
    private final static Logger LOG = LoggerFactory.getLogger(MobileHelper.class);

    private static final int BACKSPACE = 67;

    public MobileHelper(){
        getAdbPath();
    }
    
    private static String ADB = "";

    public static void turnOnWifi() {
        executeCommand(ADB + " shell am start -n io.appium.settings/.Settings -e wifi on");
        LOG.info("INFO", "Turn on WiFi");
    }

    public static void turnOffWifi() {
        executeCommand(ADB + " shell am start -n io.appium.settings/.Settings -e wifi off");
        LOG.info("INFO", "Turn off WiFi");
    }

    public static void turnOnMobileData() {
        executeCommand(ADB + " shell am start -n io.appium.settings/.Settings -e data on");
        LOG.info("INFO", "Turn on Mobile data");
    }

    public static void turnOffMobileData() {
        executeCommand(ADB + " shell am start -n io.appium.settings/.Settings -e data off");
        LOG.info("INFO", "Turn off Mobile data");
    }

    public static void turnOnAirplaneMode() {
        executeCommand(ADB + " shell settings put global airplane_mode_on 1");
        executeCommand(ADB + " shell am broadcast -a android.intent.action.AIRPLANE_MODE --ez state true");
        LOG.info("INFO", "Turn on Airplane mode");
    }

    public static void turnOffAirplaneMode() {
        executeCommand(ADB + " shell settings put global airplane_mode_on 0");
        executeCommand(ADB + " shell am broadcast -a android.intent.action.AIRPLANE_MODE --ez state false");
        LOG.info("INFO", "Turn off Airplane mode");
    }

    public static void takeScreenshotToFolder(String pathToSave){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.ENGLISH);
        ProcessBuilder pb = new ProcessBuilder(ADB, "shell", "\"screencap -p " + pathToSave + "/IMG_" + dateFormat.format(new Date()) + ".jpg\"");
        Process pc;
        try {
            pc = pb.start();
            pc.waitFor();
            DriverHelper.wait(3);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getAdbPath(){
        detectedOS = getOperatingSystemType();
        switch (detectedOS){
            case MacOS:
                ADB = System.getenv("ANDROID_HOME") + "/platform-tools/adb";
                break;
            case Windows:
                ADB = System.getenv("ANDROID_HOME") + "\\platform-tools\\adb.exe";
                break;
            default:
                break;
        }
    }

    private static OSType detectedOS;

    private static OSType getOperatingSystemType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.contains("mac")) || (OS.contains("darwin"))) {
                detectedOS = OSType.MacOS;
            } else if (OS.contains("win")) {
                detectedOS = OSType.Windows;
            } else if (OS.contains("nux")) {
                detectedOS = OSType.Linux;
            } else {
                detectedOS = OSType.Other;
            }
        }
        return detectedOS;
    }

    private enum OSType {
        Windows, MacOS, Linux, Other
    };

    private static void executeCommand(String command) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void openAndroidNotifications(AppiumDriver driver){
        driver.execute("openNotifications", null);
        LOG.info("INFO", "Open notifications bar");
    }

    public static MobileElement clearField(AndroidDriver driver, MobileElement element){
        DriverHelper.clickByLocation(driver, element, DriverHelper.ClickPoint.TOP_RIGHT);
        //element.clear();
        for (int i = 0; i < 30; i++){
            driver.pressKeyCode(BACKSPACE);
        }

        return element;
    }

    public static String getWebContextName(AppiumDriver driver) {
        return getContext(driver, "WEBVIEW_0");
    }

    public static String getNativeContextName(AppiumDriver driver) {
        return getContext(driver, "NATIVE");
    }

    private static String getContext(AppiumDriver driver, String context) {
        String contextName = "";
        Set contexts = driver.getContextHandles();

        while (contexts.size() < 2) {
            if (contexts.size() > 1) {
                break;
            } else {
                try {
                    DriverHelper.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contexts = driver.getContextHandles();
            }
        }

        for (Object c: contexts){
            if (((String)c).contains(context)){
                contextName = (String) c;
                break;
            }
        }

        return contextName;
    }

}
