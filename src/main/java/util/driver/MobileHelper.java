package util.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * @deprecated As of release 2.0, replaced by{@link net.itarray.automotion.tools.driver.MobileHelper}
 */
@Deprecated
public class MobileHelper {

    public MobileHelper(){
        new net.itarray.automotion.tools.driver.MobileHelper();
    }
    
    public static void turnOnWifi() {
        net.itarray.automotion.tools.driver.MobileHelper.turnOnWifi();
    }

    public static void turnOffWifi() {
        net.itarray.automotion.tools.driver.MobileHelper.turnOffWifi();
    }

    public static void turnOnMobileData() {
        net.itarray.automotion.tools.driver.MobileHelper.turnOnMobileData();
    }

    public static void turnOffMobileData() {
        net.itarray.automotion.tools.driver.MobileHelper.turnOffMobileData();
    }

    public static void turnOnAirplaneMode() {
        net.itarray.automotion.tools.driver.MobileHelper.turnOnAirplaneMode();
    }

    public static void turnOffAirplaneMode() {
        net.itarray.automotion.tools.driver.MobileHelper.turnOffAirplaneMode();
    }

    public static void takeScreenshotToFolder(String pathToSave){
        net.itarray.automotion.tools.driver.MobileHelper.takeScreenshotToFolder(pathToSave);
    }

    public static void openAndroidNotifications(AppiumDriver driver){
        net.itarray.automotion.tools.driver.MobileHelper.openAndroidNotifications(driver);
    }

    public static MobileElement clearField(AndroidDriver driver, MobileElement element){
        return net.itarray.automotion.tools.driver.MobileHelper.clearField(driver, element);
    }

    public static String getWebContextName(AppiumDriver driver) {
        return net.itarray.automotion.tools.driver.MobileHelper.getWebContextName(driver);
    }

    public static String getNativeContextName(AppiumDriver driver) {
        return net.itarray.automotion.tools.driver.MobileHelper.getNativeContextName(driver);
    }
}
