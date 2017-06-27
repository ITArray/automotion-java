package util.general;

/**
 * @deprecated As of release 2.0, replaced by{@link net.itarray.automotion.tools.general.SystemHelper}
 */
@Deprecated
public class SystemHelper {
    /**
     * Verify is display is retina
     *
     * @return
     */
    public static boolean isRetinaDisplay() {
        return net.itarray.automotion.tools.general.SystemHelper.isRetinaDisplay();
    }

    /**
     * Convert hex color to rgb or rgba
     *
     * @param hexARGB
     * @return
     * @throws IllegalArgumentException
     */
    public static String hexStringToARGB(String hexARGB) throws IllegalArgumentException {
        return net.itarray.automotion.tools.general.SystemHelper.hexStringToARGB(hexARGB);
    }

    public static boolean isAutomotionFolderExists() {
        return net.itarray.automotion.tools.general.SystemHelper.isAutomotionFolderExists();
    }
}
