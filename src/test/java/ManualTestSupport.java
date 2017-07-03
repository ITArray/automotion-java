import net.itarray.automotion.validation.Constants;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URI;

public class ManualTestSupport {

    public static final File OUTPUT = new File(Constants.TARGET_AUTOMOTION);

    public static void deleteOutputDirectory() {
        try {
            FileUtils.deleteDirectory(OUTPUT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void openReportInDefaultBrowser() {
        File[] htmls = OUTPUT.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().endsWith(".html");
            }
        });
        if(!Desktop.isDesktopSupported()) {
            return;
        }
        for (File html : htmls) {
            try {
                Desktop.getDesktop().browse(html.toURI());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
