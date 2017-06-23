package net.itarray.automotion.internal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;

import static net.itarray.automotion.validation.Constants.*;

public class Errors {

    private final JSONArray messages = new JSONArray();

    public void add(String message) {
        JSONObject details = new JSONObject();
        JSONObject messageObject = new JSONObject();
        messageObject.put(MESSAGE, message);
        details.put(REASON, messageObject);
        messages.add(details);
    }

    public void add(String message, WebElement webElement) {
        add(message, new Element(webElement));
    }

    public void add(String message, Element element) {
        float xContainer = element.getX();
        float yContainer = element.getY();
        float widthContainer = element.getWidth();
        float heightContainer = element.getHeight();

        JSONObject details = new JSONObject();
        JSONObject elDetails = new JSONObject();
        elDetails.put(X, xContainer);
        elDetails.put(Y, yContainer);
        elDetails.put(WIDTH, widthContainer);
        elDetails.put(HEIGHT, heightContainer);
        JSONObject mes = new JSONObject();
        mes.put(MESSAGE, message);
        mes.put(ELEMENT, elDetails);
        details.put(REASON, mes);
        messages.add(details);
    }


    public boolean hasMessages() {
        return !messages.isEmpty();
    }

    public JSONArray getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return messages.toString();
    }
}
