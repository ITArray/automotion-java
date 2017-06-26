package net.itarray.automotion.tools.util.general;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Locale;

import static net.itarray.automotion.validation.Constants.TARGET_AUTOMOTION_JSON;

public class SystemHelper {
    /**
     * Verify is display is retina
     *
     * @return
     */
    public static boolean isRetinaDisplay() {
        boolean isRetina = false;
        try {
            GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            try {
                Field field = graphicsDevice.getClass().getDeclaredField("scale");
                if (field != null) {
                    field.setAccessible(true);
                    Object scale = field.get(graphicsDevice);
                    if (scale instanceof Integer && (Integer) scale == 2) {
                        isRetina = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRetina;
    }

    /**
     * Convert hex color to rgb or rgba
     *
     * @param hexARGB
     * @return
     * @throws IllegalArgumentException
     */
    public static String hexStringToARGB(String hexARGB) throws IllegalArgumentException {

        if (!hexARGB.startsWith("#") || !(hexARGB.length() == 7 || hexARGB.length() == 9)) {

            throw new IllegalArgumentException("Hex color string is incorrect!");
        }

        int[] intARGB;

        if (hexARGB.length() == 9) {
            intARGB = new int[4];
            intARGB[0] = Integer.valueOf(hexARGB.substring(1, 3), 16); // alpha
            intARGB[1] = Integer.valueOf(hexARGB.substring(3, 5), 16); // red
            intARGB[2] = Integer.valueOf(hexARGB.substring(5, 7), 16); // green
            intARGB[3] = Integer.valueOf(hexARGB.substring(7), 16); // blue

            return String.format(Locale.US, "rgba(%d,%d,%d,%.1f)", intARGB[1], intARGB[2], intARGB[3], (float) Math.round((((float) intARGB[0] / 255) * 100)) / 100.f);
        } else {
            intARGB = new int[3];
            intARGB[0] = Integer.valueOf(hexARGB.substring(1, 3), 16); // red
            intARGB[1] = Integer.valueOf(hexARGB.substring(3, 5), 16); // green
            intARGB[2] = Integer.valueOf(hexARGB.substring(5), 16); // blue

            return String.format("rgb(%d,%d,%d)", intARGB[0], intARGB[1], intARGB[2]);
        }
    }

    public static boolean isAutomotionFolderExists() {
        File file = new File(TARGET_AUTOMOTION_JSON);

        return file.exists() && file.isDirectory() && file.list() != null && file.list().length > 0;
    }
}
