package http.helpers;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.tools.http.helpers.EnvironmentHelper}
 */
@Deprecated()
public class Helper {

    public static String getGeneratedStringWithLength(int length) {
        return net.itarray.automotion.tools.http.helpers.Helper.getGeneratedStringWithLength(length);
    }

    public static String getTodayDate() {
        return net.itarray.automotion.tools.http.helpers.Helper.getTodayDate();
    }

    public static File createFile(String filename) throws IOException, AWTException {
        return net.itarray.automotion.tools.http.helpers.Helper.createFile(filename);
    }
}
