package http.helpers;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.xuggle.xuggler.video.ConverterFactory.convertToType;

public class Helper {

    public static String getGeneratedStringWithLength(int length) {
        String s = UUID.randomUUID().toString().replace("-", "_");
        length = length <= s.length() ? length : s.length();

        return s.substring(0, length);
    }

    public static String getTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public static File createFile(String filename) throws IOException, AWTException {
        File file;
        try {
            file = new File("target/" + filename);
        } catch (Exception ex) {
            file = new File(filename);
        }
        file.createNewFile();
        String[] allowedImageExtensions = {"jpg", "jpeg", "gif", "png", "bmp"};
        String[] allowedMediaExtensions = {"avi", "mpeg", "mpg", "mp4", "mov", "wmv", "flv", "3gp"};
        String ext = FilenameUtils.getExtension(filename);

        if (Arrays.asList(allowedImageExtensions).contains(ext)) {
            BufferedImage img = new BufferedImage(256, 256,
                    BufferedImage.TYPE_INT_RGB);
            ImageIO.write(img, FilenameUtils.getExtension(filename).toUpperCase(), file);
        }else if (Arrays.asList(allowedMediaExtensions).contains(ext)) {
            try {
                createVideo("target/" + filename);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    private static void createVideo(String filename) throws AWTException, InterruptedException, IOException {
        final Robot robot = new Robot();
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Rectangle screenBounds = new Rectangle(toolkit.getScreenSize());

        final IMediaWriter writer = ToolFactory.makeWriter(filename);


        writer.addVideoStream(0, 0,
                screenBounds.width, screenBounds.height);

        long startTime = System.nanoTime();
        for (int index = 0; index < 1; index++) {
            // take the screen shot
            BufferedImage screen = robot.createScreenCapture(screenBounds);

            // convert to the right image type
            BufferedImage bgrScreen = convertToType(screen,
                    BufferedImage.TYPE_3BYTE_BGR);

            // encode the image to stream #0
            writer.encodeVideo(0, bgrScreen,
                    System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
            System.out.println("encoded image: " + index);

            // sleep for framerate milliseconds
            Thread.sleep((long) (1000 / 1));
        }

        writer.close();
    }
}
