package util.general;

import java.awt.*;
import java.lang.reflect.Field;

public class SystemHelper {
    public static boolean isRetinaDisplay(Graphics2D g) {
        boolean isRetina = false;
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        try {
            Field field = graphicsDevice.getClass().getDeclaredField("scale");
            if (field != null) {
                field.setAccessible(true);
                Object scale = field.get(graphicsDevice);
                if(scale instanceof Integer && ((Integer) scale).intValue() == 2) {
                    isRetina = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRetina;
    }
}
