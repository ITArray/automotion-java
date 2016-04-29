package util.property;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private String propertyFileName;

    public PropertyLoader(String propertyPath) {
        this.propertyFileName = propertyPath;
    }

    public String loadProperty(String name) {
        Properties props = new Properties();
        try {

            props.load(PropertyLoader.class.getResourceAsStream("/" + propertyFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }
}