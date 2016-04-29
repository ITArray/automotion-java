package util.driver;

import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MobileHelper {
    
    private static final String ADB = System.getenv("ANDROID_HOME") + "\\platform-tools\\adb.exe";

    public static void turnOnWifi() {
        executeCommand(ADB + " shell am start -n io.appium.settings/.Settings -e wifi on");
    }

    public static void turnOffWifi() {
        executeCommand(ADB + " shell am start -n io.appium.settings/.Settings -e wifi off");
    }

    public static void turnOnMobileData() {
        executeCommand(ADB + " shell am start -n io.appium.settings/.Settings -e data on");
    }

    public static void turnOffMobileData() {
        executeCommand(ADB + " shell am start -n io.appium.settings/.Settings -e data off");
    }

    public static void turnOnAirplaneMode() {
        executeCommand(ADB + " shell settings put global airplane_mode_on 1");
        executeCommand(ADB + " shell am broadcast -a android.intent.action.AIRPLANE_MODE --ez state true");
    }

    public static void turnOffAirplaneMode() {
        executeCommand(ADB + " shell settings put global airplane_mode_on 0");
        executeCommand(ADB + " shell am broadcast -a android.intent.action.AIRPLANE_MODE --ez state false");
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
    }

}
