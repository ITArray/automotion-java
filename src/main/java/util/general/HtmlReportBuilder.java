package util.general;

import com.webfirmframework.wffweb.tag.html.*;
import com.webfirmframework.wffweb.tag.html.attribute.Alt;
import com.webfirmframework.wffweb.tag.html.attribute.Src;
import com.webfirmframework.wffweb.tag.html.attribute.global.Style;
import com.webfirmframework.wffweb.tag.html.images.Img;
import com.webfirmframework.wffweb.tag.html.lists.Li;
import com.webfirmframework.wffweb.tag.html.lists.Ol;
import com.webfirmframework.wffweb.tag.html.metainfo.Head;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.validator.Constants.*;


public class HtmlReportBuilder {

    private final Logger LOG = Logger.getLogger(HtmlReportBuilder.class);

    public void buildReport() throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        Html html = new Html(null, new Style("background-color: rgb(255,250,250)")) {{
            new Head(this) {{
                new TitleTag(this) {{
                    new NoTag(this, "Automotion report");
                }};
            }};
            new Body(this) {{

                new Div(this, new Style("width: 100%; background-color: rgb(0,191,255); color: white; padding: 10px")) {{
                    new H1(this) {{
                        new NoTag(this, "Results from: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    }};
                }};
                File folder = new File(TARGET_AUTOMOTION_JSON);
                File[] listOfFiles = folder.listFiles();

                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        JSONParser parser = new JSONParser();
                        Object obj = parser.parse(new FileReader(file));

                        JSONObject jsonObject = (JSONObject) obj;
                        JSONArray details = (JSONArray) jsonObject.get(DETAILS);
                        new H1(this,
                                new Style("color: rgb(0,139,139); margin-top: 50px;")) {{
                            new NoTag(this, "Element: \"" + jsonObject.get(ELEMENT_NAME) + "\"");
                        }};
                        new H2(this,
                                new Style("color: rgb(255,69,0)")) {{
                            new NoTag(this, "Failures:");
                        }};
                        new Ol(this) {{
                            for (Object details : details) {
                                JSONObject det = (JSONObject) details;
                                JSONObject reason = (JSONObject) det.get(REASON);
                                String numE = (String) reason.get(MESSAGE);

                                new Li(this) {{
                                    new NoTag(this, numE.toString());
                                }};
                            }
                        }};
                        new P(this) {{
                            new Img(this,
                                    new Src("img/" + jsonObject.get(SCREENSHOT)),
                                    new Alt("screenshot"),
                                    new Style("width: 90%; margin-left:5%"));
                        }};
                    }
                }
            }};
        }};

        long ms = System.currentTimeMillis();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TARGET_AUTOMOTION + "result" + ms + ".html"), StandardCharsets.UTF_8))) {
            writer.write(html.toHtmlString());
        } catch (IOException ex) {
            LOG.error("Cannot create html report: " + ex.getMessage());
        }

        try {
            File file = new File(TARGET_AUTOMOTION + "result" + ms + ".html");
            if (file.getParentFile().mkdirs()) {
                if (file.createNewFile()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(html.toHtmlString());
                    writer.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
