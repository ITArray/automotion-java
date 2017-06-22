package http.helpers;

import com.google.gson.JsonArray;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.List;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.tools.helpers.Parser}
 */
@Deprecated()
public class Parser {

    public static String getJSONValue(String jsonData, String key) throws ParseException {
        return net.itarray.automotion.tools.helpers.Parser.getJSONValue(jsonData, key);
    }

    public static String getDataValue(String json, String key) throws ParseException {
        return net.itarray.automotion.tools.helpers.Parser.getDataValue(json, key);
    }

    public static String getXMLValue(String xml, String selector) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException {
        return net.itarray.automotion.tools.helpers.Parser.getXMLValue(xml, selector);
    }

    public static JsonArray jsonToJsonArray(String jsonArray) throws ParseException {
        return net.itarray.automotion.tools.helpers.Parser.jsonToJsonArray(jsonArray);
    }

    public static List<String> getXMLValues(String xml, String selector) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException {
        return net.itarray.automotion.tools.helpers.Parser.getXMLValues(xml, selector);
    }
}
