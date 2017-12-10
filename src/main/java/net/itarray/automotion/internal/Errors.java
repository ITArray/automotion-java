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
        lastMessage = message;
        JSONObject details = new JSONObject();
        JSONObject messageObject = new JSONObject();
        messageObject.put(MESSAGE, message);
        details.put(REASON, messageObject);
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
