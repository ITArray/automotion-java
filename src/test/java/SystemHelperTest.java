import org.junit.Assert;
import org.junit.Test;
import util.general.SystemHelper;

import java.util.HashMap;
import java.util.Map;

public class SystemHelperTest {

    @Test
    public void testThatColorCanBeConvertedFromHexToRgb() {
        Map<String, String> colors = new HashMap<>();
        colors.put("rgb(0,0,0)", "#000000");
        colors.put("rgb(255,255,255)", "#FFFFFF");
        colors.put("rgba(255,255,255,1.0)", "#FFFFFFFF");
        colors.put("rgba(255,255,255,0.0)", "#00FFFFFF");
        colors.put("rgba(255,255,255,0.2)", "#33FFFFFF");
        colors.put("rgba(255,255,255,0.5)", "#80FFFFFF");
        colors.put("rgb(251,220,220)", "#fbdcdc");

        for (Map.Entry<String, String> entry : colors.entrySet()) {
            Assert.assertEquals(entry.getKey(), SystemHelper.hexStringToARGB(entry.getValue()));
        }
    }
}
