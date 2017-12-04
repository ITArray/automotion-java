package net.itarray.automotion.internal;

import com.webfirmframework.wffweb.tag.html.*;
import com.webfirmframework.wffweb.tag.html.attribute.Alt;
import com.webfirmframework.wffweb.tag.html.attribute.Src;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnMouseOut;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnMouseOver;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.global.Id;
import com.webfirmframework.wffweb.tag.html.attribute.global.Style;
import com.webfirmframework.wffweb.tag.html.images.Img;
import com.webfirmframework.wffweb.tag.html.lists.Li;
import com.webfirmframework.wffweb.tag.html.lists.Ol;
import com.webfirmframework.wffweb.tag.html.metainfo.Head;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import net.itarray.automotion.tools.helpers.Helper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.itarray.automotion.validation.Constants.*;


public class HtmlReportBuilder {

    private List<String> jsonFiles;
    private Object screenshotDrawingOverlay;

    public void buildReport(String reportName, List<String> jsonFiles) {
        this.jsonFiles = jsonFiles;
        try {
            writeReport(reportName);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeReport(String reportName) throws IOException, ParseException {
        Html html = buildHtml();

        long ms = System.currentTimeMillis();
        String uuid = Helper.getGeneratedStringWithLength(7);

        File report = new File(TARGET_AUTOMOTION + reportName.replace(" ", "_") + "-" + ms + uuid + ".html");
        report.getParentFile().mkdirs();
        try (FileOutputStream fos = new FileOutputStream(report);
                BufferedOutputStream bos = new BufferedOutputStream(fos);) {

            html.toOutputStream(bos);
            bos.flush();
        }
    }

    private Html buildHtml() throws IOException, ParseException {
        return new Html(null, new Style("background-color: rgb(255,250,250)")) {{
            super.setPrependDocType(true);
            new Head(this) {{
                new TitleTag(this) {{
                    new NoTag(this, "Automotion report");
                }};
            }};
            new Body(this) {{

                new Div(this, new Style("width: 100%; background-color: rgb(0,191,255); color: white; padding: 10px")) {{
                    new H1(this) {{
                        new NoTag(this, String.format("Results from: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                    }};
                }};
                Map<String, File> filesByName = jsonFilesByNameInTargetJsonDirectory();
                for (String jsonFile : jsonFiles) {
                    if (filesByName.containsKey(jsonFile)) {
                        File file = filesByName.get(jsonFile);
                        if (file.isFile()) {
                            JSONParser parser = new JSONParser();
                            Object obj = parser.parse(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

                            JSONObject jsonObject = (JSONObject) obj;
                            JSONArray details = (JSONArray) jsonObject.get(DETAILS);
                            new H1(this,
                                    new Style("color: rgb(47,79,79); margin-top: 50px")) {{
                                new NoTag(this, String.format("Scenario: \"%s\"", jsonObject.get(SCENARIO)));
                            }};
                            new H2(this,
                                    new Style("color: rgb(0,139,139)")) {{
                                new NoTag(this, String.format("Element: \"%s\"", jsonObject.get(ELEMENT_NAME)));
                            }};
                            new H3(this,
                                    new Style("color: rgb(255,69,0)")) {{
                                new NoTag(this, "Failures:");
                            }};
                            new Ol(this) {{
                                for (Object details : details) {
                                    JSONObject det = (JSONObject) details;
                                    JSONObject reason = (JSONObject) det.get(REASON);
                                    String numE = (String) reason.get(MESSAGE);

                                    new Li(this) {{
                                        new NoTag(this, numE);
                                    }};
                                }
                            }};
                            new H4(this,
                                    new Style("color: rgb(105,105,105)")) {{
                                new NoTag(this, String.format("Time execution: %s", jsonObject.get(TIME_EXECUTION)));
                            }};

                            //TODO uncomment after implementing the hovering events
//                            new H5(this,
//                                    new Style("color: #4d4d4d")) {{
//                                new NoTag(this, "Hover over the image to see the results");
//                            }};
                            new P(this) {{
                                screenshotDrawingOverlay = jsonObject.get(DRAWINGS);
                                new Div(this,
                                                //new OnMouseOver("document.getElementById('" + screenshotDrawingOverlay.toString()+ "').style.display = 'block'"),
                                                //new OnMouseOut("document.getElementById('" + screenshotDrawingOverlay.toString()+ "').style.display = 'none'"),
                                                new Style("position:relative; left: 0; top:0; width: 96%; margin-left:2%")) {{
                                            new Img(this,
                                                    new Style("position:relative; left: 0; top:0"),
                                                new Src(String.format("img/%s", jsonObject.get(SCREENSHOT))),
                                                new Alt("screenshot"));
                                            new Img(this,
                                                new Id(screenshotDrawingOverlay.toString()),
                                                new Style("position:absolute; left: 0; top:0;"),
                                                //new Style("position:absolute; left: 0; top:0; display:none;"),
                                                new Src(String.format("img/%s", screenshotDrawingOverlay)),
                                                new Alt("screenshot-overlay"));
                                        }};

                            }};

                            while (!file.delete()) ;
                        }

                    }
                }
                jsonFiles.clear();
            }};
        }};
    }

    private Map<String, File> jsonFilesByNameInTargetJsonDirectory() {
        File folder = new File(TARGET_AUTOMOTION_JSON);
        File[] listOfFiles = folder.listFiles();
        Map<String, File> filesByName = new HashMap<>();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                filesByName.put(file.getName(), file);
            }
        }
        return filesByName;
    }
}
