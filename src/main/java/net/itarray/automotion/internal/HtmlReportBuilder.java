package net.itarray.automotion.internal;

import com.webfirmframework.wffweb.tag.html.*;
import com.webfirmframework.wffweb.tag.html.attribute.Alt;
import com.webfirmframework.wffweb.tag.html.attribute.Src;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.global.Id;
import com.webfirmframework.wffweb.tag.html.attribute.global.Style;
import com.webfirmframework.wffweb.tag.html.images.Img;
import com.webfirmframework.wffweb.tag.html.lists.Li;
import com.webfirmframework.wffweb.tag.html.lists.Ol;
import com.webfirmframework.wffweb.tag.html.metainfo.Head;
import com.webfirmframework.wffweb.tag.html.programming.Script;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Span;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.StyleTag;
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
    private int failuresCounter = 0;
    private int successCounter = 0;
    private int counter = 0;
    private String barDuration = "";
    private String barScenariosNames = "";

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
        return new Html(null,
                new Style("background-color: #fff")) {{
            super.setPrependDocType(true);
            new Head(this) {{
                new TitleTag(this) {{
                    new NoTag(this, "Automotion report");
                }};
                new NoTag(this, "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
                new NoTag(this, "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">");

                new NoTag(this, "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
                new NoTag(this, "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
                new NoTag(this, "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">");
                new NoTag(this, "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>");

                new Script(this,
                        new Src("https://cdn.plot.ly/plotly-latest.min.js"));
                new Script(this,
                        new Src("https://cdnjs.cloudflare.com/ajax/libs/numeric/1.2.6/numeric.min.js"));

                new StyleTag(this) {{
                    new NoTag(this, ".accordion {\n" +
                            "    background-color: #eee;\n" +
                            "    color: #444;\n" +
                            "    cursor: pointer;\n" +
                            "    padding: 18px;\n" +
                            "    width: 100%;\n" +
                            "    border: none;\n" +
                            "    text-align: left;\n" +
                            "    outline: none;\n" +
                            "    font-size: 15px;\n" +
                            "    transition: 0.4s;\n" +
                            "}\n" +
                            "\n" +
                            ".active, .accordion:hover {\n" +
                            "    background-color: #ccc; \n" +
                            "}\n" +
                            "\n" +
                            ".panel {\n" +
                            "    padding: 0 18px;\n" +
                            "    display: none;\n" +
                            "    background-color: white;\n" +
                            "}");
                }};
                new StyleTag(this) {{
                    new NoTag(this, "/* Style the Image Used to Trigger the Modal */\n" +
                            "#myImg {\n" +
                            "    border-radius: 5px;\n" +
                            "    cursor: pointer;\n" +
                            "    transition: 0.3s;\n" +
                            "}\n" +
                            "\n" +
                            "#myImg:hover {opacity: 0.7;}\n" +
                            "\n" +
                            "/* The Modal (background) */\n" +
                            ".modal {\n" +
                            "    display: none; /* Hidden by default */\n" +
                            "    position: fixed; /* Stay in place */\n" +
                            "    z-index: 1; /* Sit on top */\n" +
                            "    padding-top: 10px; /* Location of the box */\n" +
                            "    left: 0;\n" +
                            "    top: 0;\n" +
                            "    width: 100%; /* Full width */\n" +
                            "    height: 100%; /* Full height */\n" +
                            "    overflow: auto; /* Enable scroll if needed */\n" +
                            "    background-color: rgb(0,0,0); /* Fallback color */\n" +
                            "    background-color: rgba(0,0,0,0.9); /* Black w/ opacity */\n" +
                            "}\n" +
                            "\n" +
                            "/* Modal Content (Image) */\n" +
                            ".modal-content {\n" +
                            "    margin: auto;\n" +
                            "    display: block;\n" +
                            "height: 98%;\n" +
                            "    width: auto;\n" +
                            "}\n" +
                            "\n" +
                            "/* Caption of Modal Image (Image Text) - Same Width as the Image */\n" +
                            "#caption {\n" +
                            "    margin: auto;\n" +
                            "    display: block;\n" +
                            "height: 50px;\n" +
                            "    width: auto;\n" +
                            "    text-align: center;\n" +
                            "    color: #ccc;\n" +
                            "    padding: 10px 0;\n" +
                            "}\n" +
                            "\n" +
                            "/* Add Animation - Zoom in the Modal */\n" +
                            ".modal-content, #caption { \n" +
                            "    animation-name: zoom;\n" +
                            "    animation-duration: 0.6s;\n" +
                            "}\n" +
                            "\n" +
                            "@keyframes zoom {\n" +
                            "    from {transform:scale(0)} \n" +
                            "    to {transform:scale(1)}\n" +
                            "}\n" +
                            "\n" +
                            "/* The Close Button */\n" +
                            ".close {\n" +
                            "    position: absolute;\n" +
                            "    top: 15px;\n" +
                            "    right: 35px;\n" +
                            "    color: #f1f1f1;\n" +
                            "    font-size: 40px;\n" +
                            "    font-weight: bold;\n" +
                            "    transition: 0.3s;\n" +
                            "}\n" +
                            "\n" +
                            ".close:hover,\n" +
                            ".close:focus {\n" +
                            "    color: #bbb;\n" +
                            "    text-decoration: none;\n" +
                            "    cursor: pointer;\n" +
                            "}\n" +
                            "\n" +
                            "/* 100% Image Width on Smaller Screens */\n" +
                            "@media only screen and (max-width: 700px){\n" +
                            "    .modal-content {\n" +
                            "        width: 100%;\n" +
                            "    }\n" +
                            "}");
                }};
            }};
            new Body(this) {{
                new Style("background: white; margin: 0px");
                new Div(this, new ClassAttribute("container-fluid")) {{
                    new Div(this,
                            new ClassAttribute("row")) {{
                        new Div(this,
                                new Style("background-color: rgb(0,191,255); color: white; padding: 10px;")) {{
                            new H1(this, new Style("font-size:22px; font-weight: 200;")) {{
                                new NoTag(this, String.format("Results from: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                            }};
                        }};
                    }};

                    new Div(this,
                            new ClassAttribute("row")) {{
                        new Div(this,
                                new ClassAttribute("col-xs-12 col-sm-6 col-md-4"),
                                new Id("plot")) {{
                        }};

                        new Div(this,
                                new ClassAttribute("col-xs-12 col-md-8"),
                                new Id("bar")) {{
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
                                boolean isFailed = (Boolean) jsonObject.get("error");

                                counter++;

                                if (isFailed) {
                                    failuresCounter++;
                                } else {
                                    successCounter++;
                                }

                                barDuration += String.format("%s, ", ((String) jsonObject.get(TIME_EXECUTION)).split(" ")[0]);
                                barScenariosNames += String.format("'%d. %s', ", counter, jsonObject.get(SCENARIO));
                                new Div(this,
                                        new ClassAttribute("row")) {
                                    {
                                        String bgColor = "background: rgba(0,250,154, 0.5)";
                                        if (isFailed) {
                                            bgColor = "background: rgba(240,128,128, 0.5)";
                                        }
                                        new Div(this,
                                                new Style("margin-top:2px;" + bgColor),
                                                new ClassAttribute("accordion")) {{
                                            new H1(this,
                                                    new Style("color: rgb(47,79,79); font-size:24px; font-size:18px; font-weight: 300; text-decoration: underline;")) {{
                                                new NoTag(this, String.format("Scenario: \"%s\"", jsonObject.get(SCENARIO)));
//                                                if (isFailed) {
//                                                    new Span(this,
//                                                            new Style("color: rgb(255,99,71); float:right; font-size:18px; font-weight: 500; margin-right: 32px")) {{
//                                                        new NoTag(this, "Failed");
//                                                    }};
//                                                } else {
//                                                    new Span(this,
//                                                            new Style("color: rgb(60,179,113); float:right; font-size:18px; font-weight: 500; margin-right: 32px")) {{
//                                                        new NoTag(this, "Passed");
//                                                    }};
//                                                }
                                            }};

                                        }};

                                        new Div(this,
                                                //new Style("background: #f5f5f5"),
                                                new ClassAttribute("panel")) {{
                                            new H2(this,
                                                    new Style("color: rgb(0,139,139); font-size:18px; font-weight: 300;")) {{
                                                new NoTag(this, String.format("Element: \"%s\"", jsonObject.get(ELEMENT_NAME)));
                                            }};
                                            if (isFailed) {
                                                new H3(this,
                                                        new Style("color: rgb(255,69,0); font-size:18px; font-weight: 300;")) {{
                                                    new NoTag(this, "Failures:");
                                                }};
                                            }
                                            new Ol(this) {{
                                                for (Object details : details) {
                                                    JSONObject det = (JSONObject) details;
                                                    JSONObject reason = (JSONObject) det.get(REASON);
                                                    String numE = (String) reason.get(MESSAGE);

                                                    new Li(this,
                                                            new Style("color: rgb(105,105,105); font-size:14px; font-weight: 400;")) {{
                                                        new NoTag(this, numE);
                                                    }};
                                                }
                                            }};
                                            new H4(this,
                                                    new Style("color: rgb(105,105,105); font-size:14px; font-weight: 300;")) {{
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

                                                    if (isFailed) {
                                                        new Img(this,
                                                                new Style("position:relative; left: 0; top:0"),
                                                                new Src(String.format("img/%s", jsonObject.get(SCREENSHOT))),
                                                                new Alt("screenshot"));
                                                        new Img(this,
                                                                new Id(screenshotDrawingOverlay.toString()),
                                                                new Style("position:absolute; left: 0; top:0;"),
                                                                //new Style("position:absolute; left: 0; top:0; display:none;"),
                                                                new Src(String.format("img/%s", screenshotDrawingOverlay.toString())),
                                                                new OnClick("showModal('" + screenshotDrawingOverlay.toString() + "')"),
                                                                new Alt("screenshot-overlay"));
                                                    }
                                                }};

                                            }};
                                        }};
                                    }
                                };


                                while (!file.delete()) ;
                            }

                        }
                    }

                    new Div(this,
                            new ClassAttribute("modal"),
                            new Id("myModal")) {{

                        new Span(this,
                                new ClassAttribute("close")) {{
                            new NoTag(this, "&times;");
                        }};

                        new Img(this,
                                new Id("img01"),
                                new ClassAttribute("modal-content"));

                        new Div(this,
                                new Id("caption")) {{
                            new NoTag(this, "");
                        }};
                    }};

                    new Script(this) {{
                        new NoTag(this, "" +
                                "var data = [{\n" +
                                "  values: [" + successCounter + ", " + failuresCounter + "],\n" +
                                "  labels: ['Passed', 'Failed'],\n" +
                                "  type: 'pie',\n" +
                                "  marker: {colors: ['rgb(60,179,113)', 'rgb(255,99,71)']},\n" +
                                "  hole: .4\n" +
                                "}];\n" +
                                "\n" +
                                "var layout = {\n" +
                                "  title: 'Stats',\n" +
                                "  height: 400,\n" +
                                "  width: 500,\n" +
                                "};\n" +
                                "\n" +
                                "Plotly.newPlot('plot', data, layout);");
                    }};

                    new Script(this) {{
                        new NoTag(this, "var data = [\n" +
                                "  {\n" +
                                "    x: [" + barScenariosNames.substring(0, barScenariosNames.length() - 2) + "],\n" +
                                "    y: [" + barDuration.substring(0, barDuration.length() - 2) + "],\n" +
                                "    type: 'bar'\n" +
                                "  }\n" +
                                "];\n" +
                                "var layout = {\n" +
                                "  title: 'Duration, ms',\n" +
                                "  height: 400\n" +
                                "};\n" +
                                "\n" +
                                "\n" +
                                "Plotly.newPlot('bar', data, layout);");
                    }};

                    jsonFiles.clear();

                    new Script(this) {{
                        new NoTag(this, "var acc = document.getElementsByClassName(\"accordion\");\n" +
                                "var i;\n" +
                                "\n" +
                                "for (i = 0; i < acc.length; i++) {\n" +
                                "    acc[i].addEventListener(\"click\", function() {\n" +
                                "        this.classList.toggle(\"active\");\n" +
                                "        var panel = this.nextElementSibling;\n" +
                                "        if (panel.style.display === \"block\") {\n" +
                                "            panel.style.display = \"none\";\n" +
                                "        } else {\n" +
                                "            panel.style.display = \"block\";\n" +
                                "        }\n" +
                                "    });\n" +
                                "}");
                    }};

                    new Script(this) {{
                        new NoTag(this, "function showModal(imageId) {" +
                                "var modal = document.getElementById('myModal');\n" +
                                "\n" +
                                "// Get the image and insert it inside the modal - use its \"alt\" text as a caption\n" +
                                "var img = document.getElementById(imageId);\n" +
                                "var modalImg = document.getElementById(\"img01\");\n" +
                                "var captionText = document.getElementById(\"caption\");\n" +
                                "modal.style.display = \"block\";\n" +
                                "modalImg.src = img.src;\n" +
                                "captionText.innerHTML = img.alt;\n" +
                                "\n" +
                                "// Get the <span> element that closes the modal\n" +
                                "var span = document.getElementsByClassName(\"close\")[0];\n" +
                                "\n" +
                                "// When the user clicks on <span> (x), close the modal\n" +
                                "span.onclick = function() { \n" +
                                "  modal.style.display = \"none\";\n" +
                                "}}");
                    }};
                }};
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
