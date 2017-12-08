package net.itarray.automotion.internal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static net.itarray.automotion.validation.Constants.*;

public class Errors {

    private final JSONArray messages = new JSONArray();
    private final ResponsiveUIValidatorBase responsiveUIValidatorBase;
    private String lastMessage;

    public Errors(ResponsiveUIValidatorBase responsiveUIValidatorBase) {
        this.responsiveUIValidatorBase = responsiveUIValidatorBase;
    }

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

    public void draw(UIElement element) {
        int x = element.getOrigin().getX().intValue();
        int y = element.getOrigin().getY().intValue();
        int width = element.getWidth().intValue();
        int height = element.getHeight().intValue();
        responsiveUIValidatorBase.getDrawableScreenshot().drawRectangle(x, y, width, height);
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
