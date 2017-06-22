package net.itarray.automotion.tools.http.helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static String getJSONValue(String jsonData, String key) throws ParseException {
        JSONObject json = (JSONObject) new JSONParser().parse(jsonData);

        return json.get(key).toString();
    }

    public static String getDataValue(String json, String key) throws ParseException {
        return getJSONValue(json, key);
    }

    public static String getXMLValue(String xml, String selector) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException {
        NodeList nodes = getNodeList(xml, selector);
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        return nodes.item(0).getNodeValue();
    }

    public static JsonArray jsonToJsonArray(String jsonArray) throws ParseException {
        return (JsonArray) new JsonParser().parse(jsonArray);
    }

    public static List<String> getXMLValues(String xml, String selector) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException {
        NodeList nodes = getNodeList(xml, selector);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < nodes.getLength(); i++) {
            list.add(nodes.item(i).getNodeValue());
        }

        return list;
    }

    private static NodeList getNodeList(String xml, String selector) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        DocumentBuilderFactory domFactory =
                DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
        XPath xpath = XPathFactory.newInstance().newXPath();
        // XPath Query for showing all nodes value
        XPathExpression expr = xpath.compile(selector);

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        return (NodeList) result;
    }
}