package net.itarray.automotion.internal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static net.itarray.automotion.validation.Constants.*;

public class Errors {

    private final JSONArray messages = new JSONArray();
    private String lastMessage;

    public String getLastMessage() {
        return lastMessage;
    }

    public void add(String message) {
        internalAdd(message, null, null);
    }

    public void add(String message, UIElement element) {
        internalAdd(message, element, encode(element));
    }

    private void internalAdd(String message, UIElement element, JSONObject encodedElement) {
        lastMessage = message;
        JSONObject details = new JSONObject();
        JSONObject messageObject = new JSONObject();
        messageObject.put(MESSAGE, message);
        if (encodedElement != null) {
            messageObject.put(ELEMENT, encodedElement);
        }
        details.put(REASON, messageObject);
        messages.add(details);
    }

    private JSONObject encode(UIElement element) {
        float xContainer = element.getX().intValue();
        float yContainer = element.getY().intValue();
        float widthContainer = element.getWidth().intValue();
        float heightContainer = element.getHeight().intValue();

        JSONObject elDetails = new JSONObject();
        elDetails.put(X, xContainer);
        elDetails.put(Y, yContainer);
        elDetails.put(WIDTH, widthContainer);
        elDetails.put(HEIGHT, heightContainer);
        return elDetails;
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
