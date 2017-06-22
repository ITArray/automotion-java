package net.itarray.automotion.tools.helpers;

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
        } else if (Arrays.asList(allowedMediaExtensions).contains(ext)) {
            //createVideo("target/" + filename);
        }

        return file;
    }
}
